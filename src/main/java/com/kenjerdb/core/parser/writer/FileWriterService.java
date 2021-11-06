package com.kenjerdb.core.parser.writer;

import com.kenjerdb.core.exception.FileCorruptedException;
import com.kenjerdb.core.exception.IndexIncorrectException;
import com.kenjerdb.core.parser.reader.FileReaderService;

import javax.print.DocFlavor;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.kenjerdb.core.constant.KenjerConstant.DEFAULT_RECORD_DELIMITER;
import static com.kenjerdb.core.util.ArrayOperation.shiftLeft;
import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.StandardOpenOption.READ;
import static java.nio.file.StandardOpenOption.WRITE;

public class FileWriterService {

    private final File INSERTABLE;

    private final File DATABASE;

    public FileWriterService(File database, File insertable) {
        this.DATABASE = database;
        this.INSERTABLE = insertable;
    }

    /*
    public boolean updateByIndex(int index, String updatable) {
        StringBuilder foundString;

        try (FileReader reader = new FileReader(INSERTABLE)) {
            int indexPosition = 0;
            int code;
            String recordDelimiter = new FileReaderService()
            int delimiterLength = recordDelimiter.length();
            char symbol;
            int indexCounter = 1;
            if (INSERTABLE.length() < delimiterLength) {
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
                StringBuilder stringBuilder = new StringBuilder();
                for (char c : delimiterCharSeq) {
                    stringBuilder.append(c);
                }
                if (stringBuilder.toString().equals(recordDelimiter)) {
                    if (indexCounter++ == index) {
                        foundString = new StringBuilder();
                        do {
                            code = reader.read();
                            symbol = (char) code;
                            foundString.append(symbol);
                            shiftLeft(delimiterCharSeq, symbol);
                            StringBuilder delimiterString = new StringBuilder();
                            for (char c : delimiterCharSeq) {
                                delimiterString.append(c);
                            }
                            if (delimiterString.toString().equals(recordDelimiter)) {
                                System.out.println("ENTER");
                                try(RandomAccessFile raf = new RandomAccessFile(INSERTABLE, "rw");
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
    */
}
