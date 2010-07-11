/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see  src/context/ApplicationContext-AjaxService.xml
 *
 *  This class is mapped to AjaxAdminService bean in the above file
 *
 * @author intesar
 */
public class AdminAjaxService {

    /**
     *   Response codes
     *   1 == Operation Successfully executed
     *  -1 == Operation execution failed, no reason specified, ask user to try again
     *  -2 == Operation not executed, because of invalid input
     *  -3 == Operation failed (User doesn't have appropriate roles on data), if user believes he has roles ask him to refresh page or relogin
     *
     */
    /**
     * only Owner can delete Notification Email
     * @param id
     * @param request set my DWR, used for getting IP Address
     * @return
     */
    public int deleteEmail(int id, HttpServletRequest request) {
        try {
            this.adminService.deleteEmail(id, AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @param mims
     * @param rate
     * @param lmins
     * @param lrate
     * @param request
     * @return
     */
    public int updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, HttpServletRequest request) {
        String username = AcegiUtil.getUsername();
        try {
            this.adminService.updateRentalPrice(mims, rate, lmins, lrate, username, request.getRemoteAddr());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public List<Systems> getAllSystems() {
        try {
            String username = AcegiUtil.getUsername();
            return this.adminService.getAllSystems(username);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     *
     * @param systems
     * @return
     */
    public int saveSystems(Systems systems) {
        try {
            String username = AcegiUtil.getUsername();
            this.adminService.saveSystem(systems, username);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public List<Users> getAllUsers() {
        String username = AcegiUtil.getUsername();
        List<Users> users = null;
        users = this.adminService.getAllUsers(username);
        for (Users u : users) {
            u.setPic(null);
        }
        return users;
    }

    /**
     *
     * @param users
     * @return
     */
    public int saveUsers(Users users) {
        try {
            String username = AcegiUtil.getUsername();
            this.adminService.saveUser(users, username);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @param users
     * @return
     */
    public List<Users> saveAndGetUsers(Users users) {
        saveUsers(users);
        return getAllUsers();
    }

    /**
     *
     * @return
     */
    public List<EmailPreference> getAllEmailPreference() {
        try {
            String username = AcegiUtil.getUsername();
            return this.adminService.getAllEmailPreference(username);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     *
     * @param emailPreference
     * @return
     */
    public int saveEmailPreference(EmailPreference emailPreference) {
        try {
            String username = AcegiUtil.getUsername();
//            emailPreference.setEmailOrPhone(SQLInjectionFilterManager.getInstance().filter(emailPreference.getEmailOrPhone()));
//            emailPreference.setEmailOrPhone(emailPreference.getEmailOrPhone().toLowerCase());
//            if (emailPreference.getUsername() != null) {
//                emailPreference.setUsername(emailPreference.getUsername().toLowerCase());
//            }
            this.adminService.saveEmailPreference(emailPreference, username);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     *
     * @return
     */
    public List<EmailTimePreference> getAllEmailTimePreference() {
        try {
            String username = AcegiUtil.getUsername();
            return this.adminService.getAllEmailTimePreference(username);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public int saveEmailTimePreference(EmailTimePreference emailTimePreference) {
        String username = AcegiUtil.getUsername();
        try {
            this.adminService.saveEmailTimePreference(emailTimePreference, username);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    public int deleteEmailTimePreference(EmailTimePreference emailTimePreference) {
        try {
            this.adminService.deleteEmailTimePreference(emailTimePreference, AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    public List<SystemLease> getAllSystemLease() {
        String username = AcegiUtil.getUsername();

        return this.adminService.getAllSystemLease(username);

    }

    /**
     *
     * @return
     */
    public Organization getOrganization() {
        try {
            String username = AcegiUtil.getUsername();
            return this.adminService.getOrganization(username);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    /**
     * 
     * @param organization
     * @return
     */
    public int saveOrganization(Organization organization) {
        try {
            String username = AcegiUtil.getUsername();
            this.adminService.saveOrganization(organization, username);
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
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
            logger.warn(ex.getMessage(), ex);
            return null;
        }

    }

    public List getReport(String startDateString, String endDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);
            logger.trace(startDate + " " + endDate);
            String username = AcegiUtil.getUsername();
            UsersLight u = this.adminService.getUserByUsername(username);
            return this.adminService.getReport(startDate, endDate, u.getOrganization());
        } catch (ParseException ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }

    }

    public int saveService(Services service, HttpServletRequest request) {
        try {
            if (service.getId() == -5) {
                if (logger.isTraceEnabled()) {
                    logger.trace("computer rate save ------ " + service.getUnits() + "  " + service.getUnitPrice());
                    logger.trace("computer rate ------ " + service.getSaleTwoUnits() + " " + service.getSaleTwoPrice());
                }

                adminService.updateRentalPrice(service.getUnits(), service.getUnitPrice(),
                        service.getSaleTwoUnits(), service.getSaleTwoPrice(), AcegiUtil.getUsername(), request.getRemoteAddr());

            } else {
                service.setOrganization(this.getOrganization().getName());
                adminService.saveService(service);
            }
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     * 
     * @param id
     * @return
     */
    public int deleteService(Integer id) {
        try {
            adminService.deleteService(id, AcegiUtil.getUsername());
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }

    /**
     * 
     * @return
     */
    public List<Services> getAllServices() {
        try {
            List<Services> list = this.adminService.getAllServices(AcegiUtil.getUsername());
            return list;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return null;
        }
    }

    public int sendReports() {
        try {
            this.adminService.sendReports();
            return 1;
        } catch (InvalidInputException ex) {
            logger.warn(ex.getMessage(), ex);
            return -2;
        } catch (NoRoleException ex) {
            logger.warn(ex.getMessage(), ex);
            return -3;
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            return -1;
        }
    }
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
