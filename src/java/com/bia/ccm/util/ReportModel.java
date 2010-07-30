/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 *
 * @author intesar
 */
public class ReportModel {

    private Double totalHours;
    private Double totalMinutes;
    private Double totalPayableAmount;
    private Double totalPaidAmount;

    public Double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Double totalHours) {
        this.totalHours = totalHours;
    }

    public Double getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(Double totalMinutes) {
        this.totalMinutes = totalMinutes;
    }

    public Double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(Double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public Double getTotalPayableAmount() {
        return totalPayableAmount;
    }

    public void setTotalPayableAmount(Double totalPayableAmount) {
        this.totalPayableAmount = totalPayableAmount;
    }

    @Override
    public String toString() {
        NumberFormat formatter = new DecimalFormat("0.0");
        return "Total Paid Amount " + formatter.format(this.totalPaidAmount) + ", " +
                "Total Payable Amount " + formatter.format(this.totalPayableAmount) + ", " +
                "Total Hours " + formatter.format(this.totalHours) + ", " +
                "Total Minutes " + formatter.format(this.totalMinutes);

    }
}
