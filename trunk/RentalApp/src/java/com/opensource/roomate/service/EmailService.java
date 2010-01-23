/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.service;

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

    void SendMail(String[] sendTo);

    void sendEmail(String toAddress, String body);

    void sendEmail(String[] toAddress, String body);

    void sendEmail(String toAddress, String subject, String body);

    void sendEmail(String[] toAddress, String subject, String body);
}
