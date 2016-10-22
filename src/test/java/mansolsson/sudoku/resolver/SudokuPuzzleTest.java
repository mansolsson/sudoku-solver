package mansolsson.sudoku.resolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

public class SudokuPuzzleTest {
	@Rule
	public Timeout globalTimeout = Timeout.seconds(5);

	@Test
	public void initialBoardNotValidBecauseOfColumn() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, n, 3, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, 3, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void initialBoardNotValidBecauseOfRow() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, 2, n, n, n, n, 2, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void initialBoardNotValidBecauseOfBox() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, 1, n, n, n, n, n,
				n, n, n, n, 1, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void initialBoardNotValidBecauseOfNumberOfTiles() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n, n);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void initialBoardNotValidBecauseOfNumberTooBig() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, 10, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void initialBoardNotValidBecauseOfNumberTooSmall() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, -1, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void unsolvableSudoku() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				1, 3, n, 4, 5, 6, 7, 8, 9,
				4, 5, 6, 7, 8, 9, 1, 2, 3,
				7, 8, 9, 1, 2, 3, 4, 5, 6,
				2, 1, 4, 3, 6, 5, 8, 9, 7,
				3, 6, 5, 8, 9, 7, 2, 1, 4,
				8, 9, 7, 2, 1, 4, 3, 6, 5,
				5, n, 1, 6, 4, 2, 9, 7, 8,
				6, 4, n, 9, 7, 8, 5, 3, 1,
				9, 7, 8, 5, 3, 1, 6, 4, 2);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertFalse(result);
	}

	@Test
	public void solveEmptyBoard() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n,
				n, n, n, n, n, n, n, n, n);
		final List<Integer> expected = Arrays.asList(
				1, 2, 3, 4, 5, 6, 7, 8, 9,
				4, 5, 6, 7, 8, 9, 1, 2, 3,
				7, 8, 9, 1, 2, 3, 4, 5, 6,
				2, 1, 4, 3, 6, 5, 8, 9, 7,
				3, 6, 5, 8, 9, 7, 2, 1, 4,
				8, 9, 7, 2, 1, 4, 3, 6, 5,
				5, 3, 1, 6, 4, 2, 9, 7, 8,
				6, 4, 2, 9, 7, 8, 5, 3, 1,
				9, 7, 8, 5, 3, 1, 6, 4, 2);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertTrue(result);
		assertEquals(expected, s.getBoard());
	}

	@Test
	public void solveRealBoard1() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, 6, n, n, n, n, n, n, n,
				3, n, n, n, 2, 8, n, n, n,
				n, n, 2, 6, 3, 7, n, n, n,
				n, n, 1, n, n, n, 6, n, n,
				n, n, n, 5, n, 2, 4, n, 3,
				n, n, 5, 7, n, n, n, n, 9,
				n, 5, n, 4, 6, 1, 7, n, n,
				n, 9, 7, n, n, n, n, 6, n,
				4, n, n, n, n, n, n, 8, n);
		final List<Integer> expected = Arrays.asList(
				9, 6, 8, 1, 5, 4, 2, 3, 7,
				3, 7, 4, 9, 2, 8, 1, 5, 6,
				5, 1, 2, 6, 3, 7, 9, 4, 8,
				7, 4, 1, 8, 9, 3, 6, 2, 5,
				6, 8, 9, 5, 1, 2, 4, 7, 3,
				2, 3, 5, 7, 4, 6, 8, 1, 9,
				8, 5, 3, 4, 6, 1, 7, 9, 2,
				1, 9, 7, 2, 8, 5, 3, 6, 4,
				4, 2, 6, 3, 7, 9, 5, 8, 1);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertTrue(result);
		assertEquals(expected, s.getBoard());
	}

	@Test
	public void solveRealBoard2() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				7, 8, n, n, n, n, n, n, n,
				n, n, n, n, n, 6, n, n, 5,
				n, n, 2, 8, n, 3, n, 7, n,
				5, 9, n, n, 6, n, n, n, 4,
				6, n, n, 7, n, n, n, n, n,
				n, 1, 3, 5, n, n, n, n, n,
				n, 6, n, n, 4, 7, 2, 3, n,
				n, n, n, 2, 5, n, 4, n, n,
				n, n, 1, n, n, n, n, 5, n);
		final List<Integer> expected = Arrays.asList(
				7, 8, 6, 9, 2, 5, 3, 4, 1,
				1, 3, 9, 4, 7, 6, 8, 2, 5,
				4, 5, 2, 8, 1, 3, 6, 7, 9,
				5, 9, 7, 3, 6, 2, 1, 8, 4,
				6, 2, 4, 7, 8, 1, 5, 9, 3,
				8, 1, 3, 5, 9, 4, 7, 6, 2,
				9, 6, 5, 1, 4, 7, 2, 3, 8,
				3, 7, 8, 2, 5, 9, 4, 1, 6,
				2, 4, 1, 6, 3, 8, 9, 5, 7);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertTrue(result);
		assertEquals(expected, s.getBoard());
	}

	@Test
	public void solveRealBoard3() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				5, n, n, 6, n, n, n, 4, n,
				n, n, n, 2, 5, n, n, 9, n,
				n, n, 8, n, n, n, n, 3, n,
				n, 4, n, n, n, 7, n, 6, 5,
				n, 8, 5, n, n, n, n, n, n,
				1, n, n, n, n, 3, n, n, n,
				6, n, 2, n, n, 9, n, n, n,
				n, n, n, 7, n, n, n, n, 4,
				7, n, n, n, n, n, 1, 5, n);
		final List<Integer> expected = Arrays.asList(
				5, 2, 9, 6, 3, 8, 7, 4, 1,
				3, 1, 7, 2, 5, 4, 6, 9, 8,
				4, 6, 8, 9, 7, 1, 5, 3, 2,
				9, 4, 3, 8, 1, 7, 2, 6, 5,
				2, 8, 5, 4, 9, 6, 3, 1, 7,
				1, 7, 6, 5, 2, 3, 4, 8, 9,
				6, 5, 2, 1, 4, 9, 8, 7, 3,
				8, 3, 1 ,7, 6, 5, 9, 2, 4,
				7, 9, 4, 3, 8, 2, 1, 5, 6);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertTrue(result);
		assertEquals(expected, s.getBoard());
	}

	// 250
	@Test
	public void solveRealBoard4() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				3, 1, n, n, 9, n, n, n, n,
				n, n, n, n, n, n, n, 5, n,
				n, n, n, 7, 4, 5, n, n, n,
				n, 6, n, n, n, 7, 2, 4, 1,
				n, n, n, n, n, n, n, n, 6,
				5, n, n, n, 3, n, n, n, n,
				n, n, 9, 3, n, n, n, n, n,
				7, 5, 1, n, n, n, n, n, n,
				n, n, n, 5, 1, 6, n, n, 2);
		final List<Integer> expected = Arrays.asList(
				3, 1, 5, 8, 9, 2, 6, 7, 4,
				2, 7, 4, 1, 6, 3, 8, 5, 9,
				9, 8, 6, 7 ,4, 5, 1, 2, 3,
				8, 6, 3, 9, 5, 7, 2, 4, 1,
				1, 9, 7, 2, 8, 4, 5, 3, 6,
				5, 4, 2, 6, 3, 1, 9, 8, 7,
				6, 2, 9, 3, 7, 8, 4, 1, 5,
				7, 5, 1, 4, 2, 9, 3, 6, 8,
				4, 3, 8, 5, 1, 6, 7, 9, 2);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertTrue(result);
		assertEquals(expected, s.getBoard());
	}

	@Test
	public void solveRealBoard5() {
		final Integer n = null;
		final List<Integer> board = Arrays.asList(
				n, n, n, 5, 6, 3, n, n, 4,
				n, n, n, n, n, n, n, n, n,
				7, n, 9, n, n, n, n, n, 8,
				n, n, n, n, n, 6, 8, 5, n,
				n, n, n, 7, n, n, n, n, 9,
				1, 2, n, n, 5, n, n, n, n,
				n, 3, n, n, n, 2, 1, n, n,
				n, 1, n, n, n, n, 4, n, 5,
				n, n, 6, n, 3, n, n, n, n);
		final List<Integer> expected = Arrays.asList(
				2, 8, 1, 5, 6, 3, 9, 7, 4,
				6, 4, 3, 9, 7, 8, 5, 2, 1,
				7, 5, 9, 4, 2, 1, 3, 6, 8,
				9, 7, 4, 2, 1, 6, 8, 5, 3,
				3, 6, 5, 7, 8, 4, 2, 1, 9,
				1, 2, 8, 3, 5, 9, 6, 4, 7,
				5, 3, 7, 8, 4, 2, 1, 9, 6,
				8, 1, 2, 6, 9, 7, 4, 3, 5,
				4, 9, 6, 1, 3, 5, 7, 8, 2);
		final SudokuPuzzle s = new SudokuPuzzle(board);

		final boolean result = s.solve();

		assertTrue(result);
		assertEquals(expected, s.getBoard());
	}
}
