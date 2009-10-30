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
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
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
@Table(name = "system_lease")
@NamedQueries({
    @NamedQuery(name = "SystemLease.findByOrganization", query = "select s from SystemLease s where s.system in (select s1.id from Systems s1 where s1.organization = ?1 ) "),
    @NamedQuery(name = "SystemLease.findBySystemAndFinished", query = "select s from SystemLease s where s.system = ?1 and s.isFinished = false "),
    @NamedQuery(name = "SystemLease.findBySystemIdAndFinished", query = "select s from SystemLease s where s.system = ?1 and s.isFinished = false "),
    @NamedQuery(name = "SystemLease.findByStartAndEndDates", query = "SELECT s FROM SystemLease s where (s.startTime >= ?1 and s.startTime <= ?2) and s.system in (select t.id from Systems t where t.organization = ?3 )"),
    @NamedQuery(name = "SystemLease.findByUsernameAndStartEndDates", query = "SELECT s FROM SystemLease s where s.leaseHolderName like ?1 and (s.startTime >= ?2 and s.startTime <= ?3) "),
    @NamedQuery(name = "SystemLease.findByIsStartContractNotified", query = "SELECT s FROM SystemLease s where s.isStartContractNotified = ?1 "),
    @NamedQuery(name = "SystemLease.findByIsEndContractNotified", query = "SELECT s FROM SystemLease s where s.isEndContractNotified = ?1 and s.isFinished = true ")
})
@NamedNativeQueries({
    @NamedNativeQuery(name = "SystemLease.findReportBetweenDates", query = "SELECT  sum(total_minutes_used) as total_minutes_used, sum(payable_amount) as payable_amount, sum(amount_paid) as amount_paid FROM system_lease s where (start_time >= ?1 and end_time <= ?2)  and system in (select id from systems where organization = ?3 )")
})
public class SystemLease implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "lease_holder_name")
    private String leaseHolderName;
    @Column(name = "start_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date startTime;
    @Column(name = "end_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;
    @Column(name = "payable_amount")
    private Double payableAmount;
    @Column(name = "amount_paid")
    private Double amountPaid;
    @Column(name = "issue_agent", nullable = false)
    private String issueAgent;
    @Column(name = "return_agent")
    private String returnAgent;
    @Column(name = "total_minutes_used")
    private Long totalMinutesUsed;
    @Column(name = "system", nullable = false)
    private int system;
    @Column(name = "is_finished", nullable = false)
    private boolean isFinished;
    @Transient
    private String startTimeString;
    @Transient
    private String endTimeString;
    @Column(name = "service")
    private String service;
    @Column(name = "system_no")
    private Integer systemNo;
    @Column(name = "is_start_contract_notified")
    private Boolean isStartContractNotified = false;
    @Column(name = "is_end_contract_notified")
    private Boolean isEndContractNotified = false;
    @Column(name="discounts")
    private Double discounts;
    @Column(name="comments")
    private String comments;

    public SystemLease() {
    }

    public SystemLease(Integer id) {
        this.id = id;
    }

    public SystemLease(Integer id, Date startTime, String issueAgent, int system, boolean isFinished) {
        this.id = id;
        this.startTime = startTime;
        this.issueAgent = issueAgent;
        this.system = system;
        this.isFinished = isFinished;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLeaseHolderName() {
        return leaseHolderName;
    }

    public void setLeaseHolderName(String leaseHolderName) {
        this.leaseHolderName = leaseHolderName;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getIssueAgent() {
        return issueAgent;
    }

    public void setIssueAgent(String issueAgent) {
        this.issueAgent = issueAgent;
    }

    public String getReturnAgent() {
        return returnAgent;
    }

    public void setReturnAgent(String returnAgent) {
        this.returnAgent = returnAgent;
    }

    public Long getTotalMinutesUsed() {
        return this.totalMinutesUsed;
//        Long st = this.startTime.getTime();
//        Long et = new Date().getTime();
//        return (et - st) / (1000 * 60);
    //return totalMinutesUsed;
    }

    public void setTotalMinutesUsed(Long totalMinutesUsed) {
        this.totalMinutesUsed = totalMinutesUsed;
    }

    public int getSystem() {
        return system;
    }

    public void setSystem(int system) {
        this.system = system;
    }

    public boolean getIsFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getEndTimeString() {
        String pattern = "yyyy.MM.dd hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (this.endTime == null) {
            return "";
        }
        return sdf.format(this.endTime);
    }

    public void setEndTimeString(String endTimeString) {
        this.endTimeString = endTimeString;
    }

    public String getStartTimeString() {
        String pattern = "hh:mm aaa";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (this.startTime == null) {
            return "";
        }
        return sdf.format(this.startTime);
    }

    public void setStartTimeString(String startTimeString) {
        this.startTimeString = startTimeString;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Integer getSystemNo() {
        return systemNo;
    }

    public void setSystemNo(Integer systemNo) {
        this.systemNo = systemNo;
    }

    public Boolean getIsEndContractNotified() {
        return isEndContractNotified;
    }

    public void setIsEndContractNotified(Boolean isEndContractNotified) {
        this.isEndContractNotified = isEndContractNotified;
    }

    public Boolean getIsStartContractNotified() {
        return isStartContractNotified;
    }

    public void setIsStartContractNotified(Boolean isStartContractNotified) {
        this.isStartContractNotified = isStartContractNotified;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Double getDiscounts() {
        return discounts;
    }

    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
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
        if (!(object instanceof SystemLease)) {
            return false;
        }
        SystemLease other = (SystemLease) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return " System No : " + this.getSystemNo() + " <br> " +
                " Admin Issued At Kiosk : " + this.getIssueAgent() + " <br> " +
                " Start Time : " + this.getStartTimeString() + " <br> " +
                " End Time : " + this.getEndTimeString() + " <br> " +
                " User when returning at Kiosk : " + this.getReturnAgent() + " <br> " +
                " Total Minutes : " + this.getTotalMinutesUsed() + " <br> " +
                " Total Payable : " + this.getPayableAmount() + " <br> " +
                " Paid Amount : " + this.getAmountPaid();
    }
}
