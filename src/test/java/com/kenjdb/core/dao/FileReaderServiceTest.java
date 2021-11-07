package com.kenjdb.core.dao;

import com.kenjerdb.core.dao.KenjerDatabase;
import com.kenjerdb.core.dao.KenjerFileReaderService;
import com.kenjerdb.core.exception.DatabaseCreationException;
import com.kenjerdb.core.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class FileReaderServiceTest {

    private KenjerDatabase database;

    private static final String RESOURCES_ROOT_DIR = "/src/test/resources/";

    @BeforeEach
    public void setUp() {
        database = null;
        try {
            database = KenjerDatabase.getDefaultConfig()
                    .name("example")
                    .directory(System.getProperty("user.dir") + RESOURCES_ROOT_DIR)
                    .build();
        } catch (DatabaseCreationException | ValidationException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testRecordByIndex() {
        System.out.println(
                new KenjerFileReaderService().recordByIndex(
                        new File(database.getRootDirectory() + "/tables/t_example.txt").getAbsoluteFile(),
                        database.getDelimiter(),
                        4
                )
        );
    }

    @Test
    public void testReadAll() {
        System.out.println(
                new KenjerFileReaderService().readAll(
                        new File(database.getRootDirectory() + "/tables/t_example.txt").getAbsoluteFile()
                )
        );
    }
}
