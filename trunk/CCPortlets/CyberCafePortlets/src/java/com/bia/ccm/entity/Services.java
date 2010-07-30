/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.entity;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 *
 * @author intesar
 */
@Entity
@Table(name = "services")
@NamedQueries({
    @NamedQuery(name = "Services.findByName", query = "SELECT s FROM Services s WHERE s.name = :name"),
    @NamedQuery(name = "Services.findByUnitPrice", query = "SELECT s FROM Services s WHERE s.unitPrice = :unitPrice"),
    @NamedQuery(name = "Services.findByDescription", query = "SELECT s FROM Services s WHERE s.description = :description"),
    @NamedQuery(name = "Services.findByOrganization", query = "SELECT s FROM Services s WHERE s.organization = ?1 "),
    @NamedQuery(name = "Services.findById", query = "SELECT s FROM Services s WHERE s.id = :id")
})
public class Services implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;
    @Column(name = "description")
    private String description;
    @Column(name = "organization", nullable = false)
    private String organization;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "sale_two_enabled", nullable = false)
    private boolean saleTwoEnabled;
    @Column(name = "sale_two_units")
    private Integer saleTwoUnits;
    @Column(name = "sale_two_price")
    private Double saleTwoPrice;
    @Column(name = "units", nullable = false)
    private Integer units = 1;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "last_modified_user")
    private String lastModifiedUser;
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.DATE)
    private Date lastModifiedDate;
    @Column(name = "ip")
    private String ip;
    @Version
    private int version;

    public Services() {
    }

    public Services(Integer id) {
        this.id = id;
    }

    public Services(Integer id, String name, double unitPrice, String organization) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.organization = organization;
    }

    public Services(Integer id, String name, double unitPrice, String organization, String createUser, Date createDate, String ip) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.organization = organization;
        this.createUser = createUser;
        this.createDate = createDate;
        this.ip = ip;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isSaleTwoEnabled() {
        return saleTwoEnabled;
    }

    public void setSaleTwoEnabled(boolean saleTwoEnabled) {
        this.saleTwoEnabled = saleTwoEnabled;
    }

    public Double getSaleTwoPrice() {
        return saleTwoPrice;
    }

    public void setSaleTwoPrice(Double saleTwoPrice) {
        this.saleTwoPrice = saleTwoPrice;
    }

    public Integer getSaleTwoUnits() {
        return saleTwoUnits;
    }

    public void setSaleTwoUnits(Integer saleTwoUnits) {
        this.saleTwoUnits = saleTwoUnits;
    }

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(String lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }


    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }



    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Services)) {
            return false;
        }
        Services other = (Services) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.Services[id=" + id + "]";
    }
}
