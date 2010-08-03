/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.exceptions;

/**
 *
 * @author intesar
 */
public class UnknownException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidInputException</code> without detail message.
     */
    public UnknownException() {
        super(MSG);
    }


    /**
     * Constructs an instance of <code>InvalidInputException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public UnknownException(String msg) {
        super(msg);
    }

    private static final String MSG = "Invalid input";
}
