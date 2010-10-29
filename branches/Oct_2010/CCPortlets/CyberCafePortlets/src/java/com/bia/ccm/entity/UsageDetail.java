/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.entity;

/**
 *
 * @author intesar
 */
public class UsageDetail {
    
    private String detail;
    private Double payableAmount;

    public UsageDetail(String detail, Double payableAmount) {
        this.detail = detail;
        this.payableAmount = payableAmount;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public Double getPayableAmount() {
        return payableAmount;
    }

    public void setPayableAmount(Double payableAmount) {
        this.payableAmount = payableAmount;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UsageDetail other = (UsageDetail) obj;
        if (this.detail != other.detail && (this.detail == null || !this.detail.equals(other.detail))) {
            return false;
        }
        if (this.payableAmount != other.payableAmount && (this.payableAmount == null || !this.payableAmount.equals(other.payableAmount))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.detail != null ? this.detail.hashCode() : 0);
        hash = 97 * hash + (this.payableAmount != null ? this.payableAmount.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return this.getDetail();
    }
    
        

}
