package com.kenjerdb.core.exception;

public class IndexIncorrectException extends Throwable {
    public IndexIncorrectException(String message) {
        super("Index does not exist or incorrect: " + message);
    }
}
