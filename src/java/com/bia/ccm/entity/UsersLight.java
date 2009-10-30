/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author intesar
 */
@Entity
@Table(name = "users_light")
@NamedQueries({
    @NamedQuery(name = "UsersLight.findByUsername", query = "select u from UsersLight u where u.username = ?1 "),
    @NamedQuery(name = "UsersLight.findByOrganization", query = "select u from UsersLight u where u.organization = ?1 ")
    
})
public class UsersLight implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "organization")
    private String organization;

    public UsersLight(String username, String organization) {
        this.username = username;
        this.organization = organization;
    }

    public UsersLight() {
    }

    
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsersLight other = (UsersLight) obj;
        if (this.username != other.username && (this.username == null || !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public String toString() {
        return this.username;
    }
    
}
