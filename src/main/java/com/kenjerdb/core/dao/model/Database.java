package com.kenjerdb.core.dao.model;

import java.util.List;

public class Database {
    private List<DatabaseRow> rows;

    public Database(List<DatabaseRow> rows) {
        this.rows = rows;
    }

    public List<DatabaseRow> getRows() {
        return rows;
    }

    public void setRows(List<DatabaseRow> rows) {
        this.rows = rows;
    }
}
