package com.kenjerdb.core.parser.reader;

import com.kenjerdb.core.exception.FileCorruptedException;
import com.kenjerdb.core.exception.IndexIncorrectException;
import com.kenjerdb.core.parser.DatabaseFieldsType;
import com.sun.jndi.ldap.spi.LdapDnsProviderResult;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

import static com.kenjerdb.core.constant.KenjerConstant.DEFAULT_RECORD_DELIMITER;
import static com.kenjerdb.core.parser.DatabaseFieldsType.DELIMITER;
import static com.kenjerdb.core.util.ArrayOperation.shiftLeft;

public class FileReaderService {

    private final File SCANNABLE;

    public FileReaderService(File scannable) {
        this.SCANNABLE = scannable;
    }

    /**
     * Get String limited by two delimiters from the start
     * and from the end on the certain position (index) in the file
     * @param index position of the record in the file
     * @return String concluded in two record delimiters
     */
    public synchronized String recordByIndex(int index) {
        StringBuilder result;
        try (FileReader reader = new FileReader(SCANNABLE)) {

            int code;
            String recordDelimiter = recordByIndex(DELIMITER.ordinal());

            int delimiterLength = recordDelimiter.length();
            char symbol;
            int indexCounter = 1;

            if (SCANNABLE.length() < delimiterLength) {
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

                if (stringBuilder.toString().equals(recordDelimiter)) {
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
                            if (stringBuilderInner.toString().equals(recordDelimiter)) {
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

//    public int findRecordIndex() {
//
//    }
}
