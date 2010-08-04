/*
 * 
 */
package com.bia.ccm.services;

/**
 *
 * @author mdshannan
 */
public interface EMailService {

    String SMTP_HOST_NAME = "smtp.gmail.com";
    String SMTP_PORT = "465";
//    String EMAIL_FROM_ADDRESS = "faceguard3@bizintelapps.com";
//    String SEND_FROM_USERNAME = "faceguard3@bizintelapps.com";
//    String EMAIL_FROM_ADDRESS1 = "face.guard3@bizintelapps.com";
//    String SEND_FROM_USERNAME1 = "face.guard3@bizintelapps.com";
//    String EMAIL_FROM_ADDRESS2 = "face.guard4@bizintelapps.com";
//    String SEND_FROM_USERNAME2 = "face.guard4@bizintelapps.com";
    String USERNAME = "7cybercafe@bizintelapps.com";
    String PASSWORD = "password7123";

    String FROM = "7 Cyber Cafe";
    String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

    String EMAIL_CONTENT_TYPE = "text/html";
    
    String EMAIL_SIGNATURE = " <br/> <br/> Thanks, <br/>The BizIntelApps, 7CyberCafe Team ";

    /**
     * 
     * @param toAddress
     * @param subject
     * @param body
     */
    void sendEmail(String[] toAddress, String subject, String body);
}