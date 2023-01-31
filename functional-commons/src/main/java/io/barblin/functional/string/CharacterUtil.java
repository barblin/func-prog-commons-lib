package io.barblin.functional.string;

import java.util.function.IntUnaryOperator;

public class CharacterUtil {

    public static IntUnaryOperator charToDigit = character -> {
        if (Character.isDigit(character)) {
            return character - '0';
        }

        return -1;
    };
}