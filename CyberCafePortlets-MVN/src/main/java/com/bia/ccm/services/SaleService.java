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
public interface SaleService {

    /**
     *
     * @param organization
     * @return
     */
    List<Services> getAllServices(long organization);

    /**
     * 
     * @param systemId
     * @param agent
     * @param organization 
     */
    void chargePayment(long systemId, String agent, long organization);

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
            String comments, double paidAmount, String agent, long organization);

    /**
     *
     * @param id
     * @return
     */
    List<SystemLease> getSystemLease(long id);

    /**
     *
     * @param username
     * @return
     */
    List<Systems> getActiveSystems(long organization);

    /**
     *
     * @param systemNo
     * @param username
     * @return
     */
    Systems getSystemByNameAndOrganization(int systemNo, long organization);

    /**
     *
     * @param id
     * @param leaseHolder
     * @param cashier
     */
    void leaseSystem(long id, String leaseHolder, String cashier);

    /**
     *
     * @param id
     * @param amountPaid
     * @param cashier
     */
    void unleaseSystem(long id, double amountPaid, String cashier);

    /**
     *
     * @param id
     * @return
     */
    UsageDetail getPayableAmount(long id);

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
