/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.opensource.roomate.email;

/**
 *
 * @author inmohamm
 */
public interface EmailService {

    String SMTP_HOST_NAME = "smtp.gmail.com";
    String SMTP_PORT = "465";
    String EMAIL_MESSAGE_TEXT = "";
    String EMAIL_SUBJECT_TEXT = " You have recieved a message ";
    String EMAIL_FROM_ADDRESS = "faceguard3@bizintelapps.com";
    String SEND_FROM_USERNAME = "faceguard3@bizintelapps.com";
    String EMAIL_FROM_ADDRESS1 = "face.guard3@bizintelapps.com";
    String SEND_FROM_USERNAME1 = "face.guard3@bizintelapps.com";
    String EMAIL_FROM_ADDRESS2 = "face.guard4@bizintelapps.com";
    String SEND_FROM_USERNAME2 = "face.guard4@bizintelapps.com";
    String SEND_FROM_PASSWORD = "Abbh1234";
    String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    String[] SEND_TO = {"mohdshannan@yahoo.com"};
    String EMAIL_CONTENT_TYPE = "text/html";
    String EMAIL_SIGNATURE = " <br><br>"
            + "<a href='' target='_black'>Click here to update your post </a> "
            + "<br/>"
            + "<a href='' target='_black'>Click here to remove your post </a> "
            + "<br/><br/> "
            + " We'll keep working on making Roomate.com the best Application for you, "
            + " <br/> <br/> <br/> For any queries reach us at info@bizintelapps.com "
            + "<br/> <br/>Thanks , <br/>The BizIntelApps, Roomate Team ";

    /**
     *
     * @param sendTo
     */
    void SendMail(String[] sendTo);

    /**
     *
     * @param toAddress
     * @param body
     */
    void sendEmail(String toAddress, String body);

    /**
     *
     * @param toAddress
     * @param body
     */
    void sendEmail(String[] toAddress, String body);

    /**
     *
     * @param toAddress
     * @param subject
     * @param body
     */
    void sendEmail(String toAddress, String subject, String body);

    /**
     *
     * @param toAddress
     * @param subject
     * @param body
     */
    void sendEmail(String[] toAddress, String subject, String body);
}
