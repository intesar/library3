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

import com.bia.ccm.entity.Organization;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.services.OrganizationService;
import com.bizintelapps.easydao.dao.UserThreadLocal;
import com.bizintelapps.easydao.dao.UserThreadLocalDto;
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
public class OrganizationServiceImplTest {

    @Autowired
    protected OrganizationService organizationService;

    public OrganizationServiceImplTest() {
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
     * Test of saveOrganization method, of class OrganizationServiceImpl.
     */
    //@Test
    public void testSaveOrganization() {
        System.out.println("saveOrganization");
        Organization organization = null;
        long organizationId = 0L;
        OrganizationServiceImpl instance = new OrganizationServiceImpl();
        instance.saveOrganization(organization, organizationId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrganization method, of class OrganizationServiceImpl.
     */
    //@Test
    public void testGetOrganization() {
        System.out.println("getOrganization");
        long organizationId = 0L;
        OrganizationServiceImpl instance = new OrganizationServiceImpl();
        Organization expResult = null;
        Organization result = instance.getOrganization(organizationId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerNewOrganization method, of class OrganizationServiceImpl.
     */
    @Test
    public void testRegisterNewOrganization() {
        System.out.println("registerNewOrganization");
        registerNewOrganization();
        registerNewOrganizationWithInvalidOrganizationId();
        registerNewOrganizationWithInvalidOrganizationName();
        registerNewOrganizationWithNullOrganizationName();
        registerNewOrganizationWithDuplicateOrganizationName();
        registerNewOrganizationWithInvalidEmail();
        registerNewOrganizationWithEmptyEmail();
        registerNewOrganizationWithNullEmail();
    }

    private void registerNewOrganization() {
        System.out.println("registerNewOrganization");
        long organizationId = 100L;
        String organizationName = "CyberCafe1";
        String email = "mdshannan@gmail.com";
        organizationService.registerNewOrganization(organizationId, organizationName, email);
        Organization org = organizationService.getOrganization(organizationId);
        Assert.assertEquals(organizationName, org.getName());
    }

    private void registerNewOrganizationWithInvalidOrganizationId() {
        System.out.println("registerNewOrganization");
        long organizationId = 0L;
        String organizationName = "CyberCafe2";
        String email = "mdshannan@gmail.com";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void registerNewOrganizationWithInvalidOrganizationName() {
        System.out.println("registerNewOrganization");
        long organizationId = 101L;
        String organizationName = "";
        String email = "mdshannan@gmail.com";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void registerNewOrganizationWithNullOrganizationName() {
        System.out.println("registerNewOrganization");
        long organizationId = 101L;
        String organizationName = null;
        String email = "mdshannan@gmail.com";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void registerNewOrganizationWithDuplicateOrganizationName() {
        System.out.println("registerNewOrganization");
        long organizationId = 101L;
        String organizationName = "CyberCafe10";
        String email = "mdshannan@gmail.com";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            organizationService.registerNewOrganization(organizationId + 1, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void registerNewOrganizationWithInvalidEmail() {
        System.out.println("registerNewOrganization");
        long organizationId = 101L;
        String organizationName = "";
        String email = "mdshannan";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void registerNewOrganizationWithEmptyEmail() {
        System.out.println("registerNewOrganization");
        long organizationId = 101L;
        String organizationName = "";
        String email = "";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void registerNewOrganizationWithNullEmail() {
        System.out.println("registerNewOrganization");
        long organizationId = 101L;
        String organizationName = "";
        String email = "";
        try {
            organizationService.registerNewOrganization(organizationId, organizationName, email);
            fail("should have been an exception");
        } catch (InvalidInputException ex) {
        }
    }
}
