package com.kenjerdb.core.exception;

public class TableCreationException extends Throwable {
    public TableCreationException(String msg) {
        super("table wasn't created according to: " + msg + ". Aborting creating table!");
    }
}
