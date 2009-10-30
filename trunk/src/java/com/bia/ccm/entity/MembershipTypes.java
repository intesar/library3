/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author intesar
 */
@Entity
@Table(name = "membership_types")
@NamedQueries({
    @NamedQuery(name = "MembershipTypes.findById", query = "SELECT m FROM MembershipTypes m WHERE m.id = :id"),
    @NamedQuery(name = "MembershipTypes.findByOrganization", query = "SELECT m FROM MembershipTypes m WHERE m.organization.name = ?1 "),
    @NamedQuery(name = "MembershipTypes.findByFee", query = "SELECT m FROM MembershipTypes m WHERE m.fee = :fee"),
    @NamedQuery(name = "MembershipTypes.findByDaysValidFor", query = "SELECT m FROM MembershipTypes m WHERE m.daysValidFor = :daysValidFor"),
    @NamedQuery(name = "MembershipTypes.findByCreateDate", query = "SELECT m FROM MembershipTypes m WHERE m.createDate = :createDate"),
    @NamedQuery(name = "MembershipTypes.findByCreateUser", query = "SELECT m FROM MembershipTypes m WHERE m.createUser = :createUser"),
    @NamedQuery(name = "MembershipTypes.findByOrganizationAndName", query = "SELECT m FROM MembershipTypes m WHERE m.organization.name = ?1 AND m.name = ?2")
})
public class MembershipTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "fee", nullable = false)
    private double fee;
    @Column(name = "days_valid_for", nullable = false)
    private int daysValidFor;
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "create_user", nullable = false)
    private String createUser;   
    @JoinColumn(name = "organization", referencedColumnName = "name")
    @ManyToOne
    private Organization organization;

    public MembershipTypes() {
    }

    public MembershipTypes(Integer id) {
        this.id = id;
    }

    public MembershipTypes(Integer id, Organization organization, double fee, int daysValidFor, Date createDate, String createUser) {
        this.id = id;
        this.organization = organization;
        this.fee = fee;
        this.daysValidFor = daysValidFor;
        this.createDate = createDate;
        this.createUser = createUser;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }

    public int getDaysValidFor() {
        return daysValidFor;
    }

    public void setDaysValidFor(int daysValidFor) {
        this.daysValidFor = daysValidFor;
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

//    public Collection<MembershipDiscounts> getMembershipDiscountsCollection() {
//        return membershipDiscountsCollection;
//    }
//
//    public void setMembershipDiscountsCollection(Collection<MembershipDiscounts> membershipDiscountsCollection) {
//        this.membershipDiscountsCollection = membershipDiscountsCollection;
//    }

  

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof MembershipTypes)) {
            return false;
        }
        MembershipTypes other = (MembershipTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.MembershipTypes[id=" + id + "]";
    }
}
