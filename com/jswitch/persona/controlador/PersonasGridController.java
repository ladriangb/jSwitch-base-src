package com.jswitch.persona.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultColumnsFilter;
import com.jswitch.base.controlador.util.DefaultGridFrameController;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.base.modelo.util.bean.BeanVO;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.type.Type;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;
import com.jswitch.persona.vista.Personas2GridFrame;
import org.openswing.swing.util.server.HibernateUtils;

/**
 *
 * @author Orlando Becerra
 */
public class PersonasGridController extends DefaultGridFrameController implements ActionListener {

    private String sql1;
    private Map filteredColumns;

    public PersonasGridController(String gridFramePath, String detailFramePath, String claseModeloFullPath, String titulo) {
        super(gridFramePath, detailFramePath, claseModeloFullPath, titulo);
        DefaultColumnsFilter filter = new DefaultColumnsFilter(Persona.class.getName());
        gridFrame.getGridControl().addListFilter("nombreLargo", filter);
        gridFrame.getGridControl().addListFilter("rif.cedulaCompleta", filter);
        gridFrame.getGridControl().addListFilter("ranking", filter);
    }

    @Override
    public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType, Map otherGridParams) {
        System.out.println();
        Session s = null;
        try {
            String sql = "SELECT DISTINCT C FROM " + claseModeloFullPath + " C LEFT JOIN C.tiposPersona T ";
            List<TipoPersona> tiposPersonasFiltradas = ((Personas2GridFrame) gridFrame).getTiposPersonaFiltro();
            if (tiposPersonasFiltradas != null && tiposPersonasFiltradas.size() != 0) {
                sql += "WHERE T.id IN ( ";
                sql += tiposPersonasFiltradas.get(0).getId();
                for (int i = 1; i < tiposPersonasFiltradas.size(); i++) {
                    TipoPersona tipo = tiposPersonasFiltradas.get(i);
                    sql += ", " + tipo.getId();
                }
                sql += " )";
            }
            this.sql1 = sql;
            this.filteredColumns = filteredColumns;
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
                    "C",
                    sf,
                    s);
//            Response res = HibernateUtils.getAllFromQuery(
//                    filteredColumns,
//                    currentSortedColumns,
//                    currentSortedVersusColumns,
//                    valueObjectType,
//                    sql,
//                    new Object[0],
//                    new Type[0],
//                    "C",
//                    sf,
//                    s);
            return res;

//            SessionFactory sf = HibernateUtil.getSessionFactory();
//            Session s = sf.openSession();
//            Criteria c = s.createCriteria(Persona.class);
//            Criteria artistCriteria = c.createCriteria("tiposPersona");
//            artistCriteria.add(Restrictions.like("idPropio", "%CON%"));
//            Response res = HibernateUtils.getAllFromCriteria(filteredColumns, currentSortedColumns, currentSortedVersusColumns, c, s);
//            s.close();
//            return res;
//            SessionFactory sf = HibernateUtil.getSessionFactory();
//            Session s = sf.openSession();
//            Criteria c = s.createCriteria(Persona.class);
//            Criteria artistCriteria = c.createCriteria("tiposPersona");
//            artistCriteria.add(Expression.like("idPropio", "%CON%"));
//            List l = c.list();
//            s.close();
//            return new VOListResponse(l, false, l.size());
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "loadData", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
    }

    @Override
    public void doubleClick(int rowNumber, ValueObject persistentObject) {
        new PersonasDetailController(null, (BeanVO) persistentObject, null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
