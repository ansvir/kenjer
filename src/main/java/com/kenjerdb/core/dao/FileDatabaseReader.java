package com.kenjerdb.core.dao;

import java.io.File;

public interface FileDatabaseReader {

    String recordByIndex(File readable, String delimiter, int index);

    String readAll(File readable);
}

