/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.impl;

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
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsageDetail;
import com.bia.ccm.entity.Users;
import com.bia.ccm.services.EMailService;
import com.bia.converter.CaseConverter;
import java.util.List;
import org.jasypt.encryption.pbe.PBEStringEncryptor;
import org.jasypt.util.password.PasswordEncryptor;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author intesar
 */
public class WorkServiceImplTest {

    public WorkServiceImplTest() {
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
     * Test of getUserEmailByMacAddress method, of class WorkServiceImpl.
     */
    @Test
    public void testGetUserEmailByMacAddress() {
        System.out.println("getUserEmailByMacAddress");
        String macAddress = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        String expResult = "";
        String result = instance.getUserEmailByMacAddress(macAddress);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getActiveSystems method, of class WorkServiceImpl.
     */
    @Test
    public void testGetActiveSystems() {
        System.out.println("getActiveSystems");
        String username = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        List expResult = null;
        List result = instance.getActiveSystems(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemByNameAndOrganization method, of class WorkServiceImpl.
     */
    @Test
    public void testGetSystemByNameAndOrganization() {
        System.out.println("getSystemByNameAndOrganization");
        int systemNo = 0;
        String username = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        Systems expResult = null;
        Systems result = instance.getSystemByNameAndOrganization(systemNo, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of leaseSystem method, of class WorkServiceImpl.
     */
    @Test
    public void testLeaseSystem() {
        System.out.println("leaseSystem");
        int id = 0;
        String leaseHolder = "";
        String cashier = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.leaseSystem(id, leaseHolder, cashier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemLease method, of class WorkServiceImpl.
     */
    @Test
    public void testGetSystemLease() {
        System.out.println("getSystemLease");
        int id = 0;
        WorkServiceImpl instance = new WorkServiceImpl();
        List expResult = null;
        List result = instance.getSystemLease(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of chargePayment method, of class WorkServiceImpl.
     */
    @Test
    public void testChargePayment() {
        System.out.println("chargePayment");
        int systemId = 0;
        String agent = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.chargePayment(systemId, agent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addService method, of class WorkServiceImpl.
     */
    @Test
    public void testAddService() {
        System.out.println("addService");
        String service = "";
        long units = 0L;
        String user = "";
        double payableAmount = 0.0;
        String comments = "";
        double paidAmount = 0.0;
        String agent = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.addService(service, units, user, payableAmount, comments, paidAmount, agent);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPayableAmount method, of class WorkServiceImpl.
     */
    @Test
    public void testGetPayableAmount() {
        System.out.println("getPayableAmount");
        int id = 0;
        WorkServiceImpl instance = new WorkServiceImpl();
        UsageDetail expResult = null;
        UsageDetail result = instance.getPayableAmount(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unleaseSystem method, of class WorkServiceImpl.
     */
    @Test
    public void testUnleaseSystem() {
        System.out.println("unleaseSystem");
        int id = 0;
        double amountPaid = 0.0;
        String cashier = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.unleaseSystem(id, amountPaid, cashier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemStatus method, of class WorkServiceImpl.
     */
    @Test
    public void testGetSystemStatus() {
        System.out.println("getSystemStatus");
        String macAddress = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        Integer expResult = null;
        Integer result = instance.getSystemStatus(macAddress);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createCutomer method, of class WorkServiceImpl.
     */
    @Test
    public void testCreateCutomer() {
        System.out.println("createCutomer");
        Users customer = null;
        Users createUser = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.createCutomer(customer, createUser);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllServices method, of class WorkServiceImpl.
     */
    @Test
    public void testGetAllServices() {
        System.out.println("getAllServices");
        String username = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        List expResult = null;
        List result = instance.getAllServices(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyCustomersAtContractStart method, of class WorkServiceImpl.
     */
    @Test
    public void testNotifyCustomersAtContractStart() {
        System.out.println("notifyCustomersAtContractStart");
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.notifyCustomersAtContractStart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyCustomersAtContractEnd method, of class WorkServiceImpl.
     */
    @Test
    public void testNotifyCustomersAtContractEnd() {
        System.out.println("notifyCustomersAtContractEnd");
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.notifyCustomersAtContractEnd();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerPic method, of class WorkServiceImpl.
     */
    @Test
    public void testGetCustomerPic() {
        System.out.println("getCustomerPic");
        String key = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        Users expResult = null;
        Users result = instance.getCustomerPic(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomer method, of class WorkServiceImpl.
     */
    @Test
    public void testGetCustomer() {
        System.out.println("getCustomer");
        String key = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        Users expResult = null;
        Users result = instance.getCustomer(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCustomerWithPic method, of class WorkServiceImpl.
     */
    @Test
    public void testGetCustomerWithPic() {
        System.out.println("getCustomerWithPic");
        String key = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        Users expResult = null;
        Users result = instance.getCustomerWithPic(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringAtContractStart method, of class WorkServiceImpl.
     */
    @Test
    public void testGetStringAtContractStart() {
        System.out.println("getStringAtContractStart");
        SystemLease sl = null;
        String cashier = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        String expResult = "";
        String result = instance.getStringAtContractStart(sl, cashier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStringAtContractEnd method, of class WorkServiceImpl.
     */
    @Test
    public void testGetStringAtContractEnd() {
        System.out.println("getStringAtContractEnd");
        SystemLease sl = null;
        String cashier = "";
        WorkServiceImpl instance = new WorkServiceImpl();
        String expResult = "";
        String result = instance.getStringAtContractEnd(sl, cashier);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetUsersDao() {
        System.out.println("setUsersDao");
        UsersDao usersDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setUsersDao(usersDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystemsDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetSystemsDao() {
        System.out.println("setSystemsDao");
        SystemsDao systemsDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setSystemsDao(systemsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystemLeaseDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetSystemLeaseDao() {
        System.out.println("setSystemLeaseDao");
        SystemLeaseDao systemLeaseDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setSystemLeaseDao(systemLeaseDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrganizationDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetOrganizationDao() {
        System.out.println("setOrganizationDao");
        OrganizationDao organizationDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setOrganizationDao(organizationDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServicesDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetServicesDao() {
        System.out.println("setServicesDao");
        ServicesDao servicesDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setServicesDao(servicesDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswordEncryptor method, of class WorkServiceImpl.
     */
    @Test
    public void testSetPasswordEncryptor() {
        System.out.println("setPasswordEncryptor");
        PasswordEncryptor passwordEncryptor = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setPasswordEncryptor(passwordEncryptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipsDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetMembershipsDao() {
        System.out.println("setMembershipsDao");
        MembershipsDao membershipsDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setMembershipsDao(membershipsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipTypesDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetMembershipTypesDao() {
        System.out.println("setMembershipTypesDao");
        MembershipTypesDao membershipTypesDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setMembershipTypesDao(membershipTypesDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipDiscountsDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetMembershipDiscountsDao() {
        System.out.println("setMembershipDiscountsDao");
        MembershipDiscountsDao membershipDiscountsDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setMembershipDiscountsDao(membershipDiscountsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersLightDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetUsersLightDao() {
        System.out.println("setUsersLightDao");
        UsersLightDao usersLightDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setUsersLightDao(usersLightDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersPassDao method, of class WorkServiceImpl.
     */
    @Test
    public void testSetUsersPassDao() {
        System.out.println("setUsersPassDao");
        UsersPassDao usersPassDao = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setUsersPassDao(usersPassDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStringEncryptor method, of class WorkServiceImpl.
     */
    @Test
    public void testSetStringEncryptor() {
        System.out.println("setStringEncryptor");
        PBEStringEncryptor stringEncryptor = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setStringEncryptor(stringEncryptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmailService method, of class WorkServiceImpl.
     */
    @Test
    public void testSetEmailService() {
        System.out.println("setEmailService");
        EMailService emailService = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setEmailService(emailService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCaseConverter method, of class WorkServiceImpl.
     */
    @Test
    public void testSetCaseConverter() {
        System.out.println("setCaseConverter");
        CaseConverter caseConverter = null;
        WorkServiceImpl instance = new WorkServiceImpl();
        instance.setCaseConverter(caseConverter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}