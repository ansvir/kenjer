package com.kenjdb.core.dao;

import com.kenjerdb.core.dao.KenjerDatabase;
import com.kenjerdb.core.exception.DatabaseCreationException;
import com.kenjerdb.core.exception.ValidationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class KenjerDatabaseTest {

    @Test
    public void testCreateDatabase() {
        KenjerDatabase database = null;
        try {
            database = KenjerDatabase
                    .getDefaultConfig()
                    .name("company")
                    .directory("/home/anton/Documents/testdb")
                    .build();
        } catch (DatabaseCreationException | ValidationException e) {
            e.printStackTrace();
            fail();
        }
        System.out.println(database);
    }
}
