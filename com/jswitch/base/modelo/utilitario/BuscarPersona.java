/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.jswitch.base.modelo.utilitario;

import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.base.modelo.util.bean.BeanVO;

/**
 *
 * @author Luis Adrian
 * 
 */
public class BuscarPersona extends BeanVO {
    private String rif;
    private String nombreLargo;
    private TipoPersona tipoPersona;

    public BuscarPersona() {
    }

    public String getNombreLargo() {
        return nombreLargo;
    }

    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    public String getRif() {
        return rif;
    }

    public void setRif(String rif) {
        this.rif = rif;
    }

    public TipoPersona getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoPersona tipoPersona) {
        this.tipoPersona = tipoPersona;
    }
 
    
}
