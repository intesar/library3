/*
 * 
 * Author: Intesar Shannan Mohammed
 * 
 * Copyright 2010 BizIntelApps. All Rights Reserved.
 * 
 * This software is the proprietary information of BizIntelApps.
 * Use is subject to license terms.
 */
package com.bia.ccm.util;

/**
 *
 * @author intesar
 */
public class EmailUtil {

    public static boolean isValidEmail(String... emails) {
        String AT_THE_RATE = "@";
        String DOT = ".";
        int MIN_LENGTH = 5;
        for (String email : emails) {
            if (email.contains(AT_THE_RATE) && email.contains(DOT) && email.length() >= MIN_LENGTH) {
            } else {
                return false;
            }
        }
        return true;
    }
}
