package mansolsson.sudoku.resolver;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TileStateTest {
	@Test
	public void valueOf() {
		assertEquals(TileState.GUESS, TileState.valueOf(TileState.GUESS.toString()));
	}
}
