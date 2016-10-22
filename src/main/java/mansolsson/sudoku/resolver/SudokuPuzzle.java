package mansolsson.sudoku.resolver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.IntPredicate;
import java.util.stream.Collectors;

public class SudokuPuzzle {
	private final SudokuBoard board;

	public SudokuPuzzle(final List<Integer> tiles) {
		board = new SudokuBoard(tiles.stream().map(Tile::new).collect(Collectors.toList()));
	}

	public boolean solve() {
		if (!isInitialBoardValid()) {
			return false;
		}

		while (tryToLockTiles(TileState.LOCKED));

		return bruteForce();
	}

	private boolean tryToLockTiles(final TileState state) {
		return board.lockTilesWithOnePossibleValueWithState(state) |
				board.lockTilesInAllColumnsWithState(state) |
				board.lockTilesInAllRowsWithState(state) |
				board.lockAllTilesInBoxes(state);
	}

	private boolean bruteForce() {
		int index = 0;
		while (index < SudokuBoard.NR_TILES) {
			if (index < 0) {
				return false;
			} else if (board.get(index).isAssigned() && board.get(index).getState() != TileState.GUESS) {
				index++;
			} else {
				final Optional<Integer> lowestPossibleValue = getLowestPossibleValue(index);
				if (lowestPossibleValue.isPresent()) {
					index = makeGuess(index, lowestPossibleValue.get());
				} else {
					index = backUpToLastValidState(index);
				}
			}
		}
		return true;
	}

	private int makeGuess(int index, final Integer lowestPossibleValue) {
		board.getBoard().get(index).setValueAndState(lowestPossibleValue, TileState.GUESS);
		index++;
		while (tryToLockTiles(TileState.LOCKED_BASED_ON_GUESS));
		return index;
	}

	private int backUpToLastValidState(int index) {
		board.getBoard().get(index).setValueAndState(0, TileState.UNASSIGNED);
		index = getIndexOfPreviousGuessedNumber(index);
		if (index >= 0) {
			resetAllLockedNumberBasedOnGuessAfter(index);
		}
		return index;
	}

	private void resetAllLockedNumberBasedOnGuessAfter(final int index) {
		for (int i = index; i < SudokuBoard.NR_TILES; i++) {
			if (board.get(i).getState() == TileState.LOCKED_BASED_ON_GUESS) {
				board.get(i).setValueAndState(0, TileState.UNASSIGNED);
			}
		}
	}

	private int getIndexOfPreviousGuessedNumber(final int index) {
		for (int i = index; i >= 0; i--) {
			if (board.getBoard().get(i).getState() == TileState.GUESS) {
				return i;
			}
		}
		return -1;
	}

	private boolean isInitialBoardValid() {
		return SudokuBoard.NR_TILES == board.getBoard().size() && allNumbersValid() && validBoardState();
	}

	private boolean allNumbersValid() {
		final IntPredicate invalidValue = value -> value < 0 || value > SudokuBoard.ROW_WIDTH;
		return !board.getBoard().stream().mapToInt(Tile::getValue).anyMatch(invalidValue);
	}

	private boolean validBoardState() {
		return validateRows() && validateColumns() && validateBoxes();
	}

	private boolean validateRows() {
		for (int row = 0; row < SudokuBoard.ROW_WIDTH; row++) {
			if (!isRowValid(row)) {
				return false;
			}
		}
		return true;
	}

	private boolean isRowValid(final int row) {
		final int[] occurences = new int[SudokuBoard.ROW_WIDTH + 1];
		for (int i = 0; i < SudokuBoard.ROW_WIDTH; i++) {
			final int index = row * SudokuBoard.ROW_WIDTH + i;
			if (board.get(index).getValue() != 0) {
				occurences[board.get(index).getValue()]++;
			}
		}
		return !Arrays.stream(occurences).anyMatch(i -> i > 1);
	}

	private boolean validateColumns() {
		for (int column = 0; column < SudokuBoard.ROW_WIDTH; column++) {
			if (!isColumnValid(column)) {
				return false;
			}
		}
		return true;
	}

	private boolean isColumnValid(final int column) {
		final int[] occurences = new int[SudokuBoard.ROW_WIDTH + 1];
		for (int i = 0; i < SudokuBoard.ROW_WIDTH; i++) {
			final int index = i * SudokuBoard.ROW_WIDTH + column;
			if (board.get(index).getValue() != 0) {
				occurences[board.get(index).getValue()]++;
			}
		}
		return !Arrays.stream(occurences).anyMatch(i -> i > 1);
	}

	private boolean validateBoxes() {
		for (int box = 0; box < SudokuBoard.ROW_WIDTH; box++) {
			final int x = box % SudokuBoard.BOX_WIDTH;
			final int y = box / SudokuBoard.BOX_WIDTH;
			if (!isBoxValid(x, y)) {
				return false;
			}
		}
		return true;
	}

	private boolean isBoxValid(final int boxX, final int boxY) {
		final int[] occurences = new int[SudokuBoard.ROW_WIDTH + 1];
		for (int x = 0; x < SudokuBoard.BOX_WIDTH; x++) {
			for (int y = 0; y < SudokuBoard.BOX_WIDTH; y++) {
				final int index = (boxX * SudokuBoard.BOX_WIDTH + x) +
						(boxY * SudokuBoard.ROW_WIDTH * SudokuBoard.BOX_WIDTH + (y * SudokuBoard.ROW_WIDTH));
				if (board.get(index).getValue() != 0) {
					occurences[board.get(index).getValue()]++;
				}
			}
		}
		return !Arrays.stream(occurences).anyMatch(i -> i > 1);
	}

	private Optional<Integer> getLowestPossibleValue(final int index) {
		final List<Integer> possibleValues = board.getPossibleValues(index % SudokuBoard.ROW_WIDTH, index / SudokuBoard.ROW_WIDTH);
		possibleValues.removeAll(getAlreadyTriedNumbers(index));
		return possibleValues.isEmpty() ? Optional.empty() : Optional.of(possibleValues.get(0));
	}

	private Collection<?> getAlreadyTriedNumbers(final int index) {
		final List<Integer> numbers = new ArrayList<>();
		for (int number = 1; number <= board.get(index).getValue(); number++) {
			numbers.add(number);
		}
		return numbers;
	}

	public List<Integer> getBoard() {
		return board.getBoard().stream().map(Tile::getValue).collect(Collectors.toList());
	}
}
