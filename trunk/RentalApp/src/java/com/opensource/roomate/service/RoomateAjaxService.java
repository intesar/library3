/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

import com.opensource.roomate.entity.ResultDto;
import com.opensource.roomate.entity.Post;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;

/**
 *
 * @author inmohamm
 */
@RemoteProxy(name = "RoomateAjaxService")
public class RoomateAjaxService {

    private static RoomateService roomateService = new RoomateServiceImpl();

    @RemoteMethod
    public static int addPost(Post post, HttpServletRequest request) {
        try {
            roomateService.addPost(post, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @RemoteMethod
    public static int updatePost(Post post, String email, HttpServletRequest request) {
        try {
            roomateService.updatePost(post, email, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @RemoteMethod
    public static int deletePost(long postId, String email, HttpServletRequest request) {
        try {
            roomateService.deletePost(postId, email, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @RemoteMethod
    public static int subscribeUser(String email, String keywords, HttpServletRequest request) {
        try {
            roomateService.subscribeUser(email, keywords, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @RemoteMethod
    public static int sendMessage(long postId, String message, HttpServletRequest request) {
        try {
            roomateService.sendMessage(postId, message, request.getRemoteAddr());
            return 1;
        } catch (Exception e) {
            return -1;
        }
    }

    @RemoteMethod
    public static ResultDto search(String key, int start, int max) {
//        Date st = new Date();
        return roomateService.search(key, start, max);
//        Date ed = new Date();
//        System.out.println(" Duration ------------ " + (ed.getTime() - st.getTime()));
//        return list;
    }

    @RemoteMethod
    public static ResultDto searchById(long id) {
        return roomateService.searchById(id);
    }

    @RemoteMethod
    public static ResultDto searchByEmailAndId(String email, long id) {
        return roomateService.searchByEmailAndId(email, id);
    }

    @RemoteMethod
    public static int reportAbuse(long postId, String reportType, HttpServletRequest request) {
        roomateService.reportAbuse(postId, reportType, request.getRemoteAddr());
        return 1;
    }

    @RemoteMethod
    public static void reIndex() {
        roomateService.reIndex();
    }
}
