/*
 * 
 */
package com.bia.ccm.services;

import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import java.util.Date;
import java.util.List;

/**
 *
 * @author intesar
 */
public interface ProductService {

    /**
     * 
     * @param organization
     * @return
     */
    Systems getSystem(long organization);

    /**
     *
     * @param username
     * @return
     */
    List<Systems> getAllSystems(long organization);

    /**
     *
     * @param startDate
     * @param endDate
     * @param organization
     * @return
     */
//    List<SystemLease> getMySystemLease(Date startDate, Date endDate, String usrename);
//
//    /**
//     *
//     * @param username
//     * @return
//     */
//    List<SystemLease> getAllSystemLease(long organization);
//
//    /**
//     *
//     * @param startDate
//     * @param endDate
//     * @param organization
//     * @return
//     */
//    List<SystemLease> getSystemLease(Date startDate, Date endDate, long organization);

    /**
     *
     * 
     *  service contains ( name, organization, unitPrice, saleTwoUnits, saleTwoPrice, saleTwoEnabled )
     *
     *  unitPrice is for one unit of product or service
     *  if admin sets saleTwoUnits > 1 and saleTwoPrice > 0 then make saleTwoEnabled to true
     *    it means that if there is sale of product or services >= saleTwoUnits
     *    then apply saleTwoPrice per unit
     *    eg name = color print, organzation = sample, unitPrice = 5.00
     *           saleTwoEnabled = true, saleTwoUnits = 10, saleTwoPrice = 4
     *     case 1 user request for 4 copies 4 * 5.00 = 20.00
     *     case 2 user request for 20 copes 20 * 4.00 = 800.00
     *
     *
     *
     * @param service
     * @param organization
     * @param username
     * @param ip
     */
    void saveService(Services service, long organization);

    /**
     *
     * @param id
     * @param username
     */
    void deleteService(Long id, long organization);

    /**
     *
     * @param org
     * @return
     */
    List<Services> getAllServices(long organization);

    /**
     * 
     * @param organization
     * @return
     */
    List<Services> getAllServicesWithSystem(long organization);

    /**
     *
     * @param mims
     * @param rate
     * @param lmins
     * @param lrate
     * @param organization
     * @param username
     * @param ip
     */
    void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, long organization);

    

    /**
     *
     */
    //void sendReports();

    /**
     * 
     * @param startDate
     * @param endDate
     * @param organization
     * @return
     */
   // List getReport(Date startDate, Date endDate, long organization);

 }
