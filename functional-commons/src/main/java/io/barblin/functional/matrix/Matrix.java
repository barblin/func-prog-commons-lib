package io.barblin.functional.matrix;

import io.barblin.functional.list.List;
import io.barblin.functional.string.CharacterUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Matrix {

    private Matrix() {

    }

    public record SquareCharacterMatrix(LinkedList<String> matrix) {
        public SquareCharacterMatrix {
            Objects.requireNonNull(matrix);
            java.util.List<String> afterPurge = matrix.stream()
                    .mapMulti((String s, Consumer<String> consumer) -> {
                        if (s.chars().allMatch(Character::isDigit)) {
                            consumer.accept(s);
                        }
                    }).collect(Collectors.toList());

            int expectedDimension = matrix.size();

            if (afterPurge.isEmpty() || afterPurge.size() != expectedDimension
                    || afterPurge.get(0).length() != expectedDimension) {
                throw new IllegalArgumentException("This is not a square char-digit matrix");
            }
        }
    }

    public static Function<int[][], Function<Integer, Boolean>> isRowWithin = matrix -> row -> 0 <= row && row < matrix.length;
    public static Function<int[][], Function<Integer, Boolean>> isColWithin = matrix -> col -> 0 <= col && col < matrix[0].length;

    public static Function<int[][], Function<Integer, Function<Integer, Boolean>>> within = matrix -> row -> col -> {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }

        return isRowWithin.apply(matrix).apply(row) && isColWithin.apply(matrix).apply(col);
    };

    public static Function<int[][], Function<Integer, Function<Integer, Boolean>>> outside =
            matrix -> row -> col -> !within.apply(matrix).apply(row).apply(col);

    public static <T> java.util.List<ArrayList<T>> createLattice(int rows, int cols) {
        return IntStream.range(0, rows)
                .mapToObj(a -> new ArrayList<T>(cols))
                .collect(Collectors.toList());
    }

    public static Function<int[][], Function<java.util.List<Direction>, Long>> routesFromStartToEnd = matrix ->
            directions -> {
                int row = 0;
                int col = 0;

                long[][] cache = new long[matrix.length][matrix[0].length];
                return probeDirection(row, col, matrix, cache, directions);
            };

    private static long probeDirection(int row, int col, int[][] matrix, long[][] cache,
                                       java.util.List<Direction> directions) {
        if (outside.apply(matrix).apply(row).apply(col)) {
            return 0;
        }

        if (row == matrix.length - 1 && col == matrix.length - 1) {
            return 1;
        }

        if (cache[row][col] != 0) {
            return cache[row][col];
        }

        long ways = 0;
        for (Direction direction : directions) {
            ways += probeDirection(direction.incrRow(row), direction.incrCol(col), matrix, cache, directions);
        }

        cache[row][col] = ways;
        return cache[row][col];
    }

    public static long findPathWithLargestSum(int[][] matrix, java.util.List<Direction> directions) {
        int row = 0;
        int col = 0;

        long[][] cache = new long[matrix.length][matrix[0].length];
        return probeForSum(row, col, matrix, cache, directions);
    }

    private static long probeForSum(int row, int col, int[][] matrix, long[][] cache,
                                    java.util.List<Direction> directions) {
        if (outside.apply(matrix).apply(row).apply(col)) {
            return 0;
        }

        if (cache[row][col] != 0) {
            return cache[row][col];
        }

        for (Direction direction : directions) {
            long current = probeForSum(direction.incrRow(row), direction.incrCol(col),
                    matrix, cache, directions) + matrix[row][col];

            if (cache[row][col] < current) {
                cache[row][col] = current;
            }
        }

        return cache[row][col];
    }

    public static int[][] squareFromStringList(LinkedList<String> input, String separator) {
        if (List.isEmpty.test(input)) {
            throw new IllegalArgumentException("Input is either null or empty");
        }

        int[][] matrix = new int[input.size()][input.size()];

        return populate(input, matrix, separator);
    }

    public static int[][] fromStringList(LinkedList<String> input, String separator) {
        String first = input.getFirst();
        String[] firstArray = first.split(separator);
        int[][] matrix = new int[input.size()][firstArray.length];

        return populate(input, matrix, separator);
    }

    public static Function<SquareCharacterMatrix, int[][]> fromStringListToMatrixOfNumbers = input -> input.matrix()
            .stream()
            .map(s -> s.chars().map(CharacterUtil.charToDigit))
            .map(intStream -> intStream.toArray()).toArray(int[][]::new);

    public static char[][] fromCharArrayListToChar(LinkedList<String> input) {
        String first = input.getFirst();
        char[][] matrix = new char[input.size()][first.length()];

        return populateChar(input, matrix);
    }

    private static int[][] populate(LinkedList<String> input, int[][] matrix, String separator) {
        for (int i = 0; i < input.size(); i++) {
            String[] inputRow = input.get(i).split(separator);

            for (int j = 0; j < inputRow.length; j++) {
                matrix[i][j] = Integer.parseInt(inputRow[j]);
            }
        }

        return matrix;
    }

    private static char[][] populateChar(LinkedList<String> input, char[][] matrix) {
        for (int i = 0; i < input.size(); i++) {
            String inputRow = input.get(i);

            for (int j = 0; j < inputRow.length(); j++) {
                matrix[i][j] = inputRow.charAt(j);
            }
        }

        return matrix;
    }
}