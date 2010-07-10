/*
 * 
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

    @Override
    public void forgotPassword(String email) {
        email = email.toLowerCase();
        UsersPass up = this.usersPassDao.findByUsernameAndEnabled(email, true);
        this.emailService.sendEmail(up.getUsername(), " Activation Code : " + up.getResetCode());
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
            UsersPass userPass = new UsersPass(null, email, password, true, activationCode_, new Date());
            usersPassDao.create(userPass);
            usersPass.setEnabled(false);
            usersPassDao.update(usersPass);
        } else {
            throw new InvalidInputException("Invalid Inputs");
        }
    }

    @Override
    public void registerNewOrganization(String organizationName, String city,
            String email, String password, Integer minutes, Integer rate, Integer maxSystems, String ip) {

        // setting to lowercase
        email = email.toLowerCase();
        organizationName = organizationName.toLowerCase();
        city = city.toLowerCase();
        Date date = new Date();

        Organization o = new Organization(organizationName, (short) 1, null, city,
                email, city, null, "india", email, "Silver Member", "ccm", 0, date, "self");
        o.setContactEmail(email);
        o.setIp(ip);
        this.organizationDao.create(o);

        String encryptedPassword = passwordEncryptor.encryptPassword(password);
        Users u = new Users(null, email, encryptedPassword,
                true, "admin", organizationName, email);
        u.setIp(ip);
        this.usersDao.create(u);

        UsersLight ul = new UsersLight(email, organizationName);
        this.usersLightDao.create(ul);

        String encryptedPass = this.stringEncryptor.encrypt(password);
        String resetCode = this.stringEncryptor.encrypt(email + Calendar.getInstance().getFirstDayOfWeek());
        UsersPass usersPass = new UsersPass(null, email,
                encryptedPass, true, resetCode, date);
        this.usersPassDao.create(usersPass);

        Authorities adminAuthority = new Authorities(email, "ROLE_ADMIN");
        this.authoritiesDao.create(adminAuthority);

        Authorities userAuthority = new Authorities(email, "ROLE_USER");
        this.authoritiesDao.create(userAuthority);

        // default adding some services
        Services s = new Services(null, "other", 1.0, organizationName, email, date, ip);
        this.servicesDao.create(s);
        Services s1 = new Services(null, "print b&w", 3.0, organizationName, email, date, ip);
        this.servicesDao.create(s1);
        Services s2 = new Services(null, "copy b&w", 1.0, organizationName, email, date, ip);
        this.servicesDao.create(s2);
        Services s3 = new Services(null, "print color", 5.0, organizationName, email, date, ip);
        this.servicesDao.create(s3);
        Services s4 = new Services(null, "scan", 5.0, organizationName, email, date, ip);
        this.servicesDao.create(s4);
        Services s5 = new Services(null, "cool drink", 10.0, organizationName, email, date, ip);
        this.servicesDao.create(s5);

        //Double minuteRate = Double.parseDouble("" + minutes + "." + rate);
        for (int i = 1; i <= 20; i++) {
            boolean enabled = false;
            if (i <= maxSystems) {
                enabled = true;
            }
            Systems systems = new Systems(null, i, organizationName, true, null, minutes, rate, enabled, email, date, ip);
            this.systemsDao.create(systems);
        }

        // last sending an email
        // @Todo change FaceGuard and email format
        emailService.sendEmail(email, "Welcome to FaceGuard, username / password : " + email + " / " + password);

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
