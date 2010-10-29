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

import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.services.AccountStatusNotificationService;
import com.bizintelapps.easydao.dao.UserThreadLocal;
import com.bizintelapps.easydao.dao.UserThreadLocalDto;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-Annotations.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class AccountStatusNotificationServiceImplTest {

    @Autowired
    protected AccountStatusNotificationService accountStatusNotificationService;

    public AccountStatusNotificationServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
        UserThreadLocal.set(new UserThreadLocalDto(1000L, "test IP"));
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getEmailTimePreferences method, of class AccountStatusNotificationServiceImpl.
     */
    //@Test
    public void testGetEmailTimePreferences() {
        System.out.println("getEmailTimePreferences");
        short time = 0;
        AccountStatusNotificationServiceImpl instance = new AccountStatusNotificationServiceImpl();
        List expResult = null;
        List result = instance.getEmailTimePreferences(time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllOrganizationEmailPreference method, of class AccountStatusNotificationServiceImpl.
     */
    //@Test
    public void testGetAllOrganizationEmailPreference() {
        System.out.println("getAllOrganizationEmailPreference");
        long organization = 0L;
        AccountStatusNotificationServiceImpl instance = new AccountStatusNotificationServiceImpl();
        List expResult = null;
        List result = instance.getAllOrganizationEmailPreference(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePreferences method, of class AccountStatusNotificationServiceImpl.
     */
    @Test
    public void testSavePreferences() {
        System.out.println("savePreferences");
        PreferenceDto preferenceDto = new PreferenceDto();
        Set<String> emails = new HashSet<String>();
        emails.add("intesar@ymail.com");
        emails.add("intesar@gmail.com");
        preferenceDto.setEmails(emails);
        Set<Short> timings = new HashSet<Short>();
        Short s1 = 100;
        Short s2 = 200;
        timings.add(s1);
        timings.add(s2);
        preferenceDto.setTimings(timings);

        long organization = 200L;
        this.accountStatusNotificationService.savePreferences(preferenceDto, organization);
        PreferenceDto preferenceDto1 =  this.accountStatusNotificationService.getPreferences(organization);
        Assert.assertEquals(2, preferenceDto1.getEmails().size());
        Assert.assertEquals(2, preferenceDto1.getTimings().size());
    }

    /**
     * Test of getPreferences method, of class AccountStatusNotificationServiceImpl.
     */
    //@Test
    public void testGetPreferences() {
        System.out.println("getPreferences");
        long organization = 0L;
        AccountStatusNotificationServiceImpl instance = new AccountStatusNotificationServiceImpl();
        PreferenceDto expResult = null;
        PreferenceDto result = instance.getPreferences(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}