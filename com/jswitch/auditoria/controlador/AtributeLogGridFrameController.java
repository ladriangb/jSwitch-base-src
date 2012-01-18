/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.auditoria.controlador;

import com.jswitch.auditoria.modelo.AtributoValor;
import com.jswitch.auditoria.vista.AtributeLogGridFrame;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import java.awt.Color;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.collection.PersistentBag;
import org.hibernate.collection.PersistentList;
import org.hibernate.collection.PersistentSet;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.table.client.GridController;
import org.openswing.swing.table.java.GridDataLocator;

/**
 *
 * @author orlandobcrra
 */
public class AtributeLogGridFrameController extends GridController implements GridDataLocator {

    private ArrayList<AtributoValor> data;

    public AtributeLogGridFrameController(ArrayList<AtributoValor> data) {
        this.data = data;
        new AtributeLogGridFrame().inicializar(this, this, AtributoValor.class.getName(), null, true);
    }

    public Response loadData(int i, int i1, Map map, ArrayList al, ArrayList al1, Class type, Map map1) {
        return new VOListResponse(data, false, data.size());
    }

    @Override
    public Color getBackgroundColor(int row, String attributeName, Object value) {
        if (value instanceof String && ((String) value).equals("-|-")) {
            return Color.CYAN;
        }
        return super.getBackgroundColor(row, attributeName, value);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        AtributoValor valor = (AtributoValor) persistentObject;
//        System.out.println(valor.getValor().getClass());
        if (valor.getValor() instanceof BeanVO) {
            ArrayList<AtributoValor> atributoValors = new ArrayList<AtributoValor>(0);
            funX(valor.getValor(), atributoValors);
            new AtributeLogGridFrameController(atributoValors);
        } else if (valor.getValor() instanceof PersistentSet) {
            PersistentSet set = (PersistentSet) valor.getValor();
            ArrayList<AtributoValor> atributoValors = new ArrayList<AtributoValor>(0);
            for (Object object : set) {
                funX(object, atributoValors);
                atributoValors.add(new AtributoValor("-|-", "-|-"));
            }
            new AtributeLogGridFrameController(atributoValors);
        } else if (valor.getValor() instanceof PersistentList) {
            PersistentList set = (PersistentList) valor.getValor();
            ArrayList<AtributoValor> atributoValors = new ArrayList<AtributoValor>(0);
            for (Object object : set) {
                funX(object, atributoValors);
                atributoValors.add(new AtributoValor("----", "-----"));
            }
            new AtributeLogGridFrameController(atributoValors);
        }else if (valor.getValor() instanceof PersistentBag) {
            PersistentBag set = (PersistentBag) valor.getValor();
            ArrayList<AtributoValor> atributoValors = new ArrayList<AtributoValor>(0);
            for (Object object : set) {
                funX(object, atributoValors);
                atributoValors.add(new AtributoValor("----", "-----"));
            }
            new AtributeLogGridFrameController(atributoValors);
        }
    }

    public void funX(Object object, ArrayList<AtributoValor> atributoValors) {
        Method[] methods = object.getClass().getMethods();
        for (int i = 0; i < methods.length; i++) {
            if (methods[i].getName().startsWith("get") && !methods[i].getName().startsWith("getClass")) {

                try {
                                       
                    if (!methods[i].getReturnType().equals(Set.class) && !methods[i].getReturnType().equals(List.class)) {
                        atributoValors.add(new AtributoValor(methods[i].getName().substring(3), methods[i].invoke(object)));
                    }

                } catch (Exception ex) {
                    LoggerUtil.error(this.getClass(), "doubleClick", ex);
                }
            }
        }
    }
}
