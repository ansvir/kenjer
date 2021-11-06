package com.kenjerdb.core.util;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PlaceholderParser {


    public static List<String> allContents(String string, String placeholderStart, String placeholderEnd) {
        placeholderStart = prepareRegex(placeholderStart);
        placeholderEnd = prepareRegex(placeholderEnd);
        String regex = placeholderStart + "([^" + placeholderStart + placeholderEnd + "]*)" + placeholderEnd;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        List<String> result = new LinkedList<>();
        while(m.find()) {
           result.add(m.group(1));
        }
        return result;
    }

    private static String prepareRegex(String string) {
        return string
                .replace("\\", "\\\\]")
                .replace("*", "\\*")
                .replace("?", "\\?")
                .replace(".", "\\.")
                .replace("{", "\\{")
                .replace("[", "\\[");
    }
}
