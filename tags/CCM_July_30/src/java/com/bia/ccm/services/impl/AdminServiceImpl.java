/*
 * 
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
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.util.EMailUtil;
import com.bia.converter.CaseConverter;
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

    @Override
    public void deleteEmail(int id, String username) {
        EmailPreference email = this.emailPreferenceDao.read(id);
        if (!email.getOrganization().equals(this.getOrganization(username).getName())) {
            throw new NoRoleException();
        }
        this.emailPreferenceDao.delete(email);
    }

    @Override
    public void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, String username, String ip) {
        validateFirstRentalPrice(mims, rate);
        validateSecondRentalPrice(mims, rate, lmins, lrate);
        UsersLight u = this.usersLightDao.findByUsername(username);
        String org = u.getOrganization();
        List<Systems> list = this.systemsDao.findByOrganization(org);
        boolean flag = (lmins != null && lmins > 0 && lrate != null && lrate > 0);
        Date today = new Date();
        for (Systems system : list) {
            system.setMinimumMinutes(mims);
            system.setMinuteRate(rate);
            if (flag) {
                system.setLowerMinimumMinutes(lmins);
                system.setLowerMinuteRate(lrate);
            }
            system.setLastModifiedDate(today);
            system.setLastModifiedUser(username);
            system.setLastModifiedIP(ip);
            this.systemsDao.update(system);
        }
    }

    @Override
    public List<Systems> getAllSystems(String username) {
        return this.systemsDao.findByOrganization(this.getOrganization(username).getName());
    }

    @Override
    public void saveSystem(Systems systems, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        if (systems != null && systems.getId() == null) {
            systems.setOrganization(u.getOrganization());
            systems.setCreateDate(new Date());
            systems.setCreateUser(username);
            systems.setIsAvailable(true);
            systems.setIsShutdown(false);
            this.systemsDao.create(systems);
        } else if (systems != null && systems.getId() != null) {
            this.systemsDao.update(systems);
        }
    }

    @Override
    public List<Users> getAllUsers(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        // return name, enabled, isAdmin, password
        List<Users> users = this.usersDao.findByOrganization(u.getOrganization());
        for (Users u1 : users) {
            u1.setPic(null);
        }
        return users;
    }

    @Override
    public void saveUser(Users users, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        if (users == null) {
            throw new InvalidInputException();
        }
        if (users.getId() == null) {
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
            String resetCode = this.stringEncryptor.encrypt(username + Calendar.getInstance().getFirstDayOfWeek());
            UsersPass usersPass = new UsersPass(null, users.getUsername(),
                    encryptedPass, true, resetCode, new Date());
            usersPassDao.create(usersPass);
            String[] to = {u.getUsername()};
            emailService.sendEmail(to, "Welcome", "Welcome to FaceGuard, username / password : " + u.getUsername() + " / " + password);
        } else if (users.getId() != null && users.getId() > 0) {
            Users u1 = this.usersDao.findByUsername(users.getUsername());
            if (!u1.getOrganization().equals(u.getOrganization())) {
                throw new NoRoleException();
            }
            u1.setName(users.getName());
            u1.setEnabled(users.getEnabled());
            if (!users.getPassword().equals(u1.getPassword())) {
                // update password
                String password = users.getPassword();
                u1.setPassword(passwordEncryptor.encryptPassword(users.getPassword()));
                UsersPass usersPass = usersPassDao.findByUsernameAndEnabled(u1.getEmail(), true);
                String activationCode_ = this.stringEncryptor.encrypt(password + u1.getEmail());
                UsersPass newUserPass = new UsersPass(null, u1.getEmail(), password, true, activationCode_, new Date());
                usersPassDao.create(newUserPass);
                usersPass.setEnabled(false);
                usersPassDao.update(usersPass);
            }
            this.usersDao.update(u1);
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
        }
    }

    @Override
    public List<EmailPreference> getAllEmailPreference(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.emailPreferenceDao.findByOrganization(u.getOrganization());
    }

    @Override
    public List<EmailPreference> getAllOrganizationEmailPreference(String org) {
        return this.emailPreferenceDao.findByOrganization(org);
    }

    @Override
    public void saveEmailPreference(
            EmailPreference emailPreference, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        if (emailPreference.getId() == null) {
            emailPreference.setOrganization(u.getOrganization());
            this.emailPreferenceDao.create(emailPreference);
        } else if (emailPreference.getId() != null) {
            emailPreference.setOrganization(u.getOrganization());
            this.emailPreferenceDao.update(emailPreference);
        }
    }

    @Override
    public List<EmailTimePreference> getAllEmailTimePreference(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.emailTimePreferenceDao.findByOrganization(u.getOrganization());
    }

    @Override
    public void saveEmailTimePreference(
            EmailTimePreference emailTimePreference, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        EmailTimePreference etp = null;
        try {
            etp = this.emailTimePreferenceDao.findByOrganizationAndReportTime(u.getOrganization(), emailTimePreference.getReportTime());
            if (etp == null) {
                emailTimePreference.setOrganization(u.getOrganization());
                this.emailTimePreferenceDao.create(emailTimePreference);
            }
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            throw new InvalidInputException();
        }
    }

    @Override
    public void deleteEmailTimePreference(EmailTimePreference emailTimePreference, String username) {
        emailTimePreference = this.emailTimePreferenceDao.read(emailTimePreference.getId());
        if (!emailTimePreference.getOrganization().equals(this.getOrganization(username).getName())) {
            throw new NoRoleException();
        }
        this.emailTimePreferenceDao.delete(emailTimePreference);
    }

    @Override
    public List<SystemLease> getAllSystemLease(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.systemLeaseDao.findByOrganization(u.getOrganization());
    }

    public String saveSystemLease(
            SystemLease systemLease, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Organization getOrganization(
            String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.organizationDao.findByOrganization(u.getOrganization());
    }

    @Override
    public void saveOrganization(
            Organization organization, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        Organization org = this.organizationDao.findByOrganization(u.getOrganization());
        if (!org.getName().equals(organization.getName())) {
            throw new NoRoleException();
        }

        org.setStreet(organization.getStreet());
        org.setCity(organization.getCity());
        org.setState(organization.getState());
        org.setZipcode(organization.getZipcode());
        org.setCountry(organization.getCountry());
        org.setContactName(organization.getContactName());
        org.setContactEmail(organization.getContactEmail());
        org.setPhone(organization.getPhone());
        org.setPrintEmail(organization.getPrintEmail());
        org.setTimings(organization.getTimings());
        this.organizationDao.update(org);
    }

    @Override
    public List<SystemLease> getSystemLease(Date startDate, Date endDate, String username) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate);
        sDate.set(Calendar.HOUR, 0);
        sDate.set(Calendar.MINUTE, 0);

        Calendar eDate = Calendar.getInstance();
        eDate.setTime(endDate);
        eDate.set(Calendar.HOUR, 23);
        eDate.set(Calendar.MINUTE, 59);
        return this.systemLeaseDao.findByStartAndEndDates(sDate.getTime(), eDate.getTime(), this.getOrganization(username).getName());
    }

    @Override
    public List<SystemLease> getMySystemLease(Date startDate, Date endDate, String username) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate);
        sDate.set(Calendar.HOUR, 0);
        sDate.set(Calendar.MINUTE, 0);

        Calendar eDate = Calendar.getInstance();
        eDate.setTime(endDate);
        eDate.set(Calendar.HOUR, 23);
        eDate.set(Calendar.MINUTE, 59);
        return this.systemLeaseDao.findByUsernameAndStartEndDates(username, sDate.getTime(), eDate.getTime());
    }

    @Override
    public List getReport(
            Date startDate, Date endDate, String username) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate);
        sDate.set(Calendar.HOUR, 0);
        sDate.set(Calendar.MINUTE, 0);

        Calendar eDate = Calendar.getInstance();
        eDate.setTime(endDate);
        eDate.set(Calendar.HOUR, 23);
        eDate.set(Calendar.MINUTE, 59);

        return systemLeaseDao.findReportBetweenDates(sDate.getTime(), eDate.getTime(), this.getOrganization(username).getName());
    }

    @Override
    /**
     *  service contains ( name, organization, unitPrice, saleTwoUnits, saleTwoPrice, saleTwoEnabled )
     *
     *  unitPrice is for one unit of product or service
     *  if admin sets saleTwoUnits > 1 and saleTwoPrice > 0 then make saleTwoEnabled to true
     *    it means that if there is sale of product or services >= saleTwoUnits
     *    then apply saleTwoPrice per unit
     *    eg name = color print, organzation = sample, unitPrice = 5.00
     *           saleTwoEnabled = true, saleTwoUnits = 10, saleTwoPrice = 4
     *     case 1 user request for 4 copies 4 * 5.00 = 20.00
     *     case 2 user request for 20 copes 20 * 4.00 = 800.00
     *
     */
    public void saveService(Services service, String username, String ip) {
        // validation logic
        if (service.getId() == -5) {
            this.updateRentalPrice(service.getUnits(), service.getUnitPrice(),
                    service.getSaleTwoUnits(), service.getSaleTwoPrice(), username, ip);
        } else {
            isNotEmpty(service.getName());
            validateFirstRentalPrice(service.getUnits(), service.getUnitPrice());
            validateSecondRentalPrice(service.getUnits(), service.getUnitPrice(), service.getSaleTwoUnits(), service.getSaleTwoPrice());
            service.setSaleTwoEnabled(false);
            if (service.getSaleTwoUnits() != null && service.getSaleTwoUnits() > 1) {
                service.setSaleTwoEnabled(true);
            } 
            service.setIp(ip);

            if (service.getId() == null) {
                service.setCreateDate(new Date());
                service.setCreateUser(username);
                this.servicesDao.create(service);
            } else {
                service.setLastModifiedDate(new Date());
                service.setLastModifiedUser(username);
                this.servicesDao.update(service);
            }
        }
    }



    @Override
    public void deleteService(Integer id, String username) {
        String org = this.usersLightDao.findByUsername(username).getOrganization();
        Services services = this.servicesDao.read(id);
        if (org.equals(services.getOrganization())) {
            servicesDao.delete(services);
        } else {
            throw new NoRoleException();
        }
    }

    @Override
    public List<Services> getAllServices(String username) {
        String org = this.usersLightDao.findByUsername(username).getOrganization();
        List<Services> list = this.servicesDao.findByOrganization(org);
        Systems system = this.getSystem(org);
        Services s = new Services();
        s.setId(-5);
        s.setName("Computer");
        s.setUnits(system.getMinimumMinutes());
        s.setUnitPrice(system.getMinuteRate());
        s.setSaleTwoUnits(system.getLowerMinimumMinutes());
        s.setSaleTwoPrice(system.getLowerMinuteRate());
        list.add(s);
        return list;
    }

    @Override
    public Systems getSystem(String org) {
        return this.systemsDao.findBySystemNameAndOrganization(1, org);
    }

    @Override
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

    @Override
    public List<EmailTimePreference> getEmailTimePreferences(short time) {
        return this.emailTimePreferenceDao.findByReportTime(time);
    }

    @Override
    public UsersLight getUserByUsername(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return u;
    }
    // ----------------------------- private methods -------------------------//

    /**
     * 
     * @param value
     * @throws InvalidInputException
     */
    private void isNotEmpty(String value) throws InvalidInputException {
        if (value == null || value.trim().length() < 1) {
            throw new InvalidInputException();
        }
    }

    /**
     * if mins or rate is less then zero throw exception
     * @param mims > 0
     * @param rate > 0.0
     */
    private void validateFirstRentalPrice(int mims, double rate) {
        if (mims < 0 || rate < 0.0) {
            throw new InvalidInputException();
        }
    }

    /**
     * valid cases
     *   lmins & lrate null
     *   lmins > mims & lrate > rate
     * invalid cases
     *   lmins not null and lrate null or vise-versa
     *   lmins <= mims or lrate < rate
     * @param mims
     * @param rate
     * @param lmins
     * @param lrate
     */
    private void validateSecondRentalPrice(int mims, double rate, Integer lmins, Double lrate) {
        if (lmins == null && lrate == null) {
            return;
        } else if ((lmins != null && lrate == null) || (lmins == null && lrate != null)) {
            throw new InvalidInputException();
        } else if (lmins <= mims || lrate < rate) {
            throw new InvalidInputException();
        }
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

    public void setCaseConverter(CaseConverter caseConverter) {
        this.caseConverter = caseConverter;
    }
    private CaseConverter caseConverter;
    protected EMailService emailService;
    protected UsersLightDao usersLightDao;
    protected UsersDao usersDao;
    protected UsersPassDao usersPassDao;
    protected SystemsDao systemsDao;
    protected EmailPreferenceDao emailPreferenceDao;
    protected EmailTimePreferenceDao emailTimePreferenceDao;
    protected SystemLeaseDao systemLeaseDao;
    protected OrganizationDao organizationDao;
    protected AuthoritiesDao authoritiesDao;
    protected ServicesDao servicesDao;
    protected PasswordEncryptor passwordEncryptor;
    protected PBEStringEncryptor stringEncryptor;
    protected static final Log logger = LogFactory.getLog(AdminServiceImpl.class);
}
