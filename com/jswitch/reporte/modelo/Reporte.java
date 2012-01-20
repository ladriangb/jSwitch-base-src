package com.jswitch.reporte.modelo;

import com.jswitch.base.modelo.Dominios.CategoriaReporte;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author bc
 */
@Entity
@Table(name = "SYST_Reporte")
public class Reporte extends BeanVO implements Serializable {

    /**
     *  PK autoincremtado
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(exclude = Method.ALL)
    private Long id;
    /**
     */
    @Version
    @Column
    @BusinessKey(exclude = Method.ALL)
    private Integer optLock;
    /**
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey(exclude = Method.EQUALS)
    private CategoriaReporte categoria;
    /**
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private String tipoPapel;
    /**
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private int tipo;
    /**
     */
    @Column
    @Size(min = 2, max = 200)
    @BusinessKey(include = Method.EQUALS)
    private String file;
    /**
     */
    @Column
    @Size(min = 2, max = 100)
    @BusinessKey(exclude = Method.EQUALS)
    private String titulo;
    /**
     */
    @Column
    @Size(max = 300)
    @BusinessKey(exclude = Method.EQUALS)
    private String observacion;
    /**
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private Boolean filtroObligado;
    /**
     * True para hql, false para sql
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private Boolean hql;
    /**
     * true para pasar la data al reporte,
     * false para pasar la conexion.
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private Boolean enviarData;
    /**
     */
    @Column
    @Size(min = 0, max = 3000)
    @BusinessKey(exclude = Method.EQUALS)
    private String baseSQL;

    public Reporte() {
    }

    public Reporte(CategoriaReporte categoria, int tipo, String file, String titulo, String observacion, String baseSQL, String tipoPapel, Boolean filtroObligado, Boolean hql, Boolean enviarData) {
        this.categoria = categoria;
        this.tipo = tipo;
        this.file = file;
        this.titulo = titulo;
        this.observacion = observacion;
        this.baseSQL = baseSQL;
        this.filtroObligado = filtroObligado;
        this.tipoPapel = tipoPapel;
        this.hql = hql;
        this.enviarData = enviarData;
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

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public CategoriaReporte getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaReporte categoria) {
        this.categoria = categoria;
    }

    public String getBaseSQL() {
        return baseSQL;
    }

    public void setBaseSQL(String baseSQL) {
        this.baseSQL = baseSQL;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public String getTipoPapel() {
        return tipoPapel;
    }

    public void setTipoPapel(String tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    public Boolean getFiltroObligado() {
        return filtroObligado;
    }

    public void setFiltroObligado(Boolean filtroObligado) {
        this.filtroObligado = filtroObligado;
    }

    /**
     * @return true para hql, false para sql     * 
     */
    public Boolean getHql() {
        return hql;
    }

    /**
     * @param true para hql, false para sql
     */
    public void setHql(Boolean hql) {
        this.hql = hql;
    }

    /**
     * @return true para pasar la data al reporte,
     * false para pasar la conexion.
     * 
     */
    public Boolean getEnviarData() {
        return enviarData;
    }

    /**
     * @param true para pasar la data al reporte,
     * false para pasar la conexion.
     */
    public void setEnviarData(Boolean enviarData) {
        this.enviarData = enviarData;
    }
}
