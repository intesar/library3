/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
@Table(name = "memberships")
@NamedQueries({
    @NamedQuery(name = "Memberships.findById", query = "SELECT m FROM Memberships m WHERE m.id = :id"),
    @NamedQuery(name = "Memberships.findByStartDate", query = "SELECT m FROM Memberships m WHERE m.startDate = :startDate"),
    @NamedQuery(name = "Memberships.findByExpirationDate", query = "SELECT m FROM Memberships m WHERE m.expirationDate = :expirationDate"),
    @NamedQuery(name = "Memberships.findByCreateUser", query = "SELECT m FROM Memberships m WHERE m.createUser = :createUser"),
    @NamedQuery(name = "Memberships.findByCreateDate", query = "SELECT m FROM Memberships m WHERE m.createDate = :createDate"),
    @NamedQuery(name = "Memberships.findByEmail", query = "SELECT m FROM Memberships m WHERE m.email = :email"),
    @NamedQuery(name = "Memberships.findByOrganizationAndEmail", query = "SELECT m FROM Memberships m WHERE m.organization = ?1 AND m.email = ?2 "),
    @NamedQuery(name = "Memberships.findByOrganizationAndEmailAndActive", query = "SELECT m FROM Memberships m WHERE m.organization = ?1 AND m.email = ?2 AND m.isActive = true"),
    @NamedQuery(name = "Memberships.findByOrganization", query = "SELECT m FROM Memberships m WHERE m.organization = ?1")
})
public class Memberships implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "start_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date expirationDate;
    @Column(name = "create_user", nullable = false)
    private String createUser;
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "is_active", nullable = false)
    private boolean isActive;
    @Column(name = "organization")
    private String organization;
    @Column(name = "membership_type")
    private Integer membershipType;
    @Column(name = "membership_type_name")
    private String membershipTypeString;
    @Transient
    private String startDateString;
    @Transient
    private String expirationDateString;
    @Column(name = "ip")
    private String ip;
    @Version
    private int version;

    public Memberships() {
    }

    public Memberships(Integer id) {
        this.id = id;
    }

    public Memberships(Integer id, Date startDate, Date expirationDate, String createUser, Date createDate, String email) {
        this.id = id;
        this.startDate = startDate;
        this.expirationDate = expirationDate;
        this.createUser = createUser;
        this.createDate = createDate;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrganization() {
        return organization;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public Integer getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(Integer membershipType) {
        this.membershipType = membershipType;
    }

    public String getMembershipTypeString() {
        return membershipTypeString;
    }

    public void setMembershipTypeString(String membershipTypeString) {
        this.membershipTypeString = membershipTypeString;
    }

    public String getExpirationDateString() {
        String pattern = "yyyy.MM.dd hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (this.expirationDate == null) {
            return "";
        }
        return sdf.format(this.expirationDate);
    }

    public void setExpirationDateString(String expirationDateString) {
        this.expirationDateString = expirationDateString;
    }

    public String getStartDateString() {
        String pattern = "yyyy.MM.dd hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (this.startDate == null) {
            return "";
        }
        return sdf.format(this.startDate);
    }

    public void setStartDateString(String startDateString) {
        this.startDateString = startDateString;
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
        if (!(object instanceof Memberships)) {
            return false;
        }
        Memberships other = (Memberships) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.Memberships[id=" + id + "]";
    }
}
