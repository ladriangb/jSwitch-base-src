package com.jswitch.persona.controlador;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.util.DefaultGridInternalController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.transac.DireccionPersona;
import java.util.ArrayList;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author bc
 */
public class DireccionesGridInternalController extends DefaultGridInternalController {

    public DireccionesGridInternalController(String classNameModelFullPath, String getMethodName, GridControl miGrid, DefaultGridInternalController... listSubGrids) {
        super(classNameModelFullPath, getMethodName, miGrid, listSubGrids);
    }

    @Override
    public void createValueObject(ValueObject valueObject) throws Exception {
        int rowToSel = miGrid.getVOListTableModel().getRowCount() - 1;
        miGrid.getVOListTableModel().setField(rowToSel, "tipoDireccion", General.defaultPersona.getDireccion());
    }

    @Override
    public Response insertRecords(int[] rowNumbers, ArrayList newValueObjects) throws Exception {
        Response r = super.insertRecords(rowNumbers, newValueObjects);
        for (Object e : newValueObjects) {
            DireccionPersona d = (DireccionPersona) e;
            if (d.getTipoDireccion().getNombre().toLowerCase().contains("fiscal")) {
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction tr = s.beginTransaction();
                    Persona p = (Persona) beanVO;
                    p.setDireccionFiscal(d);
                    s.update(beanVO);
                    tr.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
                break;
            }
        }
        return r;
    }

    @Override
    public Response updateRecords(int[] rowNumbers, ArrayList oldPersistentObjects, ArrayList persistentObjects) throws Exception {
        Response r = super.updateRecords(rowNumbers, oldPersistentObjects, persistentObjects);
        for (Object e : persistentObjects) {
            DireccionPersona d = (DireccionPersona) e;
            if (d.getTipoDireccion().getNombre().toLowerCase().contains("fiscal")) {
                Session s = null;
                try {
                    s = HibernateUtil.getSessionFactory().openSession();
                    Transaction tr = s.beginTransaction();
                    Persona p = (Persona) beanVO;
                    p.setDireccionFiscal(d);
                    s.update(beanVO);
                    tr.commit();
                } catch (Exception ex) {
                    ex.printStackTrace();
                } finally {
                    s.close();
                }
                break;
            }
        }
        return r;
    }
}
