package com.kenjerdb.core.constant;

public enum KenjerDatabaseConstant {

    PROJECT_NAME("kenjerdb"), // and root of db path
    TABLES_PATH("tables"),
    EXT(".txt"),
    CIPHER_SIGN("1"),
    DEFAULT_RECORD_DELIMITER("[-||-]"),
    DEFAULT_PLACEHOLDER_DELIMITER_OPEN("<{"),
    DEFAULT_PLACEHOLDER_DELIMITER_CLOSE("}>");

    private String value;

    KenjerDatabaseConstant(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
