/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

import com.opensource.roomate.entity.ContactUs;
import com.opensource.roomate.entity.ResultDto;
import com.opensource.roomate.entity.Post;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

/**
 *
 * @author inmohamm
 */
@RemoteProxy(name = "RoomateAjaxService")
public class RoomateAjaxService {

    protected static RoomateService roomateService = new RoomateServiceImpl();
    protected static Logger logger = Logger.getLogger(RoomateAjaxService.class);

    /**
     *  1 == Operation successful
     *  -1 == Operation Unsuccessful
     */

    /**
     *
     * @param post
     * @param request
     * @return
     */
    @RemoteMethod
    public static int addPost(Post post, HttpServletRequest request) {
        try {
            roomateService.addPost(post, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     * @param post
     * @param email
     * @param request
     * @return
     */
    @RemoteMethod
    public static int updatePost(Post post, String email, HttpServletRequest request) {
        try {
            roomateService.updatePost(post, email, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     * @param postId
     * @param email
     * @param request
     * @return
     */
    @RemoteMethod
    public static int deletePost(long postId, String email, HttpServletRequest request) {
        try {
            roomateService.deletePost(postId, email, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     * @param email
     * @param keywords
     * @param request
     * @return
     */
    @RemoteMethod
    public static int subscribeUser(String email, String keywords, HttpServletRequest request) {
        try {
            roomateService.subscribeUser(email, keywords, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     * @param postId
     * @param message
     * @param request
     * @return
     */
    @RemoteMethod
    public static int sendMessage(long postId, String message, HttpServletRequest request) {
        try {
            roomateService.sendMessage(postId, message, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     * @param key
     * @param start
     * @param max
     * @return
     */
    @RemoteMethod
    public static ResultDto search(String key, int start, int max) {
//        Date st = new Date();
        return roomateService.search(key, start, max);
//        Date ed = new Date();
//        System.out.println(" Duration ------------ " + (ed.getTime() - st.getTime()));
//        return list;
    }

    /**
     *
     * @param id
     * @return
     */
    @RemoteMethod
    public static ResultDto searchById(long id) {
        return roomateService.searchById(id);
    }

    /**
     *
     * @param email
     * @param id
     * @return
     */
    @RemoteMethod
    public static ResultDto searchByEmailAndId(String email, long id) {
        return roomateService.searchByEmailAndId(email, id);
    }

    /**
     *
     * @param postId
     * @param reportType
     * @param request
     * @return
     */
    @RemoteMethod
    public static int reportAbuse(long postId, String reportType, HttpServletRequest request) {
        try {
            roomateService.reportAbuse(postId, reportType, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     */
    @RemoteMethod
    public static int reIndex() {
        try {
            roomateService.reIndex();
            return 1;
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }

    /**
     *
     * @param name
     * @param email
     * @param type
     * @param comment
     * @param request
     * @return
     */
    @RemoteMethod
    public static int contactUs(String name, String email, String type, String comment, HttpServletRequest request) {
        try {
            ContactUs contactUs = new ContactUs(name, email, type, comment, new Date(), request.getRemoteAddr());
            roomateService.contactUs(contactUs);
            return 1;
        } catch (RuntimeException e) {
            logger.warn(e.getMessage(), e);
            return -1;
        }
    }
}
