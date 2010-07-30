/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.util;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * @author intesar
 */
public class AcegiUtil {
    protected static final Log logger = LogFactory.getLog(AcegiUtil.class);

    /*
     *  returns logged in user
     */
    public static String getUsername() {
        String username = null;
        try {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (NullPointerException ex) {
            logger.warn(ex.getMessage(), ex);
        }
//        if (username == null) {
//            return "admin";
//        }
        return username;
    }

    public static boolean isAdmin() {
        GrantedAuthority[] authorities = null;
        try {
            authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        } catch (Exception ex) {
            logger.warn(ex.getMessage(), ex);
        }
        if (authorities != null) {
            for (GrantedAuthority a : authorities) {
                if (a.getAuthority().equalsIgnoreCase("role_admin")) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String getUserRole() {
        GrantedAuthority[] authorities = null;
        try {
            authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        } catch (Exception e) {
            // logger            
        }
        boolean admin = false;
        boolean employee = false;
        if (authorities != null) {
            for (GrantedAuthority a : authorities) {
                if (a.getAuthority().equalsIgnoreCase("role_admin")) {
                    admin = true;
                } else if (a.getAuthority().equalsIgnoreCase("role_user")) {
                    employee = true;
                }
            }
        }
        if (admin) {
            return "admin";
        } else if (employee) {
            return "employee";
        } else {
            return "customer";
        }
    }


}
