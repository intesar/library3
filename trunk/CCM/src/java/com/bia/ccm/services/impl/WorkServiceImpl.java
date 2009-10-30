/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.impl;

import com.abbhsoft.jpadaoframework.dao.PagingParams;
import com.bia.ccm.dao.MembershipDiscountsDao;
import com.bia.ccm.dao.MembershipTypesDao;
import com.bia.ccm.dao.MembershipsDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemLeaseDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.dao.UsersDao;
import com.bia.ccm.dao.UsersLightDao;
import com.bia.ccm.dao.UsersPassDao;
import com.bia.ccm.entity.MembershipDiscounts;
import com.bia.ccm.entity.MembershipTypes;
import com.bia.ccm.entity.Memberships;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import com.bia.ccm.entity.Users;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.entity.UsersPass;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.WorkService;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.util.password.PasswordEncryptor;

/**
 *
 * @author intesar
 */
public class WorkServiceImpl implements WorkService {

    public String getUserEmailByMacAddress(String macAddress) {
        Systems system = this.systemsDao.findByMacAddress(macAddress);
        logger.info("--------------" + system);
        if (system != null) {
            Organization org = this.organizationDao.findByOrganization(system.getOrganization());
            logger.info("--------------" + org);
            logger.info("--------------" + org.getContactEmail());
            if (org != null && org.getContactEmail() != null && org.getContactEmail().length() > 5) {
                logger.info("-------------- inside getContactEmail ");
                return org.getContactEmail();
            }
        }
        return "faceguard@bizintelapps.com";
    }

    public List<Systems> getActiveSystems(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.systemsDao.findByOrganization(u.getOrganization());
    }

    public Systems getSystemByNameAndOrganization(int systemNo, String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.systemsDao.findBySystemNameAndOrganization(systemNo, u.getOrganization());
    }

