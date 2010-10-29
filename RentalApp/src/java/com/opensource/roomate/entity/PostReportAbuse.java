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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "post_report_abuse")
@NamedQueries({
    @NamedQuery(name = "PostReportAbuse.findAll", query = "SELECT p FROM PostReportAbuse p"),
    @NamedQuery(name = "PostReportAbuse.findById", query = "SELECT p FROM PostReportAbuse p WHERE p.id = :id"),
    @NamedQuery(name = "PostReportAbuse.findByReportType", query = "SELECT p FROM PostReportAbuse p WHERE p.reportType = :reportType"),
    @NamedQuery(name = "PostReportAbuse.findByReportIp", query = "SELECT p FROM PostReportAbuse p WHERE p.reportIp = :reportIp"),
    @NamedQuery(name = "PostReportAbuse.findByPostId", query = "SELECT p FROM PostReportAbuse p WHERE p.post.id = :postId"),
    @NamedQuery(name = "PostReportAbuse.findByReportDate", query = "SELECT p FROM PostReportAbuse p WHERE p.reportDate = :reportDate")})
public class PostReportAbuse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "report_type")
    private String reportType;
    @Basic(optional = false)
    @Column(name = "report_ip")
    private String reportIp;
    @Basic(optional = false)
    @Column(name = "report_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date reportDate;
    @JoinColumn(name = "post", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Post post;

    public PostReportAbuse() {
    }

    public PostReportAbuse(Integer id) {
        this.id = id;
    }

    public PostReportAbuse(Integer id, String reportType, String reportIp, Date reportDate, Post post) {
        this.id = id;
        this.reportType = reportType;
        this.reportIp = reportIp;
        this.reportDate = reportDate;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getReportIp() {
        return reportIp;
    }

    public void setReportIp(String reportIp) {
        this.reportIp = reportIp;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof PostReportAbuse)) {
            return false;
        }
        PostReportAbuse other = (PostReportAbuse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opensource.roomate.entity.PostReportAbuse[id=" + id + "]";
    }
}
