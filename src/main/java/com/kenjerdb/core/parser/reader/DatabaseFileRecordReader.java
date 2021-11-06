package com.kenjerdb.core.parser.reader;

import com.kenjerdb.core.parser.DatabaseFieldsType;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DatabaseFileRecordReader {

    private File readable;

    public DatabaseFileRecordReader(File readable) {
        this.readable = readable;
    }

    public String read(DatabaseFieldsType field) {
        try(FileReader reader = new FileReader(readable)) {
            FileReaderService fileReaderService = new FileReaderService(readable);
            return fileReaderService.recordByIndex(field.ordinal());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
