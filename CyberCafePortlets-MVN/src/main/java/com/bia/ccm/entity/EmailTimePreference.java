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
@Table(name = "email_time_preference")
@NamedQueries({
    @NamedQuery(name = "EmailTimePreference.findByOrganization", query = "select s from EmailTimePreference s where s.organization = ?1  "),
    @NamedQuery(name = "EmailTimePreference.findByOrganizationAndReportTime", query = "select s from EmailTimePreference s where s.organization = ?1 and s.reportTime = ?2 "),
    @NamedQuery(name = "EmailTimePreference.findByReportTime", query = "SELECT s FROM EmailTimePreference s where s.reportTime = ?1 ")
})
public class EmailTimePreference extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "report_time", nullable = false)
    private short reportTime;
    @Column(name = "organization", nullable = false)
    private long organization;

    public EmailTimePreference() {
    }

    public EmailTimePreference(short reportTime, long organization) {
        this.reportTime = reportTime;
        this.organization = organization;
    }

    public short getReportTime() {
        return reportTime;
    }

    public void setReportTime(short reportTime) {
        this.reportTime = reportTime;
    }

    public long getOrganization() {
        return organization;
    }

    public void setOrganization(long organization) {
        this.organization = organization;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
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
