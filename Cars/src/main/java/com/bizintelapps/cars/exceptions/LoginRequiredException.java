package com.bizintelapps.cars.exceptions;

/**
 *
 * @author intesar
 */
public class LoginRequiredException extends RuntimeException {

    /**
     * Creates a new instance of <code>InvalidInputException</code> without detail message.
     */
    public LoginRequiredException() {
        super(MSG);
    }


    /**
     * Constructs an instance of <code>InvalidInputException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public LoginRequiredException(String msg) {
        super(msg);
    }

    private static final String MSG = "Session expired!, please relogin or reload page";
}
