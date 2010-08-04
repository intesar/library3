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

import com.bia.ccm.services.EMailService;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author intesar
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-Annotations.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class EMailServiceImplTest {

    @Autowired
    EMailService eMailService;
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
        sendValidEmail();
        sendInvalidEmail();
        sendInvalidEmails();
        sendValidSubject();
        
    }
    private void sendValidEmail() {
        String[] toAddress = {"mdshannan@gmail.com"};
        String subject = "test";
        String body = "test";
        this.eMailService.sendEmail(toAddress, subject, body);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println ( "exception waiting..");
        }
    }
    private void sendInvalidEmail() {
        String[] toAddress = {""};
        String subject = "test";
        String body = "test";
        this.eMailService.sendEmail(toAddress, subject, body);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println ( "exception waiting..");
        }
    }
    private void sendInvalidEmails() {
        String[] toAddress = {"mdshannan@gmail.com",""};
        String subject = "test";
        String body = "test";
        this.eMailService.sendEmail(toAddress, subject, body);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println ( "exception waiting..");
        }
    }
    private void sendValidSubject() {
        String[] toAddress = {"mdshannan@gmail.com"};
        String subject = "";
        String body = "test";
        this.eMailService.sendEmail(toAddress, subject, body);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            System.out.println ( "exception waiting..");
        }
    }


}