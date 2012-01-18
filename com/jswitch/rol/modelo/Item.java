package com.jswitch.rol.modelo;

import com.jswitch.base.modelo.entidades.auditoria.Auditable;
import com.jswitch.base.modelo.entidades.auditoria.AuditoriaBasica;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.validation.constraints.Size;
import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * @author Luis Adrian Gonzalez Benavides
 */
@Entity
@Table(name="SYST_Item")
public class Item extends BeanVO implements Serializable, Auditable {

    public Item() {
    }

    public Item(String nombre, String funcionId, String icono, String metodo, AuditoriaBasica auditoriaBasica) {
        this.nombre = nombre;
        this.icono = icono;
        this.funcionId = funcionId;
        this.metodo = metodo;
        this.auditoria = auditoriaBasica;
    }

    public Item(String nombre, String icono, AuditoriaBasica auditoriaBasica) {
        this.nombre = nombre;
        this.icono = icono;
        this.auditoria = auditoriaBasica;

    }
    /**
     *
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
    @Size(min = 4, max = 100)
    @BusinessKey
    private String nombre;
    /**
     *
     */
    @Column
    @Size(min = 0, max = 100)
    @BusinessKey
    private String icono;
    /**
     *
     */
    @Column
    @Size(min = 0, max = 100)
    @BusinessKey
    private String funcionId;
    /**
     *
     */
    @Column
    @Size(min = 0, max = 100)
    @BusinessKey
    private String metodo;
    /**
     */
    @Embedded
    @BusinessKey
    private AuditoriaBasica auditoria;
    /**
     *
     */
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @BusinessKey(exclude = Method.ALL)
    private List<Item> items = new ArrayList<Item>(0);
    /**
     *
     */
    @Version
    @Column
    private Integer optLock;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AuditoriaBasica getAuditoria() {
        return auditoria;
    }

    public void setAuditoria(AuditoriaBasica auditoria) {
        this.auditoria = auditoria;
    }

    public Integer getOptLock() {
        return optLock;
    }

    public void setOptLock(Integer optLock) {
        this.optLock = optLock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFuncionId() {
        return funcionId;
    }

    public void setFuncionId(String funcionId) {
        this.funcionId = funcionId;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getMetodo() {
        return metodo;
    }

    public void setMetodo(String metodo) {
        this.metodo = metodo;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return nombre;
    }
//    public int compareTo(Item o) {
//
//        if(getId()>o.getId())
//            return -1;
//        if(getId()==o.getId())
//            return 0;
//        if(getId()<o.getId())
//            return 1;
//        return -1;
//    }
}
