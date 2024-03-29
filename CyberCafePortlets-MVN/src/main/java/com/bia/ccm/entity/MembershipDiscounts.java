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

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author intesar
 */
@Entity
@Table(name = "membership_discounts")
@NamedQueries({
    @NamedQuery(name = "MembershipDiscounts.findById", query = "SELECT m FROM MembershipDiscounts m WHERE m.id = :id"),
    @NamedQuery(name = "MembershipDiscounts.findByService", query = "SELECT m FROM MembershipDiscounts m WHERE m.service = :service"),
    @NamedQuery(name = "MembershipDiscounts.findByDiscountPercentage", query = "SELECT m FROM MembershipDiscounts m WHERE m.discountPercentage = :discountPercentage"),
    @NamedQuery(name = "MembershipDiscounts.findByCreateDate", query = "SELECT m FROM MembershipDiscounts m WHERE m.createDate = :createDate"),
    @NamedQuery(name = "MembershipDiscounts.findByCreateUser", query = "SELECT m FROM MembershipDiscounts m WHERE m.createUser = :createUser"),
    @NamedQuery(name = "MembershipDiscounts.findByMembershipTypesId", query = "SELECT m FROM MembershipDiscounts m WHERE m.membershipType.id = ?1 "),
    @NamedQuery(name = "MembershipDiscounts.findByMembershipTypesIdAndService", query = "SELECT m FROM MembershipDiscounts m WHERE m.membershipType.id = ?1 and m.service = ?2")
})
public class MembershipDiscounts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "service", nullable = false)
    private String service;
    @Column(name = "discount_percentage", nullable = false)
    private double discountPercentage;
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "create_user", nullable = false)
    private String createUser;
    @JoinColumn(name = "membership_type", referencedColumnName = "id")
    @ManyToOne
    private MembershipTypes membershipType;
    @Column(name = "ip")
    private String ip;
    @Version
    private int version;

    public MembershipDiscounts() {
    }

    public MembershipDiscounts(Integer id) {
        this.id = id;
    }

    public MembershipDiscounts(Integer id, String service, double discountPercentage, Date createDate, String createUser) {
        this.id = id;
        this.service = service;
        this.discountPercentage = discountPercentage;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    public MembershipTypes getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(MembershipTypes membershipType) {
        this.membershipType = membershipType;
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
        if (!(object instanceof MembershipDiscounts)) {
            return false;
        }
        MembershipDiscounts other = (MembershipDiscounts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.MembershipDiscounts[id=" + id + "]";
    }
}
