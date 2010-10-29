/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.opensource.roomate.email;

import com.sun.mail.smtp.SMTPSendFailedException;
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

/**
 *
 * @author inmohamm
 */
public class EmailServiceImpl implements EmailService {

    @Override
    public void SendMail(String[] sendTo) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            sendSSMessage(sendTo, EMAIL_SUBJECT_TEXT, EMAIL_MESSAGE_TEXT, EMAIL_FROM_ADDRESS);
        } catch (MessagingException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void sendEmail(String[] toAddress, String body) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            sendSSMessage(toAddress, EMAIL_SUBJECT_TEXT, body, EMAIL_FROM_ADDRESS);
        } catch (MessagingException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void sendEmail(String[] toAddress, String subject, String body) {
        try {
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            if (subject == null || subject.length() <= 0) {
                subject = EMAIL_MESSAGE_TEXT;
            }
            sendSSMessage(toAddress, subject, body, EMAIL_FROM_ADDRESS);
        } catch (MessagingException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void sendEmail(String toAddress, String body) {
        try {
            String[] to = {toAddress};
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            sendSSMessage(to, EMAIL_SUBJECT_TEXT, body, EMAIL_FROM_ADDRESS);
        } catch (MessagingException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void sendEmail(String toAddress, String subject, String body) {
        try {
            String[] to = {toAddress};
            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            sendSSMessage(to, subject, body, EMAIL_FROM_ADDRESS);
        } catch (MessagingException ex) {
            logger.warn(ex.getMessage(), ex);
        }
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
            String message, String from) throws MessagingException {


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
        final String sendFrom;
        if (errorCount < 3) {
            sendFrom = SEND_FROM_USERNAME;
        } else if (errorCount < 6) {
            sendFrom = SEND_FROM_USERNAME1;
        } else {
            sendFrom = SEND_FROM_USERNAME2;
        }
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sendFrom, SEND_FROM_PASSWORD);
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(from);
        msg.setFrom(addressFrom);
        msg.setRecipients(Message.RecipientType.TO, addressTo);
        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message, EMAIL_CONTENT_TYPE);
        try {
            Transport.send(msg);
        } catch (SMTPSendFailedException ex) {
            errorCount++;
            logger.warn(ex.getMessage(), ex);
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }
    private int errorCount = 0;
    protected static Logger logger = Logger.getLogger(EmailServiceImpl.class);
}

