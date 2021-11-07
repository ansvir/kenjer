package com.kenjdb.core.dao;

import com.kenjerdb.core.dao.KenjerDatabase;
import com.kenjerdb.core.dao.KenjerFileReaderService;
import com.kenjerdb.core.exception.DatabaseCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class FileReaderServiceTest {

    private KenjerDatabase database;

    @BeforeEach
    public void setUp() {
        database = null;
        try {
            database = KenjerDatabase.getDefaultDatabase();
        } catch (DatabaseCreationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testRecordByIndex() {
        System.out.println(new KenjerFileReaderService(
                new File("src/test/resources/kenjerdb/tables/example.txt").getAbsoluteFile(),
                database
        ).recordByIndex(4));
    }

    @Test
    public void testReadAll() {
        System.out.println(
                new KenjerFileReaderService(
                        new File("src/test/resources/kenjerdb/config.txt").getAbsoluteFile(),
                        database
                ).readAll()
        );
    }
}
