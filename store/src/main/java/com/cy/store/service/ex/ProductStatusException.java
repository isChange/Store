package com.cy.store.service.ex;

public class ProductStatusException extends ServiceException{
    public ProductStatusException() {
        super();
    }

    public ProductStatusException(String message) {
        super(message);
    }

    public ProductStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductStatusException(Throwable cause) {
        super(cause);
    }

    protected ProductStatusException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
