package com.kenjerdb.core.constant;

public enum KenjerConstant {

    PROJECT_NAME("kenjdb"),
    TABLES_PATH("tables"),
    EXT(".txt"),
    DEFAULT_RECORD_DELIMITER("[-||-]"),
    DEFAULT_PLACEHOLDER_DELIMITER_OPEN("<{"),
    DEFAULT_PLACEHOLDER_DELIMITER_CLOSE("}>");

    private String value;

    KenjerConstant(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }
}
