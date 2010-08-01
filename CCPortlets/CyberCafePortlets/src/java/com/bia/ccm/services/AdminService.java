/*
 * 
 */
package com.bia.ccm.services;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author intesar
 */
public interface AdminService {

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
    List<SystemLease> getMySystemLease(Date startDate, Date endDate, String usrename);

    /**
     * 
     * @param username
     * @return
     */
    List<SystemLease> getAllSystemLease(long organization);

    /**
     *
     * @param startDate
     * @param endDate
     * @param organization
     * @return
     */
    List<SystemLease> getSystemLease(Date startDate, Date endDate, long organization);

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
    void saveService(Services service, long organization, String username, String ip);

    /**
     *
     * @param id
     * @param username
     */
    void deleteService(Integer id, long organization);

    /**
     *
     * @param org
     * @return
     */
    List<Services> getAllServices(long organization);

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
    void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, long organization, String username, String ip);

    /**
     *
     * @param emails
     * @param timings
     * @param organization
     * @param userId
     * @param ip
     */
    void savePreferences(Set<String> emails, Set<Short> timings, long organization,
            String userId, String ip);

    /**
     * 
     * @param organization
     * @return
     */
    PreferenceDto getPreferences(long organization);

    /**
     *
     */
    void sendReports();

    /**
     * 
     * @param startDate
     * @param endDate
     * @param organization
     * @return
     */
    List getReport(Date startDate, Date endDate, long organization);
    
    /**
     * 
     * @param s
     * @return
     */
    List<EmailTimePreference> getEmailTimePreferences(short time);

    /**
     * 
     * @param organization
     * @return
     */
    List<EmailPreference> getAllOrganizationEmailPreference(long organization);

    /**
     *
     * @param organization
     */
    void saveOrganization(Organization organization, long organizationId, long userId, String ip);

    /**
     * 
     * @param organizationId
     * @return
     */
    Organization getOrganization(long organizationId, String organizationName);
}
