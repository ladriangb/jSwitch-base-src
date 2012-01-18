

package com.jswitch.persona.modelo.transac;


import com.jswitch.persona.modelo.dominio.TipoTelefono2;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.persona.modelo.dominio.TipoCodigoArea;
import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 *  Clase Asociativa entre la persona y el tipo de telefono
 *  @version 1.0 22/05/2009
 *  @since JDK 1.5
 *  @see Persona
 *  @see TipoTelefono
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
@Table(name="PERS_TelefonoPersona")

public class TelefonoPersona extends BeanVO implements Serializable, Auditable {

    /**
     *  PKa autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Indicador de estado
     */
    @Column
    @BusinessKey
    private Boolean notificar;
    /**
     * Numero de telefono de la persona
     */
    @Column
    @NotNull
    @BusinessKey
    private String numeroCompleto;
    /**
     * Numero de telefono de la persona
     */
    @Column
    @BusinessKey
    private String numeroS;
    /**
     *  Observacion del numero telefonico
     */
    @Column
    @BusinessKey
    private String observacion;
    /**
     *  Tipo de telefono que posee este numero telefonico
     */
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private TipoTelefono2 tipoTelefono;
    /**
     *
     */
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private TipoCodigoArea codigoArea;
    /**
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public TelefonoPersona() {
	this.notificar = false;
    }

    public TelefonoPersona(String tel, TipoTelefono2 tipo, TipoCodigoArea cod) {
	numeroCompleto=tel.substring(1);
	notificar=tel.startsWith("04");
	tipoTelefono=tipo;
	numeroS=tel.substring(4);
	observacion=tel;
	codigoArea=cod;
    }

    public TelefonoPersona(String numeroS, String observacion, TipoTelefono2 tipoTelefono, AuditoriaBasica auditoria) {
	this.numeroS = numeroS;
	this.observacion = observacion;
	this.tipoTelefono = tipoTelefono;
	this.auditoria = auditoria;
	this.notificar = true;
    }

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    public String getNumeroS() {
	return numeroS;
    }

    public void setNumeroS(String numeroS) {
	this.numeroS = numeroS;
    }

    public String getObservacion() {
	return observacion;
    }

    public void setObservacion(String observacion) {
	this.observacion = observacion;
    }

    public Integer getOptLock() {
	return optLock;
    }

    public void setOptLock(Integer optLock) {
	this.optLock = optLock;
    }

    public TipoTelefono2 getTipoTelefono() {
	return tipoTelefono;
    }

    public void setTipoTelefono(TipoTelefono2 tipoTelefono) {
	this.tipoTelefono = tipoTelefono;
    }

    public AuditoriaBasica getAuditoria() {
	return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
	this.auditoria = auditoria;
    }

    public TipoCodigoArea getCodigoArea() {
	return codigoArea;
    }

    public void setCodigoArea(TipoCodigoArea codigoArea) {
	this.codigoArea = codigoArea;
    }

    public String getNumeroCompleto() {
	return numeroCompleto;
    }

    public void setNumeroCompleto(String numeroCompleto) {
	this.numeroCompleto = numeroCompleto;
    }

    public Boolean getNotificar() {
	return notificar;
    }

    public void setNotificar(Boolean notificar) {
	this.notificar = notificar;
    }

    public Boolean isNotificar() {
	return notificar;
    }

}
