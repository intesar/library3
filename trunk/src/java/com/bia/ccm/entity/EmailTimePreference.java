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
@Table(name = "email_time_preference")
@NamedQueries({
@NamedQuery(name = "EmailTimePreference.findByOrganization", query = "select s from EmailTimePreference s where s.organization = ?1  "),
@NamedQuery(name = "EmailTimePreference.findByOrganizationAndReportTime", query = "select s from EmailTimePreference s where s.organization = ?1 and s.reportTime = ?2 "),
@NamedQuery(name = "EmailTimePreference.findByReportTime", query = "SELECT s FROM EmailTimePreference s where s.reportTime = ?1 ")
})
public class EmailTimePreference implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "report_time", nullable = false)
    private short reportTime;
    @Column(name = "organization", nullable = false)
    private String organization;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;

    public EmailTimePreference() {
    }

    public EmailTimePreference(Integer id) {
        this.id = id;
    }

    public EmailTimePreference(Integer id, short reportTime, String organization) {
        this.id = id;
        this.reportTime = reportTime;
        this.organization = organization;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public short getReportTime() {
        return reportTime;
    }

    public void setReportTime(short reportTime) {
        this.reportTime = reportTime;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmailTimePreference)) {
            return false;
        }
        EmailTimePreference other = (EmailTimePreference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.EmailTimePreference[id=" + id + "]";
    }

}
