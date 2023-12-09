package com.koc.common.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String objectName) {
        super("Not found " + objectName);
    }
}
