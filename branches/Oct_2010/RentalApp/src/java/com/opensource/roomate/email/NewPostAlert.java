/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.email;

import com.opensource.roomate.entity.Post;

/**
 *
 * @author inmohamm
 */
public class NewPostAlert implements Runnable {

    private EmailService emailService = new EmailServiceImpl();
    private String to;
    private String subject = "Congratulations, your ad was posted successfully with # ";
    private String signature = "";
    private String url = "http://localhost:8084/";
    private String contactEmail = "info@bizintelapps.com";

    public NewPostAlert(String email, long postId) {
        this.to = email;
        String append = "email=" + email + "&id=" + postId;
        signature += "Keep this email as reference to your posted Ad for future actions"
                + "<br><br><a href='" + url + "#id=" + postId + "' target='_black'>Click here to view your post</a> "
                + "<br/><a href='" + url + "#operation=update&" + append + "' target='_black'>Click here to update your post</a> "
                + "<br/><a href='" + url + "#operation=delete&" + append + "' target='_black'>Click here to remove your post</a> "
                + "<br/><br/><br/> For any queries reach us at " + contactEmail
                + "<br/><br/>Thanks, <br/>The BizIntelApps, Roomate Team ";
    }

    @Override
    public void run() {
        emailService.sendEmail(to, subject, signature);
    }
}
