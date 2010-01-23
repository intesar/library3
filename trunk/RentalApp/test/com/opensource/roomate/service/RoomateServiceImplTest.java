/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

import com.opensource.roomate.entity.Post;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author inmohamm
 */
public class RoomateServiceImplTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Roomate");

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
        post.setEmail("mdshannan@gmail.com");
        post.setPhone("773-216-5478");
        post.setRent(600.00);
        post.setSex("brother");
        post.setZipcode("19200");

        RoomateServiceImpl impl = new RoomateServiceImpl();
        impl.addPost(post, "local");
    }

    @Test
    public void testSearch() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        List<Post> posts = impl.search("santa clara", 0, 100);
        Assert.assertTrue(posts.size() > 0);
        
        EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl) emf;
        System.out.println(empImpl.getSessionFactory().getStatistics());

        posts = impl.search("santa clara", 0, 100);
        Assert.assertTrue(posts.size() > 0);


        System.out.println(empImpl.getSessionFactory().getStatistics());

    }

    @Test
    public void testSearchById() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        List<Post> posts = impl.searchById(27L);
        Assert.assertTrue(posts.size() > 0);
    }

    @Test
    public void testSendMessage() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        impl.sendMessage(27L, "Hello, can you call me on 773-216-5478", "local");
    }

    @Test
    public void testFindPostByDateAndEmail() {
    }
}
