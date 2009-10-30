/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bia.ccm.util;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.context.SecurityContextHolder;

/**
 *
 * @author intesar
 */
public class AcegiUtil {

    /*
     *  returns logged in user
     */
    public static final String getUsername() {
        String username = null;
        try {
            username = SecurityContextHolder.getContext().getAuthentication().getName();
        } catch (NullPointerException npe) {
        }
        if (username == null) {
            return "admin";
        }
        return username;
    }

    public static final boolean isAdmin() {
        GrantedAuthority[] authorities = null;
        try {
            authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        } catch (Exception e) {
            // logger
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

    public static final String getUserRole() {
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
