package com.jswitch.persona.controlador;

import com.jswitch.base.controlador.util.DefaultLookupController;
import com.jswitch.base.controlador.util.DefaultLookupDataLocator;
import com.jswitch.base.controlador.util.DefaultLookupGridController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.persona.modelo.maestra.Persona;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import org.hibernate.classic.Session;
import org.openswing.swing.form.client.Form;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.VOListResponse;
import org.openswing.swing.util.java.Consts;

/**
 *
 * @author orlandobcrra
 */
public class CuentaBancariaPorPersonaLookupController extends DefaultLookupController {

    /**
     * forma del 
     */
    private Form mainPanel;

    public CuentaBancariaPorPersonaLookupController(Form mainPanel) {
        setLookupGridController(new DefaultLookupGridController());
        setLookupDataLocator(new CuentaBancariaDataLocator(CuentaBancariaPersona.class.getName()));
        setLookupValueObjectClassName(CuentaBancariaPersona.class.getName());
        setCodeSelectionWindow(GRID_FRAME);
        setOnInvalidCode(ON_INVALID_CODE_RESTORE_LAST_VALID_CODE);
        setAllColumnVisible(false);
        setVisibleColumn("id", true);
        setVisibleColumn("banco.nombreCorto", true);
        setVisibleColumn("tipoCuenta.nombre", true);
        setVisibleColumn("numero", true);
        setVisibleColumn("observacion", true);
        setPreferredWidthColumn("id", 50);
        setPreferredWidthColumn("numero", 200);
        setFilterableColumn("numero", true);
        setFilterableColumn("banco.nombreCorto", true);
        setFilterableColumn("tipoCuenta.nombre", true);
        setFilterableColumn("observacion", true);
        setSortableColumn("id", true);
        setSortedColumn("id", Consts.DESC_SORTED, 0);
        setSortableColumn("numero", true);
        setSortableColumn("banco.nombreCorto", true);
        setSortableColumn("tipoCuenta.nombre", true);
        setSortableColumn("observacion", true);
        setFramePreferedSize(new Dimension(550, 330));
    }

    class CuentaBancariaDataLocator extends DefaultLookupDataLocator {

        public CuentaBancariaDataLocator(String classFullName) {
            super(classFullName);
        }

        @Override
        public Response loadData(int action, int startIndex, Map filteredColumns, ArrayList currentSortedColumns, ArrayList currentSortedVersusColumns, Class valueObjectType) {
            Session s = null;
            try {
                s = HibernateUtil.getSessionFactory().openSession();
                List l = s.createQuery("SELECT C FROM " + Persona.class.getName() + " P "
                        + "JOIN P.documentos C where P.id=?").setLong(0, ((Auditable) mainPanel.getVOModel().getValueObject()).getId()).list();
                return new VOListResponse(l, false, l.size());
            } catch (Exception ex) {
            }
            return new VOListResponse();

        }
    }
}
