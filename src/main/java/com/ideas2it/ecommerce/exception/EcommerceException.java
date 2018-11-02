package com.ideas2it.ecommerce.exception;

/**
 * <p>
 * The {@code EcommerceException} is an exception class that provides
 * information regarding errors related to e-commerce Store.
 * </p>
 */
public class EcommerceException extends Exception {

    public EcommerceException(String message) {
        super(message);
    }

    public EcommerceException(Throwable cause) {
        super(cause);
    }

    public EcommerceException(String message, Throwable cause) {
        super(message, cause);
    }

}