package com.kenjerdb.core.validator;

import static com.kenjerdb.core.constant.KenjerDatabaseConstant.CIPHER_SIGN;

public enum ValidatorValuesType {

    FILE_NAME_REGEX("^[\\w,\\s-]+"),
    DIRECTORY_NAME_REGEX("^(\\w+\\.?)*\\w+$"),
    DELIMITER_REGEX("^[^" + CIPHER_SIGN.value() + "].*[^" + CIPHER_SIGN.value() + "]$"),
    PLACEHOLDER_START_REGEX("^[^" + CIPHER_SIGN.value() + ")].*$"),
    PLACEHOLDER_END_REGEX("^.*[^" + CIPHER_SIGN.value() + "]$");

    private String value;

    public String value() {
        return this.value;
    }

    ValidatorValuesType(String value) {
        this.value = value;
    }
}
