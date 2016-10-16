package mansolsson.sudoku.resolver;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TileTest {
    @Test
    public void constructorSetsValue() {
        final int value = 10;

        final Tile tile = new Tile(value);
        
        assertEquals(value, tile.getValue());
    }

    @Test
    public void constructorSetsConstantToTrueIfValueIsNotNull() {
        final Tile tile = new Tile(null);

        assertFalse(tile.isConstant());
    }
    
    @Test
    public void constructorSetsConstantToFalseIfValueIsNull() {
        final Tile tile = new Tile(1);

        assertTrue(tile.isConstant());
    }
    
    @Test
    public void setAndGetValue() {
        final Tile tile = new Tile(0);
        final int newValue = 87;

        tile.setValue(newValue);
        
        assertEquals(newValue, tile.getValue());
    }
    
    @Test
    public void setAndGetConstant() {
        final Tile tile = new Tile(1);

        tile.setConstant(false);
        
        assertFalse(tile.isConstant());
    }
}
