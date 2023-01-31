package io.barblin.functional.matrix;

import java.util.Map;
import java.util.function.Function;

public enum Direction {
    UP, DOWN, LEFT, RIGHT, LEFT_UP, LEFT_DOWN, RIGHT_UP, RIGHT_DOWN;

    private static final Map<Direction, Integer> ROW_MODIFIER = Map.of(UP, -1, DOWN, 1, LEFT, 0, RIGHT, 0, LEFT_UP, -1,
            LEFT_DOWN, 1, RIGHT_UP, -1, RIGHT_DOWN, 1);

    private static final Map<Direction, Integer> COL_MODIFIER = Map.of(UP, 0, DOWN, 0, LEFT, -1, RIGHT, 1, LEFT_UP, -1,
            LEFT_DOWN, -1, RIGHT_UP, 1, RIGHT_DOWN, 1);

    private static final Map<Direction, Integer> X_MODIFIER = Map.of(UP, 1, DOWN, -1, LEFT, 0, RIGHT, 0, LEFT_UP, -1,
            LEFT_DOWN, 1, RIGHT_UP, +1, RIGHT_DOWN, -1);

    private static final Map<Direction, Integer> Y_MODIFIER = Map.of(UP, 0, DOWN, 0, LEFT, -1, RIGHT, 1, LEFT_UP, -1,
            LEFT_DOWN, -1, RIGHT_UP, 1, RIGHT_DOWN, 1);

    public int incrRow(int curRow) {
        return extractAndIncrement
                .apply(curRow)
                .apply(this)
                .apply(ROW_MODIFIER);
    }

    public int incrCol(int curCol) {
        return extractAndIncrement
                .apply(curCol)
                .apply(this)
                .apply(COL_MODIFIER);
    }

    public int incrX(int x) {
        return extractAndIncrement
                .apply(x)
                .apply(this)
                .apply(X_MODIFIER);

    }

    public int incrY(int y) {
        return extractAndIncrement
                .apply(y)
                .apply(this)
                .apply(Y_MODIFIER);
    }

    private static Function<Direction, Function<Map<Direction, Integer>, Integer>> extractIncrement = u -> v -> v
            .getOrDefault(u, 0);

    private static Function<Integer, Function<Integer, Integer>> increment = a -> b -> a + b;

    private static Function<Integer, Function<Direction, Function<Map<Direction, Integer>, Integer>>> extractAndIncrement = current -> dir -> map -> increment
            .apply(current).apply(extractIncrement.apply(dir).apply(map));
}
