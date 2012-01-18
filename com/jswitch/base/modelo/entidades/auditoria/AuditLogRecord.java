

package com.jswitch.base.modelo.entidades.auditoria;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import com.jswitch.base.modelo.util.bean.BeanVO;
import com.jswitch.base.modelo.util.ehts.BusinessKey;
import com.jswitch.base.modelo.util.ehts.Method;
import java.util.ArrayList;
import javax.persistence.Table;

/**
 *
 * @author bc
 */
@Entity
@org.hibernate.annotations.AccessType("field")
@org.hibernate.annotations.Entity(mutable = false)
@Table(name="SYST_AudiLogRecord")
public class AuditLogRecord extends BeanVO implements Serializable {

    public AuditLogRecord() {
    }

    public AuditLogRecord(String message,
            Long entityId,
            String entityClass,
            String user2) {
        this.message = message;
        this.entityId = entityId;
        this.entityClass = entityClass;
        this.user2 = user2;
        this.created = new Date();
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @BusinessKey(include = Method.TO_STRING)
    private Long id;
    @Column
    @BusinessKey
    private String message;
    @Column
    @BusinessKey
    private Long entityId;
    @Column
    @BusinessKey
    private String entityClass;
    @Column
    @BusinessKey
    private String user2;
    @Column
    @BusinessKey
    private String userOS;
    @Column
    @BusinessKey
    private String ips;
    @Column
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @BusinessKey
    private Date created;
    @Column
    private ArrayList<String> nombres = new ArrayList<String>(0);
    @Column
    private ArrayList<Object> valores = new ArrayList<Object>(0);
//    @Column
//    private ArrayList<Type> typos = new ArrayList<Type>(0);

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<String> getNombres() {
        return nombres;
    }

    public void setNombres(ArrayList<String> nombres) {
        this.nombres = nombres;
    }

    public String getUser2() {
        return user2;
    }

    public void setUser2(String user2) {
        this.user2 = user2;
    }

    public ArrayList<Object> getValores() {
        return valores;
    }

    public void setValores(ArrayList<Object> valores) {
        this.valores = valores;
    }

    public String getEntityClass() {
        return entityClass;
    }

    public void setEntityClass(String entityClass) {
        this.entityClass = entityClass;
    }

    public String getUserOS() {
        return userOS;
    }

    public void setUserOS(String userOS) {
        this.userOS = userOS;
    }

    public String getIps() {
        return ips;
    }

    public void setIps(String ips) {
        this.ips = ips;
    }
    
}
