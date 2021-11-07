package com.kenjerdb.core.dao;

import com.kenjerdb.core.exception.FileCorruptedException;
import com.kenjerdb.core.exception.IndexIncorrectException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.CIPHER_SIGN;
import static com.kenjerdb.core.dao.model.DatabaseFieldsType.DELIMITER;
import static com.kenjerdb.core.util.ArrayOperation.shiftLeft;
import static java.nio.charset.StandardCharsets.UTF_8;

public class KenjerFileWriterService {

    private final File UPDATABLE;
    private final KenjerDatabase DATABASE;

    public KenjerFileWriterService(File updatable, KenjerDatabase database) {
        this.UPDATABLE = updatable;
        this.DATABASE = database;
    }

    public boolean updateByIndex(int index, String updatable) {
        StringBuilder foundString;
        try (FileReader reader = new FileReader(UPDATABLE)) {
            int indexPosition = 0;
            int code;
            String recordDelimiter = DATABASE.getDelimiter();
            int delimiterLength = recordDelimiter.length();
            char symbol;
            int indexCounter = 1;
            if (UPDATABLE.length() < delimiterLength) {
                throw new FileCorruptedException();
            }

            char[] delimiterCharSeq = new char[delimiterLength];

            for (int i = 0; i < delimiterLength; i++) {
                delimiterCharSeq[i] = recordDelimiter.charAt(i);
            }

            do {
                code = reader.read();
                symbol = (char) code;
                indexPosition++;

                shiftLeft(delimiterCharSeq, symbol);
                StringBuilder delimiterStringStart = new StringBuilder();
                for (char c : delimiterCharSeq) {
                    delimiterStringStart.append(c);
                }
                System.out.println(delimiterStringStart);
                System.out.println(recordDelimiter);;
                if (delimiterStringStart.toString().equals(recordDelimiter)) {
                    System.out.println("ENTER2");
                    if (indexCounter++ == index) {
                        foundString = new StringBuilder();
                        do {
                            System.out.println("ENTER3");
                            code = reader.read();
                            symbol = (char) code;
                            foundString.append(symbol);
                            shiftLeft(delimiterCharSeq, symbol);
                            StringBuilder delimiterStringEnd = new StringBuilder();
                            for (char c : delimiterCharSeq) {
                                delimiterStringEnd.append(c);
                            }
                            System.out.println(foundString);
                            if (delimiterStringEnd.toString().equals(recordDelimiter)) {
                                System.out.println("ENTER4");
                                try(RandomAccessFile raf = new RandomAccessFile(UPDATABLE, "rw");
                                    FileChannel fileChannel = raf.getChannel()
                                ) {
                                    foundString = new StringBuilder(
                                            foundString.subSequence(0, foundString.length() - delimiterLength).toString()
                                    );
                                    fileChannel.position(indexPosition).truncate(foundString.length());
                                    fileChannel.write(ByteBuffer.wrap(updatable.getBytes(UTF_8)), indexPosition + 1);
                                    fileChannel.force(true);
                                    return true;
                                }
                            }
                        } while (code != -1);
                    }
                }
            } while (code != -1);
            throw new IndexIncorrectException("Cannot find record by specified index");
        } catch (IOException | FileCorruptedException | IndexIncorrectException e) {
            e.printStackTrace();
            return false;
        }
    }
}
