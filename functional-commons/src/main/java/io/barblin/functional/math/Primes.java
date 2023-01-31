package io.barblin.functional.math;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;

public class Primes {

    public static final Function<Integer, Boolean> isPrime = n -> {
        if (Objects.isNull(n) || n < 2) {
            return false;
        }

        int sqrt = (int) Math.sqrt(n);
        for (int i = 2; i <= sqrt; i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    };

    public static final Function<Long, Optional<LinkedList<Integer>>> listOfPrimesBelow = n -> {
        if (Objects.isNull(n) || n < 2) {
            return Optional.empty();
        }

        LinkedList<Integer> result = new LinkedList<>();
        for (int i = 2; i < n; i++) {
            if (isPrime.apply(i)) {
                result.add(i);
            }
        }
        return Optional.of(result);
    };

    private Primes() {

    }
}