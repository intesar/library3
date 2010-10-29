/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.opensource.roomate.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "contact_us")
@NamedQueries({
    @NamedQuery(name = "ContactUs.findAll", query = "SELECT c FROM ContactUs c"),
    @NamedQuery(name = "ContactUs.findById", query = "SELECT c FROM ContactUs c WHERE c.id = :id"),
    @NamedQuery(name = "ContactUs.findByName", query = "SELECT c FROM ContactUs c WHERE c.name = :name"),
    @NamedQuery(name = "ContactUs.findByEmail", query = "SELECT c FROM ContactUs c WHERE c.email = :email"),
    @NamedQuery(name = "ContactUs.findByType", query = "SELECT c FROM ContactUs c WHERE c.type = :type"),
    @NamedQuery(name = "ContactUs.findByDate", query = "SELECT c FROM ContactUs c WHERE c.date = :date"),
    @NamedQuery(name = "ContactUs.findByRemoteIp", query = "SELECT c FROM ContactUs c WHERE c.remoteIp = :remoteIp")})
public class ContactUs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @Column(name = "type_")
    private String type;
    @Lob
    @Column(name = "comment")
    private String comment;
    @Basic(optional = false)
    @Column(name = "date_")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Basic(optional = false)
    @Column(name = "remote_ip")
    private String remoteIp;

    public ContactUs() {
    }

    public ContactUs(Integer id) {
        this.id = id;
    }

    public ContactUs(Integer id, String type, Date date, String remoteIp) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.remoteIp = remoteIp;
    }

    public ContactUs( String name, String email, String type, String comment, Date date, String remoteAddr) {
        this.name = name;
        this.email = email;
        this.type = type;
        this.comment = comment;
        this.date = date;
        this.remoteIp = remoteAddr;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof ContactUs)) {
            return false;
        }
        ContactUs other = (ContactUs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opensource.roomate.entity.ContactUs[id=" + id + "]";
    }

}
