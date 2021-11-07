package com.kenjerdb.core.dao.model;

import java.util.List;

class Database {
    private List<Table> tables;
    private PersistencePeriodType persistencePeriod;
    private int index;
    private String placeholderStart;
    private String placeholderEnd;
}
