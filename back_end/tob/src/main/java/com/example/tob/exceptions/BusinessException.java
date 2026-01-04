package com.example.tob.exceptions;

import java.io.Serial;

public class BusinessException extends RuntimeException {
    @Serial
    static final long serialVersionUID = 1123142324234L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

}
