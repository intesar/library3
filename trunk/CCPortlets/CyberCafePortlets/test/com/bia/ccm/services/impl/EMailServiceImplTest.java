/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.impl;

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
public class EMailServiceImplTest {

    public EMailServiceImplTest() {
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
     * Test of sendEmail method, of class EMailServiceImpl.
     */
    @Test
    public void testSendEmail() {
        System.out.println("sendEmail");
        String[] toAddress = null;
        String subject = "";
        String body = "";
        EMailServiceImpl instance = new EMailServiceImpl();
        instance.sendEmail(toAddress, subject, body);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}