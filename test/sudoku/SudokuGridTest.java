package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class SudokuGridTest {

    SudokuGrid se;

    @BeforeEach
    public void init() {
        se = new SudokuGrid();
    }

    @Test
    @DisplayName("Test insert by testing fetch by column row and block")
    void testInsert() {
     se.insert(31,7 );
     se.insert(70,3 );
     se.insert(9,1);

     se.insert(2,2,6);
     se.insert(8,5,2);
     se.insert(7,1,4);
    }

    @Test
    @DisplayName("Test get grid index by column")
    void testGetGridIndexByColumn() {
        assertEquals(31, se.getGridIndexByColumn(4,3));
        assertEquals(20,se.getGridIndexByColumn(2,2));
    }

    @Test
    @DisplayName("Test get grid index by Row")
    void testGetGridIndexByRow() {
        assertEquals(70, se.getGridIndexByRow(7,7));
        assertEquals(53, se.getGridIndexByRow(5,8));
    }

    @Test
    @DisplayName("Test get grid index by Block")
    void testGetGridIndexByBlock() {
        assertEquals(9,se.getGridIndexByBlock(0,3));
        assertEquals(64,se.getGridIndexByBlock(6,4));
    }

}
