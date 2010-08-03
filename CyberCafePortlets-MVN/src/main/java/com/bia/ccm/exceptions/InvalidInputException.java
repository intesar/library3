/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.bia.ccm.exceptions;

/**
 *
 * @author intesar
 */
public class InvalidInputException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidInputException</code> without detail message.
     */
    public InvalidInputException() {
        super(MSG);
    }


    /**
     * Constructs an instance of <code>InvalidInputException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidInputException(String msg) {
        super(msg);
    }

    private static final String MSG = "Error occured, please try again!";
}
