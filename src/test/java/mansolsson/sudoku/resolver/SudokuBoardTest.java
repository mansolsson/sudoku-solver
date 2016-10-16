package mansolsson.sudoku.resolver;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

public class SudokuBoardTest {
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
        final SudokuBoard s = new SudokuBoard(board);
        
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
        final SudokuBoard s = new SudokuBoard(board);
        
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
        final SudokuBoard s = new SudokuBoard(board);
        
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
        final SudokuBoard s = new SudokuBoard(board);
        
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
        final SudokuBoard s = new SudokuBoard(board);
        
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
        final SudokuBoard s = new SudokuBoard(board);
        
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
        final SudokuBoard s = new SudokuBoard(board);
        
        final boolean result = s.solve();
        
        assertTrue(result);
        s.print();
    }

    @Test
    @Ignore
    public void solveRealBoardEasy() {
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
        final SudokuBoard s = new SudokuBoard(board);
        
        final boolean result = s.solve();
        
        assertTrue(result);
        s.print();
    }
}
