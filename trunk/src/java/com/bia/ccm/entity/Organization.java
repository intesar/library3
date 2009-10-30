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
@Table(name = "organization")
@NamedQueries({
    @NamedQuery(name="Organization.findByOrganization", query="select s from Organization s where s.name = ?1 ")
})
public class Organization implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "enabled", nullable = false)
    private short enabled;
    @Column(name = "street")
    private String street;
    @Column(name = "city", nullable = false)
    private String city;
    @Column(name = "state_", nullable = false)
    private String state;
    @Column(name = "zipcode")
    private String zipcode;
    @Column(name = "country", nullable = false)
    private String country;
    @Column(name = "phone")
    private String phone;
    @Column(name = "contact_name")
    private String contactName;
    @Column(name = "register_type", nullable = false)
    private String registerType;
    @Column(name = "register_date")
    private String registerDate;
    @Column(name = "contact_email")
    private String contactEmail;
    @Column(name = "product_name", nullable = false)
    private String productName;
    @Column(name = "product_version", nullable = false)
    private String productVersion;
    @Column(name = "licence_key", nullable = false)
    private String licenceKey;
    @Column(name = "amount_paid", nullable = false)
    private double amountPaid;
    @Column(name = "expiration_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
    @Column(name = "register_agent")
    private String registerAgent;
    @Column(name = "create_agent", nullable = false)
    private String createAgent;
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column ( name="timings")
    private String timings;
    @Column (name="print_email")
    private String printEmail;

    public Organization() {
    }

    public Organization(String name) {
        this.name = name;
    }

    public Organization(String name, short enabled, String street, String city, String state, String country, String contactName, String registerType, String productName, String productVersion, String licenceKey, double amountPaid, Date expirationDate, String createAgent) {
        this.name = name;
        this.enabled = enabled;
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.contactName = contactName;
        this.registerType = registerType;
        this.productName = productName;
        this.productVersion = productVersion;
        this.licenceKey = licenceKey;
        this.amountPaid = amountPaid;
        this.expirationDate = expirationDate;
        this.createAgent = createAgent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getEnabled() {
        return enabled;
    }

    public void setEnabled(short enabled) {
        this.enabled = enabled;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getLicenceKey() {
        return licenceKey;
    }

    public void setLicenceKey(String licenceKey) {
        this.licenceKey = licenceKey;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRegisterAgent() {
        return registerAgent;
    }

    public void setRegisterAgent(String registerAgent) {
        this.registerAgent = registerAgent;
    }

    public String getCreateAgent() {
        return createAgent;
    }

    public void setCreateAgent(String createAgent) {
        this.createAgent = createAgent;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getPrintEmail() {
        return printEmail;
    }

    public void setPrintEmail(String printEmail) {
        this.printEmail = printEmail;
    }

    public String getTimings() {
        return timings;
    }

    public void setTimings(String timings) {
        this.timings = timings;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (name != null ? name.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Organization)) {
            return false;
        }
        Organization other = (Organization) object;
        if ((this.name == null && other.name != null) || (this.name != null && !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.Organization[name=" + name + "]";
    }

}
