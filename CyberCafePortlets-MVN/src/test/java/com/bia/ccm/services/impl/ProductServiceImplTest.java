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

import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.exceptions.InvalidInputException;
import com.bia.ccm.exceptions.NoRoleException;
import com.bia.ccm.services.ProductService;
import com.bizintelapps.easydao.dao.UserThreadLocal;
import com.bizintelapps.easydao.dao.UserThreadLocalDto;
import java.util.List;
import junit.framework.Assert;
import org.junit.After;
import org.junit.AfterClass;
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
public class ProductServiceImplTest {

    @Autowired
    protected ProductService productService;

    public ProductServiceImplTest() {
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
     * Test of updateRentalPrice method, of class AdminServiceImpl.
     */
    //@Test
    public void testUpdateRentalPrice() {
        System.out.println("updateRentalPrice");
        int mims = 0;
        double rate = 0.0;
        Integer lmins = null;
        Double lrate = null;
        long organization = 0L;
        String username = "";
        String ip = "";
        ProductServiceImpl instance = new ProductServiceImpl();
        instance.updateRentalPrice(mims, rate, lmins, lrate, organization);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSystems method, of class AdminServiceImpl.
     */
    //@Test
    public void testGetAllSystems() {
        System.out.println("getAllSystems");
        long organization = 0L;
        ProductServiceImpl instance = new ProductServiceImpl();
        List expResult = null;
        List result = instance.getAllSystems(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSystemLease method, of class AdminServiceImpl.
     */
    /**
     * Test of saveService method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveService() {
        testSaveServiceGoodCase();
        testSaveServiceWithEmptyName();
        testSaveServiceWithNullName();
        testSaveServiceWithNegativeFirstPrice();
        testSaveServiceWithNegativeFirstUnits();
        testSaveServiceWithZeroFirstUnitsAndNonZeroPrice();
        testSaveServiceWithZeroFirstUnitsAndZeroPrice();
        testSaveServiceWithNegativeSecondPrice();
        testSaveServiceWithNegativeSecondUnits();
        testSaveServiceWithNegativeAll();
        testSaveServiceWithInvalidUnitsCombination();
        testSaveServiceWithInvalidOrganization();
    }

    private void testSaveServiceGoodCase() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 5.0, organization);
        productService.saveService(service, organization);
        List<Services> list = productService.getAllServices(organization);
        Assert.assertEquals(1, list.size());
    }

    private void testSaveServiceWithInvalidOrganization() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "", 5.0, organization);
        try {
            productService.saveService(service, organization + 10);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithEmptyName() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "", 5.0, organization);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithNullName() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, null, 5.0, organization);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithNegativeFirstPrice() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", -0.001, organization);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithNegativeFirstUnits() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.001, organization);
        service.setUnits(-1);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithZeroFirstUnitsAndNonZeroPrice() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.001, organization);
        service.setUnits(0);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithZeroFirstUnitsAndZeroPrice() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.0, organization);
        service.setUnits(0);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithNegativeSecondPrice() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.001, organization);
        service.setSaleTwoPrice(-0.1);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithNegativeSecondUnits() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.001, organization);
        service.setSaleTwoUnits(-1);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithNegativeAll() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.001, organization);
        service.setUnits(-1);
        service.setUnitPrice(-0.1);
        service.setSaleTwoPrice(-0.1);
        service.setSaleTwoUnits(-1);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    private void testSaveServiceWithInvalidUnitsCombination() {
        System.out.println("saveService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 0.001, organization);
        service.setUnits(10);
        service.setSaleTwoUnits(9);
        try {
            productService.saveService(service, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * Test of deleteService method, of class AdminServiceImpl.
     */
    @Test
    public void testDeleteService() {
        testDeleteServiceWithInvalidId();
        testDeleteServiceWithInvalidOrganization();
        testDeleteServiceWithValidId();
    }

    private void testDeleteServiceWithInvalidId() {
        System.out.println("deleteService");
        long organization = 100L;
        Services service = new Services(null, "Temp", 5.0, organization);
        productService.saveService(service, organization);
        List<Services> list = productService.getAllServices(organization);
        Assert.assertEquals(1, list.size());
        try {
            productService.deleteService(-1L, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
        }
        try {
            productService.deleteService(null, organization);
            fail("No exception");
        } catch (InvalidInputException ex) {
        }
    }

    private void testDeleteServiceWithInvalidOrganization() {
        System.out.println("deleteService");
        long organization = 101L;
        Services service = new Services(null, "Temp", 5.0, organization);
        productService.saveService(service, organization);
        List<Services> list = productService.getAllServices(organization);
        Assert.assertEquals(1, list.size());
        try {
            productService.deleteService(service.getId(), 10L);
            fail("No exception");
        } catch (NoRoleException ex) {
        }

    }

    private void testDeleteServiceWithValidId() {
        System.out.println("saveService");
        long organization = 102L;
        Services service = new Services(null, "Temp", 5.0, organization);
        productService.saveService(service, organization);
        List<Services> list = productService.getAllServices(organization);
        Assert.assertEquals(1, list.size());
        try {
            productService.deleteService(service.getId(), organization);
        } catch (InvalidInputException ex) {
            fail("No exception");
        }
        list = productService.getAllServices(organization);
        Assert.assertEquals(0, list.size());
    }

    /**
     * Test of getAllServices method, of class AdminServiceImpl.
     */
    //@Test
    public void testGetAllServices() {
        System.out.println("getAllServices");
        long organization = 0L;
        ProductServiceImpl instance = new ProductServiceImpl();
        List expResult = null;
        List result = instance.getAllServices(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystem method, of class AdminServiceImpl.
     */
    //@Test
    public void testGetSystem() {
        System.out.println("getSystem");
        long organization = 0L;
        ProductServiceImpl instance = new ProductServiceImpl();
        Systems expResult = null;
        Systems result = instance.getSystem(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

   
    /**
     * Test of registerNewOrganization method, of class AdminServiceImpl.
     */
    //@Test
}
