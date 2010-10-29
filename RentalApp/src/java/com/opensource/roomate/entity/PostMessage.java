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
import javax.persistence.Lob;
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
@Table(name = "post_message")
@NamedQueries({
    @NamedQuery(name = "PostMessage.findAll", query = "SELECT p FROM PostMessage p"),
    @NamedQuery(name = "PostMessage.findById", query = "SELECT p FROM PostMessage p WHERE p.id = :id"),
    @NamedQuery(name = "PostMessage.findByNotified", query = "SELECT p FROM PostMessage p WHERE p.notified = :notified"),
    @NamedQuery(name = "PostMessage.findBySendTime", query = "SELECT p FROM PostMessage p WHERE p.sendTime = :sendTime")})
public class PostMessage implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Lob
    @Column(name = "message")
    private String message;
    @Basic(optional = false)
    @Column(name = "notified")
    private boolean notified;
    @Basic(optional = false)
    @Column(name = "send_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sendTime;
    @Column(name = "create_ip")
    private String createIp;
    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    @JoinColumn(name = "post", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Post post;

    public PostMessage() {
    }

    public PostMessage(Integer id) {
        this.id = id;
    }

    public PostMessage(Integer id, String message, Date sendTime, String createIp, Date createDate, Post post) {
        this.id = id;
        this.message = message;
        this.sendTime = sendTime;
        this.createIp = createIp;
        this.createDate = createDate;
        this.post = post;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isNotified() {
        return notified;
    }

    public void setNotified(boolean notified) {
        this.notified = notified;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getCreateIp() {
        return createIp;
    }

    public void setCreateIp(String createIp) {
        this.createIp = createIp;
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
        if (!(object instanceof PostMessage)) {
            return false;
        }
        PostMessage other = (PostMessage) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opensource.roomate.entity.PostMessage[id=" + id + "]";
    }
}
