/*
 * 
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.MembershipDiscountsDao;
import com.bia.ccm.dao.MembershipTypesDao;
import com.bia.ccm.dao.MembershipsDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.SaleService;
import com.bizintelapps.easydao.dao.PagingParams;
import java.text.SimpleDateFormat;
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
@Service(value="saleServiceImpl")
public class SaleServiceImpl implements SaleService {

    @Override
    public String getUserEmailByMacAddress(String macAddress) {
        Systems system = this.systemsDao.findByMacAddress(macAddress);
        logger.trace("--------------" + system);
        if (system != null) {
            Organization org = this.organizationDao.find(system.getOrganization());
            logger.trace("--------------" + org);
            logger.trace("--------------" + org.getContactEmail());
            if (org != null && org.getContactEmail() != null && org.getContactEmail().length() > 5) {
                logger.trace("-------------- inside getContactEmail ");
                return org.getContactEmail();
            }
        }
        return "faceguard@bizintelapps.com";
    }

    @Override
    public List<Systems> getActiveSystems(long organization) {
        return this.systemsDao.findByOrganization(organization);
    }

    @Override
    public Systems getSystemByNameAndOrganization(int systemNo, long organization) {
        return this.systemsDao.findBySystemNameAndOrganization(systemNo, organization);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void leaseSystem(long id, String leaseHolder, String cashier) {
        Systems system = this.systemsDao.find(id);
        system.setIsAvailable(false);
        system.setCurrentUserEmail(leaseHolder.toLowerCase());
        system.setStartTime(new Date());
        this.systemsDao.merge(system);
//        SystemLease systemLease = new SystemLease(null, new Date(), cashier, system.getId(), false);
//        systemLease.setService("computer");
//        systemLease.setLeaseHolderName(leaseHolder);
//        systemLease.setSystemNo(system.getName());
//        this.systemLeaseDao.persist(systemLease);
    }

    /**
     * 
     * @param id
     * @return
     *   total minues
     *   Start Time
     *   End Time
     *   Lease Holder Name
     *   Payable Amount
     *   System Name
     */
//    @Override
//    public List<SystemLease> getSystemLease(long id) {
//        List<SystemLease> list = this.systemLeaseDao.findBySystemIdAndFinished(id);
//        for (SystemLease s : list) {
//            if (s.getTotalMinutesUsed() == null || s.getService().startsWith("computer")) {
//                update(s);
//            }
//        }
//        return list;
//    }

