package com.h.sqlconnectpool.exception;

public class PoolException extends RuntimeException {
    private String message;

    public PoolException() {
    }

    public PoolException(String message) {
        super();
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
