/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jswitch.persona.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.util.DefaultGridControllerWhitSQL;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.vista.Personas2GridFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.transform.AliasedTupleSRT;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author PAPA
 */
public class PersonaGridControllerWhitSQL extends DefaultGridControllerWhitSQL {

    public PersonaGridControllerWhitSQL(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo, String sql, Object[] values, Type[] valueType) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo, sql, values, valueType);
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new PersonasDetailController(null, (BeanVO) persistentObject, null);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        Session s = null;
        try {
            String select =
                    gridFrame.getGridControl().getVOListTableModel().createSelect("P", AliasedTupleSRT.SEPARATOR);

            select = select.replaceFirst("SELECT", "SELECT DISTINCT");
            select +=", P.rif.tipoCedula as rif_tipoCedula ";
                    
                    
                    

//            sql = "" + " FROM " + claseModeloFullPath + " C LEFT JOIN C.tiposPersona T ";
//            List<TipoPersona> tiposPersonasFiltradas = ((Personas2GridFrame) gridFrame).getTiposPersonaFiltro();
//            if (tiposPersonasFiltradas != null && tiposPersonasFiltradas.size() != 0) {
//                sql += "WHERE T.id IN ( ";
//                sql += tiposPersonasFiltradas.get(0).getId();
//                for (int i = 1; i < tiposPersonasFiltradas.size(); i++) {
//                    TipoPersona tipo = tiposPersonasFiltradas.get(i);
//                    sql += ", " + tipo.getId();
//                }
//                sql += " )";
//            }
//            sql = sql.replaceAll("SELECT.*FROM", " FROM");
//            sql = select + sql;
            SessionFactory sf = HibernateUtil.getSessionFactory();
            s = sf.openSession();
            Response res = HibernateUtils.getBlockFromQuery(
                    new AliasedTupleSRT(Persona.class),
                    action,
                    startIndex,
                    General.licencia.getBlockSize(),
                    filteredColumns,
                    currentSortedColumns,
                    currentSortedVersusColumns,
                    valueObjectType,
                    select + sql,
                    values,
                    valueTypes,
                    "P",
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
