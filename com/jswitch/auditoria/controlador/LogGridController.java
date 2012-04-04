

package com.jswitch.auditoria.controlador;

import com.jswitch.auditoria.modelo.AtributoValor;
import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.AuditLogRecord;
import java.util.ArrayList;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author orlandobcrra
 */
public class LogGridController extends DefaultGridFrameController {

    public LogGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
         Session s = null;
        try {
            String tableName = "C";
            String sql =  " FROM " + claseModeloFullPath + " " + tableName + " ";
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    sql,
                    new Object[0],
                    new Type[0],
                    tableName,
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

    
    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        ArrayList<AtributoValor> atributoValors = new ArrayList<AtributoValor>(0);
        for (int i = 0; i < ((AuditLogRecord) persistentObject).getNombres().size(); i++) {
            atributoValors.add(
                    new AtributoValor(
                    ((AuditLogRecord) persistentObject).getNombres().get(i),
                    ((AuditLogRecord) persistentObject).getValores().get(i)));
        }
        new AtributeLogGridFrameController(atributoValors);
        //new DefaultDetailFrameController(detailFramePath, gridFrame.getGridControl(), (BeanVO) persistentObject, false);
    }
}
