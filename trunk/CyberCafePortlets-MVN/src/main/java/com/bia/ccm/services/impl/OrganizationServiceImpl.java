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
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.OrganizationService;
import java.util.Date;
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
@Service(value="organizationServiceImpl")
public class OrganizationServiceImpl implements OrganizationService {
@Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void saveOrganization(Organization organization, long organizationId, long userId, String ip) {
        if (organization.getId() != organizationId) {
            throw new NoRoleException();
        }
        Organization org = this.organizationDao.find(organizationId);
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
        // timing
        org.setTimings(organization.getTimings());
        // audit
        org.setIp(ip);
        org.setLastModifiedDate(new Date());
        org.setLastModifiedUser(userId);

        this.organizationDao.merge(org);

    }

    @Override
    public Organization getOrganization(long organizationId, String organizationName) {
        Organization org = this.organizationDao.find(organizationId);
        return org;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void registerNewOrganization(long organizationId, String organizationName) {

        // create organization
        Organization o = new Organization();
        o.setId(organizationId);
        o.setName(organizationName);
        this.organizationDao.persist(o);

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

        //Double minuteRate = Double.parseDouble("" + minutes + "." + rate);
        Date date = new Date();
        Integer minutes = 60;
        Double rate = 20.0;
        for (int i = 1; i <= 20; i++) {
            boolean enabled = false;
            if (i <= 50) {
                enabled = true;
            }
            Systems systems = new Systems(null, i, organizationId, true, null, minutes, rate, enabled);
            this.systemsDao.persist(systems);
        }

        // last sending an email
        // @Todo change FaceGuard and email format
//        String[] to = {email};
//        emailService.sendEmail(to, null, "Welcome to FaceGuard, Your Cafe account has been created successfully, please login " );

    }

    @Autowired
    protected OrganizationDao organizationDao;
    @Autowired
    protected ServicesDao servicesDao;
    @Autowired
    protected SystemsDao systemsDao;
    protected static final Log logger = LogFactory.getLog(OrganizationServiceImpl.class);

}
