package com.kenjdb.core.dao;

import com.kenjerdb.core.dao.KenjerDatabase;
import com.kenjerdb.core.dao.KenjerFileWriterService;
import com.kenjerdb.core.exception.DatabaseCreationException;
import com.kenjerdb.core.exception.ValidationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.DEFAULT_RECORD_DELIMITER;
import static org.junit.jupiter.api.Assertions.fail;

public class FileWriterServiceTest {

    private static final String EXAMPLE_DB_PATH = System.getProperty("user.dir") +
            "/src/test/resources/kenjerdb/example/";

    @Test
    public void testUpdateByIndex() {
        System.out.println(new KenjerFileWriterService().updateByIndex(
                new File(EXAMPLE_DB_PATH + "tables/t_example2.txt").getAbsoluteFile(),
                "I WAS INSERTED",
                DEFAULT_RECORD_DELIMITER.value(),
                3
        ));
    }
}
