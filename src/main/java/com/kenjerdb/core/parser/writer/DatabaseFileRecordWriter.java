package com.kenjerdb.core.parser.writer;

import com.kenjerdb.core.parser.DatabaseFieldsType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.kenjerdb.core.constant.KenjerConstant.DEFAULT_RECORD_DELIMITER;

public class DatabaseFileRecordWriter {

    private File writeable;

    public DatabaseFileRecordWriter(File writeable) {
        this.writeable = writeable;
    }

    public boolean write(DatabaseFieldsType field, String record) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(record);
        stringBuilder.append(DEFAULT_RECORD_DELIMITER.name());
        try(FileWriter fileWriter = new FileWriter(writeable)) {
            fileWriter.append(stringBuilder.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
