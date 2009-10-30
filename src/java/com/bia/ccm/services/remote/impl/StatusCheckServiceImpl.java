/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.remote.impl;

import com.bia.ccm.services.WorkService;
import com.bia.ccm.util.ServiceFactory;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.bia.ccm.services.remote.StatusCheckService;

/**
 *
 * @author intesar
 */
public class StatusCheckServiceImpl implements StatusCheckService {

    @Override
    public Integer getStatus(String macAddress) {
        return this.workService.getSystemStatus(macAddress);
    }

    @Override
    public String getUserEmailByMacAddress(String macAddress) {
        String email = "no-email@bizintelapps.com";
        try {
            email = this.workService.getUserEmailByMacAddress(macAddress);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return email;
    }
    protected final Log logger = LogFactory.getLog(getClass());
    private WorkService workService = (WorkService) ServiceFactory.getService("workServiceImpl");
    public static void main(String[] args) {
        StatusCheckService sc = new StatusCheckServiceImpl();
        System.out.println ( sc.getStatus("00-1C-26-DD-F5-A4"));
    }
}
