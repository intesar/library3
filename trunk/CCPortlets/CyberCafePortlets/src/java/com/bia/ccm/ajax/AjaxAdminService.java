/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.services.AdminService;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
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
    public void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, HttpServletRequest request, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        String organization = themeDisplay.getScopeGroupId() + "";
        this.adminService.updateRentalPrice(mims, rate, lmins, lrate, username, organization, request.getRemoteAddr());
    }

    /**
     *
     * @return
     */
    public List<Systems> getAllSystems(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String organization = themeDisplay.getScopeGroupId() + "";
        return this.adminService.getAllSystems(organization);
    }

    /**
     *  not in use
     * @return
     */
    public List<SystemLease> getAllSystemLease(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String organization = themeDisplay.getScopeGroupId() + "";
        return this.adminService.getAllSystemLease(organization);
    }

    /**
     *
     * @param startDateString
     * @param endDateString
     * @return
     */
    public List<SystemLease> getSystemLease(String startDateString, String endDateString, HttpSession session) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateString);
        Date endDate = sdf.parse(endDateString);
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        return this.adminService.getSystemLease(startDate, endDate, username);
    }

    /**
     *
     * @param startDateString
     * @param endDateString
     * @return
     */
    public List getReport(String startDateString, String endDateString, HttpSession session) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse(startDateString);
        Date endDate = sdf.parse(endDateString);
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String username = themeDisplay.getUserId() + "";
        return this.adminService.getReport(startDate, endDate, username);
    }

    /**
     * 
     * @param service
     * @param request
     * @return
     */
    public void saveService(Services service, HttpServletRequest request, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String organization = themeDisplay.getScopeGroupId() + "";
        this.adminService.saveService(service, themeDisplay.getUserId() + "", organization, request.getRemoteAddr());
    }

    /**
     * 
     * @param id
     * @return
     */
    public void deleteService(Integer id, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        adminService.deleteService(id, themeDisplay.getScopeGroupId() + "");
    }

    /**
     * 
     * @return
     */
    public List<Services> getAllServices(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        List<Services> list = this.adminService.getAllServices("" + themeDisplay.getScopeGroupId());
        return list;
    }

    /**
     * not in use
     * @return
     */
    public void sendReports() {
        this.adminService.sendReports();
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

    /**
     * 
     * @param session
     * @return
     */
    public PreferenceDto getPreferences(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        String organization = "" + themeDisplay.getScopeGroupId();
        return this.adminService.getPreferences(organization);
    }

    public void saveOrganization(Organization organization, HttpServletRequest request, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organizationId = themeDisplay.getScopeGroupId();
        long userId = themeDisplay.getUserId();
        String ip = request.getRemoteAddr();
        this.adminService.saveOrganization(organization, organizationId, userId, ip);
    }

    public Organization getOrganization(HttpSession session) throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organizationId = themeDisplay.getScopeGroupId();
        String organizationName = themeDisplay.getScopeGroupName();
        return this.adminService.getOrganization(organizationId, organizationName);
    }

    //  getters and setters
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    protected AdminService adminService;
    protected static final Log logger = LogFactory.getLog(AjaxAdminService.class);
    
}
