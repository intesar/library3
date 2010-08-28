/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */

package com.bia.ccm.ajax;

import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import com.bia.ccm.services.MembershipService;
import com.bia.ccm.services.ProductService;
import com.bia.ccm.services.SaleService;
import java.util.List;
import javax.servlet.http.HttpSession;
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
@ContextConfiguration(
locations = {"/applicationContext-Annotations.xml","/ApplicationContext-AjaxService.xml"
})
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class AjaxWorkServiceTest {

    @Autowired
    AjaxAdminService adminService;
    public AjaxWorkServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAllServices method, of class AjaxWorkService.
     */
    @Test
    public void testGetAllServices() {
        System.out.println("getAllServices");
        HttpSession session = null;
        //List list = this.adminService.getAllServices(session);
        //assertEquals(list.size(), 0);
        
    }

    /**
     * Test of getActiveSystems method, of class AjaxWorkService.
     */
    //@Test
    public void testGetActiveSystems() {
        System.out.println("getActiveSystems");
        HttpSession session = null;
        AjaxWorkService instance = new AjaxWorkService();
        List expResult = null;
        List result = instance.getActiveSystems(session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemByNameAndOrganization method, of class AjaxWorkService.
     */
    //@Test
    public void testGetSystemByNameAndOrganization() {
        System.out.println("getSystemByNameAndOrganization");
        int systemNo = 0;
        HttpSession session = null;
        AjaxWorkService instance = new AjaxWorkService();
        Systems expResult = null;
        Systems result = instance.getSystemByNameAndOrganization(systemNo, session);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leaseSystem method, of class AjaxWorkService.
     */
    //@Test
    public void testLeaseSystem() {
        System.out.println("leaseSystem");
        int systemId = 0;
        String leaseHolder = "";
        HttpSession session = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.leaseSystem(systemId, leaseHolder, session);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addService method, of class AjaxWorkService.
     */
    //@Test
    public void testAddService() {
        System.out.println("addService");
        String service = "";
        Long units = null;
        String user = "";
        Double payableAmount = null;
        String comments = "";
        Double paidAmount = null;
        HttpSession session = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.addService(service, units, user, payableAmount, comments, paidAmount, session);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPayableAmount method, of class AjaxWorkService.
     */
    //@Test
    public void testGetPayableAmount() {
        System.out.println("getPayableAmount");
        int id = 0;
        AjaxWorkService instance = new AjaxWorkService();
        UsageDetail expResult = null;
        UsageDetail result = instance.getPayableAmount(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unleaseSystem method, of class AjaxWorkService.
     */
    //@Test
    public void testUnleaseSystem() {
        System.out.println("unleaseSystem");
        int systemId = 0;
        double paidAmount = 0.0;
        HttpSession session = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.unleaseSystem(systemId, paidAmount, session);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemLease method, of class AjaxWorkService.
     */
    //@Test
//    public void testGetSystemLease() {
//        System.out.println("getSystemLease");
//        int id = 0;
//        AjaxWorkService instance = new AjaxWorkService();
//        List expResult = null;
//        List result = instance.getSystemLease(id);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of chargePayment method, of class AjaxWorkService.
     */
    //@Test
    public void testChargePayment() {
        System.out.println("chargePayment");
        int systemId = 0;
        HttpSession session = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.chargePayment(systemId, session);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAdminService method, of class AjaxWorkService.
     */
    //@Test
    public void testSetAdminService() {
        System.out.println("setAdminService");
        ProductService adminService = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.setAdminService(adminService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipService method, of class AjaxWorkService.
     */
    //@Test
    public void testSetMembershipService() {
        System.out.println("setMembershipService");
        MembershipService membershipService = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.setMembershipService(membershipService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setWorkService method, of class AjaxWorkService.
     */
    //@Test
    public void testSetWorkService() {
        System.out.println("setWorkService");
        SaleService workService = null;
        AjaxWorkService instance = new AjaxWorkService();
        instance.setWorkService(workService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}