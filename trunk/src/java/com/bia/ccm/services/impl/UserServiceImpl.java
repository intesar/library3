/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.AuthoritiesDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.dao.UsersDao;
import com.bia.ccm.dao.UsersLightDao;
import com.bia.ccm.dao.UsersPassDao;
import com.bia.ccm.entity.Authorities;
import com.bia.ccm.entity.AuthoritiesPK;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.entity.UsersPass;
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

    public String getUserRole(String username) {
        AuthoritiesPK authPK1 = new AuthoritiesPK(username, "ROLE_ADMIN");
        Authorities authorities1 = this.authoritiesDao.read(authPK1);
        if (authorities1 != null) {
            return "admin";
        } else {
            AuthoritiesPK authPK2 = new AuthoritiesPK(username, "ROLE_USER");
            Authorities authorities2 = this.authoritiesDao.read(authPK2);
            if (authorities2 != null) {
                return "user";
            }
        }

        return "";
    }

    public void resetPassword(String email, String activationCode, String password) {
        UsersPass usersPass = usersPassDao.findByUsernameAndEnabled(email, true);
        if (usersPass.getResetCode().trim().equals(activationCode.trim())) {
            Users u = usersDao.findByUsername(email);
            String password1 = this.passwordEncryptor.encryptPassword(password);
            u.setPassword(password1);
            usersDao.update(u);
            String p = this.stringEncryptor.encrypt(password);
            String ac = this.stringEncryptor.encrypt(p + email);
            UsersPass up = new UsersPass(null, email, p, true, ac, new Date());
            usersPassDao.create(up);
            usersPass.setEnabled(false);
            usersPassDao.update(usersPass);
        } else {
            throw new RuntimeException("Invalid Inputs");
        }
    }

    public void forgotPassword(String email) {

        try {
            UsersPass up = this.usersPassDao.findByUsernameAndEnabled(email, true);
            if (up == null) {
                throw new RuntimeException("No Registered user found with this email : " + email);
            }

            this.emailService.sendEmail(up.getUsername(), " Activation Code : " + up.getResetCode());
        } catch (NullPointerException npe) {
            logger.error(npe);
            throw new RuntimeException(" No match found!");
        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }

    }

    public void registerNewOrganization(String organizationName, String city,
            String email, String password, Integer minutes, Integer rate, Integer maxSystems) {
        Organization o = new Organization(organizationName, (short) 1, null, city,
                email, city, null, "india", email, "Silver Member", "ccm", 0, new Date(), "self");
        o.setContactEmail(email);
        
        Users u = new Users(null, email, passwordEncryptor.encryptPassword(password),
                true, "admin", organizationName, email);
        UsersLight ul = new UsersLight(email, organizationName);
        Authorities a1 = new Authorities(email, "ROLE_ADMIN");
        Authorities a2 = new Authorities(email, "ROLE_USER");
        Services s = new Services(null, "other", 1.0, organizationName);
        Services s1 = new Services(null, "print b&w", 3.0, organizationName);
        Services s2 = new Services(null, "copy b&w", 1.0, organizationName);
        Services s3 = new Services(null, "print color", 5.0, organizationName);
        Services s4 = new Services(null, "scan", 5.0, organizationName);
        Services s5 = new Services(null, "cool drink", 10.0, organizationName);
        String encryptedPass = this.stringEncryptor.encrypt(password);
        String resetCode = this.stringEncryptor.encrypt(email  + Calendar.getInstance().getFirstDayOfWeek());
        UsersPass usersPass = new UsersPass(null, email,
                encryptedPass, true, resetCode, new Date());

        try {
            this.usersDao.create(u);
            this.usersLightDao.create(ul);
            usersPassDao.create(usersPass);
            this.authoritiesDao.create(a1);
            this.authoritiesDao.create(a2);
            this.organizationDao.create(o);
            this.servicesDao.create(s);
            this.servicesDao.create(s1);
            this.servicesDao.create(s2);
            this.servicesDao.create(s3);
            this.servicesDao.create(s4);
            this.servicesDao.create(s5);
            //Double minuteRate = Double.parseDouble("" + minutes + "." + rate);
            for (int i = 1; i <=
                    50; i++) {
                boolean enabled = false;
                if (i <= maxSystems) {
                    enabled = true;
                }

                Systems systems = new Systems(null, i, organizationName, true, null, minutes, rate, enabled);
                this.systemsDao.create(systems);
            }

        } catch (Exception e) {
            logger.error(e);
            throw new RuntimeException(e);
        }

    }

//    @Override
//    public Users getUser(Integer id) {
//        return this.usersDao.read(id);
//    }
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void setEMailService(EMailService eMailService) {
        this.emailService = eMailService;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setAuthoritiesDao(AuthoritiesDao authoritiesDao) {
        this.authoritiesDao = authoritiesDao;
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
    private AuthoritiesDao authoritiesDao;
    private SystemsDao systemsDao;
    private ServicesDao servicesDao;
    private PasswordEncryptor passwordEncryptor;
    private PBEStringEncryptor stringEncryptor;
}


