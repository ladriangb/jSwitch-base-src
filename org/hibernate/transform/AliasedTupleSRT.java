package org.hibernate.transform;
import java.util.Arrays;
import org.hibernate.HibernateException;
import org.hibernate.property.ChainedPropertyAccessor;
import org.hibernate.property.Getter;
import org.hibernate.property.PropertyAccessor;
import org.hibernate.property.PropertyAccessorFactory;
import org.hibernate.property.Setter;


public class AliasedTupleSRT extends AliasedTupleSubsetResultTransformer {

    /**
     * The string that will be the separator between inner fields. e.g:
     * employed.deparment.name = employed_deparment_name Default is "_"
     */
    public final static String SEPARATOR = "_";

// IMPL NOTE : due to the delayed population of setters (setters cached
    // 		for performance), we really cannot properly define equality for
    // 		this transformer
    
    
    /**
     * Set the property value from the given instance, even if the property name
     * refence an inner field
     * 
     * @param setter the method to be executed to set the value of the tuple
     * @param alias  The name of the property
     * @param tuple The query result value
     * @param ownerObject The object to set value
     * @throws InstantiationException
     *               if this {@code Class} represents an abstract class,
     *               an interface, an array class, a primitive type, or void;
     *               or if the class has no nullary constructor;
     *               or if the instantiation fails for some other reason.
     * @throws IllegalAccessException 
     *               if the class or its nullary constructor is not accessible.
     */
    private static void setValue(Setter setter, String alias, Object tuple, Object ownerObject) 
            throws InstantiationException, IllegalAccessException {
        if (alias.contains(SEPARATOR)) {
            PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
                    new PropertyAccessor[]{
                        PropertyAccessorFactory.getPropertyAccessor(ownerObject.getClass(), null),
                        PropertyAccessorFactory.getPropertyAccessor("field")
                    });

            //the next field to deepen
            String next = alias.substring(0, alias.indexOf(SEPARATOR));
            //the other fields to be processed
            alias = alias.substring(alias.indexOf(SEPARATOR) + 1);

            Getter nextGetter = propertyAccessor.getGetter(ownerObject.getClass(), next);

            Class nextClass = nextGetter.getReturnType();

            Object nextObject = nextGetter.get(ownerObject);
            if (nextObject == null) {
                nextObject = nextClass.newInstance();
            }

            Setter nextSetter = propertyAccessor.getSetter(ownerObject.getClass(), next);
            nextSetter.set(ownerObject, nextObject, null);

            setValue(setter, alias, tuple, nextObject);
            return;
        }
        setter.set(ownerObject, tuple, null);
    }

    /**
     * Create a "setter" for the named attribute, even if the named attribute
     * refence an inner field
     *
     * @param resultClass The class on which the property is defined.
     * @param alias The name of the property
     * @return the setter for the given alias
     */
    private static Setter getSetter(Class resultClass, String alias) {
        PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
                new PropertyAccessor[]{
                    PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
                    PropertyAccessorFactory.getPropertyAccessor("field")
                });
        //if the alias reference to an inner field
        if (alias.contains(SEPARATOR)) {
            //the next field to deepen
            String next = alias.substring(0, alias.indexOf(SEPARATOR));
            //the other fields to be processed
            alias = alias.substring(alias.indexOf(SEPARATOR) + 1);
            //the resultClass of next field
            Class nextClass = propertyAccessor.getGetter(resultClass, next).getReturnType();
            return getSetter(nextClass, alias);
        } else {
            return propertyAccessor.getSetter(resultClass, alias);
        }
    }
    private final Class resultClass;
    private boolean isInitialized;
    private String[] aliases;
    private Setter[] setters;

    public AliasedTupleSRT(Class resultClass) {
        if (resultClass == null) {
            throw new IllegalArgumentException("resultClass cannot be null");
        }
        isInitialized = false;
        this.resultClass = resultClass;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isTransformedValueATupleElement(String[] aliases, int tupleLength) {
        return false;
    }

    public Object transformTuple(Object[] tuple, String[] aliases) {
        Object result;

        try {
            if (!isInitialized) {
                initialize(aliases);
            } else {
                check(aliases);
            }

            result = resultClass.newInstance();

            for (int i = 0; i < aliases.length; i++) {
                if (setters[i] != null) {
                    setValue(setters[i], aliases[i], tuple[i], result);
                    //setters[i].set(result, tuple[i], null);
                }
            }
        } catch (InstantiationException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        } catch (IllegalAccessException e) {
            throw new HibernateException("Could not instantiate resultclass: " + resultClass.getName());
        }

        return result;
    }

    private void initialize(String[] aliases) {
        PropertyAccessor propertyAccessor = new ChainedPropertyAccessor(
                new PropertyAccessor[]{
                    PropertyAccessorFactory.getPropertyAccessor(resultClass, null),
                    PropertyAccessorFactory.getPropertyAccessor("field")
                });
        this.aliases = new String[aliases.length];
        setters = new Setter[aliases.length];
        for (int i = 0; i < aliases.length; i++) {
            String alias = aliases[ i];
            if (alias != null) {
                this.aliases[ i] = alias;
                setters[ i] = getSetter(resultClass, alias);
                //setters[ i] = propertyAccessor.getSetter(resultClass, alias);
            }
        }
        isInitialized = true;
    }

    private void check(String[] aliases) {
        if (!Arrays.equals(aliases, this.aliases)) {
            throw new IllegalStateException(
                    "aliases are different from what is cached; aliases=" + Arrays.asList(aliases)
                    + " cached=" + Arrays.asList(this.aliases));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AliasedTupleSRT that = (AliasedTupleSRT) o;

        if (!resultClass.equals(that.resultClass)) {
            return false;
        }
        if (!Arrays.equals(aliases, that.aliases)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = resultClass.hashCode();
        result = 31 * result + (aliases != null ? Arrays.hashCode(aliases) : 0);
        return result;
    }
}
