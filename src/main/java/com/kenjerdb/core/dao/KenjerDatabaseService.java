package com.kenjerdb.core.dao;

import com.kenjerdb.core.dao.model.DatabaseFieldsType;
import com.kenjerdb.core.dao.model.PersistencePeriodType;
import com.kenjerdb.core.exception.TableNotExistException;

import java.util.List;

import static com.kenjerdb.core.dao.model.DatabaseFieldsType.*;

public class KenjerDatabaseService {

    private final KenjerDatabase DATABASE;

    public KenjerDatabaseService(KenjerDatabase database) {
        this.DATABASE = database;
    }

    public List<String> readTables() {
        return List.of(readRecord(TABLES.ordinal()).split(","));
    }

    public boolean isTableExist(String name) {
        try {
            readTables()
                    .stream()
                    .filter(it -> it.equals(name))
                    .findAny()
                    .orElseThrow(() -> new TableNotExistException(name));
        } catch (TableNotExistException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public PersistencePeriodType readPersistencePeriod() {
        return PersistencePeriodType.valueOf(
                        readRecord(PERSISTENCE_PERIOD.ordinal())
        );
    }

    public int readIndex() {
        return Integer.parseInt(
                readRecord(INDEX.ordinal())
        );
    }

    public String readPlaceholderStart() {
        return readRecord(PLACEHOLDER_OPEN.ordinal());
    }

    public String readPlaceholderEnd() {
        return readRecord(PLACEHOLDER_CLOSE.ordinal());
    }

    private String readRecord(int index) {
        return new KenjerFileReaderService().recordByIndex(DATABASE.getDatabase(), DATABASE.getDelimiter(), index);
    }

    public boolean write(DatabaseFieldsType field, String record) {
        return new KenjerFileWriterService().updateByIndex(DATABASE.getDatabase(), record, DATABASE.getDelimiter(), field.ordinal());
    }
}
