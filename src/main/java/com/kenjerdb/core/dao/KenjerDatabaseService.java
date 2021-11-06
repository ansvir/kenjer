package com.kenjerdb.core.dao;

import com.kenjerdb.core.exception.TableNotExistException;
import com.kenjerdb.core.parser.reader.FileReaderService;

import java.io.File;
import java.util.List;

import static com.kenjerdb.core.parser.DatabaseFieldsType.*;

public class KenjerDatabaseService {

    private File database;

    public KenjerDatabaseService(File database) {
        this.database = database;
    }

    public List<String> readTables() {
        return List.of(new FileReaderService(database).recordByIndex(TABLES.ordinal()).split(","));
    }

    public String readTableByName(String name) {
        try {
            return readTables()
                    .stream()
                    .filter(it -> it.equals(name))
                    .findFirst()
                    .orElseThrow(TableNotExistException::new);
        } catch (TableNotExistException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PersistencePeriodType readPersistencePeriod() {
        return PersistencePeriodType
                .valueOf(new FileReaderService(database).recordByIndex(PERSISTENCE_PERIOD.ordinal()));
    }

    public int readIndex() {
        return Integer.parseInt(
                new FileReaderService(database).recordByIndex(INDEX.ordinal())
        );
    }

    public String readDelimiter() {
        return new FileReaderService(database).recordByIndex(DELIMITER.ordinal());
    }
}
