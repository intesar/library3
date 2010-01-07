/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

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
    public static String post(Post post, HttpServletRequest request) {
        try {
            post.setPostDate(new Date());
            post.setRemoteIp(request.getRemoteAddr());
            roomateService.post(post);
            return "Congratulations, Your add is posted successfully!";
        } catch (Exception e) {
            return "Error, please try again!";
        }

    }

    @RemoteMethod
    public static List<Post> search(String key) {
        return roomateService.search(key, 0, 100);
    }
}
