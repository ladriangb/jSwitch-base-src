package com.jswitch.base.modelo.entidades.defaultData;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.persona.modelo.dominio.TipoActividadEconomica;
import com.jswitch.persona.modelo.dominio.TipoCapacidadEconomica;
import com.jswitch.persona.modelo.dominio.TipoDireccion;
import com.jswitch.persona.modelo.dominio.TipoTelefono2;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Table;

/**
 *
 * @author bc
 */
@Entity
@Table(name="PERS_DefaultPersona")
public class DefaultPersona extends BeanVO implements Serializable, Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    @ManyToOne()
    @BusinessKey
    private TipoCapacidadEconomica capacidadEconomica;
    @ManyToOne()
    @BusinessKey
    private TipoActividadEconomica actividadEconomica;
    @ManyToOne()
    @BusinessKey
    private TipoDireccion direccion;
    @ManyToOne()
    @BusinessKey
    private TipoTelefono2 telefono;
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

    public TipoActividadEconomica getActividadEconomica() {
        return actividadEconomica;
    }

    public void setActividadEconomica(TipoActividadEconomica actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public TipoCapacidadEconomica getCapacidadEconomica() {
        return capacidadEconomica;
    }

    public void setCapacidadEconomica(TipoCapacidadEconomica capacidadEconomica) {
        this.capacidadEconomica = capacidadEconomica;
    }

    public TipoDireccion getDireccion() {
        return direccion;
    }

    public void setDireccion(TipoDireccion direccion) {
        this.direccion = direccion;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public TipoTelefono2 getTelefono() {
        return telefono;
    }

    public void setTelefono(TipoTelefono2 telefono) {
        this.telefono = telefono;
    }
}
