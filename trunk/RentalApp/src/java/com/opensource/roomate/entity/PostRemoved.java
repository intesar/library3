package com.opensource.roomate.entity;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.Transient;

/**
 *
 * @author  intesar shannan mohammed
 *  mdshannan@gmail.com
 */
@Entity
@Table(name = "post_removed")
public class PostRemoved implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Column(name = "posted_by")
    private String postedBy;
    @Basic(optional = false)
    @Column(name = "email")
    private String email;
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Column(name = "post_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date postDate;
    @Column(name = "sex")
    private String sex;
    @Basic(optional = false)
    @Column(name = "rent")
    private Double rent;
    @Basic(optional = false)
    @Column(name = "rent_category")
    private Integer rentCategory;
    @Column(name = "currency")
    private String currency;
    @Basic(optional = false)
    @Column(name = "rental_type")
    private String rentalType;
    @Column(name = "address_line")
    private String addressLine;
    @Column(name = "city")
    private String city;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "country")
    private String country;
    @Lob
    @Column(name = "comment")
    private String comment;
    @Transient
    private String date;
    @Column(name = "create_ip")
    private String createIp;
    @Column(name = "create_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    @Column(name = "update_ip")
    private String updateIp;
    @Column(name = "update_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date updateDate;
    @Column(name = "delete_ip")
    private String deleteIp;
    @Column(name = "delete_date")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date deleteDate;
    @Transient
    private String emailTransient;
    @Column(name = "report_abuse")
    private int reportAbuse;
    @Column(name = "beds")
    private String beds;
    @Column(name = "area")
    private Integer area;

    public PostRemoved() {
    }

    public PostRemoved(Long id) {
        this.id = id;
    }

    public PostRemoved(Long id, String postedBy, String email, Date postDate, String sex, Double rent) {
        this.id = id;
        this.postedBy = postedBy;
        this.email = email;
        this.postDate = postDate;
        this.sex = sex;
        this.rent = rent;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public Integer getRentCategory() {
        return rentCategory;
    }

    public void setRentCategory(Integer rentCategory) {
        this.rentCategory = rentCategory;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
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

    public Date getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Date deleteDate) {
        this.deleteDate = deleteDate;
    }

    public String getDeleteIp() {
        return deleteIp;
    }

    public void setDeleteIp(String deleteIp) {
        this.deleteIp = deleteIp;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdateIp() {
        return updateIp;
    }

    public void setUpdateIp(String updateIp) {
        this.updateIp = updateIp;
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
        if (days < 7) {
            date = "New";
        } else if (days < 14) {
            date = "1 Week";
        } else if (days < 21) {
            date = "2 Week";
        } else if (days < 30) {
            date = "3 Week";
        } else if (days < 60) {
            date = "1 Month";
        } else if (days < 90) {
            date = "2 Months";
        } else if (days < 120) {
            date = "3 Months";
        } else {
            DateFormat df = new SimpleDateFormat("mmm yy");
            date = df.format(postDate);
        }
        return date;

    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEmailTransient() {
        return emailTransient;
    }

    public void setEmailTransient(String emailTransient) {
        this.emailTransient = emailTransient;
    }

    public int getReportAbuse() {
        return reportAbuse;
    }

    public void setReportAbuse(int reportAbuse) {
        this.reportAbuse = reportAbuse;
    }

    public Integer getArea() {
        return area;
    }

    public void setArea(Integer area) {
        this.area = area;
    }

    public String getBeds() {
        return beds;
    }

    public void setBeds(String beds) {
        this.beds = beds;
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
        if (!(object instanceof PostRemoved)) {
            return false;
        }
        PostRemoved other = (PostRemoved) object;
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
