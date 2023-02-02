package io.barblin.functional.math;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CollatzTest {

    @Test
    void nextShouldReturnNextCollatzNumber() {
        assertEquals(4, Collatz.next.apply(8L).get());
        assertEquals(1, Collatz.next.apply(1L).get());
        assertEquals(3640L, Collatz.next.apply(1213L).get());
    }

    @Test
    void nextShouldReturnEmptyOnInvalidInput() {
        assertTrue(Collatz.next.apply(0L).isEmpty());
        assertTrue(Collatz.next.apply(null).isEmpty());
        assertTrue(Collatz.next.apply(-1L).isEmpty());
    }

    @Test
    void getSequenceLengthForShouldReturnValidSequenceLength() {
        assertEquals(47L, Collatz.getSequenceLengthFor.apply(123L).get());
        assertEquals(35L, Collatz.getSequenceLengthFor.apply(236L).get());
        assertEquals(45L, Collatz.getSequenceLengthFor.apply(1213L).get());
    }

    @Test
    void getSequenceLengthForShouldReturnEmptyOnInvalidInput() {
        assertTrue(Collatz.getSequenceLengthFor.apply(0L).isEmpty());
        assertTrue(Collatz.getSequenceLengthFor.apply(null).isEmpty());
        assertTrue(Collatz.getSequenceLengthFor.apply(-1L).isEmpty());
    }
}