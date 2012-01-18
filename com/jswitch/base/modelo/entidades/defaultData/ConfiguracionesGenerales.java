package com.jswitch.base.modelo.entidades.defaultData;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Personal
 */
@Entity
@Table(name = "CONF_ConfiguracionesGenerales")
public class ConfiguracionesGenerales extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * 
     */
    @Column
    private String nombre;
    /**
     * 
     */
    @Column
    private String valorString;
    /**
     * 
     */
    @Column
    private Double valorDouble;
    /**
     * 
     */
    @Column
    private Integer valorInteger;
    /**
     * 
     */
    @Column
    private Boolean valorBoolean;
    /**
     * 
     */
    @Column
    @Temporal(TemporalType.DATE)
    private Date valorDate;
    /**
     * Version de la clase
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * Auditoria de la clase
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;

    public ConfiguracionesGenerales() {
    }

    public ConfiguracionesGenerales(String nombre) {
        this.nombre = nombre;
    }

    public ConfiguracionesGenerales(String nombre, String valorString) {
        this.nombre = nombre;
        this.valorString = valorString;
    }

    public ConfiguracionesGenerales(String nombre, Double valorDouble) {
        this.nombre = nombre;
        this.valorDouble = valorDouble;
    }

    public ConfiguracionesGenerales(String nombre, Integer valorInteger) {
        this.nombre = nombre;
        this.valorInteger = valorInteger;
    }

    public ConfiguracionesGenerales(String nombre, Boolean valorBoolean) {
        this.nombre = nombre;
        this.valorBoolean = valorBoolean;
    }

    public ConfiguracionesGenerales(String nombre, Date valorDate) {
        this.nombre = nombre;
        this.valorDate = valorDate;
    }

    /**
     * Auditoria de la clase
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Version de la clase
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Auditoria de la clase
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Version de la clase
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getValorBoolean() {
        return valorBoolean;
    }

    public void setValorBoolean(Boolean valorBoolean) {
        this.valorBoolean = valorBoolean;
    }

    public Date getValorDate() {
        return valorDate;
    }

    public void setValorDate(Date valorDate) {
        this.valorDate = valorDate;
    }

    public Double getValorDouble() {
        return valorDouble;
    }

    public void setValorDouble(Double valorDouble) {
        this.valorDouble = valorDouble;
    }

    public Integer getValorInteger() {
        return valorInteger;
    }

    public void setValorInteger(Integer valorInteger) {
        this.valorInteger = valorInteger;
    }

    public String getValorString() {
        return valorString;
    }

    public void setValorString(String valorString) {
        this.valorString = valorString;
    }
}
