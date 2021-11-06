package com.kenjerdb.core.validator;

import com.kenjerdb.core.dao.KenjerDatabase;

public class StringValidator implements Validator<String>{

    private final String REGEX;

    public StringValidator(String regex) {
        this.REGEX = regex;
    }

    @Override
    public boolean isValid(String s) {
        return s != null && s.matches(REGEX);
    }
}
