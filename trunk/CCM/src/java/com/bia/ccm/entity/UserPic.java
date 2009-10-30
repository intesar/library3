/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author intesar
 */
@Entity
@Table(name = "user_pic")
@NamedQueries({
    @NamedQuery(name = "UserPic.findByUsername", query = "SELECT u FROM UserPic u WHERE u.user.username like ?1 ")
})
public class UserPic implements Serializable {
    private static final long serialVersionUID = 1L;
    @Lob
    @Column(name = "pic")
    private byte[] pic;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @JoinColumn(name = "user", referencedColumnName = "id")
    @OneToOne (fetch=FetchType.EAGER)
    private Users user;

    public UserPic() {
    }

    public UserPic(Integer id) {
        this.id = id;
    }

    public byte[] getPic() {
        return pic;
    }

    public void setPic(byte[] pic) {
        this.pic = pic;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
        if (!(object instanceof UserPic)) {
            return false;
        }
        UserPic other = (UserPic) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.UserPic[id=" + id + "]";
    }

}
