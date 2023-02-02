package io.barblin.functional.math;

import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

public class Collatz {

    private static final HashMap<Long, Long> SEQUENCE_CACHE = new HashMap<>();
    private static final Predicate<Long> isValid = n -> Objects.isNull(n) || n < 1;

    private static final Function<Long, Long> generate = n -> {
        if (n % 2 == 0) {
            return n / 2;
        }

        return (3 * n) + 1;
    };

    public static final Function<Long, Optional<Long>> next = n -> {
        if (isValid.test(n)) {
            return Optional.empty();
        }

        if (n == 1L) {
            return Optional.of(n);
        }

        return Optional.of(generate.apply(n));
    };

    public static final Function<Long, Optional<Long>> getSequenceLengthFor = key -> {
        if (isValid.test(key)) {
            return Optional.empty();
        }

        long length = 1;
        long current = key;

        while (1 < current) {
            current = generate.apply(current);

            if (SEQUENCE_CACHE.containsKey(current)) {
                SEQUENCE_CACHE.put(key, SEQUENCE_CACHE.get(current) + length);
                return Optional.of(SEQUENCE_CACHE.get(key));
            }

            length++;
        }

        SEQUENCE_CACHE.put(key, length);
        return Optional.of(length);
    };
}
