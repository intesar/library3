package com.bia.ccm.ajax;

import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.services.AccountStatusNotificationService;
import com.bia.ccm.services.OrganizationService;
import com.bia.ccm.services.ProductService;
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
import org.springframework.beans.factory.annotation.Autowired;

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
        long organization = themeDisplay.getScopeGroupId();
        this.productService.updateRentalPrice(mims, rate, lmins, lrate, organization);
    }

    /**
     *
     * @return
     */
    public List<Systems> getAllSystems(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        return this.productService.getAllSystems(organization);
    }

    /**
     *  not in use
     * @return
     */
    public List<SystemLease> getAllSystemLease(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        return this.productService.getAllSystemLease(organization);
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
        long organization = themeDisplay.getScopeGroupId();
        return this.productService.getSystemLease(startDate, endDate, organization);
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
        long organization = themeDisplay.getScopeGroupId();
        return this.productService.getReport(startDate, endDate, organization);
    }

    /**
     * 
     * @param service
     * @param request
     * @return
     */
    public void saveService(Services service, HttpServletRequest request, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        this.productService.saveService(service, organization);
    }

    /**
     * 
     * @param id
     * @return
     */
    public void deleteService(Long id, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        productService.deleteService(id, themeDisplay.getScopeGroupId());
    }

    /**
     * 
     * @return
     */
    public List<Services> getAllServices(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        List<Services> list = this.productService.getAllServicesWithSystem(themeDisplay.getScopeGroupId());
        return list;
    }

    /**
     * not in use
     * @return
     */
    public void sendReports() {
        this.productService.sendReports();
    }

    /**
     * checks user belongs to community and he is an admin or owner
     * @param emails
     * @param timings
     * @param session
     */
    public void savePreferences(PreferenceDto preferenceDto, HttpServletRequest request, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        accountStatusNotificationService.savePreferences(preferenceDto, organization);
    }

    /**
     * 
     * @param session
     * @return
     */
    public PreferenceDto getPreferences(HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organization = themeDisplay.getScopeGroupId();
        return this.accountStatusNotificationService.getPreferences(organization);
    }

    /**
     *
     * @param organization
     * @param request
     * @param session
     */
    public void saveOrganization(Organization organization, HttpServletRequest request, HttpSession session) {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organizationId = themeDisplay.getScopeGroupId();
        this.organizationService.saveOrganization(organization, organizationId);
    }

    /**
     *
     * @param session
     * @return
     * @throws PortalException
     * @throws SystemException
     */
    public Organization getOrganization(HttpSession session) throws PortalException, SystemException {
        ThemeDisplay themeDisplay = (ThemeDisplay) session.getAttribute("THEME_DISPLAY");
        long organizationId = themeDisplay.getScopeGroupId();
        return this.organizationService.getOrganization(organizationId);
    }

    public void registerNewOrganization(Long organizationId, String organizationName, String email) {
        this.organizationService.registerNewOrganization(organizationId, organizationName, email);
    }
    //  getters and setters
    @Autowired
    protected ProductService productService;
    @Autowired
    protected OrganizationService organizationService;
    @Autowired
    protected AccountStatusNotificationService accountStatusNotificationService;
    protected static final Log logger = LogFactory.getLog(AjaxAdminService.class);
    
}
