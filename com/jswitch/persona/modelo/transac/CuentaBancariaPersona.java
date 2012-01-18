package com.jswitch.persona.modelo.transac;

import com.jswitch.persona.modelo.maestra.Persona;
import com.jswitch.persona.modelo.dominio.TipoCuentaBancaria;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Clase Asociativa entre la persona, el banco
 * y el Tipo de Cuenta
 * @version 1.0 22/05/2009
 * @since JDK 1.5
 * @see Persona
 * @see TipoCuentaBancaria
 * @author Orlando Becerra
 * @author Nelson Moncada
 * @author Luis Gonzalez
 */
@Entity
@Table(name = "PERS_CuentaBancariaPersona", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"numero", "banco_id", "tipoCuenta_id"})})
public class CuentaBancariaPersona extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autoincrementado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Numero de cuenta
     */
    @Column
    @Pattern(regexp = "\\d{20}", message = "Solo se permiten 20 numeros")
    @BusinessKey
    private String numero;
    /**
     * Observacion del numero de cuenta
     */
    @Column
    @BusinessKey
    private String observacion;
    /**
     * Banco que posee la cuenta
     */
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private Persona banco;
    /**
     * Tipo de cuenta que posee
     */
    @ManyToOne
    @NotNull
    @BusinessKey(include = Method.TO_STRING)
    private TipoCuentaBancaria tipoCuenta;
    /**
     * se domicilan los pagos aqui?
     */
    @Column
    @BusinessKey
    private Boolean domicilio;
    /**
     * version
     */
    @Version
    @Column
    private Integer optLock;
    /**
     * Auditoria Basica de los Registros
     * Bitacora
     */
    @Embedded
    private AuditoriaBasica auditoria;
//    /**
//     * Persona a la que pertenece
//     */
//    private transient Persona persona;

    public CuentaBancariaPersona() {
        this.domicilio = Boolean.FALSE;
    }

    public CuentaBancariaPersona(String numero, String observacion, Persona banco, TipoCuentaBancaria tipoCuenta, AuditoriaBasica auditoria) {
        this.numero = numero;
        this.observacion = observacion;
        this.banco = banco;
        this.tipoCuenta = tipoCuenta;
        this.auditoria = auditoria;
        this.domicilio = Boolean.FALSE;
    }

    /**
     * Auditoria Basica de los Registros
     * Bitacora
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Banco que posee la cuenta
     * @return the banco
     */
    public Persona getBanco() {
        return banco;
    }

    /**
     * se domicilan los pagos aqui?
     * @return the domicilio
     */
    public Boolean getDomicilio() {
        return domicilio;
    }

    /**
     * Pk autoincrementado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Numero de cuenta
     * @return the numero
     */
    public String getNumero() {
        return numero;
    }

    /**
     * Observacion del numero de cuenta
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * version
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Tipo de cuenta que posee
     * @return the tipoCuenta
     */
    public TipoCuentaBancaria getTipoCuenta() {
        return tipoCuenta;
    }

    /**
     * Auditoria Basica de los Registros
     * Bitacora
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Banco que posee la cuenta
     * @param banco the banco to set
     */
    public void setBanco(Persona banco) {
        this.banco = banco;
    }

    /**
     * se domicilan los pagos aqui?
     * @param domicilio the domicilio to set
     */
    public void setDomicilio(Boolean domicilio) {
        this.domicilio = domicilio;
    }

    /**
     * Pk autoincrementado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Numero de cuenta
     * @param numero the numero to set
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Observacion del numero de cuenta
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * version
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Tipo de cuenta que posee
     * @param tipoCuenta the tipoCuenta to set
     */
    public void setTipoCuenta(TipoCuentaBancaria tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

}
