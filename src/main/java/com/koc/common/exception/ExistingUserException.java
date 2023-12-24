package com.koc.common.exception;

public class ExistingUserException extends ValidationException {

    public ExistingUserException() {
        super("already exist user");
    }
}
