package com.koc.common.exception;

public class ValidationException extends RuntimeException {

    public ValidationException(String field) {
        super("error field : " + field);
    }
}
