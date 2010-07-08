package com.opensource.roomate.entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author inmohamm
 */
@Entity
@Table(name = "subscribe_user")
@NamedQueries({
    @NamedQuery(name = "SubscribeUser.findAll", query = "SELECT s FROM SubscribeUser s"),
    @NamedQuery(name = "SubscribeUser.findById", query = "SELECT s FROM SubscribeUser s WHERE s.id = :id"),
    @NamedQuery(name = "SubscribeUser.findByEmail", query = "SELECT s FROM SubscribeUser s WHERE s.email = :email"),
    @NamedQuery(name = "SubscribeUser.findBySearchKey", query = "SELECT s FROM SubscribeUser s WHERE s.searchKey = :searchKey"),
    @NamedQuery(name = "SubscribeUser.findByCreateIp", query = "SELECT s FROM SubscribeUser s WHERE s.createIp = :createIp"),
    @NamedQuery(name = "SubscribeUser.findByCreateDate", query = "SELECT s FROM SubscribeUser s WHERE s.createDate = :createDate"),
    @NamedQuery(name = "SubscribeUser.findByDeleteIp", query = "SELECT s FROM SubscribeUser s WHERE s.deleteIp = :deleteIp"),
    @NamedQuery(name = "SubscribeUser.findByDeleteDate", query = "SELECT s FROM SubscribeUser s WHERE s.deleteDate = :deleteDate"),
    @NamedQuery(name = "SubscribeUser.findByActive", query = "SELECT s FROM SubscribeUser s WHERE s.active = :active")})
public class SubscribeUser implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "search_key")
    private String searchKey;
    @Column(name = "create_ip")
    private String createIp;
    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "delete_ip")
    private String deleteIp;
    @Column(name = "delete_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deleteDate;
    @Column(name = "active")
    private Short active;

    public SubscribeUser() {
    }

    public SubscribeUser(Integer id) {
        this.id = id;
    }

    public SubscribeUser(Integer id, String email, String searchKey, String createIp, Date createDate) {
        this.id = id;
        this.email = email;
        this.searchKey = searchKey;
        this.createIp = createIp;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDeleteIp() {
        return deleteIp;
    }

    public void setDeleteIp(String deleteIp) {
        this.deleteIp = deleteIp;
    }

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public Short getActive() {
        return active;
    }

    public void setActive(Short active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SubscribeUser)) {
            return false;
        }
        SubscribeUser other = (SubscribeUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opensource.roomate.entity.SubscribeUser[id=" + id + "]";
    }

}
