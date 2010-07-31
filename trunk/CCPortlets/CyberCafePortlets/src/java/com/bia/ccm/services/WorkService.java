/*
 * 
 */
package com.bia.ccm.services;

import com.bia.ccm.entity.Services;

import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface WorkService {

    /**
     *
     * @param organization
     * @return
     */
    List<Services> getAllServices(String organization);

    /**
     * 
     * @param systemId
     * @param agent
     * @param organization 
     */
    void chargePayment(int systemId, String agent, String organization);

    /**
     *
     * @param service
     * @param units
     * @param user
     * @param payableAmount
     * @param comments
     * @param paidAmount
     * @param agent
     */
    void addService(String service, long units, String user, double payableAmount,
            String comments, double paidAmount, String agent, String organization);

    /**
     *
     * @param id
     * @return
     */
    List<SystemLease> getSystemLease(int id);

    /**
     *
     * @param username
     * @return
     */
    List<Systems> getActiveSystems(String username);

    /**
     *
     * @param systemNo
     * @param username
     * @return
     */
    Systems getSystemByNameAndOrganization(int systemNo, String username);

    /**
     *
     * @param id
     * @param leaseHolder
     * @param cashier
     */
    void leaseSystem(int id, String leaseHolder, String cashier);

    /**
     *
     * @param id
     * @param amountPaid
     * @param cashier
     */
    void unleaseSystem(int id, double amountPaid, String cashier);

    /**
     *
     * @param id
     * @return
     */
    UsageDetail getPayableAmount(int id);

    /**
     *
     * @param macAddress
     * @return
     */
    Integer getSystemStatus(String macAddress);

    /**
     *
     * @param macAddress
     * @return
     */
    String getUserEmailByMacAddress(String macAddress);

    /**
     *
     */
    void notifyCustomersAtContractEnd();

    /**
     * 
     */
    void notifyCustomersAtContractStart();
}