    public void leaseSystem(int id, String leaseHolder, String cashier) {
        Systems system = this.systemsDao.read(id);
        system.setIsAvailable(false);
        system.setCurrentUserEmail(leaseHolder);
        system.setStartTime(new Date());
        this.systemsDao.update(system);
        SystemLease systemLease = new SystemLease(null, new Date(), cashier, system.getId(), false);
        systemLease.setService("computer " + system.getName());
        systemLease.setLeaseHolderName(leaseHolder);
        systemLease.setSystemNo(system.getName());
        this.systemLeaseDao.create(systemLease);
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
    public List<SystemLease> getSystemLease(int id) {
        List<SystemLease> list = this.systemLeaseDao.findBySystemIdAndFinished(id);
        for (SystemLease s : list) {
            if (s.getTotalMinutesUsed() == null || s.getService().startsWith("computer")) {
                update(s);
            }
        }
        return list;
    }

    public void chargePayment(int systemId, String agent) {
        Systems s = systemsDao.read(systemId);
        UsersLight u = this.usersLightDao.findByUsername(agent);
        if (!s.getOrganization().equals(u.getOrganization())) {
            throw new RuntimeException("We are keeping an Eye on you! ");
        }
        List<SystemLease> list = getSystemLease(systemId);
        for (SystemLease sl : list) {

            if (sl.getPayableAmount() != null) {
                sl.setAmountPaid(sl.getPayableAmount());
            }

            sl.setReturnAgent(agent);
            sl.setIsFinished(true);
            Date endTime = new Date();
            if (sl.getService().startsWith("computer")) {
                if (((new Date().getTime() - sl.getEndTime().getTime()) / (60 * 1000)) > 1) {
                    throw new RuntimeException("This Record is not updated for more than 1 minutes, Please refresh again!");
                }
                Long totalMinutes = (endTime.getTime() - sl.getStartTime().getTime()) / (60 * 1000);
                sl.setTotalMinutesUsed(totalMinutes);
            }
            sl.setEndTime(new Date());
            this.systemLeaseDao.update(sl);
        }
        Systems system = this.systemsDao.read(systemId);
        system.setIsAvailable(true);
        system.setCurrentUserEmail("");
        system.setStartTime(null);
        this.systemsDao.update(system);
    }

    /**
     * get customer
     * if customer is not null
     * find wheather customer is member of this internet cafe
     * if so what is his percentage discount
     * apply if any
     * update the record
     * @param systemlease
     */
    private double getDiscountPercentage(String org, String customerEmail, String service) {
        double discountPercentage = 0.0;
        Memberships membership = membershipsDao.findByOrganizationAndEmail(org, customerEmail);
        if (membership != null && membership.getExpirationDate().after(new Date()) && membership.isIsActive()) {
            MembershipTypes membershipTypes = membershipTypesDao.read(membership.getMembershipType());
            MembershipDiscounts membershipDiscounts = membershipDiscountsDao.findByMembershipTypesIdAndService(membershipTypes.getId(), service);
            discountPercentage = membershipDiscounts.getDiscountPercentage();
        }
        return discountPercentage;
    }

    private void update(SystemLease systemLease) {
        Systems system = this.systemsDao.read(systemLease.getSystem());
        Double rate = system.getMinuteRate();
        Date endTime = new Date();
        Long totalMinutes = (endTime.getTime() - systemLease.getStartTime().getTime()) / (1000 * 60);
        Double payableAmount = null;
        logger.debug("rate ******** : " + rate);
        // if (rate >= 1.0) {
        if (system.getLowerMinuteRate() != null && system.getLowerMinuteRate() > 0.0 &&
                system.getLowerMinimumMinutes() != null && system.getLowerMinimumMinutes() > 0) {
            // apply pattern for dual rate
            Double amt = 0.0;
            long quotient = totalMinutes / system.getMinimumMinutes();
            long reminder = totalMinutes % system.getMinimumMinutes();
            if (reminder <= system.getLowerMinimumMinutes()) {
                amt = system.getLowerMinuteRate();
            } else {
                amt = system.getMinuteRate();
            }
            payableAmount = amt + quotient * system.getMinuteRate();
        } else {
            Integer minimumMins = system.getMinimumMinutes();//Double.parseDouble(rateString.substring(0, a));
            logger.debug("minimumMins ******** : " + minimumMins);
            payableAmount = Math.ceil((double) totalMinutes / minimumMins) * rate;
            logger.debug("Math.ceil((double)totalMinutes / minimumMins) ******** : " + Math.ceil(totalMinutes / minimumMins));
            logger.debug("payableAmount ******** : " + payableAmount);
        }
        systemLease.setTotalMinutesUsed(totalMinutes);
        systemLease.setEndTime(endTime);
        applyDiscount(system.getOrganization(), systemLease.getLeaseHolderName(), "computer", payableAmount, systemLease);
    //systemLease.setDiscounts(0.0);
    //systemLease.setComments("You din't get any Discounts, since you din't have any membership!");

    }

    public void addService(String service, long units, String user, double payableAmount,
            String comments, double paidAmount, String agent) {
        int u = 0;
        UsersLight user1 = this.usersLightDao.findByUsername(agent);
        try {
            u = Integer.parseInt(user.trim());
        } catch (Exception e) {
            logger.debug(e.getMessage());
        }
        if (u == 0) {
            Systems s = systemsDao.findBySystemNameAndOrganization(1, user1.getOrganization());
            SystemLease sl = new SystemLease(null, new Date(), agent, s.getId(), true);
            sl.setAmountPaid(paidAmount);
            sl.setEndTime(new Date());
            sl.setLeaseHolderName(user);
            sl.setPayableAmount(payableAmount);
            sl.setReturnAgent(agent);
            sl.setService(service);
            sl.setTotalMinutesUsed(units);
            this.systemLeaseDao.create(sl);
        } else {
            Systems s = systemsDao.findBySystemNameAndOrganization(u, user1.getOrganization());
            SystemLease sl = new SystemLease(null, new Date(), agent, s.getId(), false);
            sl.setEndTime(new Date());
            sl.setLeaseHolderName(s.getCurrentUserEmail());
            applyDiscount(user1.getOrganization(), user, service, payableAmount, sl);
            sl.setService(service);
            sl.setTotalMinutesUsed(units);
            this.systemLeaseDao.create(sl);
        }

    }

    private void applyDiscount(String org, String user, String service, double payableAmount, SystemLease sl) {
        // finding if user is member
        double discount = this.getDiscountPercentage(org, user, service);

        if (discount > 0.0 && discount <= 100) {
            try {
                double amt = payableAmount * ((100 - discount) / 100);
                sl.setPayableAmount(Math.ceil(amt));
                sl.setDiscounts(payableAmount - Math.ceil(amt));
                sl.setComments("You have received " + discount + "% Discount");
            } catch (Exception e) {
                logger.error(e);
            }
        } else {
            sl.setPayableAmount(payableAmount);
            sl.setDiscounts(0.0);
            sl.setComments("You received " + discount + "% Discount");
        }
    }

    public UsageDetail getPayableAmount(int id) {
        SystemLease systemLease = null;//this.systemLeaseDao.findBySystemAndFinished(id);
        System.out.println(systemLease);
        Systems system = this.systemsDao.read(id);
        Double rate = system.getMinuteRate();
        Date endTime = new Date();
        Long totalMinutes = (endTime.getTime() - systemLease.getStartTime().getTime()) / (1000 * 60);

        Double payableAmount = null;
        logger.debug("rate ******** : " + rate);
        if (rate >= 1.0) {
            Integer minimumMins = system.getMinimumMinutes();//Double.parseDouble(rateString.substring(0, a));
            logger.debug("minimumMins ******** : " + minimumMins);
            payableAmount = Math.ceil((double) totalMinutes / minimumMins) * rate;
            logger.debug("Math.ceil((double)totalMinutes / minimumMins) ******** : " + Math.ceil(totalMinutes / minimumMins));
            logger.debug("payableAmount ******** : " + payableAmount);
        } else {
            payableAmount = totalMinutes * rate;
        }
        String pattern = "hh:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String str = " User : " + systemLease.getLeaseHolderName() + " \n" +
                " System : " + system.getName() + " \n" +
                " Start Time : " + sdf.format(systemLease.getStartTime()) + " \n" +
                " End Time : " + sdf.format(endTime) + " \n" +
                " Total Minutes : " + totalMinutes + " \n" +
                " Payable Amount : " + payableAmount;
        UsageDetail ud = new UsageDetail(str, payableAmount);
        return ud;
    }

    public void unleaseSystem(int id, double amountPaid, String cashier) {
        Systems system = this.systemsDao.read(id);
        system.setIsAvailable(true);
        this.systemsDao.update(system);
        SystemLease systemLease = null;//this.systemLeaseDao.findBySystemAndFinished(id);
        systemLease.setEndTime(new Date());
        systemLease.setIsFinished(true);
        systemLease.setAmountPaid(amountPaid);
        Double rate = system.getMinuteRate();
        Date endTime = new Date();
        Long totalMinutes = (endTime.getTime() - systemLease.getStartTime().getTime()) / (1000 * 60);
        Double payableAmount = null;
        if (rate >= 1.0) {
            Double rt = system.getMinuteRate();//Double.parseDouble(rateString.substring(a + 1, a + 2));
            Integer minimumMins = system.getMinimumMinutes();//Double.parseDouble(rateString.substring(0, a));
            payableAmount = Math.ceil((double) totalMinutes / minimumMins) * rt;
        } else {
            payableAmount = totalMinutes * rate;
        }
        systemLease.setPayableAmount(payableAmount);
        systemLease.setReturnAgent(cashier);
        systemLease.setTotalMinutesUsed(totalMinutes);
        this.systemLeaseDao.create(systemLease);

    }

    /**
     *  0 -error
     *  1 - do nothing
     *  2 - logoff
     *  3 - shutdown
     * @param macAddress
     * @return
     */
    public Integer getSystemStatus(String macAddress) {
        Integer status = new Integer(0);
        logger.debug("inside system status ");
        try {
            Systems system = this.systemsDao.findByMacAddress(macAddress);
            logger.debug("found system : " + system);
            if (system == null || system.getOrganization() == null || system.getOrganization().length() <= 0) {
                logger.debug(" return 0 because " + system);
                return 0;
            }
            String org = system.getOrganization();
            Long count = this.systemsDao.findNoOfActiveSystemsByOrganization(org);
            logger.debug("systems idles : " + count);
            if (count >= 1) {
                return 3;
            }

            if (system.getIsShutdown()) {
                status = 3;
            } else if (system.getIsAvailable()) {
                status = 2;
            } else if (!system.getIsAvailable()) {
                status = 1;
            }
            logger.debug("status " + status);
        } catch (NullPointerException npe) {
            logger.error(npe);
            npe.printStackTrace();
        } catch (RuntimeException re) {
            logger.error(re);
            re.printStackTrace();
        } catch (Exception e) {
            logger.error(e);
            e.printStackTrace();
        }
        return status;
    }

    public void createCutomer(Users customer, Users createUser) {

        if (customer.getId() == null) {
//            Users user = usersDao.findByUsername(customer.getEmail());
//            logger.info("________________________ before createCustomer1 _________________");
//            if ( user != null && user.getUsername() != null ) {
//                throw new RuntimeException("User already exists exception");
//            }
            customer.setUsername(customer.getEmail());
            customer.setPassword(new Date().getTime() + "");
            customer.setPassword(passwordEncryptor.encryptPassword(customer.getPassword()));
            customer.setEnabled(true);
            customer.setCreateDate(new Date());
            if (createUser != null) {
                customer.setCreateUser(createUser.getUsername());
            }
            logger.debug("__________________________________ " + "inside save customer");
            UsersLight ul = new UsersLight(customer.getUsername(), null);
            String encryptedPass = this.stringEncryptor.encrypt(new Date().getTime() + "");
            String resetCode = this.stringEncryptor.encrypt(customer.getEmail() + Calendar.getInstance().getFirstDayOfWeek());
            UsersPass usersPass = new UsersPass(null, customer.getEmail(),
                    encryptedPass, true, resetCode, new Date());


            if (customer.getImg() != null) {
                customer.setPic(this.bufferedImageToByteArray(customer.getImg()));
            }
            //Users u = usersDao.findByUsername(customer.getUsername());
            logger.info("________________________ before createCustomer _________________");
            this.usersDao.create(customer);
            this.usersPassDao.create(usersPass);
            this.usersLightDao.create(ul);
            logger.info("________________________ after createCustomer _________________");
        } else {
            // get img then update
            // if img is not null copy img to pic and save it
            if (customer.getImg() != null) {
                customer.setPic(this.bufferedImageToByteArray(customer.getImg()));
            }
//            if (customer.getPic() == null) {
//                Users c = this.usersDao.read(customer.getId());
//                customer.setPic(c.getPic());
//            } else if (customer.getPic() != null) {
//                customer.setPic(this.bufferedImageToByteArray(customer.getImage()));
//            }
            this.usersDao.update(customer);
        }
    }

    public List<Services> getAllServices(String username) {
        UsersLight u = this.usersLightDao.findByUsername(username);
        return this.servicesDao.findByOrganization(u.getOrganization());
    }

    public void notifyCustomersAtContractStart() {
        long start = 0;
        int limit = 100;
        for (; true; start += limit) {
            List<SystemLease> list = this.systemLeaseDao.findByIsStartContractNotified(false, new PagingParams(start, limit, null));
            logger.debug("********* list ");
            if (list == null || list.size() <= 0) {
                break;
            }
            logger.debug("********* before iteration");
            for (SystemLease sl : list) {
                try {
                    logger.debug("********* before method1 " + sl.getService());
                    if (!sl.getLeaseHolderName().equalsIgnoreCase("Walkin Customer")) {
                        this.emailService.sendEmail(sl.getLeaseHolderName(), getStringAtContractStart(sl, sl.getIssueAgent()));
                    }
                    sl.setIsStartContractNotified(true);
                    this.systemLeaseDao.update(sl);
                } catch (RuntimeException re) {
                    re.printStackTrace();
                    logger.error(re);
                }
            }
        }

    }

    public void notifyCustomersAtContractEnd() {
        long start = 0;
        int limit = 100;
        for (; true; start += limit) {
            List<SystemLease> list = this.systemLeaseDao.findByIsEndContractNotified(false, new PagingParams(start, limit, null));
            logger.debug("********* list ");
            if (list == null || list.size() <= 0) {
                break;
            }
            logger.debug("********* before iteration");
            for (SystemLease sl : list) {
                try {
                    logger.debug("********* before method1 " + sl.getService());
                    logger.debug("********* System id " + sl.getId());
                    System.out.println ( "********* System id " + sl.getId() );
                    if (!sl.getLeaseHolderName().equalsIgnoreCase("Walkin Customer")) {
                        this.emailService.sendEmail(sl.getLeaseHolderName(), getStringAtContractStart(sl, sl.getReturnAgent()));
                    }
                    sl.setIsEndContractNotified(true);
                    this.systemLeaseDao.update(sl);
                } catch (RuntimeException re) {
                    re.printStackTrace();
                    logger.error(re);
                }
            }
        }
    }

    public Users getCustomerPic(String key) {
        Users u = this.usersDao.findByKey(key);
        if (u.getPic() != null) {
            u.setImage(this.byteArrayToBufferedImage(u.getPic()));
        }
        return u;
    }

    public Users getCustomer(String key) {
        return this.usersDao.findByKey(key);
    }

    public Users getCustomerWithPic(String key) {
        Users u = this.usersDao.findByKey(key);
        //u.setImage(this.byteArrayToBufferedImage(u.getUserPic().getPic()));
        return u;
    }

    private BufferedImage byteArrayToBufferedImage(byte[] bytes) {
        try {
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(bytes));
            return image;
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Voodoo to scale the image to 200x200
     * @param uploadImage The image to work on
     * @return The altered image
     */
    private BufferedImage scaleToSize(BufferedImage uploadImage) {
        AffineTransform atx = new AffineTransform();
        atx.scale(400d / uploadImage.getWidth(), 400d / uploadImage.getHeight());
        AffineTransformOp afop = new AffineTransformOp(atx, AffineTransformOp.TYPE_BILINEAR);
        uploadImage = afop.filter(uploadImage, null);
        return uploadImage;
    }

    /**
     * And scrawl the text on the image in 10 rows of 20 chars
     * @param uploadImage The image to work on
     * @param uploadFile The text to write on the image
     * @param color The selected color
     * @return The altered image
     */
    private BufferedImage grafitiTextOnImage(BufferedImage uploadImage) {

        Graphics2D g2d = uploadImage.createGraphics();
        for (int row = 0; row < 10; row++) {
            String output = null;


            g2d.setFont(new Font("SansSerif", Font.BOLD, 16));

            g2d.drawString(output, 5, (row + 1) * 20);
        }

        return uploadImage;
    }

    private byte[] bufferedImageToByteArray(BufferedImage aBufferedImage) {
        try {
            // O P E N
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // W R I T E
            ImageIO.write(aBufferedImage, "jpg", baos);

            // C L O S E
            baos.flush();
            byte[] resultImageAsRawBytes = baos.toByteArray();
            baos.close();
            return resultImageAsRawBytes;


        } catch (IOException ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public String getStringAtContractStart(SystemLease sl, String cashier) {
        Users u = usersDao.findByUsername(cashier);
        Organization org = organizationDao.findByOrganization(u.getOrganization());
        List<Services> serviceses = servicesDao.findByOrganization(org.getName());
        String s = "";
        for (Services ss : serviceses) {
            s += "<tr><td>" + ss.getName() + "</td><td> " + ss.getUnitPrice() + "</td></tr>";

        }
        String str = "";
        str += "Dear " + sl.getLeaseHolderName() + " Welcome to FaceGuard.org, <br> " +
                "At FaceGuard.org We care your Security. <br> " +
                "Use of Service Report: <br> " +
                "Cyber Cafe  Name : " + org.getName() + " <br> " +
                org.getName() + " Contact No : " + org.getPhone() + " <br> " +
                org.getName() + " Contact Email : " + org.getContactEmail() + " <br> " +
                org.getName() + " Address : " + org.getStreet() + " " + org.getCity() + " <br> " +
                org.getName() + " Admin At Kiosk : " + sl.getIssueAgent() + " <br> " +
                org.getName() + " Operating Hours : " + org.getTimings() + " <br> " +
                org.getName() + " Print Email : " + org.getPrintEmail() + " <br> " +
                "Service      :" + sl.getService() + " <br> " +
                "Start Time : " + sl.getStartTimeString() + " <br> " +
                "<br> " +
                org.getName() + " Also Offers:  <br> " +
                "<table> <thead> <tr> <td> Service </td> <td>  Unit Price </td> </tr> </thead><body> " + s +
                " If someone else have used the above service at " + org.getName() + " please contact and report Cyber Cafe or Email us at info@bizintelapps.com < br > " +
                " Thanks  <br>" +
                "Team BizIntelApps (Business Intelligent Application) & FaceGuard.org ";
        return str;
    }

    public String getStringAtContractEnd(SystemLease sl, String cashier) {
        Users u = usersDao.findByUsername(cashier);
        Organization org = organizationDao.findByOrganization(u.getOrganization());
        List<Services> serviceses = servicesDao.findByOrganization(org.getName());
        String s = "";
        for (Services ss : serviceses) {
            s += "<tr><td>" + ss.getName() + "</td><td> " + ss.getUnitPrice() + "</td></tr>";

        }
        String str = "";
        str += "Dear " + sl.getLeaseHolderName() + " Welcome to FaceGuard.org, <br> " +
                "At FaceGuard.org We care your Security. <br> " +
                "Use of Service Report: <br> " +
                "Cyber Cafe  Name : " + org.getName() + " <br> " +
                org.getName() + " Contact No : " + org.getPhone() + " <br> " +
                org.getName() + " Contact Email : " + org.getContactEmail() + " <br> " +
                org.getName() + " Address : " + org.getStreet() + " " + org.getCity() + " <br> " +
                org.getName() + " Admin, Issed By : " + sl.getIssueAgent() + " <br> " +
                org.getName() + " Operating Hours : " + org.getTimings() + " <br> " +
                "Service      :" + sl.getService() + " <br> " +
                "Start Time : " + sl.getStartTimeString() + " <br> " +
                "End Time : " + sl.getEndTimeString() + " <br> " +
                "Total Minutest :" + sl.getTotalMinutesUsed() + "<br> " +
                "Payable Amount :" + sl.getPayableAmount() + "<br> " +
                "Paid Amount :" + sl.getAmountPaid() + "<br> " +
                org.getName() + " Admin, Paid To : " + sl.getReturnAgent() + " <br> " +
                org.getName() + " Print Email : " + org.getPrintEmail() + " <br> " +
                "<br> " +
                org.getName() + " Also Offers:  <br> " +
                "<table> <thead> <tr> <td> Service </td> <td>  Unit Price </td> </tr> </thead><body> " + s + "</body></table>" +
                " If someone else have used the above service at " + org.getName() + " please contact and report Cyber Cafe or Email us at info@bizintelapps.com < br > " +
                " -- <br> " +
                " Thanks  <br>" +
                "Team BizIntelApps (Business Intelligent Application) & FaceGuard.org ";
        return str;
    }

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public void setSystemsDao(SystemsDao systemsDao) {
        this.systemsDao = systemsDao;
    }

    public void setSystemLeaseDao(SystemLeaseDao systemLeaseDao) {
        this.systemLeaseDao = systemLeaseDao;
    }

    public void setOrganizationDao(OrganizationDao organizationDao) {
        this.organizationDao = organizationDao;
    }

    public void setServicesDao(ServicesDao servicesDao) {
        this.servicesDao = servicesDao;
    }

    public void setPasswordEncryptor(PasswordEncryptor passwordEncryptor) {
        this.passwordEncryptor = passwordEncryptor;
    }

    public void setMembershipsDao(MembershipsDao membershipsDao) {
        this.membershipsDao = membershipsDao;
    }

    public void setMembershipTypesDao(MembershipTypesDao membershipTypesDao) {
        this.membershipTypesDao = membershipTypesDao;
    }

    public void setMembershipDiscountsDao(MembershipDiscountsDao membershipDiscountsDao) {
        this.membershipDiscountsDao = membershipDiscountsDao;
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
    protected final Log logger = LogFactory.getLog(getClass());
    private SystemsDao systemsDao;
    private UsersDao usersDao;
    private UsersLightDao usersLightDao;
    private UsersPassDao usersPassDao;
    private SystemLeaseDao systemLeaseDao;
    private OrganizationDao organizationDao;
    private ServicesDao servicesDao;
    private PasswordEncryptor passwordEncryptor;
    private PBEStringEncryptor stringEncryptor;
    private MembershipsDao membershipsDao;
    private MembershipTypesDao membershipTypesDao;
    private MembershipDiscountsDao membershipDiscountsDao;
}

