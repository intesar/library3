/*
 * 
 */
package com.bia.ccm.ajax;

import com.abbhsoft.sqlInjectionFilter.SQLInjectionFilterManager;
import com.bia.ccm.entity.Users;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.UserService;
import com.bia.ccm.services.WorkService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;
import com.bia.converter.CaseConverter;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 *  @see  src/context/ApplicationContext-AjaxService.xml
 *
 *  This class is mapped to AjaxUserService bean in the above file
 *
 * @author intesar
 */
public class UserAjaxService {

    @Deprecated
    public String getLoggedInUserRole() {
        String username = AcegiUtil.getUsername();
        return this.userService.getUserRole(username);
    }

    /**
     * 
     * @param email
     * @return
     */
    public int forgotPassword(String email) {
        try {
            this.userService.forgotPassword(email);
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
     * @param email
     * @param activationCode
     * @param password
     * @param request 
     * @return
     */
    public int resetPassword(String email, String activationCode, String password, HttpServletRequest request) {
        try {
            this.userService.resetPassword(email, activationCode, password, request.getRemoteAddr());
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

    
    public boolean isUserAdmin() {
        return AcegiUtil.isAdmin();
    }

    public String getUserRole() {
        return AcegiUtil.getUserRole();
    }

    /**
     * @see com.bia.ccm.services.impl.UserServiceImpl.registerNewOrganization for more documentation
     * @param organizationName
     * @param city
     * @param email
     * @param password
     * @param minutes
     * @param rate
     * @param maxSystems
     * @return
     */
    public int registerNewOrganization(String organizationName, String city,
            String email, String password, Integer minutes, Integer rate, 
            Integer maxSystems, HttpServletRequest request) {
        try {
            this.userService.registerNewOrganization(organizationName,
                    city, email, password, minutes, rate, maxSystems, request.getRemoteAddr());
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

    public int createCustomer(Users c) {

        String msg = "Customer Created Successfully!";
        try {
//            if (c.getImg() != null) {
//                c.setPic(this.bufferedImageToByteArray(c.getImg()));//this.scaleToSize(c.getImg())
//            }
            c.setCreateDate(new Date());
            c.setEmail(SQLInjectionFilterManager.getInstance().filter(c.getEmail()));
            c.setUsername(SQLInjectionFilterManager.getInstance().filter(c.getUsername()));
            caseConverter.toLowerCase(c);
            logger.info("________________________ before create _________________");
            this.workService.createCutomer(c, null);
            logger.info("________________________ after create _________________");
            emailService.sendEmail(c.getEmail(), "Welcome to FaceGuard, username / password : " + c.getUsername() + " / " + c.getPassword());
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

    public void setCaseConverter(CaseConverter caseConverter) {
        this.caseConverter = caseConverter;
    }
    public void setEmailService(EMailService emailService) {
        this.emailService = emailService;
    }
    protected EMailService emailService;
    protected CaseConverter caseConverter;
    protected static final Log logger = LogFactory.getLog(UserAjaxService.class);
    protected UserService userService = (UserService) ServiceFactory.getService("userServiceImpl");
    protected WorkService workService = (WorkService) ServiceFactory.getService("workServiceImpl");

   
}
