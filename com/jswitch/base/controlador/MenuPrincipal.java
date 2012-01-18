package com.jswitch.base.controlador;

import com.jswitch.base.controlador.helpcenter.CorreosGridFrameController;
import com.jswitch.base.controlador.logger.LoggerUtil;
import com.jswitch.base.controlador.sistema.usuarios.UsuarioDetailController;
import com.jswitch.base.controlador.sistema.usuarios.UsuariosGridController;
import com.jswitch.base.controlador.util.DefaultAllGridFrameController;
import com.jswitch.base.controlador.util.DefaultDetailFrameController;
import com.jswitch.base.modelo.HibernateUtil;
import com.jswitch.base.modelo.entidades.Encabezado;
import com.jswitch.base.modelo.entidades.Licencia;
import com.jswitch.base.modelo.entidades.TipoDocumento;
import com.jswitch.base.modelo.entidades.Usuario;
import com.jswitch.base.modelo.entidades.helpcenter.maestra.Mensaje;
import com.jswitch.base.vista.mant.DefaultMantenimientoGridFrame;
import com.jswitch.base.vista.mant.EncabezadoGridFrame;
import com.jswitch.base.vista.mant.TipoDocumentoGridFrame;
import com.jswitch.base.vista.sistema.CambiarPassDialog;
import com.jswitch.base.vista.sistema.ConfiguracionDetailFrame;
import com.jswitch.base.vista.sistema.EmpresaDetailFrame;
import com.jswitch.base.vista.sistema.LicenciasGridFrame;
import com.jswitch.base.vista.sistema.UsuarioDetailFrame;
import com.jswitch.base.vista.sistema.UsuariosGridFrame;
import com.jswitch.persona.controlador.PersonasGridController;
import com.jswitch.persona.controlador.mant.TipoPersonaGridFrameController;
import com.jswitch.persona.modelo.dominio.TipoActividadEconomica;
import com.jswitch.persona.modelo.dominio.TipoCapacidadEconomica;
import com.jswitch.persona.modelo.dominio.TipoCodigoArea;
import com.jswitch.persona.modelo.dominio.TipoCuentaBancaria;
import com.jswitch.persona.modelo.dominio.TipoDireccion;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.dominio.TipoTelefono2;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.vista.BuscarPersonaDialog;
import com.jswitch.persona.vista.PersonaDetailFrame;
import com.jswitch.persona.vista.Personas2GridFrame;
import com.jswitch.persona.vista.RifDialog;
import com.jswitch.persona.vista.mant.CodigoAreaGridFrame;
import com.jswitch.persona.vista.mant.TipoPersonaGridFrame;
import com.jswitch.reporte.controlador.ReportesGridController;
import com.jswitch.reporte.modelo.Reporte;
import com.jswitch.reporte.vista.ReportesGridFrame;
import com.jswitch.rol.controlador.RolGridFrameController;
import com.jswitch.rol.controlador.RolOptionFrameController;
import com.jswitch.rol.modelo.Item;
import com.jswitch.rol.modelo.MenuByRol;
import com.jswitch.rol.modelo.Rol;
import com.jswitch.rol.vista.RolOptionFrame;
import de.muntjak.tinylookandfeel.TinyLookAndFeel;
import de.muntjak.tinylookandfeel.controlpanel.ControlPanel;
import java.io.File;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.openswing.swing.client.OptionPane;
import org.openswing.swing.mdi.client.*;
import org.openswing.swing.mdi.java.ApplicationFunction;
import org.openswing.swing.tree.java.OpenSwingTreeNode;

/**
 * @author Orlando Becerra
 * @author Enrique Becerra
 */
public class MenuPrincipal implements ClientFacade {
    Session s = null;
    private boolean tinyCP1 = true;
    private ControlPanel cp = null;

    private MenuByRol addFuntion(DefaultMutableTreeNode padre, Item funcion) {

        String hql = "FROM " + MenuByRol.class.getName()
                + " WHERE itemId=" + funcion.getId() + " AND "
                + "rolId=" + General.usuario.getRol().getId();
        Query query = s.createQuery(hql);
        MenuByRol mbr = (MenuByRol) query.list().get(0);
        boolean sw = (mbr).isEnable();

        if (sw) {
            ApplicationFunction hijo = null;
            if (funcion.getMetodo() != null) {
                hijo = new ApplicationFunction(funcion.getNombre(),
                        funcion.getFuncionId(), funcion.getIcono(),
                        funcion.getMetodo());
            } else {
                hijo = new ApplicationFunction(funcion.getNombre(),
                        funcion.getIcono());

            }
            padre.add(hijo);
            for (Item it : funcion.getItems()) {
                addFuntion(hijo, it);
            }
        }
        return mbr;
    }
    
    /**
     * Monta el menu dependiendo del rol del usuario
     */
    public DefaultTreeModel getApplicationFunctions() {
        DefaultMutableTreeNode root = new OpenSwingTreeNode("Menu");

        DefaultTreeModel model = new DefaultTreeModel(root);
        {


            try {
                s = HibernateUtil.getSessionFactory().openSession();
                Transaction t = s.beginTransaction();
                {
                    String hql = "FROM " + Item.class.getName() + " WHERE nombre='root'";
                    Query query = s.createQuery(hql);
                    List mensajes = query.list();
                    Item rootItem = (Item) mensajes.get(0);
                    for (Item it : rootItem.getItems()) {
                        //Collections.sort(it.getItems());
                        MenuByRol mbr = addFuntion(root, it);
                        General.permisologiaModulo.put(it.getNombre(), mbr);
                    }

                }
                t.commit();
            } catch (Exception e) {
                LoggerUtil.error(this.getClass(), "getApplicationFuntions", e);
            } finally {
                s.close();
            }

        }

        return model;
    }

