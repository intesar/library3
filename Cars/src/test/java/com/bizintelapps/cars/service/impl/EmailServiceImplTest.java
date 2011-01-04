/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bizintelapps.cars.service.impl;

import com.bizintelapps.cars.service.EmailService;
import com.bizintelapps.cars.entity.Car;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.bizintelapps.cars.entity.Image;
import com.bizintelapps.cars.entity.ResultDto;
import com.bizintelapps.cars.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.junit.runner.RunWith;
import com.bizintelapps.cars.entity.Car;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/applicationContext-Annotations.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
public class EmailServiceImplTest {

    @Autowired
    EmailService emailService;

    public EmailServiceImplTest() {
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
     * Test of sendEmail method, of class EmailServiceImpl.
     */
    @Test
    public void testSendEmail() {
        System.out.println("sendEmail");
        String toAddress = "atefahmed@gmail.com";
        String subject = "test";
        Car car = new Car();
        car.setMake("Toyota");
        car.setYear(2002);
        String comment = "Test";
        emailService.sendEmail(toAddress, subject, car, comment);
    }

}