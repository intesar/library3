/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.impl;

import com.bia.ccm.dao.EmailPreferenceDao;
import com.bia.ccm.dao.EmailTimePreferenceDao;
import com.bia.ccm.dao.OrganizationDao;
import com.bia.ccm.dao.ServicesDao;
import com.bia.ccm.dao.SystemLeaseDao;
import com.bia.ccm.dao.SystemsDao;
import com.bia.ccm.dao.UsersDao;
import com.bia.ccm.dao.UsersLightDao;
import com.bia.ccm.dao.UsersPassDao;
import com.bia.ccm.entity.EmailPreference;
import com.bia.ccm.entity.EmailTimePreference;
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.entity.UsersLight;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.util.ServiceFactory;
import com.bia.converter.CaseConverter;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import junit.framework.Assert;
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
public class AdminServiceImplTest {

    public AdminServiceImplTest() {
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
     * Test of deleteEmail method, of class AdminServiceImpl.
     */
    @Test
    public void testDeleteEmail() {
        System.out.println("deleteEmail");
        int id = 0;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.deleteEmail(id, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateRentalPrice method, of class AdminServiceImpl.
     */
    @Test
    public void testUpdateRentalPrice() {
        System.out.println("updateRentalPrice");
        int mims = 0;
        double rate = 0.0;
        Integer lmins = null;
        Double lrate = null;
        String username = "";
        String ip = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.updateRentalPrice(mims, rate, lmins, lrate, username, ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSystems method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllSystems() {
        System.out.println("getAllSystems");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllSystems(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveSystem method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveSystem() {
        System.out.println("saveSystem");
        Systems systems = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveSystem(systems, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllUsers method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllUsers() {
        System.out.println("getAllUsers");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllUsers(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of savePreferences method, of class AdminServiceImpl.
     */
    @Test
    public void testSavePreferences() {
        System.out.println("savePreferences");
        Set<String> emails = new HashSet<String>();
        emails.add("intesar@ymail.com");
        Set<Short> timings = new HashSet<Short>();
        Short s = new Short("" + 100);
        timings.add(s);
        String organization = "100";
        String userId = "100";
        String ip = "100";
        AdminService instance = (AdminService) ServiceFactory.getService("adminServiceImpl");
        instance.savePreferences(emails, timings, organization, userId, ip);

        PreferenceDto dto = instance.getPreferences(organization);
        Assert.assertEquals(emails, dto.getEmails());
        Assert.assertEquals(timings, dto.getTimings());
    }

    /**
     * Test of getPreferences method, of class AdminServiceImpl.
     */
    @Test
    public void testGetPreferences() {
        System.out.println("getPreferences");
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        PreferenceDto expResult = null;
        PreferenceDto result = instance.getPreferences(organization);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEmailPreference method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllEmailPreference() {
        System.out.println("getAllEmailPreference");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllEmailPreference(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllOrganizationEmailPreference method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllOrganizationEmailPreference() {
        System.out.println("getAllOrganizationEmailPreference");
        String org = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllOrganizationEmailPreference(org);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveEmailPreference method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveEmailPreference() {
        System.out.println("saveEmailPreference");
        EmailPreference emailPreference = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveEmailPreference(emailPreference, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllEmailTimePreference method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllEmailTimePreference() {
        System.out.println("getAllEmailTimePreference");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllEmailTimePreference(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveEmailTimePreference method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveEmailTimePreference() {
        System.out.println("saveEmailTimePreference");
        EmailTimePreference emailTimePreference = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveEmailTimePreference(emailTimePreference, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteEmailTimePreference method, of class AdminServiceImpl.
     */
    @Test
    public void testDeleteEmailTimePreference() {
        System.out.println("deleteEmailTimePreference");
        EmailTimePreference emailTimePreference = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.deleteEmailTimePreference(emailTimePreference, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSystemLease method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllSystemLease() {
        System.out.println("getAllSystemLease");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllSystemLease(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveSystemLease method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveSystemLease() {
        System.out.println("saveSystemLease");
        SystemLease systemLease = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        String expResult = "";
        String result = instance.saveSystemLease(systemLease, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrganization method, of class AdminServiceImpl.
     */
    @Test
    public void testGetOrganization() {
        System.out.println("getOrganization");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        Organization expResult = null;
        Organization result = instance.getOrganization(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveOrganization method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveOrganization() {
        System.out.println("saveOrganization");
        Organization organization = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveOrganization(organization, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemLease method, of class AdminServiceImpl.
     */
    @Test
    public void testGetSystemLease() {
        System.out.println("getSystemLease");
        Date startDate = null;
        Date endDate = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getSystemLease(startDate, endDate, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMySystemLease method, of class AdminServiceImpl.
     */
    @Test
    public void testGetMySystemLease() {
        System.out.println("getMySystemLease");
        Date startDate = null;
        Date endDate = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getMySystemLease(startDate, endDate, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReport method, of class AdminServiceImpl.
     */
    @Test
    public void testGetReport() {
        System.out.println("getReport");
        Date startDate = null;
        Date endDate = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getReport(startDate, endDate, username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveService method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveService() {
        System.out.println("saveService");
        Services service = null;
        String username = "";
        String ip = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveService(service, username, ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteService method, of class AdminServiceImpl.
     */
    @Test
    public void testDeleteService() {
        System.out.println("deleteService");
        Integer id = null;
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.deleteService(id, username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllServices method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllServices() {
        System.out.println("getAllServices");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllServices(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystem method, of class AdminServiceImpl.
     */
    @Test
    public void testGetSystem() {
        System.out.println("getSystem");
        String org = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        Systems expResult = null;
        Systems result = instance.getSystem(org);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendReports method, of class AdminServiceImpl.
     */
    @Test
    public void testSendReports() {
        System.out.println("sendReports");
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.sendReports();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEmailTimePreferences method, of class AdminServiceImpl.
     */
    @Test
    public void testGetEmailTimePreferences() {
        System.out.println("getEmailTimePreferences");
        short time = 0;
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getEmailTimePreferences(time);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUserByUsername method, of class AdminServiceImpl.
     */
    @Test
    public void testGetUserByUsername() {
        System.out.println("getUserByUsername");
        String username = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        UsersLight expResult = null;
        UsersLight result = instance.getUserByUsername(username);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetUsersDao() {
        System.out.println("setUsersDao");
        UsersDao usersDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setUsersDao(usersDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystemsDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetSystemsDao() {
        System.out.println("setSystemsDao");
        SystemsDao systemsDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setSystemsDao(systemsDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmailPreferenceDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetEmailPreferenceDao() {
        System.out.println("setEmailPreferenceDao");
        EmailPreferenceDao emailPreferenceDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setEmailPreferenceDao(emailPreferenceDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmailTimePreferenceDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetEmailTimePreferenceDao() {
        System.out.println("setEmailTimePreferenceDao");
        EmailTimePreferenceDao emailTimePreferenceDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setEmailTimePreferenceDao(emailTimePreferenceDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOrganizationDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetOrganizationDao() {
        System.out.println("setOrganizationDao");
        OrganizationDao organizationDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setOrganizationDao(organizationDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystemLeaseDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetSystemLeaseDao() {
        System.out.println("setSystemLeaseDao");
        SystemLeaseDao systemLeaseDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setSystemLeaseDao(systemLeaseDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setServicesDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetServicesDao() {
        System.out.println("setServicesDao");
        ServicesDao servicesDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setServicesDao(servicesDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswordEncryptor method, of class AdminServiceImpl.
     */
    @Test
    public void testSetPasswordEncryptor() {
        System.out.println("setPasswordEncryptor");
        PasswordEncryptor passwordEncryptor = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setPasswordEncryptor(passwordEncryptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersLightDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetUsersLightDao() {
        System.out.println("setUsersLightDao");
        UsersLightDao usersLightDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setUsersLightDao(usersLightDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setUsersPassDao method, of class AdminServiceImpl.
     */
    @Test
    public void testSetUsersPassDao() {
        System.out.println("setUsersPassDao");
        UsersPassDao usersPassDao = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setUsersPassDao(usersPassDao);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStringEncryptor method, of class AdminServiceImpl.
     */
    @Test
    public void testSetStringEncryptor() {
        System.out.println("setStringEncryptor");
        PBEStringEncryptor stringEncryptor = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setStringEncryptor(stringEncryptor);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEmailService method, of class AdminServiceImpl.
     */
    @Test
    public void testSetEmailService() {
        System.out.println("setEmailService");
        EMailService emailService = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setEmailService(emailService);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCaseConverter method, of class AdminServiceImpl.
     */
    @Test
    public void testSetCaseConverter() {
        System.out.println("setCaseConverter");
        CaseConverter caseConverter = null;
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.setCaseConverter(caseConverter);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}