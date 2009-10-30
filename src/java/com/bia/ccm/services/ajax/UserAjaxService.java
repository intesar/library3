/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.ajax;

import com.abbhsoft.sqlInjectionFilter.SQLInjectionFilterManager;
import com.bia.ccm.entity.Users;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.UserService;
import com.bia.ccm.services.WorkService;
import com.bia.ccm.util.AcegiUtil;
import com.bia.ccm.util.ServiceFactory;
import com.bia.converter.CaseConverter;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class UserAjaxService {

    public String getLoggedInUserRole() {
        String username = AcegiUtil.getUsername();
        return this.userService.getUserRole(username);
    }

    public String resetPassword(String email, String activationCode, String password) {
        String str = "successful";
        try {
            email = SQLInjectionFilterManager.getInstance().filter(email);
            //activationCode  = SQLInjectionFilterManager.getInstance().filter(activationCode);            
            this.userService.resetPassword(email, activationCode, password);
        } catch ( RuntimeException re) {
            logger.error(re);
            str = "please check your inputs!";
        }
        return str;
    }
    public String forgotPassword(String email) {

        email = SQLInjectionFilterManager.getInstance().filter(email);

        String msg = " Please check your email for your password! ";
        try {
            this.userService.forgotPassword(email.toLowerCase());
        } catch (RuntimeException re) {
            logger.error(re);
            msg = re.getMessage();
        }
        return msg;

    }

    public boolean isUserAdmin() {
        return AcegiUtil.isAdmin();
    }

    public String getUserRole() {
        return AcegiUtil.getUserRole();
    }

    public String registerNewOrganization(String organizationName, String city,
            String email, String password, Integer minutes, Integer rate, Integer maxSystems) {
        if (organizationName != null) {
            organizationName = SQLInjectionFilterManager.getInstance().filter(organizationName);
            organizationName = organizationName.toLowerCase();
        }
        if (city != null) {
            city = SQLInjectionFilterManager.getInstance().filter(city);
            city = city.toLowerCase();
        }
        if (email != null) {
            email = SQLInjectionFilterManager.getInstance().filter(email);
            email = email.toLowerCase();
        }

        logger.debug("error");
        String str = "Please login with your email and password";
        try {
            this.userService.registerNewOrganization(organizationName,
                    city, email, password, minutes, rate, maxSystems);
            emailService.sendEmail(email, "Welcome to FaceGuard, username / password : " + email + " / " + password);

            return str;
        } catch (Exception e) {
            e.printStackTrace();
            logger.debug(e);
            return "Error creating new organization, try registering with different Email or Organization Name";
        }

    }

    public String createCustomer(Users c) {

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
        } catch (RuntimeException re) {
            logger.error(re);
            re.printStackTrace();
            msg = re.getMessage();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e);
            msg = e.getMessage();
        }
        return msg;
    }
    
    public void setCaseConverter(CaseConverter caseConverter) {
        this.caseConverter = caseConverter;
    }
     public void setEmailService(EMailService emailService) {
        this.emailService = emailService;
    }
    private EMailService emailService;
    private CaseConverter caseConverter;
    protected final Log logger = LogFactory.getLog(getClass());
    protected UserService userService = (UserService) ServiceFactory.getService("userServiceImpl");
    private WorkService workService = (WorkService) ServiceFactory.getService("workServiceImpl");
    

    public static void main(String[] args) {
        UserAjaxService uas = new UserAjaxService();
        uas.registerNewOrganization("apolokk", "hyd", "intesar.mohammed@bizintelapps.com", "apollo13", 15, 50, 10);
        System.out.println(" _________________________________ ");
        //System.out.println(uas.userService.getUser(1));
        System.out.println(" _________________________________ ");

    }
}
