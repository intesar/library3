/*
 * 
 */
package com.bia.ccm.ajax;

import com.bia.ccm.entity.Users;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.UserService;
import com.bia.ccm.services.WorkService;
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
public class AjaxUserService {

  

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

    /**
     *
     * @param user
     * @return
     */
    public int createCustomer(Users user, HttpServletRequest request) {
        try {
            user.setIp(request.getRemoteAddr());
            this.workService.createCutomer(user, null);
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

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public void setWorkService(WorkService workService) {
        this.workService = workService;
    }

    
    protected static final Log logger = LogFactory.getLog(AjaxUserService.class);
    protected UserService userService;
    protected WorkService workService;

   
}
