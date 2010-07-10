/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author intesar
 */
@Entity
@Table(name = "systems")
@NamedQueries({
    @NamedQuery(name = "Systems.findByOrganization", query = "select s from Systems s where s.organization = ?1  "),
    @NamedQuery(name = "Systems.findBySystemNameAndOrganization", query = "select s from Systems s where s.name = ?1 and s.organization = ?2"),
    @NamedQuery(name = "Systems.findByMacAddress", query = "select s from Systems s where lower(s.macAddress) = ?1 "),
    @NamedQuery(name = "Systems.findNoOfActiveSystemsByOrganization", query = "select count(s) from Systems s where s.enabled = true and s.isAvailable = true and s.organization = ?1  ")
})
public class Systems implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name", nullable = false)
    private int name;
    @Column(name = "organization", nullable = false)
    private String organization;
    @Column(name = "is_available", nullable = false)
    private boolean isAvailable;
    @Column(name = "expected_free_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expectedFreeTime;
    @Column(name = "description")
    private String description;
    @Column(name = "minimum_minutes", nullable = false)
    private Integer minimumMinutes;
    @Column(name = "rate", nullable = false)
    private Double minuteRate;
    @Column(name = "lower_minimum_minutes")
    private Integer lowerMinimumMinutes;
    @Column(name = "lower_rate")
    private Double lowerMinuteRate;
    @Column(name = "enabled", nullable = false)
    private boolean enabled;
    @Column(name = "is_shutdown")
    private Boolean isShutdown;
    @Column(name = "create_user")
    private String createUser;
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @Column(name = "mac_address")
    private String macAddress;
    @Column(name = "ip_address")
    private String ipAddress;
    @Column(name = "status_")
    private String status;
    @Column(name = "comment")
    private String comment;
    @Transient
    private String enabledString;
    @Column(name = "current_user_email")
    private String currentUserEmail;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    private Date startTime;
    @Transient
    private String startTimeString;

    public Systems() {
    }

    public Systems(Integer id) {
        this.id = id;
    }

    public Systems(Integer id, int name, String organization, boolean isAvailable, Date expectedFreeTime, Integer minimumMinutes, double minuteRate, boolean enabled) {
        this.id = id;
        this.name = name;
        this.organization = organization;
        this.isAvailable = isAvailable;
        this.expectedFreeTime = expectedFreeTime;
        this.minimumMinutes = minimumMinutes;
        this.minuteRate = minuteRate;
        this.enabled = enabled;
        this.macAddress = organization + name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public boolean getIsAvailable() {
        return isAvailable;
    }

    public void setIsAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    public Date getExpectedFreeTime() {
        return expectedFreeTime;
    }

    public void setExpectedFreeTime(Date expectedFreeTime) {
        this.expectedFreeTime = expectedFreeTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getMinuteRate() {
        return minuteRate;
    }

    public void setMinuteRate(double minuteRate) {
        this.minuteRate = minuteRate;
    }

    public Integer getLowerMinimumMinutes() {
        return lowerMinimumMinutes;
    }

    public void setLowerMinimumMinutes(Integer lowerMinimumMinutes) {
        this.lowerMinimumMinutes = lowerMinimumMinutes;
    }

    public Double getLowerMinuteRate() {
        return lowerMinuteRate;
    }

    public void setLowerMinuteRate(Double lowerMinuteRate) {
        this.lowerMinuteRate = lowerMinuteRate;
    }
    
    

    public boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getIsShutdown() {
        return isShutdown;
    }

    public void setIsShutdown(Boolean isShutdown) {
        this.isShutdown = isShutdown;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Integer getMinimumMinutes() {
        return minimumMinutes;
    }

    public void setMinimumMinutes(Integer minimumMinutes) {
        this.minimumMinutes = minimumMinutes;
    }

    public String getEnabledString() {
        if (enabled) {
            return "yes";
        } else {
            return "no";
        }
    }

    public void setEnabledString(String enabledString) {
        if (enabledString.equals("yes")) {
            this.enabledString = "yes";
            enabled = true;
        } else {
            this.enabledString = "no";
            enabled = false;
        }
    }

    public String getCurrentUserEmail() {
        return currentUserEmail;
    }

    public void setCurrentUserEmail(String currentUserEmail) {
        this.currentUserEmail = currentUserEmail;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getStartTimeString() {
        String pattern = "hh:mm aaa";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (this.startTime != null) {
            return sdf.format(this.startTime);
        } else {
            return "";
        }
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

        @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Systems)) {
            return false;
        }
        Systems other = (Systems) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.bia.ccm.entity.Systems[id=" + id + "]";
    }
}
