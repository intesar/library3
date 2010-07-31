/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.services.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author intesar
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({com.bia.ccm.services.impl.MembershipServiceImplTest.class,com.bia.ccm.services.impl.EMailServiceImplTest.class,com.bia.ccm.services.impl.EmailTaskTest.class,com.bia.ccm.services.impl.AdminServiceImplTest.class,com.bia.ccm.services.impl.UserServiceImplTest.class,com.bia.ccm.services.impl.WorkServiceImplTest.class})
public class ImplSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}