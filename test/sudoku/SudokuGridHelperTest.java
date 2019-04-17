package sudoku;

import config.Constants;
import io.SudokuGridLoader;
import jdk.internal.cmm.SystemResourcePressureImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuGridHelperTest {

    SudokuGridHelper sgh;
    SudokuGrid sg;

    @BeforeEach
    public void init() {

        sg = new SudokuGrid();
        sgh = new SudokuGridHelper(sg);
    }


    @Test
    void getVariableFields() {
        sg=SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        Integer[] actual=sgh.getVariableFields();
        Integer[] expected=new Integer[actual.length];
        for(int i=0; i<81; i++)
            expected[i]=i;
        assertArrayEquals(expected,actual);
    }


    @Test
    void valueIsValidForGridIndex() {
                assertEquals(true,sgh.valueIsValidForGridIndex(12,3));
                assertEquals(true,sgh.valueIsValidForGridIndex(21,5));
    }

    @Test
    void getValidValuesForGridIndex() {
       Integer[] expected= new Integer[9];
       for(int i=0;i<9;i++) {
           expected[i] = i+1;
       }
       assertArrayEquals(expected, sgh.getValidValuesForGridIndex(5));
    }

    @Test
    void getVariableFieldsForRow() {
        Integer[] expected= new Integer[9];
        for(int i=18;i<27;i++) {
            expected[i-18] = i;
        }
        assertArrayEquals(expected, sgh.getVariableFieldsForRow(2));
    }
}