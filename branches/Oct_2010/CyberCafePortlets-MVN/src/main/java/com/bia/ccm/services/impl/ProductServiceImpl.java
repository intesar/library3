/*
 * 
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.EmailPreferenceDao;
import com.bia.ccm.dao.EmailTimePreferenceDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.ProductService;
import com.bia.ccm.services.EMailService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author intesar
 */
@Service(value="productServiceImpl")
public class ProductServiceImpl implements ProductService {

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void updateRentalPrice(int mims, double rate, Integer lmins, Double lrate, long organization) {
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
            this.systemsDao.merge(system);
        }
    }

    @Override
    public List<Systems> getAllSystems(long organization) {
        return this.systemsDao.findByOrganization(organization);
    }

    
//    @Override
//    public List<SystemLease> getAllSystemLease(long organization) {
//        return this.systemLeaseDao.findByOrganization(organization);
//    }
//
//    public String saveSystemLease(
//            SystemLease systemLease, String username) {
//        throw new UnsupportedOperationException("Not supported yet.");
//    }
//
//    @Override
//    public List<SystemLease> getSystemLease(Date startDate, Date endDate, long organization) {
//        Calendar sDate = getCalendar(startDate, 0, 0);
//        Calendar eDate = getCalendar(startDate, 23, 59);
//        return this.systemLeaseDao.findByStartAndEndDates(sDate.getTime(), eDate.getTime(), organization);
//    }
//
//    @Override
//    public List<SystemLease> getMySystemLease(Date startDate, Date endDate, String username) {
//        Calendar sDate = getCalendar(startDate, 0, 0);
//        Calendar eDate = getCalendar(startDate, 23, 59);
//        return this.systemLeaseDao.findByUsernameAndStartEndDates(username, sDate.getTime(), eDate.getTime());
//    }
//
//    @Override
//    public List getReport(
//            Date startDate, Date endDate, long organization) {
//        Calendar sDate = getCalendar(startDate, 0, 0);
//        Calendar eDate = getCalendar(startDate, 23, 59);
//        return systemLeaseDao.findReportBetweenDates(sDate.getTime(), eDate.getTime(), organization);
//    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveService(Services service, long organization) {
        // validation logic
        if (service.getId() != null && service.getId() == -5) {
            this.updateRentalPrice(service.getUnits(), service.getUnitPrice(),
                    service.getSaleTwoUnits(), service.getSaleTwoPrice(), organization);
        } else {
            isNotEmpty(service.getName(), "Please provide a valid Service Name!");
            validateFirstRentalPrice(service.getUnits(), service.getUnitPrice());
            validateSecondRentalPrice(service.getUnits(), service.getUnitPrice(), service.getSaleTwoUnits(), service.getSaleTwoPrice());
            service.setSaleTwoEnabled(false);
            if (service.getSaleTwoUnits() != null && service.getSaleTwoUnits() > 1) {
                service.setSaleTwoEnabled(true);
            }
            // validate organization

            if (service.getId() == null) {
                service.setOrganization(organization);
                this.servicesDao.persist(service);
            } else {
                if ( service.getOrganization() != organization ) {
                    throw new NoRoleException();
                }
                this.servicesDao.merge(service);
            }
        }
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void deleteService(Long id, long organization) {
        if ( id == null || id < 1 || organization < 1 ) {
            throw new InvalidInputException();
        }
        Services services = this.servicesDao.find(id);
        if (services.getOrganization() == organization) {
            servicesDao.delete(services);
        } else {
            throw new NoRoleException();
        }
    }

    @Override
    public List<Services> getAllServices(long organization) {
       return this.servicesDao.findByOrganization(organization);
    }

    @Override
    public List<Services> getAllServicesWithSystem(long organization) {
        List<Services> list = this.servicesDao.findByOrganization(organization);
        Systems system = this.getSystem(organization);
        Services s = new Services();
        s.setId(-5L);
        s.setName("Computer");
        s.setUnits(system.getMinimumMinutes());
        s.setUnitPrice(system.getMinuteRate());
        s.setSaleTwoUnits(system.getLowerMinimumMinutes());
        s.setSaleTwoPrice(system.getLowerMinuteRate());
        list.add(s);
        return list;
    }

    @Override
    public Systems getSystem(long organization) {
        return this.systemsDao.findBySystemNameAndOrganization(1, organization);
    }

//    @Override
//    public void sendReports() {
//        List<Organization> orgs = this.organizationDao.findAll();
//        for (Organization organization : orgs) {
//            List<EmailPreference> emailPreferences = this.emailPreferenceDao.findByOrganization(organization.getId());
//            if (emailPreferences != null && emailPreferences.size() > 0) {
//
//                Calendar startDate = Calendar.getInstance();
//                startDate.set(Calendar.HOUR_OF_DAY, 0);
//                startDate.set(Calendar.MINUTE, 0);
//
//                Calendar endDate = Calendar.getInstance();
//                startDate.set(Calendar.HOUR_OF_DAY, 23);
//                startDate.set(Calendar.MINUTE, 59);
//
//                List result = getReport(startDate.getTime(), endDate.getTime(), organization.getId());
//                String[] toAddress = new String[emailPreferences.size()];
//                int count = 0;
//                String subject = "Courtesy BizIntelApps & FaceQuard.com";
//                for (EmailPreference ep : emailPreferences) {
//                    String email = ep.getEmail();
//
//                    toAddress[count++] = email;
//                }
//
//                emailService.sendEmail(toAddress, subject, new Date() + " [Total Minutes, Payable, Paid]" + result.toString());
//            }
//        }
//    }

    
    // ----------------------------- private methods -------------------------//

    private Calendar getCalendar(Date startDate, int hours, int minutes) {
        Calendar sDate = Calendar.getInstance();
        sDate.setTime(startDate);
        sDate.set(Calendar.HOUR_OF_DAY, hours);
        sDate.set(Calendar.MINUTE, minutes);
        return sDate;
    }
    
    /**
     * 
     * @param value
     * @throws InvalidInputException
     */
    private void isNotEmpty(String value, String errorMessage) throws InvalidInputException {
        if (value == null || value.trim().length() < 1) {
            throw new InvalidInputException(errorMessage);
        }
    }

    /**
     * if mins or rate is less then zero throw exception
     * @param mims > 0
     * @param rate > 0.0
     */
    private void validateFirstRentalPrice(int mims, double rate) {
        if (mims < 0 || rate < 0.0) {
            throw new InvalidInputException("Units Or Price cannot be less than zero");
        } else if ( mims == 0 && rate > 0.0 ) {
            throw new InvalidInputException("Units cannot be zero if setting price!");
        } else if ( mims ==0 && rate == 0.0 ) {
                throw new InvalidInputException("Units & Price cannot be zero!");
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
            throw new InvalidInputException(" Invalid second units Or price!");
        } else if (lmins <= mims || lrate < 0.0) {
            throw new InvalidInputException(" Invalid second units Or price!");
        }
    }

    // getters & setters
    @Autowired
    protected EMailService emailService;
    @Autowired
    protected SystemsDao systemsDao;
    @Autowired
    protected EmailPreferenceDao emailPreferenceDao;
    @Autowired
    protected EmailTimePreferenceDao emailTimePreferenceDao;
    @Autowired
    protected OrganizationDao organizationDao;
    @Autowired
    protected ServicesDao servicesDao;
    protected static final Log logger = LogFactory.getLog(ProductServiceImpl.class);
}
