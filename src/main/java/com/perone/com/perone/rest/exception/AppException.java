package com.perone.com.perone.rest.exception;

public class AppException extends Exception {

    private int status;

    public AppException(int status, String message) {
        super(message);
        this.status = status;
    }
}
