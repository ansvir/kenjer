package com.kenjerdb.core.exception;

public class TableNotExistException extends Throwable {
    public TableNotExistException() {
        super("Queried table doesn't exist");
    }
}
