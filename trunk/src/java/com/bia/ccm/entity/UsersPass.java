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
@Table(name = "users_pass")
@NamedQueries({
    @NamedQuery(name = "UsersPass.findById", query = "SELECT u FROM UsersPass u WHERE u.id = :id"), 
    @NamedQuery(name = "UsersPass.findByUsername", query = "SELECT u FROM UsersPass u WHERE u.username = :username"),
    @NamedQuery(name = "UsersPass.findByPassword", query = "SELECT u FROM UsersPass u WHERE u.password = :password"),
    @NamedQuery(name = "UsersPass.findByEnabled", query = "SELECT u FROM UsersPass u WHERE u.enabled = :enabled"),
    @NamedQuery(name = "UsersPass.findByResetCode", query = "SELECT u FROM UsersPass u WHERE u.resetCode = :resetCode"),
    @NamedQuery(name = "UsersPass.findByCreateDate", query = "SELECT u FROM UsersPass u WHERE u.createDate = :createDate"),
    @NamedQuery(name = "UsersPass.findByUsernameAndEnabled", query = "SELECT u FROM UsersPass u WHERE u.username = ?1 and u.enabled = ?2")
})
public class UsersPass implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "reset_code", nullable = false)
    private String resetCode;
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    public UsersPass() {
    }

    public UsersPass(Integer id) {
        this.id = id;
    }

    public UsersPass(Integer id, String username, String password, boolean enabled, String resetCode, Date createDate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.resetCode = resetCode;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getResetCode() {
        return resetCode;
    }

    public void setResetCode(String resetCode) {
        this.resetCode = resetCode;
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
        if (!(object instanceof UsersPass)) {
            return false;
        }
        UsersPass other = (UsersPass) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.UsersPass[id=" + id + "]";
    }

}
