package com.kenjerdb.core.exception;

public class ValidationException extends Throwable {
    public ValidationException(String object) {
        super("Object '" + object + "' is invalid.");
    }
}
