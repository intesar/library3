/*
 * 
 */
package com.bia.ccm.services.impl;

import com.bia.ccm.services.EMailService;

import com.sun.mail.smtp.SMTPSendFailedException;
import java.security.Security;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author mdshannan
 */
public class EMailServiceImpl implements EMailService {

    @Override
    public void sendEmail(String[] toAddress, String subject, String body) {
        try {
            if ( logger.isTraceEnabled()) {
                logger.trace("Sending email to queue..." + toAddress[0]);
            }
            EmailTask emailTask = new EmailTask(toAddress, subject, body);
            executorService.execute(emailTask);
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }
    protected static final Log logger = LogFactory.getLog(EMailServiceImpl.class);
    protected static final ExecutorService executorService = Executors.newCachedThreadPool();
}

class EmailTask implements Runnable {
    protected String[] toAddress;
    protected String subject;
    protected String body;
    protected static int errorCount = 0;
    protected static final Log logger = LogFactory.getLog(EmailTask.class);


    public EmailTask(String[] toAddress, String subject, String body) {
        this.toAddress = toAddress;
        this.subject = subject;
        this.body = body;
    }

    @Override
    public void run() {
        try {
            if ( logger.isTraceEnabled()) {
                logger.trace("Started sending email..." + toAddress[0]);
            }
            this.sendSSMessage(toAddress, subject, body);
            if ( logger.isTraceEnabled()) {
                logger.trace("Finished sending email..." + toAddress[0]);
            }
        } catch (MessagingException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    protected void sendSSMessage(String recipients[], String subject,
            String message) throws MessagingException {

        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        if (subject == null || subject.length() <= 0) {
            subject = EMailService.EMAIL_MESSAGE_TEXT;
        }

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
        props.put("mail.smtp.host", EMailService.SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.port", EMailService.SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", EMailService.SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", EMailService.SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");
        final String sendFrom;
        if (errorCount < 3) {
            sendFrom = EMailService.SEND_FROM_USERNAME;
        } else if (errorCount < 6) {
            sendFrom = EMailService.SEND_FROM_USERNAME1;
        } else {
            sendFrom = EMailService.SEND_FROM_USERNAME2;
        }
        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(sendFrom, EMailService.SEND_FROM_PASSWORD);
                    }
                });

        session.setDebug(debug);

        Message msg = new MimeMessage(session);
        InternetAddress addressFrom = new InternetAddress(EMailService.EMAIL_FROM_ADDRESS);
        msg.setFrom(addressFrom);
        msg.setRecipients(Message.RecipientType.TO, addressTo);
        // Setting the Subject and Content Type
        msg.setSubject(subject);
        msg.setContent(message + EMailService.EMAIL_SIGNATURE, EMailService.EMAIL_CONTENT_TYPE);
        try {
            Transport.send(msg);
        } catch (SMTPSendFailedException ex) {
            errorCount++;
            logger.warn(ex.getMessage(), ex);
        } catch (RuntimeException ex) {
            logger.warn(ex.getMessage(), ex);
        }
    }
}
