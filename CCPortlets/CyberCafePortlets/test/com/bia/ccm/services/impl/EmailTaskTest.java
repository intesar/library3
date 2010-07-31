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
public class EmailTaskTest {

    public EmailTaskTest() {
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
     * Test of run method, of class EmailTask.
     */
    @Test
    public void testRun() {
        System.out.println("run");
        EmailTask instance = null;
        instance.run();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sendSSMessage method, of class EmailTask.
     */
    @Test
    public void testSendSSMessage() throws Exception {
        System.out.println("sendSSMessage");
        String[] recipients = null;
        String subject = "";
        String message = "";
        EmailTask instance = null;
        instance.sendSSMessage(recipients, subject, message);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}