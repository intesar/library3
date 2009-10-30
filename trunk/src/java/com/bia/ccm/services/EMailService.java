/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.services;

/**
 *
 * @author mdshannan
 */
public interface EMailService {

    String airtel = "@airtelap.com";
    String idea = "@ideacellular.net";
    String hutch = "@south.hutch.co.in";
    String bsnl = "@bsnlumS.com";
    String _160BY2 = "@160by2.com";
    String SMTP_HOST_NAME = "smtp.gmail.com";
    String SMTP_PORT = "465";
    String EMAIL_MESSAGE_TEXT = "";
    String EMAIL_SUBJECT_TEXT = " BizIntelApps FaceGuard Accounts Status Report ";
    String EMAIL_SUBJECT_TEXT_160BY2 = "bizintelapps1";
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
    String EMAIL_SIGNATURE = " <br><br> Users have often told us that the more " +
            "they use FaceGuard, the more they discover its benefits. " +
            " We'll keep working on making FaceGuard the best Application for your business, " +
            
            " <br/> <br/> <br/> For any queries reach us at info@bizintelapps.com " +
            "<br/> <br/>Thanks , <br/>The BizIntelApps, FaceGuard Team ";

    void SendMail(String[] sendTo);

    void sendEmail(String toAddress, String body);

    void sendEmail(String[] toAddress, String body);

    void sendEmail(String toAddress, String subject, String body);

    void sendEmail(String[] toAddress, String subject, String body);
}