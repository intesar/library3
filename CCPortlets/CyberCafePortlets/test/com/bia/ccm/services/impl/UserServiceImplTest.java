/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.impl;

import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.dao.UsersDao;
import com.bia.ccm.dao.UsersLightDao;
import com.bia.ccm.dao.UsersPassDao;
import com.bia.ccm.services.EMailService;
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
public class UserServiceImplTest {

    public UserServiceImplTest() {
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
     * Test of getUserRole method, of class UserServiceImpl.
     */
    @Test
    public void testGetUserRole() {
        System.out.println("getUserRole");
        String username = "";
        UserServiceImpl instance = new UserServiceImpl();
        String expResult = "";
        String result = instance.getUserRole(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of forgotPassword method, of class UserServiceImpl.
     */
    @Test
    public void testForgotPassword() {
        System.out.println("forgotPassword");
        String email = "";
        UserServiceImpl instance = new UserServiceImpl();
        instance.forgotPassword(email);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetPassword method, of class UserServiceImpl.
     */
    @Test
    public void testResetPassword() {
        System.out.println("resetPassword");
        String email = "";
        String activationCode = "";
        String newPassword = "";
        String ip = "";
        UserServiceImpl instance = new UserServiceImpl();
        instance.resetPassword(email, activationCode, newPassword, ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersDao method, of class UserServiceImpl.
     */
    @Test
    public void testSetUsersDao() {
        System.out.println("setUsersDao");
        UsersDao usersDao = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setUsersDao(usersDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEMailService method, of class UserServiceImpl.
     */
    @Test
    public void testSetEMailService() {
        System.out.println("setEMailService");
        EMailService eMailService = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setEMailService(eMailService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrganizationDao method, of class UserServiceImpl.
     */
    @Test
    public void testSetOrganizationDao() {
        System.out.println("setOrganizationDao");
        OrganizationDao organizationDao = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setOrganizationDao(organizationDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystemsDao method, of class UserServiceImpl.
     */
    @Test
    public void testSetSystemsDao() {
        System.out.println("setSystemsDao");
        SystemsDao systemsDao = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setSystemsDao(systemsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServicesDao method, of class UserServiceImpl.
     */
    @Test
    public void testSetServicesDao() {
        System.out.println("setServicesDao");
        ServicesDao servicesDao = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setServicesDao(servicesDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswordEncryptor method, of class UserServiceImpl.
     */
    @Test
    public void testSetPasswordEncryptor() {
        System.out.println("setPasswordEncryptor");
        PasswordEncryptor passwordEncryptor = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setPasswordEncryptor(passwordEncryptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersLightDao method, of class UserServiceImpl.
     */
    @Test
    public void testSetUsersLightDao() {
        System.out.println("setUsersLightDao");
        UsersLightDao usersLightDao = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setUsersLightDao(usersLightDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersPassDao method, of class UserServiceImpl.
     */
    @Test
    public void testSetUsersPassDao() {
        System.out.println("setUsersPassDao");
        UsersPassDao usersPassDao = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setUsersPassDao(usersPassDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStringEncryptor method, of class UserServiceImpl.
     */
    @Test
    public void testSetStringEncryptor() {
        System.out.println("setStringEncryptor");
        PBEStringEncryptor stringEncryptor = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setStringEncryptor(stringEncryptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmailService method, of class UserServiceImpl.
     */
    @Test
    public void testSetEmailService() {
        System.out.println("setEmailService");
        EMailService emailService = null;
        UserServiceImpl instance = new UserServiceImpl();
        instance.setEmailService(emailService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}