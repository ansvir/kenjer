package com.kenjerdb.core.dao;

import com.kenjerdb.core.exception.TableCreationException;

import java.io.File;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.EXT;
import static com.kenjerdb.core.constant.KenjerDatabaseConstant.TABLES_PATH;
import static com.kenjerdb.core.dao.model.DatabaseFieldsType.INDEX;

class KenjerDatabaseTable extends KenjerDatabase {

    private File table;

    private KenjerDatabaseTable(String name) {
        table = new File(
                getRootDirectory() +
                        "/" + TABLES_PATH.name() + "/" +
                        name + EXT.name()
        );
    }

    public class Builder {

        private String name;

        public Builder() {}

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public KenjerDatabaseTable build() throws TableCreationException {
            if (name == null) {
                    name = "table" + new KenjerFileReaderService().recordByIndex(
                            KenjerDatabaseTable.this.getDatabase(),
                            KenjerDatabaseTable.this.getDelimiter(),
                            INDEX.ordinal());
            }

            return new KenjerDatabaseTable(name);
        }
    }

    protected File getTable() {
        return this.table;
    }
}
