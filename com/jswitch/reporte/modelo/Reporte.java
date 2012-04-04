package com.jswitch.reporte.modelo;

import com.jswitch.base.modelo.Dominios.CategoriaReporte;
import com.jswitch.base.modelo.Dominios.FiltroReporte;
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
     * VERSION
     */
    @Version
    @Column
    @BusinessKey(exclude = Method.ALL)
    private Integer optLock;
    /**
     * CATEGORIA DEL REPORTE
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey(exclude = Method.EQUALS)
    private CategoriaReporte categoria;
    /**
     * TIPO DE PAPEL
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private String tipoPapel;
    /**
     * TIPO
     * 0 DETALLE
     * 1 RESUMEN
     * 2 ESTADISTICO
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private int tipo;
    /**
     * NONMBRE DEL ARCHIVO JASPER 
     */
    @Column
    @Size(min = 2, max = 200)
    @BusinessKey(include = Method.EQUALS)
    private String file;
    /**
     * TITULO DEL REPORTE 
     */
    @Column
    @Size(min = 2, max = 100)
    @BusinessKey(exclude = Method.EQUALS)
    private String titulo;
    /**
     * DESCRIPCION DEL FUNCIONAMIENTO DEL REPORTE
     */
    @Column
    @Size(max = 300)
    @BusinessKey(exclude = Method.EQUALS)
    private String observacion;
    /**
     * APLICA FILTRO OBLIGADO
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
     * true para utilizar la descripcion del FIELD en el reporte y no el nombre,
     * false para lo contrario.
     */
    @Column
    @BusinessKey(exclude = Method.EQUALS)
    private Boolean useFieldDescription;
    /**
     * hql or sql query
     */
    @Column
    @Size(min = 0, max = 3000)
    @BusinessKey(exclude = Method.EQUALS)
    private String baseSQL;
    /**
     *  FILTRADO POR
     */
    @Column
    @Enumerated(EnumType.STRING)
    @BusinessKey(exclude = Method.EQUALS)
    private FiltroReporte filtro;

    public Reporte() {
    }

    public Reporte(CategoriaReporte categoria, int tipo, String file, String titulo, String observacion, String baseSQL, String tipoPapel, Boolean filtroObligado, Boolean hql, Boolean enviarData, Boolean useFieldDescription) {
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
        this.useFieldDescription = useFieldDescription;
    }

    public Reporte(CategoriaReporte categoria, FiltroReporte filtro, int tipo, String file, String titulo, String observacion, String baseSQL, String tipoPapel, Boolean filtroObligado, Boolean hql, Boolean enviarData, Boolean useFieldDescription) {
        this.filtro = filtro;
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
        this.useFieldDescription = useFieldDescription;
    }

    /**
     * hql or sql query
     * @return the baseSQL
     */
    public String getBaseSQL() {
        return baseSQL;
    }

    /**
     * CATEGORIA DEL REPORTE
     * @return the categoria
     */
    public CategoriaReporte getCategoria() {
        return categoria;
    }

    /**
     * true para pasar la data al reporte,
     * false para pasar la conexion.
     * @return the enviarData
     */
    public Boolean getEnviarData() {
        return enviarData;
    }

    /**
     * NONMBRE DEL ARCHIVO JASPER
     * @return the file
     */
    public String getFile() {
        return file;
    }

    /**
     * FILTRADO POR
     * @return the filtro
     */
    public FiltroReporte getFiltro() {
        return filtro;
    }

    /**
     * APLICA FILTRO OBLIGADO
     * @return the filtroObligado
     */
    public Boolean getFiltroObligado() {
        return filtroObligado;
    }

    /**
     * True para hql, false para sql
     * @return the hql
     */
    public Boolean getHql() {
        return hql;
    }

    /**
     * PK autoincremtado
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * DESCRIPCION DEL FUNCIONAMIENTO DEL REPORTE
     * @return the observacion
     */
    public String getObservacion() {
        return observacion;
    }

    /**
     * VERSION
     * @return the optLock
     */
    public Integer getOptLock() {
        return optLock;
    }

    /**
     * TIPO
     * 0 DETALLE
     * 1 RESUMEN
     * 2 ESTADISTICO
     * @return the tipo
     */
    public int getTipo() {
        return tipo;
    }

    /**
     * TIPO DE PAPEL
     * @return the tipoPapel
     */
    public String getTipoPapel() {
        return tipoPapel;
    }

    /**
     * TITULO DEL REPORTE
     * @return the titulo
     */
    public String getTitulo() {
        return titulo;
    }

    /**
     * true para utilizar la descripcion del FIELD en el reporte y no el nombre,
     * false para lo contrario.
     * @return the useFieldDescription
     */
    public Boolean getUseFieldDescription() {
        return useFieldDescription;
    }

    /**
     * hql or sql query
     * @param baseSQL the baseSQL to set
     */
    public void setBaseSQL(String baseSQL) {
        this.baseSQL = baseSQL;
    }

    /**
     * CATEGORIA DEL REPORTE
     * @param categoria the categoria to set
     */
    public void setCategoria(CategoriaReporte categoria) {
        this.categoria = categoria;
    }

    /**
     * true para pasar la data al reporte,
     * false para pasar la conexion.
     * @param enviarData the enviarData to set
     */
    public void setEnviarData(Boolean enviarData) {
        this.enviarData = enviarData;
    }

    /**
     * NONMBRE DEL ARCHIVO JASPER
     * @param file the file to set
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * FILTRADO POR
     * @param filtro the filtro to set
     */
    public void setFiltro(FiltroReporte filtro) {
        this.filtro = filtro;
    }

    /**
     * APLICA FILTRO OBLIGADO
     * @param filtroObligado the filtroObligado to set
     */
    public void setFiltroObligado(Boolean filtroObligado) {
        this.filtroObligado = filtroObligado;
    }

    /**
     * True para hql, false para sql
     * @param hql the hql to set
     */
    public void setHql(Boolean hql) {
        this.hql = hql;
    }

    /**
     * PK autoincremtado
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * DESCRIPCION DEL FUNCIONAMIENTO DEL REPORTE
     * @param observacion the observacion to set
     */
    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    /**
     * VERSION
     * @param optLock the optLock to set
     */
    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    /**
     * TIPO
     * 0 DETALLE
     * 1 RESUMEN
     * 2 ESTADISTICO
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    /**
     * TIPO DE PAPEL
     * @param tipoPapel the tipoPapel to set
     */
    public void setTipoPapel(String tipoPapel) {
        this.tipoPapel = tipoPapel;
    }

    /**
     * TITULO DEL REPORTE
     * @param titulo the titulo to set
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /**
     * true para utilizar la descripcion del FIELD en el reporte y no el nombre,
     * false para lo contrario.
     * @param useFieldDescription the useFieldDescription to set
     */
    public void setUseFieldDescription(Boolean useFieldDescription) {
        this.useFieldDescription = useFieldDescription;
    }

}
