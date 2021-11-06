package com.kenjerdb.core.exception;

public class FileCorruptedException extends Throwable {
    public FileCorruptedException() {
        super("File was corrupted");
    }
}
