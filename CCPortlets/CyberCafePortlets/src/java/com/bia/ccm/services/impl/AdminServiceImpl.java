/*
 * 
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.EmailPreferenceDao;
import com.bia.ccm.dao.EmailTimePreferenceDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemLeaseDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.util.EMailUtil;
import com.bia.converter.CaseConverter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
    public void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, String organization, String username, String ip) {
        validateFirstRentalPrice(mims, rate);
        validateSecondRentalPrice(mims, rate, lmins, lrate);
        List<Systems> list = this.systemsDao.findByOrganization(organization);
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
    public List<Systems> getAllSystems(String organization) {
        return this.systemsDao.findByOrganization(organization);
    }

    @Override
    public List<EmailTimePreference> getEmailTimePreferences(short time) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<EmailPreference> getAllOrganizationEmailPreference(String organization) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void savePreferences(Set<String> emails, Set<Short> timings, String organization,
            String userId, String ip) {
        List<EmailPreference> emailPreferences = this.emailPreferenceDao.findByOrganization(organization);
        // delete all and add new
        for (EmailPreference emailPreference : emailPreferences) {
            this.emailPreferenceDao.delete(emailPreference);
        }
        for (String email : emails) {
            EmailPreference emailPreference = new EmailPreference(email, organization, userId, userId, ip);
            this.emailPreferenceDao.create(emailPreference);
        }
        List<EmailTimePreference> emailTimePreferences = this.emailTimePreferenceDao.findByOrganization(organization);
        for (EmailTimePreference emailTimePreference : emailTimePreferences) {
            this.emailTimePreferenceDao.delete(emailTimePreference);
        }
        for (Short timing : timings) {
            EmailTimePreference emailTimePreference = new EmailTimePreference(timing, organization, userId, ip);
            this.emailTimePreferenceDao.create(emailTimePreference);
        }
    }

    @Override
    public PreferenceDto getPreferences(String organization) {
        PreferenceDto dto = new PreferenceDto();
        List<EmailPreference> emailPreferences = this.emailPreferenceDao.findByOrganization(organization);
        for (EmailPreference emailPreference : emailPreferences) {
            dto.getEmails().add(emailPreference.getEmailOrPhone());
        }
        List<EmailTimePreference> emailTimePreferences = this.emailTimePreferenceDao.findByOrganization(organization);
        for (EmailTimePreference emailTimePreference : emailTimePreferences) {
            dto.getTimings().add(emailTimePreference.getReportTime());
        }
        return dto;
    }

    @Override
    public List<SystemLease> getAllSystemLease(String organization) {
        return this.systemLeaseDao.findByOrganization(organization);
    }

    public String saveSystemLease(
            SystemLease systemLease, String username) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<SystemLease> getSystemLease(Date startDate, Date endDate, String organization) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate);
        sDate.set(Calendar.HOUR, 0);
        sDate.set(Calendar.MINUTE, 0);

        Calendar eDate = Calendar.getInstance();
        eDate.setTime(endDate);
        eDate.set(Calendar.HOUR, 23);
        eDate.set(Calendar.MINUTE, 59);
        return this.systemLeaseDao.findByStartAndEndDates(sDate.getTime(), eDate.getTime(), organization);
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
            Date startDate, Date endDate, String organization) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate);
        sDate.set(Calendar.HOUR, 0);
        sDate.set(Calendar.MINUTE, 0);

        Calendar eDate = Calendar.getInstance();
        eDate.setTime(endDate);
        eDate.set(Calendar.HOUR, 23);
        eDate.set(Calendar.MINUTE, 59);

        return systemLeaseDao.findReportBetweenDates(sDate.getTime(), eDate.getTime(), organization);
    }

    @Override
    public void saveService(Services service, String username, String organization, String ip) {
        // validation logic
        if (service.getId() == -5) {
            this.updateRentalPrice(service.getUnits(), service.getUnitPrice(),
                    service.getSaleTwoUnits(), service.getSaleTwoPrice(), organization, username, ip);
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
    public void deleteService(Integer id, String organization) {
        Services services = this.servicesDao.read(id);
        if (organization.equals(services.getOrganization())) {
            servicesDao.delete(services);
        } else {
            throw new NoRoleException();
        }
    }

    @Override
    public List<Services> getAllServices(String organization) {
        List<Services> list = this.servicesDao.findByOrganization(organization);
        Systems system = this.getSystem(organization);
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
        } else if (lmins <= mims || lrate < 0.0) {
            throw new InvalidInputException();
        }
    }

    // getters & setters
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

    public void setServicesDao(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
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
    protected SystemsDao systemsDao;
    protected EmailPreferenceDao emailPreferenceDao;
    protected EmailTimePreferenceDao emailTimePreferenceDao;
    protected SystemLeaseDao systemLeaseDao;
    protected OrganizationDao organizationDao;
    protected ServicesDao servicesDao;
    protected PasswordEncryptor passwordEncryptor;
    protected PBEStringEncryptor stringEncryptor;
    protected static final Log logger = LogFactory.getLog(AdminServiceImpl.class);

    
}
