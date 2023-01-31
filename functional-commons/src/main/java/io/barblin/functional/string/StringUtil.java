package io.barblin.functional.string;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class StringUtil {

    private StringUtil() {
    }

    public static final Predicate<String> isEmpty = s -> s != null && 0 < s.length();

    public static final Function<String, Function<String, List<String>>> splitToList = string -> split -> Arrays
            .asList(string.split(split));
}