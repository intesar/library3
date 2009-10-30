/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.ajax;

import com.abbhsoft.sqlInjectionFilter.SQLInjectionFilterManager;
import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;

import com.bia.converter.CaseConverter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class AdminAjaxService {

    public String deleteEmail(int id) {
        String msg = "Deleted Email Successful!";
        try {
            this.adminService.deleteEmail(id);
            return msg;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    public String updateRentalPrice(int mims, double rate, Integer lmins, Double lrate) {
        String msg = "Price Updated Successful!";
        String username = AcegiUtil.getUsername();
        if (mims < 0 || rate < 0.0 || (lmins != null & lmins < 0) || (lrate != null && lrate < 0.0)) {
            return "We are keeping an eye on you! ";
        }
        try {
            this.adminService.updateRentalPrice(mims, rate, lmins, lrate, username);
            return msg;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    public List<Systems> getAllSystems() {
        String username = AcegiUtil.getUsername();
        return this.adminService.getAllSystems(username);
    }

    public String saveSystems(Systems systems) {
        String msg = "Operation succesful!";
        try {
            String username = AcegiUtil.getUsername();
            this.adminService.saveSystem(systems, username);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
        return msg;
    }

    public List<Users> getAllUsers() {
        String username = AcegiUtil.getUsername();
        List<Users> users = null;
        users =  this.adminService.getAllUsers(username);
        for ( Users u : users ) {
            u.setPic(null);
        }
        return users;
    }

    public String saveUsers(Users users) {
        logger.debug("inside ------------------ save users ");
        String msg = "Operation succesful!";
        users.setEmail(SQLInjectionFilterManager.getInstance().filter(users.getEmail()));
        users.setUsername(SQLInjectionFilterManager.getInstance().filter(users.getUsername()));
        logger.debug("inside ------------------ save users1 ");
        try {
            String username = AcegiUtil.getUsername();
            this.adminService.saveUser(users, username);
            logger.debug("inside ------------------ save users 2 ");
        } catch (Exception e) {
            logger.error(e);
            logger.debug("inside ------------------ save users 3 " + e.getMessage());
            return e.getMessage();
        }
        return msg;
    }

    public List<Users> saveAndGetUsers(Users users) {
        saveUsers(users);
        return getAllUsers();
    }
    public List<EmailPreference> getAllEmailPreference() {
        String username = AcegiUtil.getUsername();
        return this.adminService.getAllEmailPreference(username);
    }

    public String saveEmailPreference(EmailPreference emailPreference) {
        String msg = "Operation succesful!";
        try {
            String username = AcegiUtil.getUsername();
            emailPreference.setEmailOrPhone(SQLInjectionFilterManager.getInstance().filter(emailPreference.getEmailOrPhone()));
            emailPreference.setEmailOrPhone(emailPreference.getEmailOrPhone().toLowerCase());
            if (emailPreference.getUsername() != null) {
                emailPreference.setUsername(emailPreference.getUsername().toLowerCase());
            }
            this.adminService.saveEmailPreference(emailPreference, username);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
        return msg;
    }

    public List<EmailTimePreference> getAllEmailTimePreference() {
        String username = AcegiUtil.getUsername();
        return this.adminService.getAllEmailTimePreference(username);
    }

    public String saveEmailTimePreference(EmailTimePreference emailTimePreference) {
        String username = AcegiUtil.getUsername();
        try {
            this.adminService.saveEmailTimePreference(emailTimePreference, username);
            return "Added Successfully!";
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
    }

    public String deleteEmailTimePreference(EmailTimePreference emailTimePreference) {
        try {
            this.adminService.deleteEmailTimePreference(emailTimePreference);
            return "Deleted Successfully";
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
    }

    public List<SystemLease> getAllSystemLease() {
        String username = AcegiUtil.getUsername();

        return this.adminService.getAllSystemLease(username);

    }

    public Organization getOrganization() {
        String username = AcegiUtil.getUsername();
        return this.adminService.getOrganization(username);
    }

    public String saveOrganization(Organization organization) {
        String msg = "Operation succesful!";
        try {
            organization.setName(SQLInjectionFilterManager.getInstance().filter(organization.getName()));
            String username = AcegiUtil.getUsername();
            caseConverter.toLowerCase(organization);
            this.adminService.saveOrganization(organization, username);
        } catch (Exception e) {
            logger.error(e);
            return e.getMessage();
        }
        return msg;
    }

    public List<SystemLease> getSystemLease(String startDateString, String endDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        String username = AcegiUtil.getUsername();
        UsersLight u = this.adminService.getUserByUsername(username);

        try {
            startDate = sdf.parse(startDateString);
            endDate = sdf.parse(endDateString);
            return this.adminService.getSystemLease(startDate, endDate, u.getOrganization());
        } catch (ParseException ex) {
            Logger.getLogger(AdminAjaxService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List getReport(String startDateString, String endDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);
            logger.debug(startDate + " " + endDate);
            String username = AcegiUtil.getUsername();
            UsersLight u = this.adminService.getUserByUsername(username);
            return this.adminService.getReport(startDate, endDate, u.getOrganization());
        } catch (ParseException ex) {
            Logger.getLogger(AdminAjaxService.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public String saveService(Services service) {
        try {
            String msg = " Service Saved Successful! ";
            if (service.getOrganization() == null || service.getOrganization().length() <= 0) {
                service.setOrganization(this.getOrganization().getName());
            }
            service.setName(SQLInjectionFilterManager.getInstance().filter(service.getName()));
            service.setName(service.getName().toLowerCase());
            this.adminService.saveService(service);
            return msg;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    public String deleteService(Integer id) {
        try {
            if (id == null || id <= 0) {
                return "Id is null";
            }
            String msg = " Service Deleted Successful! ";
            this.adminService.deleteService(id);
            return msg;
        } catch (Exception e) {
            logger.error(e.getMessage());
            return e.getMessage();
        }
    }

    public List<Services> getAllServices() {
        String org = this.getOrganization().getName();
        return this.adminService.getAllServices(org);
    }

    
    public void sendReports() {
        this.adminService.sendReports();
    }

    public void setCaseConverter(CaseConverter caseConverter) {
        this.caseConverter = caseConverter;
    }
    private CaseConverter caseConverter;
    protected final Log logger = LogFactory.getLog(getClass());
    
    private AdminService adminService = (AdminService) ServiceFactory.getService("adminServiceImpl");
    
    

    public static void main(String[] args) {
        AdminAjaxService aas = new AdminAjaxService();
//        EmailPreference ep = new EmailPreference(null, "test", "mohdshannan@yahoo.com", "bia");
//        aas.saveEmailPreference(ep);
//        short s = (short) 500;
//        EmailTimePreference etp = new EmailTimePreference(null, s, "bia");
//        aas.saveEmailTimePreference(etp);
//        Systems s1 = new Systems(null, 2, "bia", true, null, 20.8, true);
//        aas.saveSystems(s1);
//        Users u = new Users(null, "shannan", "shannan", true, "admin", "bia", "shannan");
//        aas.saveUsers(u);
//        
//        System.out.println(aas.getAllEmailPreference());
//        System.out.println(aas.getAllEmailTimePreference());
//        System.out.println(aas.getAllSystemLease());
//        System.out.println(aas.getAllSystems());
//        System.out.println(aas.getAllUsers());
//        System.out.println(aas.getOrganization());
        Date dt1 = new Date(107, 7, 7);
        Date dt2 = new Date(110, 11, 11);

        //System.out.println(aas.getSystemLease("2007-07-07", "2008-10-10").size());
        //System.out.println ();//getReport("2008-08-04", "2008-08-04").toString());
        // System.out.println(aas.getReport("2007-07-07", "2008-10-10"));
        aas.sendReports();
    }
}
