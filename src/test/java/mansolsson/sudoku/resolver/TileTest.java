package mansolsson.sudoku.resolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TileTest {
	@Test
	public void constructorSetsValueAndStateToLocked() {
		final Tile tile = new Tile(8);

		assertEquals(8, tile.getValue());
		assertEquals(TileState.LOCKED, tile.getState());
	}

	@Test
	public void constructorSetsValueToZeroStateToUnassignedIfInputIsNull() {
		final Tile tile = new Tile(null);

		assertEquals(0, tile.getValue());
		assertEquals(TileState.UNASSIGNED, tile.getState());
	}

	@Test
	public void setValueAndStateSetsValueAndState() {
		final Tile tile = new Tile(null);

		tile.setValueAndState(5, TileState.GUESS);

		assertEquals(5, tile.getValue());
		assertEquals(TileState.GUESS, tile.getState());
	}

	@Test
	public void isAssignedIfStateIsNotUnassigned() {
		final Tile tile = new Tile(null);

		for (final TileState tileState : TileState.values()) {
			if (tileState != TileState.UNASSIGNED) {
				tile.setValueAndState(5, tileState);
				assertTrue(tile.isAssigned());
			}
		}
	}

	@Test
	public void isNotAssignedIfStateIsUnassigned() {
		final Tile tile = new Tile(null);

		tile.setValueAndState(5, TileState.UNASSIGNED);

		assertFalse(tile.isAssigned());
	}
}
