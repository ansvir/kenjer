package com.kenjerdb.core.dao;

public interface FileDatabaseReader {
    String recordByIndex(int index);

    String readAll();
}

