/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service.util;

/**
 *
 * @author inmohamm
 */
public class MessageAlert implements Runnable {

    private EmailService emailService = new EmailServiceImpl();
    private String to;
    private String subject = "You have recieved message for your post # ";
    private String signature = "";
    private String url = "http://localhost:8084/";
    private String contactEmail = "info@bizintelapps.com";
    private String message;
    
    public MessageAlert(String to, long postId, String message) {
        this.to = to;
        this.subject = subject + postId;
        this.message = message;
        String append = "email=" + to + "&id=" + postId;
        signature += "<hr>"
                + "<br><br><a href='" + url + "#operation=view&" + append + "' target='_black'>Click here to view your post</a> "
                + "<br/><a href='" + url + "#operation=update&" + append + "' target='_black'>Click here to update your post</a> "
                + "<br/><a href='" + url + "#operation=delete&" + append + "' target='_black'>Click here to remove your post</a> "
                + "<br/><br/><br/> For any queries reach us at " + contactEmail
                + "<br/><br/>Thanks, <br/>The BizIntelApps, Roomate Team ";
    }

    @Override
    public void run() {
        emailService.sendEmail(to, subject, message + signature);
    }
}
