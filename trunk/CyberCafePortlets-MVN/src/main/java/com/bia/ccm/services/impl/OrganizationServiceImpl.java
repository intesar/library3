/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.services.OrganizationService;
import com.bia.ccm.util.EmailUtil;
import javax.persistence.NoResultException;
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
@Service(value = "organizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService {

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void saveOrganization(Organization organization, long organizationId) {
        if (organization.getId() != organizationId) {
            throw new NoRoleException();
        }
        Organization org = this.organizationDao.findByOrganizationId(organizationId);
        // address
        org.setStreet(organization.getStreet());
        org.setCity(organization.getCity());
        org.setState(organization.getState());
        org.setZipcode(organization.getZipcode());
        org.setCountry(organization.getCountry());
        // phones
        org.setPhone(organization.getPhone());
        org.setFax(organization.getFax());
        // contact
        org.setContactName(organization.getContactName());
        org.setContactEmail(organization.getContactEmail());

        this.organizationDao.merge(org);

    }

    @Override
    public Organization getOrganization(long organizationId) {
        Organization org = this.organizationDao.findByOrganizationId(organizationId);
        return org;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void registerNewOrganization(long organizationId, String organizationName, String email) {
        if(logger.isTraceEnabled()) {
            logger.trace("validating input");
        }
        // validate organizationId --> should be greater then zero
        if (organizationId <= 0) {
            throw new InvalidInputException("Invalid Organization ID");
        }
        // organization cannot be null, empty or duplicate
        if (organizationName == null || organizationName.trim().length() == 0 || isOrganizationNameExists(organizationName)) {
            throw new InvalidInputException("Invalid Cyber Cafe Name!");
        }
        // validate email --> cannot be null, empty or invalid
        if (email == null || email.trim().length() == 0 || !EmailUtil.isValidEmail(email)) {
            throw new InvalidInputException("Invalid Email");
        }

        if(logger.isTraceEnabled()) {
            logger.trace(" creating org ");
        }
        // create organization
        Organization o = new Organization();
        o.setOrganizationId(organizationId);
        o.setName(organizationName);
        o.setContactEmail(email);
        this.organizationDao.persist(o);

        if(logger.isTraceEnabled()) {
            logger.trace("creating default services");
        }
        // default adding some services
        Services s = new Services(null, "Other", null, organizationId, 1, 1.0, null, null);
        this.servicesDao.persist(s);
        Services s1 = new Services(null, "Print B&W", null, organizationId, 1, 1.0, null, null);
        this.servicesDao.persist(s1);
        Services s2 = new Services(null, "Copy B&W", null, organizationId, 1, 1.0, null, null);
        this.servicesDao.persist(s2);
        Services s3 = new Services(null, "Print Color", null, organizationId, 1, 3.0, null, null);
        this.servicesDao.persist(s3);
        Services s4 = new Services(null, "Scan", null, organizationId, 1, 5.0, null, null);
        this.servicesDao.persist(s4);
        Services s5 = new Services(null, "Cool Drink", null, organizationId, 1, 10.0, null, null);
        this.servicesDao.persist(s5);

        if(logger.isTraceEnabled()) {
            logger.trace("creating systems");
        }
        //Double minuteRate = Double.parseDouble("" + minutes + "." + rate);
        Integer minutes = 60;
        Double rate = 20.0;
        boolean enabled = false;
        for (int i = 1; i <= 20; i++) {
            if (i <= 10) {
                enabled = true;
            }
            Systems systems = new Systems(null, i, organizationId, true, null, minutes, rate, enabled);
            this.systemsDao.persist(systems);
        }

        if(logger.isTraceEnabled()) {
            logger.trace("sending email");
        }
//      sending an email
        String[] to = {email};
        String host = "http://67.184.225.240/web/" + organizationName + "/home";
        String url = "<a href='" + host + "'>" + host + "</a>";
        eMailService.sendEmail(to, "Cyber Cafe Registration Successful!", "Welcome to 7CyberCafe.com, Your Cafe account has been created successfully, please login to " + url);

    }

    private boolean isOrganizationNameExists(String organizationName) {
        Organization org = null;
        try {
            org = this.organizationDao.findByOrganizationName(organizationName);
        } catch (NoResultException ex) {
            return false;
        }
        if (org == null || org.getId() == null) {
            return false;
        } else {
            return true;
        }
    }

    
    @Autowired
    EMailService eMailService;
    @Autowired
    protected OrganizationDao organizationDao;
    @Autowired
    protected ServicesDao servicesDao;
    @Autowired
    protected SystemsDao systemsDao;
    protected static final Log logger = LogFactory.getLog(OrganizationServiceImpl.class);
}
