package com.jswitch.base.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import com.jswitch.persona.modelo.maestra.Persona;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 *  Clase Asociativa que almacena una Observacion
 *  relacionada a un persona
 *  @version 1.0 22/05/2009
 *  @since JDK 1.5
 *  @see Persona
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
@Table(name="ALL_NotaTecnica")
public class NotaTecnica extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     *  Observacion de la persona
     */
    @Column
    @Size(min = 1, max = 1024)
    @BusinessKey
    private String observacion;
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

    public NotaTecnica() {
    }

    public NotaTecnica(String observacion, AuditoriaBasica auditoria) {
        this.observacion = observacion;
        this.auditoria = auditoria;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }
}
