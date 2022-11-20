package com.raymundo.lab2;

/**
 * The exception that is thrown when an invalid expression is specified.
 * Used in {@link ExpressionResolver} class
 */
public class InvalidExpressionException extends Exception {

    /**
     * A constructor that involves a constructor from superclass {@link Exception}
     *
     * @param message Message to print
     */
    public InvalidExpressionException(String message) {
        super(message);
    }
}
