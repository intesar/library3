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
@Table(name = "email_preference")
@NamedQueries({
    @NamedQuery(name = "EmailPreference.findByOrganization", query = "select s from EmailPreference s where s.organization = ?1 ")
})
public class EmailPreference extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "organization", nullable = false)
    private long organization;

    public EmailPreference() {
    }

    public EmailPreference(Long id) {
        this.id = id;
    }

    public EmailPreference(String email, long organization) {
        this.email = email;
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof EmailPreference)) {
            return false;
        }
        EmailPreference other = (EmailPreference) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.EmailPreference[id=" + id + "]";
    }
}
