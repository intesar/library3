/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.AuthoritiesDao;
import com.bia.ccm.dao.EmailPreferenceDao;
import com.bia.ccm.dao.EmailTimePreferenceDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemLeaseDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.dao.UsersDao;
import com.bia.ccm.dao.UsersLightDao;
import com.bia.ccm.dao.UsersPassDao;
import com.bia.ccm.entity.Authorities;
import com.bia.ccm.entity.AuthoritiesPK;
import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.entity.UsersPass;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.util.EMailUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

/**
 *
 * @author intesar
 */
public class AdminServiceImpl implements AdminService {

    protected final Log logger = LogFactory.getLog(getClass());

    public void deleteEmail(int id) {
        EmailPreference email = this.emailPreferenceDao.read(id);
        this.emailPreferenceDao.delete(email);
    }

    public void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        String org = u.getOrganization();
        List<Systems> list = this.systemsDao.findByOrganization(org);
        for (Systems s : list) {
            s.setMinimumMinutes(mims);
            s.setMinuteRate(rate);
            if (lmins != null && lmins > 0 && lrate != null && lrate > 0) {
                s.setLowerMinimumMinutes(lmins);
                s.setLowerMinuteRate(lrate);
            }
            this.systemsDao.update(s);
        }
    }

    public List<Systems> getAllSystems(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.systemsDao.findByOrganization(u.getOrganization());
    }

    public void saveSystem(Systems systems, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        try {
            if (systems != null && systems.getId() == null) {
                systems.setOrganization(u.getOrganization());
                systems.setCreateDate(new Date());
                systems.setCreateUser(username);
                systems.setIsAvailable(true);
                systems.setIsShutdown(false);
                this.systemsDao.create(systems);
            } else if (systems != null && systems.getId() != null) {
                this.systemsDao.update(systems);

            } else {
                throw new RuntimeException("Please check inputs");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    public List<Users> getAllUsers(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.usersDao.findByOrganization(u.getOrganization());
    }

    public void saveUser(Users users, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        try {
            if (users != null && users.getId() == null) {
                users.setOrganization(u.getOrganization());
                users.setCreateDate(new Date());
                users.setCreateUser(username);
                users.setEmail(users.getUsername());
                String password = users.getPassword();
                users.setPassword(passwordEncryptor.encryptPassword(users.getPassword()));
                this.usersDao.create(users);
                UsersLight ul = new UsersLight(users.getUsername(), u.getOrganization());
                this.usersLightDao.create(ul);
                if (users.getRole().equalsIgnoreCase("admin")) {
                    Authorities a1 = new Authorities(users.getUsername(), "ROLE_ADMIN");
                    this.authoritiesDao.create(a1);
                }
                Authorities a2 = new Authorities(users.getUsername(), "ROLE_USER");
                this.authoritiesDao.create(a2);
                // storing pass in UsersPass
                String encryptedPass = this.stringEncryptor.encrypt(password);
                String resetCode = this.stringEncryptor.encrypt( username + Calendar.getInstance().getFirstDayOfWeek());
                UsersPass usersPass = new UsersPass(null, users.getUsername(),
                        encryptedPass, true, resetCode, new Date());
                usersPassDao.create(usersPass);
                emailService.sendEmail(u.getUsername(), "Welcome to FaceGuard, username / password : " + u.getUsername() + " / " + password);
            } else if (users != null && users.getId() != null) {
                this.usersDao.update(users);
                if (users.getRole().equalsIgnoreCase("employee")) {
                    Authorities a1 = this.authoritiesDao.read(new AuthoritiesPK(users.getUsername(), "ROLE_ADMIN"));
                    if (a1 != null) {
                        this.authoritiesDao.delete(a1);
                    }
                } else if (users.getRole().equalsIgnoreCase("admin")) {
                    AuthoritiesPK authPK = new AuthoritiesPK(users.getUsername(), "ROLE_ADMIN");
                    Authorities a2 = this.authoritiesDao.read(authPK);
                    if (a2 == null) {
                        a2 = new Authorities(authPK);
                        this.authoritiesDao.create(a2);
                    }
                }
            } else {
                throw new RuntimeException("Please check inputs");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<EmailPreference> getAllEmailPreference(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.emailPreferenceDao.findByOrganization(u.getOrganization());
    }

    public List<EmailPreference> getAllOrganizationEmailPreference(String org) {
        return this.emailPreferenceDao.findByOrganization(org);
    }

    public void saveEmailPreference(
            EmailPreference emailPreference, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        try {
            if (emailPreference != null && emailPreference.getId() == null) {
                emailPreference.setOrganization(u.getOrganization());

                this.emailPreferenceDao.create(emailPreference);
            } else if (emailPreference != null && emailPreference.getId() != null) {
                this.emailPreferenceDao.update(emailPreference);

            } else {
                throw new RuntimeException("Please check inputs");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<EmailTimePreference> getAllEmailTimePreference(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.emailTimePreferenceDao.findByOrganization(u.getOrganization());
    }

    public void saveEmailTimePreference(
            EmailTimePreference emailTimePreference, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        EmailTimePreference etp = null;
        try {
            etp = this.emailTimePreferenceDao.findByOrganizationAndReportTime(u.getOrganization(), emailTimePreference.getReportTime());
        } catch (Exception e) {
            logger.error(e);
        }
        if (emailTimePreference != null && emailTimePreference.getId() == null && etp == null) {
            emailTimePreference.setOrganization(u.getOrganization());
            this.emailTimePreferenceDao.create(emailTimePreference);
        }
//        } else if (emailTimePreference != null && emailTimePreference.getId() != null) {
//            this.emailTimePreferenceDao.update(emailTimePreference);
//
//
//        }
    }

    public void deleteEmailTimePreference(EmailTimePreference emailTimePreference) {
        emailTimePreference = this.emailTimePreferenceDao.read(emailTimePreference.getId());
        this.emailTimePreferenceDao.delete(emailTimePreference);
    }

    public List<SystemLease> getAllSystemLease(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.systemLeaseDao.findByOrganization(u.getOrganization());
    }

    public String saveSystemLease(
            SystemLease systemLease, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Organization getOrganization(
            String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.organizationDao.findByOrganization(u.getOrganization());
    }

    public void saveOrganization(
            Organization organization, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        try {
            if (organization != null) {
                this.organizationDao.update(organization);
            } else {
                throw new RuntimeException("Please check inputs");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }

    public List<SystemLease> getSystemLease(Date startDate, Date endDate, String org) {
        startDate.setHours(0);
        startDate.setMinutes(0);
        endDate.setHours(23);
        endDate.setMinutes(59);
        return this.systemLeaseDao.findByStartAndEndDates(startDate, endDate, org);
    }

    @Override
    public List<SystemLease> getMySystemLease(Date startDate, Date endDate, String username) {
        startDate.setHours(0);
        startDate.setMinutes(0);
        endDate.setHours(23);
        endDate.setMinutes(59);
        return this.systemLeaseDao.findByUsernameAndStartEndDates(username, startDate, endDate);
    }

    public List getReport(
            Date startDate, Date endDate, String org) {
        startDate.setHours(0);
        startDate.setMinutes(0);
        endDate.setHours(23);
        endDate.setMinutes(59);
        return this.systemLeaseDao.findReportBetweenDates(startDate, endDate, org);
    }

    public void saveService(Services service) {
        if (service.getId() == null) {
            this.servicesDao.create(service);
        } else {
            this.servicesDao.update(service);
        }
    }

    public void deleteService(Integer id) {
        Services services = this.servicesDao.read(id);
        //this.servicesDao.update(services);
        this.servicesDao.delete(services);
    }

    public List<Services> getAllServices(String org) {
        return this.servicesDao.findByOrganization(org);
    }

    public void sendReports() {
        List<Organization> orgs = this.organizationDao.readAll().getResults();
        for (Organization organization : orgs) {
            List<EmailPreference> emailPreferences = this.emailPreferenceDao.findByOrganization(organization.getName());
            if (emailPreferences != null && emailPreferences.size() > 0) {
                Date endDate = new Date();
                endDate.setHours(23);
                endDate.setMinutes(59);
                Date startDate = new Date();
                startDate.setHours(0);
                startDate.setMinutes(0);
                List result = getReport(startDate, endDate, organization.getName());
                String[] toAddress = new String[emailPreferences.size()];
                int count = 0;
                String subject = "Courtesy BizIntelApps & FaceQuard.com";
                for (EmailPreference ep : emailPreferences) {
                    String email = null;
                    String emailOrPhone = ep.getEmailOrPhone();
                    String serviceProvider = ep.getServiceProvider();
                    email = EMailUtil.buildEmailAddress(emailOrPhone, serviceProvider);

                    if (email.contains(EMailService._160BY2)) {
                        subject = EMailService.EMAIL_SUBJECT_TEXT_160BY2;
                    }
                    toAddress[count++] = email;
                }

                emailService.sendEmail(toAddress, subject, new Date() + " [Total Minutes, Payable, Paid]" + result.toString());
            }
        }
    }

    public List<EmailTimePreference> getEmailTimePreferences(short time) {
        return this.emailTimePreferenceDao.findByReportTime(time);
    }

    public UsersLight getUserByUsername(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return u;
    }
    // getters & setters
    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void setSystemsDao(SystemsDao systemsDao) {
        this.systemsDao = systemsDao;
    }

    public void setEmailPreferenceDao(EmailPreferenceDao emailPreferenceDao) {
        this.emailPreferenceDao = emailPreferenceDao;
    }

    public void setEmailTimePreferenceDao(EmailTimePreferenceDao emailTimePreferenceDao) {
        this.emailTimePreferenceDao = emailTimePreferenceDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setSystemLeaseDao(SystemLeaseDao systemLeaseDao) {
        this.systemLeaseDao = systemLeaseDao;
    }

    public void setAuthoritiesDao(AuthoritiesDao authoritiesDao) {
        this.authoritiesDao = authoritiesDao;
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
    private UsersLightDao usersLightDao;
    private UsersDao usersDao;
    private UsersPassDao usersPassDao;
    private SystemsDao systemsDao;
    private EmailPreferenceDao emailPreferenceDao;
    private EmailTimePreferenceDao emailTimePreferenceDao;
    private SystemLeaseDao systemLeaseDao;
    private OrganizationDao organizationDao;
    private AuthoritiesDao authoritiesDao;
    private ServicesDao servicesDao;
    private PasswordEncryptor passwordEncryptor;
    private PBEStringEncryptor stringEncryptor;
    
}
