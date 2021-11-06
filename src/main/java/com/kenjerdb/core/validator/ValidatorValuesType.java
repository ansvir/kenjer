package com.kenjerdb.core.validator;

public enum ValidatorValuesType {

    FILE_NAME_REGEX("^[\\w,\\s-]+"),
    DIRECTORY_NAME_REGEX("^(\\w+\\.?)*\\w+$");

    private String value;

    public String getValue() {
        return this.value;
    }

    ValidatorValuesType(String value) {
        this.value = value;
    }
}
