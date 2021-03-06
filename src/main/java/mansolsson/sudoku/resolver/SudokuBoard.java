package mansolsson.sudoku.resolver;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class SudokuBoard {
    protected static final int BOX_WIDTH = 3;
    protected static final int ROW_WIDTH = BOX_WIDTH * BOX_WIDTH;
    private static final int COLUMN_HEIGHT = ROW_WIDTH;
    protected static final int NR_TILES = ROW_WIDTH * ROW_WIDTH;
    private static final List<Integer> ALL_POSSIBLE_NUMBERS = asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    private final List<Tile> board;

    public SudokuBoard(final List<Tile> board) {
        this.board = board;
    }

    public List<Integer> getPossibleValues(final int x, final int y) {
        final List<Integer> possibleValues = new ArrayList<>(ALL_POSSIBLE_NUMBERS);

        possibleValues.removeAll(getNumbersInRow(y));
        possibleValues.removeAll(getNumbersInColumn(x));
        possibleValues.removeAll(getNumbersInBox(x, y));

        return possibleValues;
    }

    private List<Integer> getNumbersInRow(final int y) {
        final List<Integer> numbersInRow = new ArrayList<>();
        for (int i = 0; i < ROW_WIDTH; i++) {
            numbersInRow.add(board.get(ROW_WIDTH * y + i).getValue());
        }
        return numbersInRow;
    }

    private List<Integer> getNumbersInColumn(final int x) {
        final List<Integer> numbersInColumn = new ArrayList<>();
        for (int i = 0; i < COLUMN_HEIGHT; i++) {
            numbersInColumn.add(board.get(ROW_WIDTH * i + x).getValue());
        }
        return numbersInColumn;
    }

    private List<Integer> getNumbersInBox(final int x, final int y) {
        final List<Integer> numbersInBox = new ArrayList<>();
        final int startOfBoxX = (x / BOX_WIDTH) * BOX_WIDTH;
        final int startOfBoxY = (y / BOX_WIDTH) * BOX_WIDTH;
        for (int boxX = 0; boxX < BOX_WIDTH; boxX++) {
            for (int boxY = 0; boxY < BOX_WIDTH; boxY++) {
                final int index = (startOfBoxY + boxY) * ROW_WIDTH + (startOfBoxX + boxX);
                numbersInBox.add(board.get(index).getValue());
            }
        }
        return numbersInBox;
    }

    public boolean lockTilesWithOnePossibleValueWithState(final TileState state) {
        boolean anyTileLocked = false;
        for (int index = 0; index < board.size(); index++) {
            if (!board.get(index).isAssigned()) {
                final List<Integer> values = getPossibleValues(index % ROW_WIDTH, index / ROW_WIDTH);
                if (values.size() == 1) {
                    board.get(index).setValueAndState(values.get(0), state);
                    anyTileLocked = true;
                }
            }
        }
        return anyTileLocked;
    }

    public boolean lockTilesInAllRowsWithState(final TileState state) {
        boolean anyTileLocked = false;
        for (int y = 0; y < ROW_WIDTH; y++) {
            if (lockTilesInRowWithState(y, state)) {
                anyTileLocked = true;
            }
        }
        return anyTileLocked;
    }

    private boolean lockTilesInRowWithState(final int y, final TileState state) {
        final Map<Integer, List<Integer>> numberPossibleIn = getNumberPossibleIn(getNumbersInRow(y));

        for (int x = 0; x < ROW_WIDTH; x++) {
            final int index = y * ROW_WIDTH + x;
            if (!board.get(index).isAssigned()) {
                getPossibleValues(x, y).stream()
                                       .filter(numberPossibleIn::containsKey)
                                       .map(numberPossibleIn::get)
                                       .forEach(list -> list.add(index));
            }
        }

        return lockTiles(numberPossibleIn, state);
    }

    private Map<Integer, List<Integer>> getNumberPossibleIn(final List<Integer> usedNumbers) {
        return ALL_POSSIBLE_NUMBERS.stream().filter(n -> !usedNumbers.contains(n)).collect(toMap(Function.identity(), n -> new ArrayList<>()));
    }

    private boolean lockTiles(final Map<Integer, List<Integer>> numberPossibleIn, final TileState state) {
        final Predicate<Entry<Integer, List<Integer>>> onePossibleValue = e -> e.getValue().size() == 1;

        final Consumer<Entry<Integer, List<Integer>>> lockTile = e -> board.get(e.getValue().get(0)).setValueAndState(e.getKey(), state);

        numberPossibleIn.entrySet().stream().filter(onePossibleValue).forEach(lockTile);

        return numberPossibleIn.entrySet().stream().anyMatch(onePossibleValue);
    }

    public boolean lockTilesInAllColumnsWithState(final TileState state) {
        boolean anyTileLocked = false;
        for (int x = 0; x < ROW_WIDTH; x++) {
            if (lockTilesInColumnWithState(x, state)) {
                anyTileLocked = true;
            }
        }
        return anyTileLocked;
    }

    private boolean lockTilesInColumnWithState(final int x, final TileState state) {
        final Map<Integer, List<Integer>> numberPossibleIn = getNumberPossibleIn(getNumbersInColumn(x));

        for (int y = 0; y < ROW_WIDTH; y++) {
            final int index = y * ROW_WIDTH + x;
            if (!board.get(index).isAssigned()) {
                getPossibleValues(x, y).stream()
                                       .filter(numberPossibleIn::containsKey)
                                       .map(numberPossibleIn::get)
                                       .forEach(list -> list.add(index));
            }
        }

        return lockTiles(numberPossibleIn, state);
    }

    public boolean lockAllTilesInBoxes(final TileState state) {
        boolean anyTileLocked = false;
        for (int box = 0; box < ROW_WIDTH; box++) {
            if (lockTilesInBoxWithState(box % BOX_WIDTH * BOX_WIDTH, box / BOX_WIDTH * BOX_WIDTH, state)) {
                anyTileLocked = true;
            }
        }
        return anyTileLocked;
    }

    private boolean lockTilesInBoxWithState(final int startX, final int startY, final TileState state) {
        final Map<Integer, List<Integer>> numberPossibleIn = getNumberPossibleIn(getNumbersInBox(startX, startY));

        for (int x = 0; x < BOX_WIDTH; x++) {
            for (int y = 0; y < BOX_WIDTH; y++) {
                final int index = (startX + x) + (startY + y) * ROW_WIDTH;
                if (!board.get(index).isAssigned()) {
                    getPossibleValues(startX + x, startY + y).stream()
                                                             .filter(numberPossibleIn::containsKey)
                                                             .map(numberPossibleIn::get)
                                                             .forEach(list -> list.add(index));
                }
            }
        }

        return lockTiles(numberPossibleIn, state);
    }

    public List<Tile> getBoard() {
        return board;
    }

    public Tile get(final int index) {
        return board.get(index);
    }
}
