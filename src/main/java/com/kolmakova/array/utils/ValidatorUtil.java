package com.kolmakova.array.utils;

import java.util.Arrays;


public class ValidatorUtil {
    private static final String LINE_CONTAINS_DIGITS_ONLY = "-?\\d+";

    public static boolean isInvalid(String line) {
        return !isValid(line);
    }

    public static boolean isValid(String line) {
        String[] splitElements = line.split(" ");

        return Arrays.stream(splitElements)
                .allMatch(string -> string.matches(LINE_CONTAINS_DIGITS_ONLY));
    }
}
