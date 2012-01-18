package com.jswitch.persona.modelo.maestra;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.Version;
import com.jswitch.base.modelo.entidades.Documento;
import com.jswitch.base.modelo.entidades.Observacion;
import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.persona.modelo.dominio.TipoPersona;
import com.jswitch.persona.modelo.dominio.TipoCapacidadEconomica;
import com.jswitch.persona.modelo.dominio.TipoActividadEconomica;
import com.jswitch.persona.modelo.transac.CuentaBancariaPersona;
import com.jswitch.persona.modelo.transac.DireccionPersona;
import com.jswitch.persona.modelo.transac.TelefonoPersona;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import org.hibernate.annotations.Index;
import org.hibernate.validator.constraints.Email;
import javax.validation.constraints.Size;
import javax.validation.constraints.Past;
import com.jswitch.base.modelo.Dominios.Ranking;
import com.jswitch.base.modelo.Dominios.TipoContribuyente;
import javax.persistence.Table;

/**
 * Clase Maestra Supertipo de Personas
 * @version 1.1 22/05/2009
 * @since JDK 1.5
 * @see Tipo
 * @see TipoCapacidadEconomica
 * @see TipoActividadEconomica
 * @see TipoPersona
 * @see ObservacionPersona
 * @see EmailPersona
 * @see TelefonoPersona
 * @see DireccionPersona
 * @see DocumentoPersona
 * @see CuentaBancariaPersona
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "PERS_Persona")
public class Persona extends BeanVO implements Serializable, Auditable {

    /**
     * Pk autogenerado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    /**
     * Codigo en Archivo
     * donde conseguir el archivo fisico
     */
    @Column
    @BusinessKey
    private String codigoArchivo;
    /**
     * Codigo ??
     */
    @Column
    @BusinessKey
    private String codigoX;
    /**
     * Datos de la identificacion de la persona
     */
    @Embedded
    @BusinessKey
    private Rif rif;
    /**
     * Nombre completo de la persona
     * Autogenerado si es persona natural
     */
    @Column
    @Size(min = 2, max = 240)
    @Index(name = "nombreLargoPersona")
    @BusinessKey
    private String nombreLargo;
    /**
     * Nombre Corto de la persona
     */
    @Column
    @Index(name = "nombreCortoPersona")
    @BusinessKey(include = Method.TO_STRING)
    private String nombreCorto;
    /**
     *
     */
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private String alias2;
    /**
     * Email de la persona
     */
    @Column
    @Email
    @BusinessKey
    private String email;
    /**
     * Web de la persona
     */
    @Column
    @BusinessKey
    private String web;
    /**
     * Rankin de persona
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Ranking ranking;
    /**
     * Tipo de contribullente
     * <p>
     * Ejemplo: Contribuyente: formal, ordinario
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private TipoContribuyente tipoContribuyente;
    /**
     * Fecha del ultimo balance de la persona
     */
    @Column
    @Temporal(javax.persistence.TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaUltimoBalance;
    /**
     * Es un ente gubernamental?
     */
    @Column
    @BusinessKey
    private Boolean gobierno;
    /**
     * Capacidad Economica de la persona
     */
    @ManyToOne()
    @BusinessKey
    private TipoCapacidadEconomica capacidadEconomica;
    /**
     * Actividad Economica de la persona
     */
    @ManyToOne()
    @BusinessKey
    private TipoActividadEconomica actividadEconomica;
    /**
     * Coleccion, tipo de persona
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<TipoPersona> tiposPersona = new HashSet<TipoPersona>(0);
    /**
     * Coleccion de observaciones de la persona
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Observacion> observaciones = new HashSet<Observacion>(0);
    /**
     *  Coleccion de telefonos de la persona
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<TelefonoPersona> telefonos = new HashSet<TelefonoPersona>(0);
    /**
     * Coleccion de direcciones de la persona
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<DireccionPersona> direcciones = new HashSet<DireccionPersona>(0);
    /**
     * Coleccion de documentos anexos de la persona
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Documento> documentos = new HashSet<Documento>(0);
    /**
     * Coleccion de cuentas bancarias de la persona
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<CuentaBancariaPersona> cuentasBancarias = new HashSet<CuentaBancariaPersona>(0);
    /**
     * Coleccion de sucursales de de la persona juridica
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @BusinessKey(exclude = Method.ALL)
    private Set<Sucursal> sucursales = new HashSet<Sucursal>(0);
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

    /**
     * Crea una instancia de Persona
     */
    public Persona() {
        auditoria = new AuditoriaBasica();
    }

    /**
     * Crea una instancia de Persona
     * @param rif 
     */
    public Persona(Rif rif) {
        this.rif = rif;
    }

    /**
     * Crea una instancia de Persona
     * @param rif
     * @param ranking
     * @param tipoContribuyente
     * @param fechaUltimoBalance
     * @param gobierno 
     */
    public Persona(Rif rif, Ranking ranking, TipoContribuyente tipoContribuyente, Date fechaUltimoBalance, Boolean gobierno) {
        this.gobierno = gobierno;
        this.rif = rif;
        this.ranking = ranking;
        this.tipoContribuyente = tipoContribuyente;
        this.fechaUltimoBalance = fechaUltimoBalance;
    }

    /**
     * Crea una instancia de Persona
     * @param rif
     * @param ranking
     * @param tipoContribuyente
     * @param fechaUltimoBalance
     * @param gobierno
     * @param capacidadEconomica
     * @param actividadEconomica 
     */
    public Persona(Rif rif, Ranking ranking, TipoContribuyente tipoContribuyente, Date fechaUltimoBalance, Boolean gobierno, TipoCapacidadEconomica capacidadEconomica, TipoActividadEconomica actividadEconomica) {
        this.gobierno = gobierno;
        this.rif = rif;
        this.ranking = ranking;
        this.tipoContribuyente = tipoContribuyente;
        this.fechaUltimoBalance = fechaUltimoBalance;
        this.capacidadEconomica = capacidadEconomica;
        this.actividadEconomica = actividadEconomica;
    }

    /**
     * Actividad Economica de la persona
     * @return the actividadEconomica
     */
    public TipoActividadEconomica getActividadEconomica() {
        return actividadEconomica;
    }

    /**
     *
     * @return the alias2
     */
    public String getAlias2() {
        return alias2;
    }

    /**
     *
     * @return the auditoria
     */
    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    /**
     * Capacidad Economica de la persona
     * @return the capacidadEconomica
     */
    public TipoCapacidadEconomica getCapacidadEconomica() {
        return capacidadEconomica;
    }

    /**
     * Codigo en Archivo
     * donde conseguir el archivo fisico
     * @return the codigoArchivo
     */
    public String getCodigoArchivo() {
        return codigoArchivo;
    }

    /**
     * Codigo ??
     * @return the codigoX
     */
    public String getCodigoX() {
        return codigoX;
    }

    /**
     * Coleccion de cuentas bancarias de la persona
     * @return the cuentasBancarias
     */
    public Set<CuentaBancariaPersona> getCuentasBancarias() {
        return cuentasBancarias;
    }

    /**
     * Coleccion de direcciones de la persona
     * @return the direcciones
     */
    public Set<DireccionPersona> getDirecciones() {
        return direcciones;
    }

    /**
     * Coleccion de documentos anexos de la persona
     * @return the documentos
     */
    public Set<Documento> getDocumentos() {
        return documentos;
    }

    /**
     * Email de la persona
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Fecha del ultimo balance de la persona
     * @return the fechaUltimoBalance
     */
    public Date getFechaUltimoBalance() {
        return fechaUltimoBalance;
    }

    /**
     * Es un ente gubernamental?
     * @return the gobierno
     */
    public Boolean getGobierno() {
        return gobierno;
    }

    /**
     * Pk autogenerado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Nombre Corto de la persona
     * @return the nombreCorto
     */
    public String getNombreCorto() {
        return nombreCorto;
    }

    /**
     * Nombre completo de la persona
     * Autogenerado si es persona natural
     * @return the nombreLargo
     */
    public String getNombreLargo() {
        return nombreLargo;
    }

    /**
     * Coleccion de observaciones de la persona
     * @return the observaciones
     */
    public Set<Observacion> getObservaciones() {
        return observaciones;
    }

    /**
     *
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * Rankin de persona
     * @return the ranking
     */
    public Ranking getRanking() {
        return ranking;
    }

    /**
     * Datos de la identificacion de la persona
     * @return the rif
     */
    public Rif getRif() {
        return rif;
    }

    /**
     * Coleccion de sucursales de de la persona juridica
     * @return the sucursales
     */
    public Set<Sucursal> getSucursales() {
        return sucursales;
    }

    /**
     * Coleccion de telefonos de la persona
     * @return the telefonos
     */
    public Set<TelefonoPersona> getTelefonos() {
        return telefonos;
    }

    /**
     * Tipo de contribullente
     * <p>
     * Ejemplo: Contribuyente: formal, ordinario
     * @return the tipoContribuyente
     */
    public TipoContribuyente getTipoContribuyente() {
        return tipoContribuyente;
    }

    /**
     * Coleccion, tipo de persona
     * @return the tiposPersona
     */
    public Set<TipoPersona> getTiposPersona() {
        return tiposPersona;
    }

    /**
     * Web de la persona
     * @return the web
     */
    public String getWeb() {
        return web;
    }

    /**
     * Actividad Economica de la persona
     * @param actividadEconomica the actividadEconomica to set
     */
    public void setActividadEconomica(TipoActividadEconomica actividadEconomica) {
        this.actividadEconomica = actividadEconomica;
    }

    /**
     *
     * @param alias2 the alias2 to set
     */
    public void setAlias2(String alias2) {
        this.alias2 = alias2;
    }

    /**
     *
     * @param auditoria the auditoria to set
     */
    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    /**
     * Capacidad Economica de la persona
     * @param capacidadEconomica the capacidadEconomica to set
     */
    public void setCapacidadEconomica(TipoCapacidadEconomica capacidadEconomica) {
        this.capacidadEconomica = capacidadEconomica;
    }

    /**
     * Codigo en Archivo
     * donde conseguir el archivo fisico
     * @param codigoArchivo the codigoArchivo to set
     */
    public void setCodigoArchivo(String codigoArchivo) {
        this.codigoArchivo = codigoArchivo;
    }

    /**
     * Codigo ??
     * @param codigoX the codigoX to set
     */
    public void setCodigoX(String codigoX) {
        this.codigoX = codigoX;
    }

    /**
     * Coleccion de cuentas bancarias de la persona
     * @param cuentasBancarias the cuentasBancarias to set
     */
    public void setCuentasBancarias(Set<CuentaBancariaPersona> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    /**
     * Coleccion de direcciones de la persona
     * @param direcciones the direcciones to set
     */
    public void setDirecciones(Set<DireccionPersona> direcciones) {
        this.direcciones = direcciones;
    }

    /**
     * Coleccion de documentos anexos de la persona
     * @param documentos the documentos to set
     */
    public void setDocumentos(Set<Documento> documentos) {
        this.documentos = documentos;
    }

    /**
     * Email de la persona
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Fecha del ultimo balance de la persona
     * @param fechaUltimoBalance the fechaUltimoBalance to set
     */
    public void setFechaUltimoBalance(Date fechaUltimoBalance) {
        this.fechaUltimoBalance = fechaUltimoBalance;
    }

    /**
     * Es un ente gubernamental?
     * @param gobierno the gobierno to set
     */
    public void setGobierno(Boolean gobierno) {
        this.gobierno = gobierno;
    }

    /**
     * Pk autogenerado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Nombre Corto de la persona
     * @param nombreCorto the nombreCorto to set
     */
    public void setNombreCorto(String nombreCorto) {
        this.nombreCorto = nombreCorto;
    }

    /**
     * Nombre completo de la persona
     * Autogenerado si es persona natural
     * @param nombreLargo the nombreLargo to set
     */
    public void setNombreLargo(String nombreLargo) {
        this.nombreLargo = nombreLargo;
    }

    /**
     * Coleccion de observaciones de la persona
     * @param observaciones the observaciones to set
     */
    public void setObservaciones(Set<Observacion> observaciones) {
        this.observaciones = observaciones;
    }

    /**
     *
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * Rankin de persona
     * @param ranking the ranking to set
     */
    public void setRanking(Ranking ranking) {
        this.ranking = ranking;
    }

    /**
     * Datos de la identificacion de la persona
     * @param rif the rif to set
     */
    public void setRif(Rif rif) {
        this.rif = rif;
    }

    /**
     * Coleccion de sucursales de de la persona juridica
     * @param sucursales the sucursales to set
     */
    public void setSucursales(Set<Sucursal> sucursales) {
        this.sucursales = sucursales;
    }

    /**
     * Coleccion de telefonos de la persona
     * @param telefonos the telefonos to set
     */
    public void setTelefonos(Set<TelefonoPersona> telefonos) {
        this.telefonos = telefonos;
    }

    /**
     * Tipo de contribullente
     * <p>
     * Ejemplo: Contribuyente: formal, ordinario
     * @param tipoContribuyente the tipoContribuyente to set
     */
    public void setTipoContribuyente(TipoContribuyente tipoContribuyente) {
        this.tipoContribuyente = tipoContribuyente;
    }

    /**
     * Coleccion, tipo de persona
     * @param tiposPersona the tiposPersona to set
     */
    public void setTiposPersona(Set<TipoPersona> tiposPersona) {
        this.tiposPersona = tiposPersona;
    }

    /**
     * Web de la persona
     * @param web the web to set
     */
    public void setWeb(String web) {
        this.web = web;
    }

    public String toString2() {
        return "Persona[ "
                + "id=" + id + ", "
                + "rif=" + rif.toString() + ", "
                + "ranking=" + ranking + ", "
                + "tipoContribuyente=" + tipoContribuyente + ", "
                + "fechaUltimoBalance=" + fechaUltimoBalance + ", "
                + "capacidadEconomica=" + capacidadEconomica.getId() + ", "
                + "actividadEconomica=" + actividadEconomica.getId() + "]";
    }
}
