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
import com.bia.ccm.entity.Organization;
import com.bia.ccm.entity.PreferenceDto;
import com.bia.ccm.entity.Services;
import com.bia.ccm.entity.SystemLease;
import com.bia.ccm.entity.Systems;
import com.bia.ccm.services.AdminService;
import com.bia.ccm.services.EMailService;
import com.bia.ccm.util.ServiceFactory;
import com.bia.converter.CaseConverter;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
     * Test of updateRentalPrice method, of class AdminServiceImpl.
     */
    @Test
    public void testUpdateRentalPrice() {
        System.out.println("updateRentalPrice");
        int mims = 0;
        double rate = 0.0;
        Integer lmins = null;
        Double lrate = null;
        String organization = "";
        String username = "";
        String ip = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.updateRentalPrice(mims, rate, lmins, lrate, organization, username, ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllSystems method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllSystems() {
        System.out.println("getAllSystems");
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllSystems(organization);
        assertEquals(expResult, result);
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
     * Test of getAllOrganizationEmailPreference method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllOrganizationEmailPreference() {
        System.out.println("getAllOrganizationEmailPreference");
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllOrganizationEmailPreference(organization);
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
        Set<String> emails = null;
        Set<Short> timings = null;
        String organization = "";
        String userId = "";
        String ip = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.savePreferences(emails, timings, organization, userId, ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
     * Test of getAllSystemLease method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllSystemLease() {
        System.out.println("getAllSystemLease");
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllSystemLease(organization);
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
     * Test of getSystemLease method, of class AdminServiceImpl.
     */
    @Test
    public void testGetSystemLease() {
        System.out.println("getSystemLease");
        Date startDate = null;
        Date endDate = null;
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getSystemLease(startDate, endDate, organization);
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
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getReport(startDate, endDate, organization);
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
        String organization = "";
        String ip = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveService(service, username, organization, ip);
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
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.deleteService(id, organization);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllServices method, of class AdminServiceImpl.
     */
    @Test
    public void testGetAllServices() {
        System.out.println("getAllServices");
        String organization = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        List expResult = null;
        List result = instance.getAllServices(organization);
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
     * Test of saveOrganization method, of class AdminServiceImpl.
     */
    @Test
    public void testSaveOrganization() {
        System.out.println("saveOrganization");
        Organization organization = null;
        long organizationId = 0L;
        long userId = 0L;
        String ip = "";
        AdminServiceImpl instance = new AdminServiceImpl();
        instance.saveOrganization(organization, organizationId, userId, ip);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOrganization method, of class AdminServiceImpl.
     */
    @Test
    public void testGetOrganization() {
        System.out.println("getOrganization");
        long organizationId = 100L;
        String organizationName = "test";
        AdminService instance = (AdminService) ServiceFactory.getService("adminServiceImpl");
        String expResult = organizationName;
        Organization result = instance.getOrganization(organizationId, organizationName);
        assertEquals(expResult, result.getName());
        
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