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
        System.out.println ( p.getDate());
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

}