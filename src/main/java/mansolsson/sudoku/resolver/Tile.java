package mansolsson.sudoku.resolver;

public class Tile {
    private int value;
    private boolean constant;
    
    public Tile(final Integer value) {
        this.value = value == null ? 0 : value.intValue();
        this.constant = value != null;
    }
    
    public void setValue(final int value) {
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    public void setConstant(final boolean constant) {
        this.constant = constant;
    }
    
    public boolean isConstant() {
        return constant;
    }
}
