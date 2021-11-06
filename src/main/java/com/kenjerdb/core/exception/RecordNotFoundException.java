package com.kenjerdb.core.exception;

public class RecordNotFoundException extends Throwable {
    public RecordNotFoundException() {
        super("Record wasn't found in the file");
    }
}
