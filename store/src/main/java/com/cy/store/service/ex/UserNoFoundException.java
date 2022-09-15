package com.cy.store.service.ex;

public class UserNoFoundException extends ServiceException{
    public UserNoFoundException() {
        super();
    }

    public UserNoFoundException(String message) {
        super(message);
    }

    public UserNoFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNoFoundException(Throwable cause) {
        super(cause);
    }

    protected UserNoFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
