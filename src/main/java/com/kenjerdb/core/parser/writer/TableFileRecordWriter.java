package com.kenjerdb.core.parser.writer;

import com.kenjerdb.core.parser.TableColumnsType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.kenjerdb.core.constant.KenjerConstant.DEFAULT_RECORD_DELIMITER;

public class TableFileRecordWriter {
    private File writeable;

    public TableFileRecordWriter(File writeable) {
        this.writeable = writeable;
    }

    public boolean write(TableColumnsType column, String record) {
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
