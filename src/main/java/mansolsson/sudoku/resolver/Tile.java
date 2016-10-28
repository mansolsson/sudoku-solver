package mansolsson.sudoku.resolver;

public class Tile {
    private int value;
    private TileState state;

    public Tile(final Integer value) {
        this.value = value == null ? 0 : value.intValue();
        this.state = value == null ? TileState.UNASSIGNED : TileState.LOCKED;
    }

    public boolean isAssigned() {
        return state != TileState.UNASSIGNED;
    }

    public void setValueAndState(final int value, final TileState state) {
        this.value = value;
        this.state = state;
    }

    public int getValue() {
        return value;
    }

    public TileState getState() {
        return state;
    }
}
