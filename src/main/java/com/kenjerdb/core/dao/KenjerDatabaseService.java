package com.kenjerdb.core.dao;

import com.kenjerdb.core.dao.model.DatabaseFieldsType;
import com.kenjerdb.core.dao.model.DatabaseRow;
import com.kenjerdb.core.dao.model.PersistencePeriodType;
import com.kenjerdb.core.util.Parser;

import static com.kenjerdb.core.dao.model.DatabaseFieldsType.*;
import static com.kenjerdb.core.util.Parser.parseIntToLong;

public class KenjerDatabaseService {

    private final KenjerDatabase DATABASE;

    public KenjerDatabaseService(KenjerDatabase database) {
        this.DATABASE = database;
    }

    public PersistencePeriodType readPersistencePeriod() {
        return PersistencePeriodType.valueOf(
                        readRecord(parseIntToLong(PERSISTENCE_PERIOD.ordinal())).getContent()
        );
    }

    public int readIndex() {
        return Integer.parseInt(
                readRecord(parseIntToLong(INDEX.ordinal())).getContent()
        );
    }

    public String readPlaceholderStart() {
        return readRecord(parseIntToLong(PLACEHOLDER_OPEN.ordinal())).getContent();
    }

    public String readPlaceholderEnd() {
        return readRecord(parseIntToLong(PLACEHOLDER_CLOSE.ordinal())).getContent();
    }

    private DatabaseRow readRecord(Long index) {
        return new DatabaseRow(
                index,
                new KenjerFileReaderService().recordByIndex(DATABASE.getDatabase(), DATABASE.getDelimiter(), index)
        );
    }

    public boolean write(DatabaseFieldsType field, String record) {
        return new KenjerFileWriterService().updateByIndex(DATABASE.getDatabase(), record, DATABASE.getDelimiter(), field.ordinal());
    }
}
