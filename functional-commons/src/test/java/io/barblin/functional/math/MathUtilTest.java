package io.barblin.functional.math;

import static org.junit.jupiter.api.Assertions.assertEquals;

import io.barblin.functional.math.MathUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class MathUtilTest {
    @Test
    void logThenSqrtShouldReturnOne() {
        Assertions.assertEquals(1.0, MathUtil.log10ThenSqrt.apply(10.0));
    }

    @Test
    void logShouldReturn1() {
        assertEquals(2.302585092994046, MathUtil.log.apply(10.0));
    }

    @Test
    void sqrtShouldReturn1() {
        assertEquals(3.0, MathUtil.sqrt.apply(9.0));
    }
}
