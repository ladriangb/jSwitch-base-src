package com.jswitch.base.controlador.util;

import java.util.ArrayList;
import java.util.HashMap;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.items.client.ItemsDataLocator;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author bc
 */
public class DefaultItemsDataLocator extends ItemsDataLocator {

    protected String claseModeloFullPath;
    protected String where;

    public DefaultItemsDataLocator(String claseModeloFullPath, String where) {
        this.claseModeloFullPath = claseModeloFullPath;
        if (where != null) {
            this.where = where;
        } else {
            this.where = "";
        }
    }

    @Override
    public Response loadData(Class valueObjectType) {
        Session s = null;
        try {
            String sql = "FROM " + claseModeloFullPath + " C " + where;
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getAllFromQuery(
                    new HashMap(0),
                    new ArrayList(0),
                    new ArrayList(0),
                    valueObjectType,
                    sql,
                    new Object[0],
                    new Type[0],
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
}
