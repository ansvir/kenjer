package com.kenjerdb.core.dao;

import com.kenjerdb.core.dao.model.PersistencePeriodType;

import java.io.File;

public interface FileDatabase {

    String getName();

    File getDatabase();

    String getRootDirectory();

    PersistencePeriodType getPeriod();

    String getDelimiter();

    String getPlaceholderStart();

    String getPlaceholderEnd();

}
