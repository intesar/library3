/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.util;

import com.bia.ccm.services.EMailService;

/**
 *
 * @author intesar
 */
public class EMailUtil {

    public static String buildEmailAddress(String emailOrPhone, String serviceProvider) {
        String email;


        if (emailOrPhone.contains("@")) {
            email = emailOrPhone;
        } else {
            if (serviceProvider.startsWith("a") || serviceProvider.startsWith("A")) {
                email = emailOrPhone + EMailService.airtel;
            } else if (serviceProvider.startsWith("i") || serviceProvider.startsWith("I")) {
                email = emailOrPhone + EMailService.idea;
            } else if (serviceProvider.startsWith("b") || serviceProvider.startsWith("B")) {
                email = emailOrPhone + EMailService.bsnl;
            } else {
                email = emailOrPhone + EMailService._160BY2;
            }
        }

        return email;
    }
}
