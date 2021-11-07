package com.kenjerdb.core.dao.model;

public class DatabaseRow {
    private Long rowId;
    private String content;

    public DatabaseRow(Long rowId, String content) {
        this.rowId = rowId;
        this.content = content;
    }

    public Long getRowId() {
        return rowId;
    }

    public void setRowId(Long rowId) {
        this.rowId = rowId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
