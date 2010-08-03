/*
 *
 * Author: Intesar Shannan Mohammed
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.entity;

import com.bizintelapps.easydao.dao.BaseModel;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

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
    @NamedQuery(name = "Services.findById", query = "SELECT s FROM Services s WHERE s.id = ?1")
})
public class Services extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "organization", nullable = false)
    private long organization;
    @Column(name = "units", nullable = false)
    private Integer units = 1;
    @Column(name = "unit_price", nullable = false)
    private double unitPrice;
    @Column(name = "sale_two_enabled", nullable = false)
    private boolean saleTwoEnabled;
    @Column(name = "sale_two_units")
    private Integer saleTwoUnits;
    @Column(name = "sale_two_price")
    private Double saleTwoPrice;

    public Services() {
    }

    public Services(Long id, String name, String description, long organization, Integer units, double unitPrice,
            Integer saleTwoUnits, Double saleTwoPrice) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.organization = organization;
        this.units = units;
        this.unitPrice = unitPrice;
        this.saleTwoUnits = saleTwoUnits;
        this.saleTwoPrice = saleTwoPrice;
    }

    public Services(Long id, String name, double unitPrice, long organization) {
        this.id = id;
        this.name = name;
        this.unitPrice = unitPrice;
        this.organization = organization;
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

    public long getOrganization() {
        return organization;
    }

    public void setOrganization(long organization) {
        this.organization = organization;
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
