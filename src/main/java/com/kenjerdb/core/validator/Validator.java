package com.kenjerdb.core.validator;

public interface Validator<Subject> {
    boolean isValid(Subject s);
}
