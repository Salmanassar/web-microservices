package com.appsdeveloper.app.ws.mobileappws.ui.exceptions;

public class UserServiceException extends RuntimeException {
    private String message;
    private static final long serialVersionUID = 1385739264573725819L;

    public UserServiceException(String message) {
        super(message);
    }
}
