package com.kenjerdb.core.exception;

public class TableNotExistException extends Throwable {
    public TableNotExistException(String table) {
        super("Queried table '" + table + "' doesn't exist");
    }
}
