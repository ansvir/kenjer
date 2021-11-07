package com.kenjerdb.core.dao;

import com.kenjerdb.core.exception.FileCorruptedException;
import com.kenjerdb.core.exception.IndexIncorrectException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.kenjerdb.core.dao.model.DatabaseFieldsType.DELIMITER;
import static com.kenjerdb.core.util.ArrayOperation.shiftLeft;

public class KenjerFileReaderService implements FileDatabaseReader {

    private final File READABLE;
    private final KenjerDatabase DATABASE;

    public KenjerFileReaderService(File readable, KenjerDatabase database) {
        this.READABLE = readable;
        this.DATABASE = database;
    }

    /**
     * Get String limited by two delimiters from the start
     * and from the end on the certain position (index) in the file
     * @param index position of the record in the file
     * @return String concluded in two record delimiters
     */
    public synchronized String recordByIndex(int index) {
        StringBuilder result;
        try (FileReader reader = new FileReader(READABLE)) {

            int code;

            String delimiter = DATABASE.getDelimiter();
            int delimiterLength = delimiter.length();
            char symbol;
            int indexCounter = 1;

            if (DATABASE.getDelimiter().length() < delimiterLength) {
                throw new FileCorruptedException();
            }

            char[] delimiterCharSeq = new char[delimiterLength];

            do {
                code = reader.read();
                symbol = (char) code;

                shiftLeft(delimiterCharSeq, symbol);
                StringBuilder stringBuilder = new StringBuilder();
                for (char c : delimiterCharSeq) {
                    stringBuilder.append(c);
                }

                if (stringBuilder.toString().equals(delimiter)) {
                    if (indexCounter++ == index) {
                        result = new StringBuilder();
                        do {
                            code = reader.read();
                            symbol = (char) code;
                            shiftLeft(delimiterCharSeq, symbol);
                            StringBuilder stringBuilderInner = new StringBuilder();
                            for (char c : delimiterCharSeq) {
                                stringBuilderInner.append(c);
                            }
                            result.append(symbol);
                            if (stringBuilderInner.toString().equals(delimiter)) {
                                return result.subSequence(0, result.length() - delimiterLength).toString();
                            }
                        } while (code != -1);
                    }
                }
            } while (code != -1);
        throw new IndexIncorrectException("Cannot find record by specified index");
        } catch (IOException | FileCorruptedException | IndexIncorrectException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Read all content of a file
     * @return read content
     */
    public String readAll() {
        StringBuilder result;
        try(FileReader reader = new FileReader(READABLE)) {
            result = new StringBuilder();
            int code = -1;
            do {
                code = reader.read();
                char symbol = (char) code;
                result.append(symbol);
            } while (code != -1);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result.substring(0, result.length() - 1);
    }
}
