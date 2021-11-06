package com.kenjerdb.core.dao;

import com.kenjerdb.core.parser.reader.FileReaderService;
import com.kenjerdb.core.parser.writer.DatabaseFileRecordWriter;

import java.io.File;

import static com.kenjerdb.core.constant.KenjerConstant.EXT;
import static com.kenjerdb.core.constant.KenjerConstant.TABLES_PATH;
import static com.kenjerdb.core.parser.DatabaseFieldsType.INDEX;

public class KenjerTable {

    private KenjerDatabase database;

    private File table;

    public KenjerTable(KenjerDatabase database, String name) {
        DatabaseFileRecordWriter fileRecordWriter = new DatabaseFileRecordWriter(database.getDatabase());
//        fileRecordWriter.write(name);
        table = new File(
                database.getRootDirectory() +
                        "/" + TABLES_PATH.name() + "/" +
                        name + EXT.name()
        );
    }

    public static class Configurer {

        private KenjerDatabase database;
        private String name;

        public Configurer() {}

        public Configurer database(KenjerDatabase database) {
            this.database = database;
            return this;
        }

        public Configurer name(String name) {
            this.name = name;
            return this;
        }

        public KenjerTable configure() {
            if (name == null) {
                name = "table" + new FileReaderService(database.getDatabase()).recordByIndex(INDEX.ordinal());
            }

            if (database == null) {
                database = KenjerDatabase.getDefault();
            }

            return new KenjerTable(
                    database,
                    name
            );
        }
    }
}
