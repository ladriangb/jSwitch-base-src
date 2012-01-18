package com.jswitch.persona.modelo.maestra;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import com.jswitch.persona.modelo.dominio.TipoActividadEconomica;
import com.jswitch.persona.modelo.dominio.TipoCapacidadEconomica;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import com.jswitch.base.modelo.Dominios.EstadoCivil;
import com.jswitch.base.modelo.Dominios.Ranking;
import com.jswitch.base.modelo.Dominios.Sexo;
import com.jswitch.base.modelo.Dominios.TipoContribuyente;
import com.jswitch.base.modelo.Dominios.TipoNombre;
import java.util.Calendar;
import javax.persistence.Table;

/**
 *  Clase Maestra de Personas Naturales
 *  @version 1.0 22/05/2009
 *  @since JDK 1.5
 *  @see Persona
 * @author Orlando Becerra
 * @author Nelson Moncada
 */
@Entity
@EntityListeners(value = {PersonaNaturalEvents.class})
@Table(name = "PERS_PersonaNatural")
public class PersonaNatural extends Persona {

    /**
     * Tipo de Nombre. Ejm: Sr., Dc., Ing., Lic.,
     */
    @Column
    @Enumerated(EnumType.STRING)
    private TipoNombre tipoNombre;
    /**
     * Primer Apellido de la persona natural
     */
    @Column
    @Size(min = 2, max = 40)
    private String primerApellido;
    /**
     * Segundo Apellido de la persona natural
     */
    @Column
    @Size(max = 40)
    private String segundoApellido;
    /**
     * Primer Nombre de la persona natural
     */
    @Column
    @Size(min = 2, max = 40)
    private String primerNombre;
    /**
     * Segundo Nombre de la persona natural
     */
    @Column
    @Size(max = 40)
    private String segundoNombre;
    /**
     *  Nombre de Casada
     */
    @Column
    @BusinessKey
    private String nombreCasada;
    /**
     * Sexo de la persona natural
     */
    @Column
    @NotNull
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private Sexo sexo;
    /**
     *  Estado civil de la persona natural
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey
    private EstadoCivil estadoCivil;
    /**
     * Fecha de nacimiento de la persona natural
     */
    @Column
    @Temporal(value = TemporalType.DATE)
    @Past
    @BusinessKey
    private Date fechaNacimiento;
    /**
     * Ciudad de nacimiento de la persona natural
     */
    @Column
    @BusinessKey
    private String ciudadNacimiento;
    /**
     * Estado de nacimiento de la persona natural
     */
    @Column
    @BusinessKey
    private String estadoNacimiento;
    /**
     * Profecion de la persona natural
     */
    @Column
    @BusinessKey
    private String profecion;
    /**
     * Profecion de la persona natural
     */
    @Column
    @BusinessKey
    private Float estatura;
    /**
     * Profecion de la persona natural
     */
    @Column
    @BusinessKey
    private Float peso;
    /**
     * ocupacion de la persona natural
     */
    @Column
    @BusinessKey
    private String ocupacion;
    @Transient
    private Integer edad;

    public PersonaNatural() {
    }

    public PersonaNatural(Rif rif, TipoNombre tipoNombre, Ranking ranking, TipoContribuyente tipoContribuyente, Date fechaUltimoBalance, Boolean gobierno, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre, String nombreCasada, Sexo sexo, EstadoCivil estadoCivil, Date fechaNacimiento, String ciudadNacimiento, String estadoNacimiento, String profecion, String ocupacion) {
        super(rif, ranking, tipoContribuyente, fechaUltimoBalance, gobierno);
        this.tipoNombre = tipoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.nombreCasada = nombreCasada;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = this.getEdad();
        this.ciudadNacimiento = ciudadNacimiento;
        this.estadoNacimiento = estadoNacimiento;
        this.profecion = profecion;
        this.ocupacion = ocupacion;
    }

