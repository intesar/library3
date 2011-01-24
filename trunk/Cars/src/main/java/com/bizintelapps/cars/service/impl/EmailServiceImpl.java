/*
 *
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 *
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bizintelapps.cars.service.impl;

import com.bizintelapps.cars.entity.Car;
import com.bizintelapps.cars.entity.Image;
import com.bizintelapps.cars.service.EmailService;
import java.security.Security;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author inmohamm
 */
@Service
public class EmailServiceImpl implements EmailService {

    @Override
    public void sendEmail(String toAddress, String subject, Car car, String comment) {
        String[] to = {toAddress};
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        sendSSMessage(to, subject, car, comment);

    }

    /**
     * 
     * @param recipients
     * @param subject
     * @param message
     * @param from
     * @throws MessagingException
     */
    private void sendSSMessage(String recipients[], String subject,
            Car car, String comment) {

        try {
            InternetAddress[] addressTo = new InternetAddress[recipients.length];
            for (int i = 0; i < recipients.length; i++) {
                if (recipients[i] != null && recipients[i].length() > 0) {

                    addressTo[i] = new InternetAddress(recipients[i]);

                }
            }

            if (addressTo == null || addressTo.length == 0) {
                return;
            }

            boolean debug = true;

            Properties props = new Properties();
            props.put("mail.smtp.host", SMTP_HOST_NAME);
            props.put("mail.smtp.auth", "true");
            props.put("mail.debug", "true");
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.port", SMTP_PORT);
            props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
            props.put("mail.smtp.socketFactory.fallback", "false");
            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {

                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(SEND_FROM_USERNAME, SEND_FROM_PASSWORD);
                        }
                    });

            session.setDebug(debug);

            Message msg = new MimeMessage(session);
            InternetAddress addressFrom = new InternetAddress(EMAIL_FROM_ADDRESS);
            msg.setFrom(addressFrom);
            msg.setRecipients(Message.RecipientType.TO, addressTo);
            // Setting the Subject and Content Type
            msg.setSubject(subject);
            String message = buildMessage(car, comment);
            msg.setContent(message, EMAIL_CONTENT_TYPE);

            Transport.send(msg);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
            throw new RuntimeException("Error sending email, please check to and from emails are correct!");
        }
    }

    
    private String buildMessage(Car car, String comment) {
        String msg = comment + "<br/><br/>";
        // TODO - change from email and password in interface
        // TODO - add car details
        msg += "<div>Make : "+ car.getMake() + "</div>"
            +  "<div>Model : "+ car.getModel()+ "</div>"
            +  "<div>Year : "+ car.getYear() + "</div>"
            +  "<div>Color : "+ car.getExteriorColor() + "</div>"
            +  "<div>Doors : "+ car.getDoors() + "</div>"
            +  "<div>Transmission : " + car.getTransmission() + "</div>"
            +  "<div><strong>Only : $" + car.getAskingPrice() + "</strong></div>"
            +  "<br/><br/>"
            ;
        // TODO - add images as <img> tags check js file for reference
        for ( Image image : car.getImages() ) {
            msg += "<div> "
                    + "<img src='/library?p_p_id=31&p_p_lifecycle=0&p_p_state=normal&p_p_mode=view&p_p_col_id=column-1&p_p_col_count=1&_31_struts_action=/image_gallery/view&_31_folderId="+image.getUuid()+"'/> </div>";

      }

        msg += EMAIL_SIGNATURE;
        return msg;
    }
    protected static Logger logger = Logger.getLogger(EmailServiceImpl.class);
}
