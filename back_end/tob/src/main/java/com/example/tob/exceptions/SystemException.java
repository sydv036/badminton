package com.example.tob.exceptions;

public class SystemException extends Exception {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SystemException(Throwable throwable) {
        super(throwable);
    }
}
