/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.entity;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
import javax.persistence.Transient;
import org.apache.solr.analysis.LowerCaseFilterFactory;
import org.apache.solr.analysis.SnowballPorterFilterFactory;
import org.apache.solr.analysis.StandardTokenizerFactory;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
import org.hibernate.search.annotations.AnalyzerDef;
import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Parameter;
import org.hibernate.search.annotations.Resolution;
import org.hibernate.search.annotations.Store;
import org.hibernate.search.annotations.TokenFilterDef;
import org.hibernate.search.annotations.TokenizerDef;

/**
 *
 * @author  intesar shannan mohammed
 *  mdshannan@gmail.com
 */
@DataTransferObject
@Indexed
@AnalyzerDef(name = "customanalyzer",
tokenizer = @TokenizerDef(factory = StandardTokenizerFactory.class),
filters = {
    @TokenFilterDef(factory = LowerCaseFilterFactory.class),
    @TokenFilterDef(factory = SnowballPorterFilterFactory.class, params = {
        @Parameter(name = "language", value = "English")
    })
})
@Entity
@Table(name = "post")
@NamedQueries({@NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @RemoteProperty
    @DocumentId
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "posted_by")
    private String postedBy;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Basic(optional = false)
    @Column(name = "poster_contact")
    private String posterContact;
    @RemoteProperty
    @Field(index = Index.UN_TOKENIZED, store = Store.YES)
    @DateBridge(resolution = Resolution.MILLISECOND)
    @Basic(optional = false)
    @Column(name = "post_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postDate;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Basic(optional = false)
    @Column(name = "sex")
    private String sex;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Basic(optional = false)
    @Column(name = "rent")
    private Double rent;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "address_line")
    private String addressLine;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "city")
    private String city;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "zipcode")
    private String zipcode;
    @RemoteProperty
    @Field(index = Index.TOKENIZED, store = Store.NO)
    @Column(name = "country")
    private String country;
    @RemoteProperty
    @Basic(optional = false)
    @Column(name = "remote_ip")
    private String remoteIp;
    @RemoteProperty
    @Lob
    @Column(name = "comment")
    private String comment;
    @Transient
    @RemoteProperty
    private String date;

    public Post() {
    }

    public Post(Long id) {
        this.id = id;
    }

    public Post(Long id, String postedBy, String posterContact, Date postDate, String sex, Double rent, String remoteIp) {
        this.id = id;
        this.postedBy = postedBy;
        this.posterContact = posterContact;
        this.postDate = postDate;
        this.sex = sex;
        this.rent = rent;
        this.remoteIp = remoteIp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPosterContact() {
        return posterContact;
    }

    public void setPosterContact(String posterContact) {
        this.posterContact = posterContact;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getRent() {
        return rent;
    }

    public void setRent(Double rent) {
        this.rent = rent;
    }

    public String getAddressLine() {
        return addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRemoteIp() {
        return remoteIp;
    }

    public void setRemoteIp(String remoteIp) {
        this.remoteIp = remoteIp;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * New if posted less than 7days
     * 1 Week ago if posted less then 14 days ago
     * 2 weeks ago if posted less than 21 days ago
     * 3 weeks ago if posted less than 30 days ago
     * 1 month ago if posted less than 60 days ago
     * 2 months ago if posted less than 90 days ago
     * @return
     */
    public String getDate() {
        long days = (new Date().getTime() - postDate.getTime()) / 86400000;
        if ( days < 7 ) {
            date = "New";
        } else  if (days < 14) {
            date = "1 Week ago";
        } else if (days < 21) {
            date = "2 Week ago";
        } else if (days < 30) {
            date = "3 Week ago";
        } else if (days < 60) {
            date = "1 Month ago";
        } else if (days < 90) {
            date = "2 Months ago";
        } else if (days < 120) {
            date = "3 Months ago";
        } else {
            DateFormat df = new SimpleDateFormat("mmm yy");
            date = df.format(postDate);
        }
        return date;

    }

    public void setDate(String date) {
        this.date = date;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.opensource.roomate.entity.Post[id=" + id + "]";
    }
}
