/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.entity;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author imran
 */
@Entity
@Table(name = "authorities")
@NamedQueries({@NamedQuery(name = "Authorities.findByUsername", query = "SELECT a FROM Authorities a WHERE a.authoritiesPK.username = :username"), @NamedQuery(name = "Authorities.findByAuthority", query = "SELECT a FROM Authorities a WHERE a.authoritiesPK.authority = :authority")})
public class Authorities implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected AuthoritiesPK authoritiesPK;

    public Authorities() {
    }

    public Authorities(AuthoritiesPK authoritiesPK) {
        this.authoritiesPK = authoritiesPK;
    }

    public Authorities(String username, String authority) {
        this.authoritiesPK = new AuthoritiesPK(username, authority);
    }

    public AuthoritiesPK getAuthoritiesPK() {
        return authoritiesPK;
    }

    public void setAuthoritiesPK(AuthoritiesPK authoritiesPK) {
        this.authoritiesPK = authoritiesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (authoritiesPK != null ? authoritiesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Authorities)) {
            return false;
        }
        Authorities other = (Authorities) object;
        if ((this.authoritiesPK == null && other.authoritiesPK != null) || (this.authoritiesPK != null && !this.authoritiesPK.equals(other.authoritiesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.Authorities[authoritiesPK=" + authoritiesPK + "]";
    }

}
