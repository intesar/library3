/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

import com.opensource.roomate.entity.ResultDto;
import com.opensource.roomate.entity.Post;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import junit.framework.Assert;
import org.apache.log4j.Logger;
import org.hibernate.ejb.EntityManagerFactoryImpl;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author inmohamm
 */
public class RoomateServiceImplTest {

    Logger logger = Logger.getLogger(RoomateServiceImplTest.class);
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Roomate");

    public RoomateServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    //@Test
    public void testPost() {
        Post post = new Post();
        post.setAddressLine("2333 w arthur ave, apt 1w");
        post.setCity("New York City");
        post.setCountry("usa");
        post.setPostedBy("Intesar shannan Mohammed");
        post.setPostDate(new Date());
        post.setEmailTransient("mdshannan@gmail.com");
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
        //impl.reIndex();
        ResultDto posts = impl.search("chicago", 0, 100);
        Assert.assertTrue(posts.getList().size() > 0);
        logger.info("--- chicago --");
        logger.info("--- chicago --" + posts.getList().size());
        EntityManagerFactoryImpl empImpl = (EntityManagerFactoryImpl) emf;
        System.out.println(empImpl.getSessionFactory().getStatistics());

        posts = impl.search("chicago", 0, 100);
        Assert.assertTrue(posts.getList().size() > 0);
        for (Post p : posts.getList()) {
            logger.info("--- " + p.getRent());
        }

        System.out.println(empImpl.getSessionFactory().getStatistics());

    }

    //@Test
    public void testSearchById() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        ResultDto dto = impl.searchById(27L);
        Assert.assertTrue(dto.getList().size() > 0);
    }

    //@Test
    public void testSendMessage() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        impl.sendMessage(27L, "Hello, can you call me on 773-216-5478", "local");
    }

    //@Test
    public void testSearchByCityZipcodeRentAndType() {
        RoomateServiceImpl impl = new RoomateServiceImpl();
        List<Post> list = impl.searchByCityZipcodeRentAndType("santa clara", "95050", 5000.0, "All", 0, 20);
        Assert.assertTrue(list != null);
        System.out.println(list.size());
    }

    //@Test
    public void testFindPostByDateAndEmail() {
    }
}
