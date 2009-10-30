/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.remote;

/**
 *
 * @author intesar
 */
public interface StatusCheckService {
 /**
     *  0 -error
     *  1 - do nothing
     *  2 - logoff
     *  3 - shutdown
     * Web service operation
     */
    public Integer getStatus(String macAddress);

    public String getUserEmailByMacAddress(String macAddress);
}
