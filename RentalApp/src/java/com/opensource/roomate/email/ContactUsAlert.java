/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.opensource.roomate.email;

import com.opensource.roomate.entity.ContactUs;

/**
 *
 * @author inmohamm
 */
public class ContactUsAlert implements Runnable {

    private EmailService emailService = new EmailServiceImpl();
    private String to = "mdshannan@gmail.com";
    private String subject = "Contact Us Alert";
    private String message = "";
    private ContactUs contactUs;

    public ContactUsAlert(ContactUs contact) {
        this.contactUs = contact;
    }

    @Override
    public void run() {
        message += "Name : " + contactUs.getName() + "<br/>"
                + "Email : " + contactUs.getEmail() + "<br/>"
                + "Type : " + contactUs.getType() + "<br/>"
                + "Message : " + contactUs.getComment() + "<br/>"
                + "IP : " + contactUs.getRemoteIp();
        emailService.sendEmail(to, subject, message);
    }
}
