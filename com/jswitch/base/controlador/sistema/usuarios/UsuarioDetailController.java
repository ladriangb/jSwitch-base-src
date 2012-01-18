package com.jswitch.base.controlador.sistema.usuarios;

import com.jswitch.base.controlador.General;
import com.jswitch.base.controlador.licencia.Equipo;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import java.util.List;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.Usuario;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.vista.sistema.UsuarioDetailFrame;
import org.hibernate.classic.Session;
import org.openswing.swing.client.GridControl;
import org.openswing.swing.message.receive.java.ErrorResponse;
import org.openswing.swing.message.receive.java.Response;
import org.openswing.swing.message.receive.java.ValueObject;

/**
 *
 * @author bc
 */
public class UsuarioDetailController extends DefaultDetailFrameController {

    public UsuarioDetailController(String detailFramePath, GridControl gridControl, BeanVO beanVO, boolean aplicarLogicaNegocio) {
        super(detailFramePath, gridControl, beanVO, aplicarLogicaNegocio);
        if (!General.usuario.getModificarPermisos()) {
            ((UsuarioDetailFrame) vista).ocultarInformacion();
        }

    }

    @Override
    public Response insertRecord(ValueObject newPersistentObject) throws Exception {
        Usuario unew = (Usuario) newPersistentObject;
        Response r = userNameRepetido(unew);
        if (r != null) {
            return r;
        }
        unew.setPassword2(Equipo.encodeText(unew.getPassword()));
        return super.insertRecord(unew);
    }

    @Override
    public Response updateRecord(ValueObject oldPersistentObject, ValueObject persistentObject) throws Exception {
        Usuario uold = (Usuario) oldPersistentObject;
        Usuario unew = (Usuario) persistentObject;
        if (!uold.getUserName().equals(unew.getUserName())) {
            Response r = userNameRepetido(unew);
            if (r != null) {
                return r;
            }
        }
        unew.setPassword2(Equipo.encodeText(unew.getPassword()));
        return super.updateRecord(oldPersistentObject, unew);
    }

    private Response userNameRepetido(Usuario unew) {
        Session s = null;
        try {
            s = HibernateUtil.getSessionFactory().openSession();
            List l = s.createQuery("FROM " + com.jswitch.base.modelo.entidades.Usuario.class.getName()).list();
            for (Object o : l) {
                Usuario u2 = (Usuario) o;
                if (u2.getUserName().equals(unew.getUserName())) {
                    return new ErrorResponse("Nombre de Usuario ya existe");
                }
            }
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "updateRecord", ex);
            return new ErrorResponse(ex.getMessage());
        } finally {
            s.close();
        }
        return null;
    }

 
}
