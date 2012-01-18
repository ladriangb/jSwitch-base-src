

package com.jswitch.base.controlador;


import com.jswitch.base.modelo.entidades.Empresa;
import com.jswitch.base.modelo.entidades.Licencia;
import com.jswitch.base.modelo.entidades.Oficina;
import com.jswitch.base.modelo.entidades.Usuario;
import com.jswitch.base.modelo.entidades.defaultData.ConfiguracionesGenerales;
import com.jswitch.base.modelo.entidades.defaultData.DefaultPersona;
import com.jswitch.rol.modelo.MenuByRol;
import java.util.HashMap;
import java.util.List;


/**
 *
 * @author orlandobcrra
 */
public class General {

    public static Usuario usuario = new Usuario();
    public static String usuarioOS = "";
    public static String IP = "";
    public static Empresa empresa = new Empresa();
    public static Licencia licencia = new Licencia();
    public static Oficina oficina = new Oficina();
    public static String version = "";
    public static String edition = "";
    public static String copyRight = "";
    public static String contacto = "";
    public static String splashLine2 = "";
    public static DefaultPersona defaultPersona;
    public static HashMap<String,ConfiguracionesGenerales> parametros=new HashMap<String,ConfiguracionesGenerales>();
    public static HashMap<String,MenuByRol> permisologiaModulo=new HashMap<String,MenuByRol>();
    
}
