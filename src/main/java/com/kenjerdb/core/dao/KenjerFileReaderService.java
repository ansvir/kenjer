package com.kenjerdb.core.dao;

import com.kenjerdb.core.exception.FileCorruptedException;
import com.kenjerdb.core.exception.IndexIncorrectException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import static com.kenjerdb.core.util.ArrayOperation.shiftLeft;

public class KenjerFileReaderService implements FileDatabaseReader {

    public KenjerFileReaderService() {
    }

    /**
     * Get String limited by two delimiters from the start
     * and from the end on the certain position (index) in the file
     *
     * @param readable  file to be read
     * @param index     position of the record in the file
     * @param delimiter file records delimiter
     * @return String concluded in two record delimiters
     */
    public String recordByIndex(File readable, String delimiter, Long index) {

        StringBuilder result;

        try (FileReader reader = new FileReader(readable.getAbsoluteFile())) {
            int code;
            int delimiterLength = delimiter.length();
            char symbol;

            if (readable.length() < delimiterLength) {
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
                    StringBuilder rowIndexString = new StringBuilder();
                    char symbolAfterDelimiter;
                    code = reader.read();
                    do {
                        symbolAfterDelimiter = (char) code;
                        rowIndexString.append(symbolAfterDelimiter);
                        code = reader.read();
                        shiftLeft(delimiterCharSeq, symbol);
                    } while (code != -1 && code != 32);
                    Long rowIndex = Long.parseLong(rowIndexString.toString());
                    if (rowIndex.equals(index)) {
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
     *
     * @return read content
     */
    public String readAll(File readable) {
        StringBuilder result;
        try (FileReader reader = new FileReader(readable)) {
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
