

package com.jswitch.base.modelo.entidades.auditoria;

import com.jswitch.base.controlador.General;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.hibernate.CallbackException;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Hibernate;
import org.hibernate.collection.PersistentList;
import org.hibernate.collection.PersistentSet;
import org.hibernate.type.Type;

/**
 *
 * @author admin
 */
public class AuditLogInterceptor extends EmptyInterceptor {

    public static final AuditLogInterceptor INSTANCE2 = new AuditLogInterceptor();
    //private Session session;
    private Set inserts = new HashSet();
    private Set updates = new HashSet();
    private Set delete = new HashSet();

//    public void setSession(Session session) {
//        this.session = session;
//    }
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types)
            throws CallbackException {
        if (entity instanceof Auditable) {
            AuditLogRecord au = new AuditLogRecord();
            au.setCreated(new Date());
            au.setEntityClass(entity.getClass().getName());
            au.setEntityId(((Auditable) entity).getId());
            au.setUserOS(General.usuarioOS);
            au.setIps(General.IP);
            au.setMessage("insert");
            au.setUser2(General.usuario.getUserName());
            for (int i = 0; i < types.length; i++) {
                au.getNombres().add(propertyNames[i]);
                au.getValores().add(state[i]);
            }
            inserts.add(au);
        }
        return false;
    }

    @Override
    public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Auditable) {
            AuditLogRecord au = new AuditLogRecord();
            au.setCreated(new Date());
            au.setEntityClass(entity.getClass().getName());
            au.setEntityId(((Auditable) entity).getId());
            au.setUserOS(General.usuarioOS);
            au.setIps(General.IP);
            au.setMessage("delete");
            au.setUser2(General.usuario.getUserName());
            for (int i = 0; i < types.length; i++) {
                if (state[i] instanceof PersistentSet) {
                    Hibernate.initialize(state[i]);
                    String ids = "";
                    for (Object object : ((PersistentSet) state[i])) {
                        if (object instanceof Auditable) {
                            ids += " - " + ((Auditable) object).getId();
                        }
                    }
                    au.getNombres().add(propertyNames[i]);
                    if (ids.length() > 0) {
                        ids = ids.substring(3);
                    }
                    au.getValores().add(ids);
                } else if (state[i] instanceof PersistentList) {
                    Hibernate.initialize(state[i]);
                    String ids = "";
                    for (Object object : ((PersistentList) state[i])) {
                        if (object instanceof Auditable) {
                            ids += " - " + ((Auditable) object).getId();
                        }
                    }
                    au.getNombres().add(propertyNames[i]);
                    if (ids.length() > 0) {
                        ids = ids.substring(3);
                    }
                    au.getValores().add(ids);
                } else {
                    au.getNombres().add(propertyNames[i]);
                    au.getValores().add(state[i]);
                }
            }
            delete.add(au);
        }
    }

    @Override
    public boolean onFlushDirty(Object entity,
            Serializable id,
            Object[] currentState,
            Object[] previousState,
            String[] propertyNames,
            Type[] types)
            throws CallbackException {
        if (entity instanceof Auditable) {
            AuditLogRecord au = new AuditLogRecord();
            au.setCreated(new Date());
            au.setEntityClass(entity.getClass().getName());
            au.setEntityId(((Auditable) entity).getId());
            au.setMessage("update");
            au.setUserOS(General.usuarioOS);
            au.setIps(General.IP);
            au.setUser2(General.usuario.getUserName());
            for (int i = 0; i < types.length; i++) {
                au.getNombres().add(propertyNames[i]);
                au.getValores().add(currentState[i]);
            }
            updates.add(au);
        }
        return false;
    }

    @Override
    public void postFlush(Iterator iterator)
            throws CallbackException {
        try {
            for (Iterator it = inserts.iterator(); it.hasNext();) {
                //System.out.println("ins ** :" + inserts.size());
                AuditLog.logEvent((AuditLogRecord) it.next());
            }
            for (Iterator it = updates.iterator(); it.hasNext();) {
                //System.out.println("up ** :" + updates.size());
                AuditLog.logEvent((AuditLogRecord) it.next());
            }
            for (Iterator it = delete.iterator(); it.hasNext();) {
                //System.out.println("del ** :" + delete.size());
                AuditLog.logEvent((AuditLogRecord) it.next());
            }
        } finally {
            inserts.clear();
            updates.clear();
            delete.clear();
        }
    }
}