    public PersonaNatural(Rif rif, TipoNombre tipoNombre, Ranking ranking, TipoContribuyente tipoContribuyente, Date fechaUltimoBalance, Boolean gobierno, TipoCapacidadEconomica capacidadEconomica, TipoActividadEconomica actividadEconomica, String primerApellido, String segundoApellido, String primerNombre, String segundoNombre, String nombreCasada, Sexo sexo, EstadoCivil estadoCivil, Date fechaNacimiento, String ciudadNacimiento, String estadoNacimiento, String profecion, String ocupacion) {
        super(rif, ranking, tipoContribuyente, fechaUltimoBalance, gobierno, capacidadEconomica, actividadEconomica);
        this.tipoNombre = tipoNombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.primerNombre = primerNombre;
        this.segundoNombre = segundoNombre;
        this.nombreCasada = nombreCasada;
        this.sexo = sexo;
        this.estadoCivil = estadoCivil;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = this.getEdad();
        this.ciudadNacimiento = ciudadNacimiento;
        this.estadoNacimiento = estadoNacimiento;
        this.profecion = profecion;
        this.ocupacion = ocupacion;
    }

    public String getCiudadNacimiento() {
        return ciudadNacimiento;
    }

    @BusinessKey
    public TipoNombre getTipoNombre() {
        return tipoNombre;
    }

    public void setTipoNombre(TipoNombre tipoNombre) {
        this.tipoNombre = tipoNombre;
    }

    public void setCiudadNacimiento(String ciudadNacimiento) {
        this.ciudadNacimiento = ciudadNacimiento;
    }

    public String getEstadoNacimiento() {
        return estadoNacimiento;
    }

    public EstadoCivil getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(EstadoCivil estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public void setEstadoNacimiento(String estadoNacimiento) {
        this.estadoNacimiento = estadoNacimiento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreCasada() {
        return nombreCasada;
    }

    public void setNombreCasada(String nombreCasada) {
        this.nombreCasada = nombreCasada;
    }

    public String getOcupacion() {
        return ocupacion;
    }

    public void setOcupacion(String ocupacion) {
        this.ocupacion = ocupacion;
    }

    @Size(min = 2, max = 40)
    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getPrimerNombre() {
        return primerNombre;
    }

    public void setPrimerNombre(String primerNombre) {
        this.primerNombre = primerNombre;
    }

    public String getProfecion() {
        return profecion;
    }

    public void setProfecion(String profecion) {
        this.profecion = profecion;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getSegundoNombre() {
        return segundoNombre;
    }

    public void setSegundoNombre(String segundoNombre) {
        this.segundoNombre = segundoNombre;
    }

    @Override
    public String toString2() {
        return "PersonaNatural[ "
                + super.toString2() + ", "
                + "primerApellido=" + primerApellido + ", "
                + "segundoApellido=" + segundoApellido + ", "
                + "primerNombre=" + primerNombre + ", "
                + "segundoNombre=" + segundoNombre + ", "
                + "nombreCasada=" + nombreCasada + ", "
                + "sexo=" + sexo + ", "
                + "estadoCivil=" + estadoCivil + ", "
                + "fechaNacimiento=" + fechaNacimiento + ", "
                + "ciudadNacimiento=" + ciudadNacimiento + ", "
                + "estadoNacimiento=" + estadoNacimiento + ", "
                + "profecion=" + profecion + ", "
                + "ocupacion=" + ocupacion + "]";
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        if (edad == null) {
            if (fechaNacimiento != null) {
                Calendar hoy = Calendar.getInstance();
                Calendar nac = Calendar.getInstance();

                nac.setTime(fechaNacimiento);


                int yh = hoy.get(Calendar.YEAR), yn = nac.get(Calendar.YEAR);
                int mh = hoy.get(Calendar.MONTH), mn = nac.get(Calendar.MONTH);
                int dh = hoy.get(Calendar.DAY_OF_MONTH), dn = nac.get(Calendar.DAY_OF_MONTH);
                edad = yh - yn;
                if ((mh - mn) < 0) {
                    edad--;
                } else if ((mh == mn) && (dh - dn) < 0) {
                    edad--;
                }

            }
        }
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Float getEstatura() {
        return estatura;
    }

    public void setEstatura(Float estatura) {
        this.estatura = estatura;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }
}
