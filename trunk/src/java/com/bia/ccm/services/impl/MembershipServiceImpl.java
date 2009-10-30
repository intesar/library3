/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.services.MembershipService;
import com.bia.ccm.dao.MembershipDiscountsDao;
import com.bia.ccm.dao.MembershipTypesDao;
import com.bia.ccm.dao.MembershipsDao;
import com.bia.ccm.entity.MembershipDiscounts;
import com.bia.ccm.entity.MembershipTypes;
import com.bia.ccm.entity.Memberships;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.services.EMailService;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class MembershipServiceImpl implements MembershipService {

    /**
     * add/update  membership to organization
     * if id is null creates a new otherwise tries to save
     * @param membershipTypes
     */
    public void saveMembershipType(MembershipTypes membershipTypes) {
        if (membershipTypes.getId() == null) {
            this.membershipTypesDao.create(membershipTypes);
        } else {
            this.membershipTypesDao.update(membershipTypes);
        }
    }

    /**
     * 
     * @param org
     * @return
     */
    public List<MembershipTypes> getMembershipTypesByOrganization(String org) {
        List<MembershipTypes> list = null;
        list = this.membershipTypesDao.findByOrganization(org);
        return list;
    }

    /**
     * 
     * @param org
     * @param name
     * @return
     */
    public MembershipTypes getMembershipTypesByOrganizationAndName(String org, String name) {
        return this.membershipTypesDao.findByOrganizationAndName(org, name);
    }

    /**
     * add/update MembershipDiscounts 
     * if the id is null create a new one else updates the record
     * @param membershipDiscounts
     */
    public void saveMembershipDiscounts(MembershipDiscounts membershipDiscounts) {
        if (membershipDiscounts.getId() == null) {
            this.membershipDiscountsDao.create(membershipDiscounts);
        } else {
            this.membershipDiscountsDao.update(membershipDiscounts);
        }
    }

    public MembershipDiscounts getMembershipDiscountsById(Integer id) {
        return this.membershipDiscountsDao.read(id);
    }

    /**
     * 
     * @param id
     * @return
     */
    public List<MembershipDiscounts> getMembershipDiscountsByMembershipTypesId(Integer id) {
        List<MembershipDiscounts> list = null;
        list = this.membershipDiscountsDao.findByMembershipTypesId(id);
        return list;
    }

    /**
     * add/update Membership
     * if the id is null create a new one else updates the record
     * @param membership
     */
    public void saveMembership(Memberships memberships, Organization org, String cashier) {
        logger.debug("inside saveMembership-------------");
        if (memberships.getId() == null) {
            memberships.setCreateDate(new Date());
            memberships.setCreateUser(cashier);
            memberships.setStartDate(new Date());
            logger.debug("inside saveMembership-------------" + org.getName());
            logger.debug("inside saveMembership-------------" + memberships.getMembershipTypeString());
            MembershipTypes mt = this.membershipTypesDao.findByOrganizationAndName(org.getName(),
                    memberships.getMembershipTypeString());
            int days = mt.getDaysValidFor();
            logger.debug("inside saveMembership-------------" + days);
            Calendar c = Calendar.getInstance();
            c.add(Calendar.DATE, days);
            logger.debug("inside saveMembership-------------" + c);
            memberships.setExpirationDate(c.getTime());
            memberships.setMembershipType(mt.getId());
            memberships.setOrganization(org.getName());
            logger.debug("inside saveMembership------------- added all ");
            this.membershipsDao.create(memberships);
            String emails[] = {memberships.getEmail(), org.getContactEmail()};
            this.emailService.sendEmail(emails, getMembershipStringAtContractStart(memberships, org));
        } else {
            logger.debug("inside saveMembership------------- else");
            this.membershipsDao.update(memberships);
        }

    }

    public String getMembershipStringAtContractStart(Memberships memberships, Organization org) {


        String s = "";
        String str = "";
        str += "Dear " + memberships.getEmail() + " Welcome to FaceGuard.org, <br> " +
                "At FaceGuard.org We care your Security. <br> " +
                "Thanks for Buying Membership at : " + org.getName() + "<br> " +
                "Cyber Cafe  Name : " + org.getName() + " <br> " +
                org.getName() + " Contact No : " + org.getPhone() + " <br> " +
                org.getName() + " Contact Email : " + org.getContactEmail() + " <br> " +
                org.getName() + " Address : " + org.getStreet() + " " + org.getCity() + " <br> " +
                org.getName() + " Admin At Kiosk : " + memberships.getCreateUser() + " <br> " +
                org.getName() + " Operating Hours : " + " <br> " +
                "Membership Name : " + memberships.getMembershipTypeString() + "<br>" +
                "Membership Start Date : " + memberships.getStartDateString() + "<br>" +
                "Membership Expiration Date : " + memberships.getExpirationDateString() + "<br>" +
                "21.com Also Offers:  <br> " +
                "<table> <thead> <tr> <td> Service </td> <td>  Unit Price </td> </tr> </thead><body> " + s +
                " If someone else have used the above service at " + org.getName() + " please contact and report Cyber Cafe or Email us at info@bizintelapps.com < br > " +
                " -- <br> " +
                " Thanks  <br>" +
                "Team BizIntelApps (Business Intelligent Application) & FaceGuard.org ";
        return str;
    }

    /**
     * 
     * @param org
     * @param user
     * @return
     */
    public Memberships getMembershipsByOrganizationAndUsername(String org, String user) {
        return this.membershipsDao.findByOrganizationAndEmail(org, user);
    }

    /**
     * 
     * @param org
     * @return
     */
    public List<Memberships> getMembershipsByOrganization(String org) {
        List list = null;
        list = this.membershipsDao.findByOrganization(org);
        return list;
    }

    /**
     * 
     * @param id
     * @return
     */
    public Memberships getMembershipsById(Integer id) {
        return this.membershipsDao.read(id);
    }

    /**
     * 
     * @param membershipDiscountsDao
     */
    public void setMembershipDiscountsDao(MembershipDiscountsDao membershipDiscountsDao) {
        this.membershipDiscountsDao = membershipDiscountsDao;
    }

    /**
     * 
     * @param membershipTypesDao
     */
    public void setMembershipTypesDao(MembershipTypesDao membershipTypesDao) {
        this.membershipTypesDao = membershipTypesDao;
    }

    /**
     * 
     * @param membershipsDao
     */
    public void setMembershipsDao(MembershipsDao membershipsDao) {
        this.membershipsDao = membershipsDao;
    }
     public void setEmailService(EMailService emailService) {
        this.emailService = emailService;
    }
    private EMailService emailService;
    protected final Log logger = LogFactory.getLog(getClass());
    private MembershipsDao membershipsDao;
    private MembershipTypesDao membershipTypesDao;
    private MembershipDiscountsDao membershipDiscountsDao;
    
}
