/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.soa;

import com.bia.ccm.services.WorkService;
import com.bia.ccm.util.ServiceFactory;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import javax.jws.WebMethod;
//import javax.jws.WebParam;
//import javax.jws.WebService;

/**
 *
 * @author intesar
 */
//@WebService()
public class ClientService {

    /**
     *  0 -error
     *  1 - do nothing
     *  2 - logoff
     *  3 - shutdown
     * Web service operation
     */
    //@WebMethod(operationName = "getStatus")
    public Integer getStatus(/*@WebParam(name = "macAddress") */ String macAddress) {
        return this.workService.getSystemStatus(macAddress);
    }

    /**
     * Web service operation
     */
    //@WebMethod(operationName = "getUserEmailByMacAddress")
    public String getUserEmailByMacAddress(/* @WebParam(name = "macAddress") */ String macAddress) {
        String email = "faceguard@bizintelapps.com";
        try {
            email = this.workService.getUserEmailByMacAddress(macAddress);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return email;
    }

    public static void main(String[] args) {
        ClientService clientService = new ClientService();
        System.out.println(clientService.getStatus("90erw"));
        System.out.println ( clientService.getUserEmailByMacAddress("bizintelapps.com16"));
    }
    protected final Log logger = LogFactory.getLog(getClass());
    private WorkService workService = (WorkService) ServiceFactory.getService("workServiceImpl");
}
