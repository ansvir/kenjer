package com.kenjdb.core.parser.reader;

import com.kenjerdb.core.parser.reader.FileReaderService;
import org.junit.jupiter.api.Test;

import java.io.File;

public class FileReaderServiceTest {

    @Test
    public void testRecordByIndex() {
        System.out.println(new FileReaderService(
                new File("src/test/resources/kenjerdb/tables/example.txt").getAbsoluteFile()
        ).recordByIndex(4));
    }
}
