/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.impl;

import com.bia.ccm.dao.MembershipDiscountsDao;
import com.bia.ccm.dao.MembershipTypesDao;
import com.bia.ccm.dao.MembershipsDao;
import com.bia.ccm.entity.MembershipDiscounts;
import com.bia.ccm.entity.MembershipTypes;
import com.bia.ccm.entity.Memberships;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.services.EMailService;
import java.util.List;
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
public class MembershipServiceImplTest {

    public MembershipServiceImplTest() {
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
     * Test of saveMembershipType method, of class MembershipServiceImpl.
     */
    @Test
    public void testSaveMembershipType() {
        System.out.println("saveMembershipType");
        MembershipTypes membershipTypes = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.saveMembershipType(membershipTypes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipTypesByOrganization method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipTypesByOrganization() {
        System.out.println("getMembershipTypesByOrganization");
        String org = "";
        MembershipServiceImpl instance = new MembershipServiceImpl();
        List expResult = null;
        List result = instance.getMembershipTypesByOrganization(org);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipTypesByOrganizationAndName method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipTypesByOrganizationAndName() {
        System.out.println("getMembershipTypesByOrganizationAndName");
        String org = "";
        String name = "";
        MembershipServiceImpl instance = new MembershipServiceImpl();
        MembershipTypes expResult = null;
        MembershipTypes result = instance.getMembershipTypesByOrganizationAndName(org, name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMembershipDiscounts method, of class MembershipServiceImpl.
     */
    @Test
    public void testSaveMembershipDiscounts() {
        System.out.println("saveMembershipDiscounts");
        MembershipDiscounts membershipDiscounts = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.saveMembershipDiscounts(membershipDiscounts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipDiscountsById method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipDiscountsById() {
        System.out.println("getMembershipDiscountsById");
        Integer id = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        MembershipDiscounts expResult = null;
        MembershipDiscounts result = instance.getMembershipDiscountsById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipDiscountsByMembershipTypesId method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipDiscountsByMembershipTypesId() {
        System.out.println("getMembershipDiscountsByMembershipTypesId");
        Integer id = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        List expResult = null;
        List result = instance.getMembershipDiscountsByMembershipTypesId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validateUserMembership method, of class MembershipServiceImpl.
     */
    @Test
    public void testValidateUserMembership() {
        System.out.println("validateUserMembership");
        String email = "";
        String org = "";
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.validateUserMembership(email, org);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveMembership method, of class MembershipServiceImpl.
     */
    @Test
    public void testSaveMembership() {
        System.out.println("saveMembership");
        Memberships memberships = null;
        Organization org = null;
        String cashier = "";
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.saveMembership(memberships, org, cashier);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipStringAtContractStart method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipStringAtContractStart() {
        System.out.println("getMembershipStringAtContractStart");
        Memberships memberships = null;
        Organization org = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        String expResult = "";
        String result = instance.getMembershipStringAtContractStart(memberships, org);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipsByOrganization method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipsByOrganization() {
        System.out.println("getMembershipsByOrganization");
        String org = "";
        MembershipServiceImpl instance = new MembershipServiceImpl();
        List expResult = null;
        List result = instance.getMembershipsByOrganization(org);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMembershipsById method, of class MembershipServiceImpl.
     */
    @Test
    public void testGetMembershipsById() {
        System.out.println("getMembershipsById");
        Integer id = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        Memberships expResult = null;
        Memberships result = instance.getMembershipsById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipDiscountsDao method, of class MembershipServiceImpl.
     */
    @Test
    public void testSetMembershipDiscountsDao() {
        System.out.println("setMembershipDiscountsDao");
        MembershipDiscountsDao membershipDiscountsDao = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.setMembershipDiscountsDao(membershipDiscountsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipTypesDao method, of class MembershipServiceImpl.
     */
    @Test
    public void testSetMembershipTypesDao() {
        System.out.println("setMembershipTypesDao");
        MembershipTypesDao membershipTypesDao = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.setMembershipTypesDao(membershipTypesDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMembershipsDao method, of class MembershipServiceImpl.
     */
    @Test
    public void testSetMembershipsDao() {
        System.out.println("setMembershipsDao");
        MembershipsDao membershipsDao = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.setMembershipsDao(membershipsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmailService method, of class MembershipServiceImpl.
     */
    @Test
    public void testSetEmailService() {
        System.out.println("setEmailService");
        EMailService emailService = null;
        MembershipServiceImpl instance = new MembershipServiceImpl();
        instance.setEmailService(emailService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}