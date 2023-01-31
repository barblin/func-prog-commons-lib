package io.barblin.functional.math;

import java.util.Optional;
import java.util.function.Function;

public class MathUtil {

    private MathUtil() {

    }

    public static final Function<Double, Double> log = val -> Math.log(val);
    public static final Function<Double, Double> log10 = val -> Math.log10(val);
    public static final Function<Double, Double> sqrt = val -> Math.sqrt(val);
    public static final Function<Double, Double> logThenSqrt = sqrt.compose(log);
    public static final Function<Double, Double> log10ThenSqrt = sqrt.compose(log10);
    
    public static final Function<String, Optional<Integer>> toInt = s -> {
        try {
            return Optional.of(Integer.parseInt(s));
        } catch (NumberFormatException ex){/*  */
            return Optional.empty();
        }
    };
}
