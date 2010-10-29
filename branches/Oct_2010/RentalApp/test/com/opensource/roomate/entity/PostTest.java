/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.entity;

import java.util.Date;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author inmohamm
 */
public class PostTest {

    public PostTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testGetId() {
    }

    @Test
    public void testSetId() {
    }

    @Test
    public void testGetPostedBy() {
    }

    @Test
    public void testSetPostedBy() {
    }

    @Test
    public void testGetPosterContact() {
    }

    @Test
    public void testSetPosterContact() {
    }

    @Test
    public void testGetPostDate() {
    }

    @Test
    public void testSetPostDate() {
    }

    @Test
    public void testGetSex() {
    }

    @Test
    public void testSetSex() {
    }

    @Test
    public void testGetRent() {
    }

    @Test
    public void testSetRent() {
    }

    @Test
    public void testGetAddressLine() {
    }

    @Test
    public void testSetAddressLine() {
    }

    @Test
    public void testGetCity() {
    }

    @Test
    public void testSetCity() {
    }

    @Test
    public void testGetZipcode() {
    }

    @Test
    public void testSetZipcode() {
    }

    @Test
    public void testGetCountry() {
    }

    @Test
    public void testSetCountry() {
    }

    @Test
    public void testGetRemoteIp() {
    }

    @Test
    public void testSetRemoteIp() {
    }

    @Test
    public void testGetComment() {
    }

    @Test
    public void testSetComment() {
    }

    @Test
    public void testGetDate() {
        Post p = new Post();
        p.setPostDate(new Date());
        System.out.println(p.getDate());
        Assert.assertEquals(p.getDate(), "New");


    }

    @Test
    public void testSetDate() {
    }

    @Test
    public void testHashCode() {
    }

    @Test
    public void testEquals() {
    }

    @Test
    public void testToString() {
    }

    /**
     * 1-100 = 100
     * 101 - 200 = 200
     * 201 - 300 = 300
     *
     *
     */
    @Test
    public void testRentAdjustment() {
        Assert.assertEquals((int)Math.ceil(0d/100), 0);
        Assert.assertEquals((int)Math.ceil(100d/100), 1);
        Assert.assertEquals((int)Math.ceil(99d / 100), 1);
        Assert.assertEquals((int)Math.ceil(44d / 100), 1);
        Assert.assertEquals((int)Math.ceil(144d / 100), 2);
        Assert.assertEquals((int)Math.ceil(444d / 100), 5);
        Assert.assertEquals((int)Math.ceil(644d / 100), 7);
        Assert.assertEquals((int)Math.ceil(844d / 100), 9);
        Assert.assertEquals((int)Math.ceil(1044d / 100), 11);
        Assert.assertEquals((int)Math.ceil(1644d / 100), 17);
        Assert.assertEquals((int)Math.ceil(2444d / 100), 25);

        //Assert.assertEquals((int)Math.ceil(null / 100) * 100, 0);
    }
}
