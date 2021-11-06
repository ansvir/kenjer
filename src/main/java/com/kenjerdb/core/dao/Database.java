package com.kenjerdb.core.dao;

import java.util.List;

class Database {
    private List<Table> tables;
    private PersistencePeriodType persistencePeriod;
    private int index;
    private String delimiter;
    private String placeholderStart;
    private String placeholderEnd;
}
