/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

import com.opensource.roomate.entity.Post;
import java.util.Date;
import java.util.List;
import junit.framework.Assert;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author inmohamm
 */
public class RoomateServiceImplTest {

    public RoomateServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testPost() {
        Post post = new Post();
        post.setAddressLine("2333 w arthur ave, apt 1w");
        post.setCity("New York City");
        post.setCountry("usa");
        post.setPostedBy("Intesar shannan Mohammed");
        post.setPostDate(new Date());
        post.setPosterContact("mdshannan@gmail.com");
        post.setRemoteIp("local");
        post.setRent(600.00);
        post.setSex("brother");
        post.setZipcode("19200");

        RoomateServiceImpl impl = new RoomateServiceImpl();
        impl.post(post);
    }

    @Test
    public void testSearch() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        List<Post> posts = impl.search("santa clara", 0, 100);
        Assert.assertTrue(posts.size() > 0);
    }
}