//    @Override
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void chargePayment(long systemId, String agent, long organization) {
//        Systems s = systemsDao.find(systemId);
//        if (! (s.getOrganization() == organization) ) {
//            throw new NoRoleException();
//        }
//        List<SystemLease> list = getSystemLease(systemId);
//        for (SystemLease sl : list) {
//
//            if (sl.getPayableAmount() != null) {
//                sl.setAmountPaid(sl.getPayableAmount());
//            }
//
//            sl.setReturnAgent(agent);
//            sl.setIsFinished(true);
//            Date endTime = new Date();
//            if (sl.getService().startsWith("computer")) {
//                if (((new Date().getTime() - sl.getEndTime().getTime()) / (60 * 1000)) > 1) {
//                    throw new InvalidInputException("This Record is not updated for more than 1 minutes, Please refresh again!");
//                }
//                Long totalMinutes = (endTime.getTime() - sl.getStartTime().getTime()) / (60 * 1000);
//                sl.setTotalMinutesUsed(totalMinutes);
//            }
//            sl.setEndTime(new Date());
//            this.systemLeaseDao.merge(sl);
//        }
//        Systems system = this.systemsDao.find(systemId);
//        system.setIsAvailable(true);
//        system.setCurrentUserEmail("");
//        system.setStartTime(null);
//        this.systemsDao.merge(system);
//    }

    /**
     * get customer
     * if customer is not null
     * find wheather customer is member of this internet cafe
     * if so what is his percentage discount
     * apply if any
     * update the record
     * @param systemlease
     */
    private double getDiscountPercentage(long organization, String customerEmail, String service) {
        double discountPercentage = 0.0;
//        Memberships membership = membershipsDao.findByOrganizationAndEmailAndActive(organization + "", customerEmail);
//        if (membership != null && membership.getExpirationDate().after(new Date()) && membership.isIsActive()) {
//            MembershipTypes membershipTypes = membershipTypesDao.find(membership.getMembershipType());
//            MembershipDiscounts membershipDiscounts = membershipDiscountsDao.findByMembershipTypesIdAndService(membershipTypes.getId(), service);
//            discountPercentage = membershipDiscounts.getDiscountPercentage();
//        }
        return discountPercentage;
    }

//    private void update(SystemLease systemLease) {
//        Systems system = this.systemsDao.find(systemLease.getSystem());
//        Double rate = system.getMinuteRate();
//        Date endTime = new Date();
//        Long totalMinutes = (endTime.getTime() - systemLease.getStartTime().getTime()) / (1000 * 60);
//        Double payableAmount = null;
//        logger.debug("rate ******** : " + rate);
//        // if (rate >= 1.0) {
//        if (system.getLowerMinuteRate() != null && system.getLowerMinuteRate() > 0.0
//                && system.getLowerMinimumMinutes() != null && system.getLowerMinimumMinutes() > 0) {
//            // apply pattern for dual rate
//            Double amt = 0.0;
//            long quotient = totalMinutes / system.getMinimumMinutes();
//            long reminder = totalMinutes % system.getMinimumMinutes();
//            if (reminder <= system.getLowerMinimumMinutes()) {
//                amt = system.getLowerMinuteRate();
//            } else {
//                amt = system.getMinuteRate();
//            }
//            payableAmount = amt + quotient * system.getMinuteRate();
//        } else {
//            Integer minimumMins = system.getMinimumMinutes();//Double.parseDouble(rateString.substring(0, a));
//            logger.debug("minimumMins ******** : " + minimumMins);
//            payableAmount = Math.ceil((double) totalMinutes / minimumMins) * rate;
//            logger.debug("Math.ceil((double)totalMinutes / minimumMins) ******** : " + Math.ceil(totalMinutes / minimumMins));
//            logger.debug("payableAmount ******** : " + payableAmount);
//        }
//        systemLease.setTotalMinutesUsed(totalMinutes);
//        systemLease.setEndTime(endTime);
//        //applyDiscount(system.getOrganization(), systemLease.getLeaseHolderName(), "computer", payableAmount, systemLease);
//        //systemLease.setDiscounts(0.0);
//        //systemLease.setComments("You din't get any Discounts, since you din't have any membership!");
//
//    }

//    @Override
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void addService(String service, long units, String user, double payableAmount,
//            String comments, double paidAmount, String agent, long organization) {
//        if (units <= 0 || payableAmount <= 0 || paidAmount <= 0) {
//            throw new NoRoleException();
//        }
//
//        int u = 0;
//        try {
//            u = Integer.parseInt(user.trim());
//        } catch (Exception e) {
//            logger.debug(e.getMessage());
//        }
//        if (u == 0) {
//            Systems s = systemsDao.findBySystemNameAndOrganization(1, organization);
//            SystemLease sl = new SystemLease(null, new Date(), agent, s.getId(), true);
//            sl.setAmountPaid(paidAmount);
//            sl.setEndTime(new Date());
//            sl.setLeaseHolderName(user);
//            sl.setPayableAmount(payableAmount);
//            sl.setReturnAgent(agent);
//            sl.setService(service);
//            sl.setTotalMinutesUsed(units);
//            this.systemLeaseDao.persist(sl);
//        } else {
//            Systems s = systemsDao.findBySystemNameAndOrganization(u, organization);
//            SystemLease sl = new SystemLease(null, new Date(), agent, s.getId(), false);
//            sl.setEndTime(new Date());
//            sl.setLeaseHolderName(s.getCurrentUserEmail());
//            applyDiscount(organization, user, service, payableAmount, sl);
//            sl.setService(service);
//            sl.setTotalMinutesUsed(units);
//            this.systemLeaseDao.persist(sl);
//        }
//
//    }
//
//    private void applyDiscount(long organization, String user, String service, double payableAmount, SystemLease sl) {
//        // finding if user is member
//        double discount = this.getDiscountPercentage(organization, user, service);
//
//        if (discount > 0.0 && discount <= 100) {
//            try {
//                double amt = payableAmount * ((100 - discount) / 100);
//                sl.setPayableAmount(Math.ceil(amt));
//                sl.setDiscounts(payableAmount - Math.ceil(amt));
//                sl.setComments("You have received " + discount + "% Discount");
//            } catch (Exception e) {
//                logger.error(e);
//            }
//        } else {
//            sl.setPayableAmount(payableAmount);
//            sl.setDiscounts(0.0);
//            sl.setComments("You received " + discount + "% Discount");
//        }
//    }

//    @Override
//    @Transactional(propagation=Propagation.REQUIRED)
//    public UsageDetail getPayableAmount(long id) {
//        SystemLease systemLease = null;//this.systemLeaseDao.findBySystemAndFinished(id);
//        System.out.println(systemLease);
//        Systems system = this.systemsDao.find(id);
//        Double rate = system.getMinuteRate();
//        Date endTime = new Date();
//        Long totalMinutes = (endTime.getTime() - systemLease.getStartTime().getTime()) / (1000 * 60);
//
//        Double payableAmount = null;
//        logger.debug("rate ******** : " + rate);
//        if (rate >= 1.0) {
//            Integer minimumMins = system.getMinimumMinutes();//Double.parseDouble(rateString.substring(0, a));
//            logger.debug("minimumMins ******** : " + minimumMins);
//            payableAmount = Math.ceil((double) totalMinutes / minimumMins) * rate;
//            logger.debug("Math.ceil((double)totalMinutes / minimumMins) ******** : " + Math.ceil(totalMinutes / minimumMins));
//            logger.debug("payableAmount ******** : " + payableAmount);
//        } else {
//            payableAmount = totalMinutes * rate;
//        }
//        String pattern = "hh:mm";
//        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
//        String str = " User : " + systemLease.getLeaseHolderName() + " \n"
//                + " System : " + system.getName() + " \n"
//                + " Start Time : " + sdf.format(systemLease.getStartTime()) + " \n"
//                + " End Time : " + sdf.format(endTime) + " \n"
//                + " Total Minutes : " + totalMinutes + " \n"
//                + " Payable Amount : " + payableAmount;
//        UsageDetail ud = new UsageDetail(str, payableAmount);
//        return ud;
//    }
//
//    @Override
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void unleaseSystem(long id, double amountPaid, String cashier) {
//        if (id <= 0 || amountPaid <= 0) {
//            throw new InvalidInputException();
//        }
//
//        Systems system = this.systemsDao.find(id);
//        system.setIsAvailable(true);
//        this.systemsDao.merge(system);
//        SystemLease systemLease = null;//this.systemLeaseDao.findBySystemAndFinished(id);
//        systemLease.setEndTime(new Date());
//        systemLease.setIsFinished(true);
//        systemLease.setAmountPaid(amountPaid);
//        Double rate = system.getMinuteRate();
//        Date endTime = new Date();
//        Long totalMinutes = (endTime.getTime() - systemLease.getStartTime().getTime()) / (1000 * 60);
//        Double payableAmount = null;
//        if (rate >= 1.0) {
//            Double rt = system.getMinuteRate();//Double.parseDouble(rateString.substring(a + 1, a + 2));
//            Integer minimumMins = system.getMinimumMinutes();//Double.parseDouble(rateString.substring(0, a));
//            payableAmount = Math.ceil((double) totalMinutes / minimumMins) * rt;
//        } else {
//            payableAmount = totalMinutes * rate;
//        }
//        systemLease.setPayableAmount(payableAmount);
//        systemLease.setReturnAgent(cashier);
//        systemLease.setTotalMinutesUsed(totalMinutes);
//        this.systemLeaseDao.persist(systemLease);
//
//    }
//
//    /**
//     *  0 -error
//     *  1 - do nothing
//     *  2 - logoff
//     *  3 - shutdown
//     * @param macAddress
//     * @return
//     */
//    @Override
//    public Integer getSystemStatus(String macAddress) {
//        Integer status = new Integer(0);
//        logger.debug("inside system status ");
//        try {
//            Systems system = this.systemsDao.findByMacAddress(macAddress);
//            logger.debug("found system : " + system);
//            if (system == null || system.getOrganization()  <= 0) {
//                logger.debug(" return 0 because " + system);
//                return 0;
//            }
//            long org = system.getOrganization();
//            Long count = this.systemsDao.findNoOfActiveSystemsByOrganization(org);
//            logger.debug("systems idles : " + count);
//            if (count >= 1) {
//                return 3;
//            }
//
//            if (system.getIsShutdown()) {
//                status = 3;
//            } else if (system.getIsAvailable()) {
//                status = 2;
//            } else if (!system.getIsAvailable()) {
//                status = 1;
//            }
//            logger.debug("status " + status);
//        } catch (NullPointerException ex) {
//            logger.warn(ex.getMessage(), ex);
//        } catch (RuntimeException ex) {
//            logger.warn(ex.getMessage(), ex);
//        } catch (Exception ex) {
//            logger.warn(ex.getMessage(), ex);
//        }
//        return status;
//    }
//
//    @Override
//    public List<Services> getAllServices(long organization) {
//        return this.servicesDao.findByOrganization(organization);
//    }
//
//    @Override
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void notifyCustomersAtContractStart() {
//        int start = 0;
//        int limit = 100;
//        for (; true; start += limit) {
//            List<SystemLease> list = this.systemLeaseDao.findByIsStartContractNotified(false, new PagingParams(start, limit));
//            logger.debug("********* list ");
//            if (list == null || list.size() <= 0) {
//                break;
//            }
//            logger.debug("********* before iteration");
//            for (SystemLease sl : list) {
//                try {
//                    logger.debug("********* before method1 " + sl.getService());
//                    if (!sl.getLeaseHolderName().equalsIgnoreCase("Walkin Customer")) {
//                        String[] to = {sl.getLeaseHolderName()};
//                        this.emailService.sendEmail(to, null, getStringAtContractStart(sl, sl.getIssueAgent()));
//                    }
//                    sl.setIsStartContractNotified(true);
//                    this.systemLeaseDao.merge(sl);
//                } catch (RuntimeException ex) {
//                    logger.warn(ex.getMessage(), ex);
//                }
//            }
//        }
//
//    }
//
//    @Override
//    @Transactional(propagation=Propagation.REQUIRED)
//    public void notifyCustomersAtContractEnd() {
//        int start = 0;
//        int limit = 100;
//        for (; true; start += limit) {
//            List<SystemLease> list = this.systemLeaseDao.findByIsEndContractNotified(false, new PagingParams(start, limit));
//            logger.debug("********* list ");
//            if (list == null || list.size() <= 0) {
//                break;
//            }
//            logger.debug("********* before iteration");
//            for (SystemLease sl : list) {
//                try {
//                    logger.debug("********* before method1 " + sl.getService());
//                    logger.debug("********* System id " + sl.getId());
//                    System.out.println("********* System id " + sl.getId());
//                    if (!sl.getLeaseHolderName().equalsIgnoreCase("Walkin Customer")) {
//                        String[] to = {sl.getLeaseHolderName()};
//                        this.emailService.sendEmail(to, null, getStringAtContractStart(sl, sl.getReturnAgent()));
//                    }
//                    sl.setIsEndContractNotified(true);
//                    this.systemLeaseDao.merge(sl);
//                } catch (RuntimeException ex) {
//                    logger.warn(ex.getMessage(), ex);
//                }
//            }
//        }
//    }
//
//    public String getStringAtContractStart(SystemLease sl, String cashier) {
//        //Organization org = organizationDao.findByOrganization(organization);
////        List<Services> serviceses = servicesDao.findByOrganization(sl);
////        String s = "";
////        for (Services ss : serviceses) {
////            s += "<tr><td>" + ss.getName() + "</td><td> " + ss.getUnitPrice() + "</td></tr>";
////
////        }
//        String str = "";
//        str += "Dear " + sl.getLeaseHolderName() + " Welcome to FaceGuard.org, <br> "
//                + "At FaceGuard.org We care your Security. <br> "
//                + "Use of Service Report: <br> "
//                //                + "Cyber Cafe  Name : " + org.getName() + " <br> "
//                //                + org.getName() + " Contact No : " + org.getPhone() + " <br> "
//                //                + org.getName() + " Contact Email : " + org.getContactEmail() + " <br> "
//                //                + org.getName() + " Address : " + org.getStreet() + " " + org.getCity() + " <br> "
//                //                + org.getName() + " Admin At Kiosk : " + sl.getIssueAgent() + " <br> "
//                //                + org.getName() + " Operating Hours : " + org.getTimings() + " <br> "
//                //                + org.getName() + " Print Email : " + org.getPrintEmail() + " <br> "
//                + "Service      :" + sl.getService() + " <br> "
//                + "Start Time : " + sl.getStartTimeString() + " <br> "
//                + "<br> "
//                //                + org.getName() + " Also Offers:  <br> "
//                //                + "<table> <thead> <tr> <td> Service </td> <td>  Unit Price </td> </tr> </thead><body> " + s
//                //                + " If someone else have used the above service at " + org.getName() + " please contact and report Cyber Cafe or Email us at info@bizintelapps.com < br > "
//                + " Thanks  <br>"
//                + "Team BizIntelApps (Business Intelligent Application) & FaceGuard.org ";
//        return str;
//    }
//
//    public String getStringAtContractEnd(SystemLease sl, String cashier) {
////        Organization org = organizationDao.findByOrganization(organization);
////        List<Services> serviceses = servicesDao.findByOrganization(org.getName());
////        String s = "";
////        for (Services ss : serviceses) {
////            s += "<tr><td>" + ss.getName() + "</td><td> " + ss.getUnitPrice() + "</td></tr>";
////
////        }
//        String str = "";
//        str += "Dear " + sl.getLeaseHolderName() + " Welcome to FaceGuard.org, <br> "
//                + "At FaceGuard.org We care your Security. <br> "
//                + "Use of Service Report: <br> "
//                //                + "Cyber Cafe  Name : " + org.getName() + " <br> "
//                //                + org.getName() + " Contact No : " + org.getPhone() + " <br> "
//                //                + org.getName() + " Contact Email : " + org.getContactEmail() + " <br> "
//                //                + org.getName() + " Address : " + org.getStreet() + " " + org.getCity() + " <br> "
//                //                + org.getName() + " Admin, Issed By : " + sl.getIssueAgent() + " <br> "
//                //                + org.getName() + " Operating Hours : " + org.getTimings() + " <br> "
//                + "Service      :" + sl.getService() + " <br> "
//                + "Start Time : " + sl.getStartTimeString() + " <br> "
//                + "End Time : " + sl.getEndTimeString() + " <br> "
//                + "Total Minutest :" + sl.getTotalMinutesUsed() + "<br> "
//                + "Payable Amount :" + sl.getPayableAmount() + "<br> "
//                + "Paid Amount :" + sl.getAmountPaid() + "<br> "
//                //                + org.getName() + " Admin, Paid To : " + sl.getReturnAgent() + " <br> "
//                //                + org.getName() + " Print Email : " + org.getPrintEmail() + " <br> "
//                //                + "<br> "
//                //                + org.getName() + " Also Offers:  <br> "
//                //                + "<table> <thead> <tr> <td> Service </td> <td>  Unit Price </td> </tr> </thead><body> " + s + "</body></table>"
//                //                + " If someone else have used the above service at " + org.getName() + " please contact and report Cyber Cafe or Email us at info@bizintelapps.com < br > "
//                + " -- <br> "
//                + " Thanks  <br>"
//                + "Team BizIntelApps (Business Intelligent Application) & FaceGuard.org ";
//        return str;
//    }

   

    @Autowired
    protected EMailService emailService;
    @Autowired
    protected SystemsDao systemsDao;
    @Autowired
    protected OrganizationDao organizationDao;
    @Autowired
    protected ServicesDao servicesDao;
//    @Autowired
    protected MembershipsDao membershipsDao;
//    @Autowired
    protected MembershipTypesDao membershipTypesDao;
//    @Autowired
    protected MembershipDiscountsDao membershipDiscountsDao;
    protected final Log logger = LogFactory.getLog(getClass());
    
}
