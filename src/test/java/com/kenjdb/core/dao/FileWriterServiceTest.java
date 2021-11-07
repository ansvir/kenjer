package com.kenjdb.core.dao;

import com.kenjerdb.core.dao.KenjerDatabase;
import com.kenjerdb.core.dao.KenjerFileWriterService;
import com.kenjerdb.core.exception.DatabaseCreationException;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.fail;

public class FileWriterServiceTest {

    @Test
    public void testUpdateByIndex() {
        KenjerDatabase database = null;
        try {
            database = KenjerDatabase.getDefaultDatabase();
        } catch (DatabaseCreationException e) {
            e.printStackTrace();
            fail();
        }
        System.out.println(new KenjerFileWriterService(
                new File("src/test/resources/kenjerdb/tables/example2.txt").getAbsoluteFile(),
                database
        ).updateByIndex(1, "INSERT ME"));
    }
}