    // <editor-fold defaultstate="collapsed" desc="Personas">
    public void getPersonas() {
        new PersonasGridController(Personas2GridFrame.class.getName(), PersonaDetailFrame.class.getName(), Persona.class.getName(), null);
    }

    public void getPersonaNueva() {
        new RifDialog();
    }

    public void getTipoPersona() {
        new TipoPersonaGridFrameController(TipoPersonaGridFrame.class.getName(), null, TipoPersona.class.getName(), "Tipos de Persona");
    }

    public void getTipoActividadEconomica() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoActividadEconomica.class.getName(), "Tipo Actividad Economica");
    }

    public void getTipoCapacidadEconomica() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoCapacidadEconomica.class.getName(), "Tipo Capacidad Economica");
    }

    public void getTipoTelefono() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoTelefono2.class.getName(), "Tipo Telefono");
    }

    public void getTipoDireccion() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoDireccion.class.getName(), "Tipo Direccion");
    }

    public void getTipoCuentaBancaria() {
        new DefaultAllGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, TipoCuentaBancaria.class.getName(), "Tipo Cuenta Bancaria");
    }

    public void getCodigoArea() {
        new DefaultAllGridFrameController(CodigoAreaGridFrame.class.getName(), null, TipoCodigoArea.class.getName(), null);
    }

    public void getBuscarPersona() {
        new BuscarPersonaDialog(null).setVisible(true);
    }
// </editor-fold>


    // <editor-fold defaultstate="collapsed" desc="Roles">
    public void getRoles() {
        new RolOptionFrameController(RolOptionFrame.class.getName(), Rol.class.getName(), "Roles");
    }

    public void getNuevoRol() {

        if (General.usuario.getAdministraUsuarios()) {
            new RolGridFrameController(DefaultMantenimientoGridFrame.class.getName(), null, Rol.class.getName(), "Nuevo Rol");
        } else {
            JOptionPane.showMessageDialog(MDIFrame.getInstance(), "Notiene permisos para crear Nuevos Roles", "Error de Validacion de Roles", JOptionPane.ERROR_MESSAGE);
        }
    }
// </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Sistema Base">
    public void getEncabezado(String name) {
        new DefaultAllGridFrameController(EncabezadoGridFrame.class.getName(), null, Encabezado.class.getName(), "Encabezados de Reporte");
    }
    public void getTipoDocAnex(String name) {
        new DefaultAllGridFrameController(TipoDocumentoGridFrame.class.getName(), null, TipoDocumento.class.getName(), "Tipo Docuemtnos Anexos");
    }

    public void getTipoDocAnex() {
        new DefaultAllGridFrameController(TipoDocumentoGridFrame.class.getName(), null, TipoDocumento.class.getName(), "Tipo Docuemtnos Anexos");
    }

    public void getUsuarios(String name) {
        if (General.usuario.getAdministraUsuarios()) {
            new UsuariosGridController(UsuariosGridFrame.class.getName(), UsuarioDetailFrame.class.getName(), Usuario.class.getName(), null);
        } else {
            new UsuarioDetailController(UsuarioDetailFrame.class.getName(), null, General.usuario, false);
        }
    }

    public void getCambiarPass(){
        CambiarPassDialog.showDialog();
    }
    public void getEmpresa(String name) {
        new DefaultDetailFrameController(EmpresaDetailFrame.class.getName(), null, General.empresa, false);
    }

    public void getConfiguracion(String name) {
        new DefaultDetailFrameController(ConfiguracionDetailFrame.class.getName(), null, General.empresa, false);
    }

    public void getLicencias(String name) {
        new DefaultAllGridFrameController(LicenciasGridFrame.class.getName(), null, Licencia.class.getName(), "Licencias");
    }

    public void getReporteH(String name) {
        new ReportesGridController(ReportesGridFrame.class.getName(), null, Reporte.class.getName(), null);
    }

    public void getHelpCenter() {
        new CorreosGridFrameController(Mensaje.class.getName());
    }

    public void getExit() {
        MDIFrame.getInstance().menuFileExit_actionPerformed(null);
    }

    public void getHelp() {
        if (Principal.helpManager != null) {
            Principal.helpManager.showNavigatorWindow();
        }
    }

    public void AUN_NO_FUN() {
        OptionPane.showMessageDialog(
                MDIFrame.getInstance(),
                "build",
                "warning",
                JOptionPane.WARNING_MESSAGE);
    }

    public void getConfigLnF() {
        if (tinyCP1) {
            TinyLookAndFeel.controlPanelInstantiated = true;
            System.setProperty("swing.aatext", "true");
            try {
                UIManager.setLookAndFeel("de.muntjak.tinylookandfeel.TinyLookAndFeel");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            cp = new ControlPanel();
            cp.openTheme2(new File(System.getProperty("user.dir") + "/Default.theme"));
            tinyCP1 = false;
        } else if (cp != null) {
            cp.theFrame.setVisible(true);
        }
    }
  // </editor-fold>

    
}
