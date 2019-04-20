package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;

public class SudokuGridTest {

    SudokuGrid sudokuGrid;

    @BeforeEach
    public void init() {
        sudokuGrid = new SudokuGrid();
    }

    @Test
    @DisplayName("Test insert by testing fetch by column row and block")
    void testInsert() {
     sudokuGrid.insert(31,7 );
     sudokuGrid.insert(70,3 );
     sudokuGrid.insert(9,1);

     sudokuGrid.insert(2,2,6);
     sudokuGrid.insert(8,5,2);
     sudokuGrid.insert(7,1,4);
    }

    @Test
    @DisplayName("Test get grid index by Row")
    void testGetGridIndexByRow() {
        assertEquals(70, sudokuGrid.getGridIndexByRow(7,7));
        assertEquals(53, sudokuGrid.getGridIndexByRow(5,8));
    }
}
