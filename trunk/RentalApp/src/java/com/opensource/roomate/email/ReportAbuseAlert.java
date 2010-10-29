/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.opensource.roomate.email;

import com.opensource.roomate.entity.PostReportAbuse;
import java.util.List;

/**
 *
 * @author inmohamm
 */
public class ReportAbuseAlert implements Runnable {

    private EmailService emailService = new EmailServiceImpl();
    private String to = "mdshannan@gmail.com";
    private String subject = "ReportAbuse for your post # ";
    private String signature = "";
    private String url = "http://localhost:8084/";
    private String message = "";
    private List<PostReportAbuse> list;

    public ReportAbuseAlert(long postId, List<PostReportAbuse> list) {
        this.subject = subject + postId;
        String append = "email=" + to + "&id=" + postId;
        signature += "<hr>"
                + "<br><br><a href='" + url + "#operation=view&" + append + "' target='_black'>Click here to view your post</a> "
                + "<br/><a href='" + url + "#operation=update&" + append + "' target='_black'>Click here to update your post</a> "
                + "<br/><a href='" + url + "#operation=delete&" + append + "' target='_black'>Click here to remove your post</a> ";
        this.list = list;
    }

    @Override
    public void run() {
        for (PostReportAbuse pra : list) {
            message += pra.getReportType() + " " + pra.getReportDate() + " " + pra.getReportIp() + "<br/>";
        }
        emailService.sendEmail(to, subject, message + signature);
    }
}
