package com.kenjerdb.core.dao;

import com.kenjerdb.core.exception.FileCorruptedException;
import com.kenjerdb.core.exception.IndexIncorrectException;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import static com.kenjerdb.core.util.ArrayOperation.shiftLeft;
import static java.nio.charset.StandardCharsets.UTF_8;

public class KenjerFileWriterService {

    public KenjerFileWriterService() {}

    public boolean updateByIndex(File updatable, String replaceable, String delimiter, int index) {
        StringBuilder foundString;
        try (FileReader reader = new FileReader(updatable)) {
            int delimiterLength = delimiter.length();
            if (updatable.length() < delimiter.length()) {
                throw new FileCorruptedException();
            }
            int indexPosition = 0;
            int code;
            char symbol;
            int indexCounter = 0;

            char[] delimiterCharSeq = new char[delimiterLength];

            for (int i = 0; i < delimiterLength; i++) {
                delimiterCharSeq[i] = delimiter.charAt(i);
            }

            do {
                code = reader.read();
                symbol = (char) code;
                indexPosition++;


                StringBuilder delimiterStringStart = new StringBuilder();
                for (char c : delimiterCharSeq) {
                    delimiterStringStart.append(c);
                }
                shiftLeft(delimiterCharSeq, symbol);
                System.out.println(delimiterStringStart);
                System.out.println(delimiter);
                if (delimiterStringStart.toString().equals(delimiter)) {
                    System.out.println("ENTER2");
                    if (indexCounter++ == index) {
                        foundString = new StringBuilder();
                        do {
                            System.out.println("ENTER3");
                            code = reader.read();
                            symbol = (char) code;
                            foundString.append(symbol);
                            StringBuilder delimiterStringEnd = new StringBuilder();
                            for (char c : delimiterCharSeq) {
                                delimiterStringEnd.append(c);
                            }
                            shiftLeft(delimiterCharSeq, symbol);
                            System.out.println(foundString);
                            if (delimiterStringEnd.toString().equals(delimiter)) {
                                System.out.println("ENTER4");
                                try(RandomAccessFile raf = new RandomAccessFile(updatable, "rw");
                                    FileChannel fileChannel = raf.getChannel()
                                ) {
                                    foundString = new StringBuilder(
                                            foundString.subSequence(0, foundString.length() - delimiterLength).toString()
                                    );
                                    fileChannel.position(indexPosition).truncate(foundString.length());
                                    fileChannel.write(ByteBuffer.wrap(replaceable.getBytes(UTF_8)), indexPosition + 1);
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

    public boolean append(File updatable, String insertable) {
        try (FileWriter fileWriter = new FileWriter(updatable)) {
            fileWriter.append(insertable);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean createFile(String path, boolean overwrite) throws IOException {
        try (Writer fileWriter = new FileWriter(path, overwrite)) {}
        return true;
    }

    public boolean createDirectory(String path) {
        return new File(path).mkdirs();
    }
}
