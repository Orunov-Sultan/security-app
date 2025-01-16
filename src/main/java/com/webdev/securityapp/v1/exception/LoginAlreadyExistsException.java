package com.webdev.securityapp.v1.exception;

public class LoginAlreadyExistsException extends RuntimeException {
    public LoginAlreadyExistsException(String message) {
        super(message);
    }

    public LoginAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAlreadyExistsException(Throwable cause) {
        super(cause);
    }
}
