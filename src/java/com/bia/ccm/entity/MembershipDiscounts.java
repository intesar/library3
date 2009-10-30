/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
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
