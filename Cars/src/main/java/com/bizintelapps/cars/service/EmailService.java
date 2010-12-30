/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bizintelapps.cars.service;

import com.bizintelapps.cars.entity.Car;

/**
 *
 * @author inmohamm
 */
public interface EmailService {

    String SMTP_HOST_NAME = "smtp.gmail.com";
    String SMTP_PORT = "465";
    String EMAIL_FROM_ADDRESS = "faceguard3@bizintelapps.com";
    String SEND_FROM_USERNAME = "faceguard3@bizintelapps.com";
    String SEND_FROM_PASSWORD = "Abbh1234";
    String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    String EMAIL_CONTENT_TYPE = "text/html";
    String EMAIL_SIGNATURE = "<br/><br/>"
            + "Thanks, <br/>"
            + "Manheim Motors, Inc. <br/>"
            + "417 N Villa Avenue, Villa Park, IL 60181 <br/>"
            + "(630) 279-7000";

    
    /**
     * 
     * @param toAddress
     * @param car
     * @param comment
     */
    void sendEmail(String toAddress, String subject, Car car, String comment);

   
}
