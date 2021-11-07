package com.kenjerdb.core.exception;

public class DatabaseCreationException extends Throwable {
    public DatabaseCreationException(String msg) {
        super("Database wasn't created according to: " + msg + ". Aborting creating database!");
    }
}
