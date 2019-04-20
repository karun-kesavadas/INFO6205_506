package sudoku;

import config.Constants;
import io.SudokuGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridHelperTest {

    SudokuGridHelper sudokuGridHelper;
    SudokuGrid sudokuGrid;

    @BeforeEach
    public void init() {

        sudokuGrid = new SudokuGrid();
        sudokuGridHelper = new SudokuGridHelper(sudokuGrid);
    }


    @Test
    void getVariableFields() {
        sudokuGrid =SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        Integer[] actual= sudokuGridHelper.getVariableFields();
        Integer[] expected=new Integer[actual.length];
        for(int i=0; i<81; i++)
            expected[i]=i;
        assertArrayEquals(expected,actual);
    }


    @Test
    void valueIsValidForGridIndex() {
                assertEquals(true, sudokuGridHelper.valueIsValidForGridIndex(12,3));
                assertEquals(true, sudokuGridHelper.valueIsValidForGridIndex(21,5));
    }

    @Test
    void getValidValuesForGridIndex() {
       Integer[] expected= new Integer[9];
       for(int i=0;i<9;i++) {
           expected[i] = i+1;
       }
       assertArrayEquals(expected, sudokuGridHelper.getValidValuesForGridIndex(5));
    }

    @Test
    void getVariableFieldsForRow() {
        Integer[] expected= new Integer[9];
        for(int i=18;i<27;i++) {
            expected[i-18] = i;
        }
        assertArrayEquals(expected, sudokuGridHelper.getVariableFieldsForRow(2));
    }
}