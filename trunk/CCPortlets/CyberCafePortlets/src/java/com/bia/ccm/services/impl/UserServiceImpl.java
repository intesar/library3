/*
 * 
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.dao.UsersDao;
import com.bia.ccm.dao.UsersLightDao;
import com.bia.ccm.dao.UsersPassDao;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.entity.UsersPass;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.UserService;
import java.util.Calendar;
import java.util.Date;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

/**
 *
 * @author intesar
 */
public class UserServiceImpl implements UserService {

    protected final Log logger = LogFactory.getLog(getClass());

    @Override
    public String getUserRole(String username) {
//        AuthoritiesPK authPK1 = new AuthoritiesPK(username, "ROLE_ADMIN");
//        Authorities authorities1 = this.authoritiesDao.read(authPK1);
//        if (authorities1 != null) {
//            return "admin";
//        } else {
//            AuthoritiesPK authPK2 = new AuthoritiesPK(username, "ROLE_USER");
//            Authorities authorities2 = this.authoritiesDao.read(authPK2);
//            if (authorities2 != null) {
//                return "user";
//            }
//        }

        return "";
    }

    @Override
    public void forgotPassword(String email) {
        email = email.toLowerCase();
        UsersPass up = this.usersPassDao.findByUsernameAndEnabled(email, true);
        String[] to = {up.getUsername()};
        this.emailService.sendEmail(to, null, " Activation Code : " + up.getResetCode());
    }

    @Override
    public void resetPassword(String email, String activationCode, String newPassword, String ip) {
        email = email.toLowerCase();
        UsersPass usersPass = usersPassDao.findByUsernameAndEnabled(email, true);
        if (usersPass.getResetCode().trim().equals(activationCode.trim())) {
            Users user = usersDao.findByUsername(email);
            String password = this.passwordEncryptor.encryptPassword(newPassword);
            user.setPassword(password);
            user.setIp(ip);
            usersDao.update(user);
            String activationCode_ = this.stringEncryptor.encrypt(password + email);
            UsersPass newUserPass = new UsersPass(null, email, password, true, activationCode_, new Date());
            usersPassDao.create(newUserPass);
            usersPass.setEnabled(false);
            usersPassDao.update(usersPass);
        } else {
            throw new InvalidInputException("Invalid Inputs");
        }
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void setEMailService(EMailService eMailService) {
        this.emailService = eMailService;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

   
    public void setSystemsDao(SystemsDao systemsDao) {
        this.systemsDao = systemsDao;
    }

    public void setServicesDao(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public void setUsersLightDao(UsersLightDao usersLightDao) {
        this.usersLightDao = usersLightDao;
    }

    public void setUsersPassDao(UsersPassDao usersPassDao) {
        this.usersPassDao = usersPassDao;
    }

    public void setStringEncryptor(PBEStringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public void setEmailService(EMailService emailService) {
        this.emailService = emailService;
    }
    private EMailService emailService;
    private UsersDao usersDao;
    private UsersLightDao usersLightDao;
    private UsersPassDao usersPassDao;
    private OrganizationDao organizationDao;
    private SystemsDao systemsDao;
    private ServicesDao servicesDao;
    private PasswordEncryptor passwordEncryptor;
    private PBEStringEncryptor stringEncryptor;
}
