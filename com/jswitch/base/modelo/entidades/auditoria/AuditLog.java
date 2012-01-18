
package com.jswitch.base.modelo.entidades.auditoria;
import com.jswitch.base.modelo.HibernateUtil;
import org.hibernate.classic.Session;

/**
 *
 * @author bc
 */
public class AuditLog {

    public static void logEvent(AuditLogRecord entity) {
        //System.out.println("guardo audit ***************");
        Session s = HibernateUtil.getSessionFactorySinIntercertor().openSession();
        try {
            //TODO AUDITORIA PROBLEMAS
            //FIXME AUDITORIA PROBLEMAS
            s.beginTransaction();
            s.save(entity);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            s.close();
        }
    }
}
