/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.exceptions;

/**
 *
 * @author intesar
 */
public class NoRoleException extends RuntimeException {

    /**
     * Creates a new instance of <code>NoRoleException</code> without detail message.
     */
    public NoRoleException() {
        super(MSG);
    }


    /**
     * Constructs an instance of <code>NoRoleException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public NoRoleException(String msg) {
        super(msg);
    }

    private static final String MSG = "User doesn't have permission to execute this operation!";
}
