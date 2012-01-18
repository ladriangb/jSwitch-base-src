/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package com.jswitch.rol.controlador;

import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultAllGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.rol.modelo.Item;
import com.jswitch.rol.modelo.MenuByRol;
import com.jswitch.rol.modelo.Rol;
import java.util.ArrayList;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.message.receive.java.Response;
import org.hibernate.SessionFactory;
import org.openswing.swing.util.server.HibernateUtils;
import java.util.Map;
import org.hibernate.type.BooleanType;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;

/**
 *
 * @author PAPA
 */
public class RolGridFrameController extends DefaultAllGridFrameController {

    public RolGridFrameController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Response r = super.insertRecords(rowNumbers, newValueObjects);

        for (Object object : newValueObjects) {
            MenuByRoles((Rol) object);
        }

        return r;
    }

    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String sql = "FROM " + claseModeloFullPath + " C where C.auditoria.visible2=?";
//            filteredColumns.put(
//                    "auditoria.visible2",
//                    new FilterWhereClause[]{
//                        new FilterWhereClause("auditoria.visible2", "=", true),
//                        null
//                    });
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getAllFromQuery(
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[]{Boolean.TRUE},
                    new Type[]{BooleanType.class.newInstance()},
                    "C",
                    sf,
                    s);
            return res;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }

    public void MenuByRoles(Rol rol) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction tx = null;

            // <editor-fold defaultstate="collapsed" desc="Roles">
            tx = s.beginTransaction();
            Item items = (Item) optenerData(Item.class.getName()
                    + " where nombre='root'").get(0);
            for (Item item : items.getItems()) {
                fillMenu(rol, item, s);
            }


            tx.commit();
            // </editor-fold>

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            s.close();
        }

    }

    void fillMenu(Rol rol, Item items, Session s) {
        MenuByRol nuevo = new MenuByRol(items.getId(), rol.getId(), Boolean.TRUE);
        s.save(nuevo);
        for (Item item : items.getItems()) {
            fillMenu(rol, item, s);
        }
    }

    java.util.List optenerData(String valueObjectClassName) {

        Session s = null;
        java.util.List mensajes = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            Transaction t = s.beginTransaction();
            {
                String hql = "FROM " + valueObjectClassName;
                Query query = s.createQuery(hql);
                mensajes = query.list();
            }
            t.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
        return mensajes;
    }
}
