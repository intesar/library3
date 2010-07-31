/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 *
 * @author intesar
 */
public interface AdminService {
    // Systems
    public void deleteEmail(int id, String username);

    public void deleteEmailTimePreference(EmailTimePreference emailTimePreference, String username);

    public List<SystemLease> getMySystemLease(Date startDate, Date endDate, String organization);

    public UsersLight getUserByUsername(String username);

    public void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, String username, String ip);

    public List<Systems> getAllSystems(String username);

    public void saveSystem(Systems systems, String username);

    //Users
    public List<Users> getAllUsers(String username);

    
    //public Users getUserByUsername(String username);
    //Email
    public List<EmailPreference> getAllEmailPreference(String username);

    public void saveEmailPreference(EmailPreference emailPreference, String username);
    //EmailTimePreference
    public List<EmailTimePreference> getAllEmailTimePreference(String username);
    public List<EmailPreference> getAllOrganizationEmailPreference(String org);

    public void saveEmailTimePreference(EmailTimePreference emailTimePreference, String username);
    //SystemLease
    public List<SystemLease> getAllSystemLease(String username);
    // lease history
    public List<SystemLease> getSystemLease(Date startDate, Date endDate, String username);

    public List getReport(Date startDate, Date endDate, String username);

    public Organization getOrganization(String username);

    public void saveOrganization(Organization organization, String username);

    public void saveService(Services service, String username, String ip);

    public void deleteService(Integer id, String username);

    public List<Services> getAllServices(String org);

    public Systems getSystem(String org);
    
    void sendReports();
    
    List<EmailTimePreference> getEmailTimePreferences(short time);

    void savePreferences(Set<String> emails, Set<Short> timings, String organization,
            String userId, String ip);

    public PreferenceDto getPreferences(String organization);
}
