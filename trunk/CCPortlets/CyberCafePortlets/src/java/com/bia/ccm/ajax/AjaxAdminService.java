/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.util.AcegiUtil;
import com.liferay.portal.theme.ThemeDisplay;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @see  src/context/ApplicationContext-AjaxService.xml
 *
 *  This class is mapped to AjaxAdminService bean in the above file
 *
 * @author intesar
 */
public class AjaxAdminService {

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
        try {
            String username = AcegiUtil.getUsername();
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
     *  not in use
     * @return
     */
    public List<SystemLease> getAllSystemLease() {
        String username = AcegiUtil.getUsername();

        return this.adminService.getAllSystemLease(username);

    }

    /**
     *
     * @param startDateString
     * @param endDateString
     * @return
     */
    public List<SystemLease> getSystemLease(String startDateString, String endDateString) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);
            String username = AcegiUtil.getUsername();
            return this.adminService.getSystemLease(startDate, endDate, username);
        } catch (ParseException ex) {
            logger.warn(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     *
     * @param startDateString
     * @param endDateString
     * @return
     */
    public List getReport(String startDateString, String endDateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date startDate = sdf.parse(startDateString);
            Date endDate = sdf.parse(endDateString);
            String username = AcegiUtil.getUsername();
            return this.adminService.getReport(startDate, endDate, username);
        } catch (ParseException ex) {
            logger.warn(ex.getMessage(), ex);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
        return null;
    }

    /**
     * 
     * @param service
     * @param request
     * @return
     */
    public int saveService(Services service, HttpServletRequest request) {
        try {
            this.adminService.saveService(service, AcegiUtil.getUsername(), request.getRemoteAddr());
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

    /**
     * not in use
     * @return
     */
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

    /**
     * checks user belongs to community and he is an admin or owner
     * @param emails
     * @param timings
     * @param session
     */
    public void savePreferences(PreferenceDto preferenceDto, HttpServletRequest request, HttpSession session) {
        // TODO role check
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String organization = "" + themeDisplay.getScopeGroupId();
        String userId = "" + themeDisplay.getUserId(); 
        String ip = request.getRemoteAddr();
        adminService.savePreferences(preferenceDto.getEmails(), preferenceDto.getTimings(), organization, userId, ip);
    }

    public PreferenceDto getPreferences(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String organization = "" + themeDisplay.getScopeGroupId();
        return this.adminService.getPreferences(organization);
    }

    // email and timings
    /**
     *   Response codes
     *   1 == Operation Successfully executed
     *  -1 == Operation execution failed, no reason specified, ask user to try again
     *  -2 == Operation not executed, because of invalid input
     *  -3 == Operation failed (User doesn't have appropriate roles on data), if user believes he has roles ask him to refresh page or relogin
     *
     * only Owner can delete Notification Email
     * @param id
     * @return
     */

    

    public int deleteEmail(int id) {
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

    /**
     *
     * @param emailTimePreference
     * @return
     */
    public int saveEmailTimePreference(EmailTimePreference emailTimePreference) {
        try {
            String username = AcegiUtil.getUsername();
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

    /**
     *
     * @param emailTimePreference
     * @return
     */
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

    //  getters and setters
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    protected static final Log logger = LogFactory.getLog(AjaxAdminService.class);
    protected AdminService adminService;
}
