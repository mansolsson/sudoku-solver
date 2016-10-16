package mansolsson.sudoku.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;

public class SudokuBoard {
    private static final int BOX_WIDTH = 3;
    private static final int ROW_WIDTH = BOX_WIDTH * BOX_WIDTH;
    private static final int NR_TILES = ROW_WIDTH * ROW_WIDTH;
    private static final List<Integer> NUMBERS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
    
    private final List<Tile> board = new ArrayList<>();

    public SudokuBoard(final List<Integer> tiles) {
        tiles.stream().map(Tile::new).forEach(board::add);
    }

    public boolean solve() {
        if (!isInitialBoardValid()) {
            return false;
        }

        int index = 0;
        while (index < NR_TILES) {
            if (index < 0) {
                return false;
            } else if (board.get(index).isConstant()) {
                index++;
            } else {
                final Integer lowestPossibleValue = getLowestPossibleValue(index);
                if (lowestPossibleValue == null) {
                    board.get(index).setValue(0);
                    index--;
                } else {
                    board.get(index).setValue(lowestPossibleValue);
                    index++;
                }
            }
        }
        return true;
    }

    private boolean isInitialBoardValid() {
        return NR_TILES == board.size() && allNumbersValid() && validBoardState();
    }

    private boolean allNumbersValid() {
        final IntPredicate invalidValue = v -> v < 0 || v > ROW_WIDTH;
        return !board.stream().mapToInt(Tile::getValue).anyMatch(invalidValue);
    }
    
    private boolean validBoardState() {
        return validateRows() && validateColumns() && validateBoxes();
    }

    private boolean validateRows() {
        for (int row = 0; row < ROW_WIDTH; row++) {
            if (!isRowValid(row)) {
                return false;
            }
        }
        return true;
    }

    private boolean isRowValid(final int row) {
        final int[] occurences = new int[ROW_WIDTH + 1];
        for (int i = 0; i < ROW_WIDTH; i++) {
            final int index = row * ROW_WIDTH + i;
            if (board.get(index).getValue() != 0) {
                occurences[board.get(index).getValue()]++;
            }
        }
        return !Arrays.stream(occurences).anyMatch(i -> i > 1);
    }

    private boolean validateColumns() {
        for (int column = 0; column < ROW_WIDTH; column++) {
            if (!isColumnValid(column)) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isColumnValid(final int column) {
        final int[] occurences = new int[ROW_WIDTH + 1];
        for (int i = 0; i < ROW_WIDTH; i++) {
            final int index = i * ROW_WIDTH + column;
            if (board.get(index).getValue() != 0) {
                occurences[board.get(index).getValue()]++;
            }
        }
        return !Arrays.stream(occurences).anyMatch(i -> i > 1);
    }

    private boolean validateBoxes() {
        for (int box = 0; box < ROW_WIDTH; box++) {
            final int x = box % BOX_WIDTH;
            final int y = box / BOX_WIDTH;
            if (!isBoxValid(x, y)) {
                return false;
            }
        }
        return true;
    }

    private boolean isBoxValid(final int boxX, final int boxY) {
        final int[] occurences = new int[ROW_WIDTH + 1];
        for (int x = 0; x < BOX_WIDTH; x++) {
            for (int y = 0; y < BOX_WIDTH; y++) {
                final int index = (boxX * BOX_WIDTH + x) + (boxY * ROW_WIDTH * BOX_WIDTH + (y * ROW_WIDTH));
                if (board.get(index).getValue() != 0) {
                    occurences[board.get(index).getValue()]++;
                }
            }
        }
        return !Arrays.stream(occurences).anyMatch(i -> i > 1);
    }

    private Integer getLowestPossibleValue(final int index) {
        final List<Integer> possibleValues = new ArrayList<>(NUMBERS);
        for (int i = 1; i <= board.get(index).getValue(); i++) {
            possibleValues.remove(Integer.valueOf(i));
        }

        final int row = index / ROW_WIDTH;
        for (int i = 0; i < ROW_WIDTH; i++) {
            possibleValues.remove(Integer.valueOf(board.get(row * ROW_WIDTH + i).getValue()));
        }

        final int col = index % ROW_WIDTH;
        for (int i = 0; i < ROW_WIDTH; i++) {
            possibleValues.remove(Integer.valueOf(board.get(ROW_WIDTH * i + col).getValue()));
        }

        final int boxY = index / ROW_WIDTH / BOX_WIDTH;
        final int boxX = index % ROW_WIDTH / BOX_WIDTH;

        for (int x = 0; x < BOX_WIDTH; x++) {
            for (int y = 0; y < BOX_WIDTH; y++) {
                final int i = (boxX * BOX_WIDTH + x) + (boxY * ROW_WIDTH * BOX_WIDTH + (y * ROW_WIDTH));
                possibleValues.remove(Integer.valueOf(board.get(i).getValue()));
            }
        }
        return possibleValues.isEmpty() ? null : possibleValues.get(0);
    }

    public void print() {
        for (int i = 0; i < board.size(); i++) {
            System.out.print(board.get(i).getValue());
            if ((i + 1) % ROW_WIDTH == 0) {
                System.out.println();
            }
        }
    }
}
