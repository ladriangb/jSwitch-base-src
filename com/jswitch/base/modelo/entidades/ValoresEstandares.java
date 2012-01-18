package com.jswitch.base.modelo.entidades;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.Version;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import javax.persistence.Table;

/**
 *
 * @author Orlando Becerra
 */
@Entity
@Table(name="ALL_ValoresEstandares")
public class ValoresEstandares extends BeanVO implements Serializable, Auditable {

    /**
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     */
    @Column
    @BusinessKey
    private Double iva;
    /**
     */
    @Column
    @BusinessKey
    private Double ut;
    /**
     *
     */
    @Column
    @BusinessKey
    private Long secPoliza;
    /**
     *
     */
    @Column
    @BusinessKey
    private Long secRecibo;
    /**
     *
     */
    @Version
    @Column
    private Integer optLock;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

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

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Double getIva() {
        return iva;
    }

    public void setIva(Double iva) {
        this.iva = iva;
    }

    public Double getUt() {
        return ut;
    }

    public void setUt(Double ut) {
        this.ut = ut;
    }

    public Long getSecPoliza() {
        return secPoliza;
    }

    public void setSecPoliza(Long secPoliza) {
        this.secPoliza = secPoliza;
    }

    public Long getSecRecibo() {
        return secRecibo;
    }

    public void setSecRecibo(Long secRecibo) {
        this.secRecibo = secRecibo;
    }
}
