package com.kenjerdb.core.util;

public class Parser {

    public static Long parseIntToLong(int integer) {
        return Long.parseLong(String.valueOf(Integer.valueOf(integer)));
    }
}
