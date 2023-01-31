package io.barblin.functional.list;

import io.barblin.functional.math.MathUtil;
import io.barblin.functional.string.StringUtil;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class List {

    private List() {
    }

    public static final Predicate<java.util.List<? extends Object>> isEmpty = l -> l == null || l.isEmpty();

    public static Stream<Integer> toIntegerList(java.util.List<String> stringList) {
        if (isEmpty.test(stringList)) {
            return Stream.of();
        }

        return stringList.stream()
                .filter(Objects::nonNull)
                .map(MathUtil.toInt)
                .flatMap(Optional::stream);
    }

    public static Stream<java.util.List<String>> toListOfStrings(java.util.List<String> stringList, String split) {
        if (isEmpty.test(stringList)) {
            return Stream.of();
        }

        return stringList.stream()
                .filter(StringUtil.isEmpty)
                .map(s -> StringUtil.splitToList.apply(s).apply(split));
    }
}
