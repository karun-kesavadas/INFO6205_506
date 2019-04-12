package sudoku;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    SudokuElement se;
    @BeforeEach
    public void init(){
       se=new SudokuElement ();
    }

    @Test
    @DisplayName("Test getConflictCount")
    void getConflictCount() {
        se.insert(0,2);
        se.insert(3,2);
        se.insert(1,4);
        se.insert(2,4);
        assertEquals(2,se.getConflictCount());
    }

    @Test
    @DisplayName("Test getGridIndices")
    void getGridIndices() {

        for(int i=0;i<9;i++) {
            se.insert(i, i + 1);
            se.setGridIndex(i, i);
        }
        int[] expected=new int[] {0,1,2,3,4,5,6,7,8};
        assertArrayEquals(expected,se.getGridIndices());
    }

    @Test
    @DisplayName("Test setGridIndex")
    void setGridIndex() {
        for(int i=0;i<9;i++) {
            se.insert(i, i + 1);
            se.setGridIndex(i, i);
        }
        int [] actual=se.getGridIndices();
        assertAll(()-> assertEquals(2,actual[2]),
                  ()-> assertEquals(5,actual[5]));}

    @Test
    @DisplayName("Test insert")
    void insert() {
        for(int i=0;i<9;i++)
            se.insert(i,i+1);
        int actual=se.fetch(6);
        assertEquals(7,actual);
    }

    @Test
    @DisplayName("Test fetch")
    void fetch() {
        se.insert(0,2);
       // se.insert(1,4);
        int actual=se.fetch(0);
        assertEquals(2,actual);
    }

    @Test
    @DisplayName("Test toArray")
    void testtoArray(){
        for(int i=0;i<9;i++)
            se.insert(i,i+1);
        int[] expected=new int[] {1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected,se.toArray());

    }

    @Test
    @DisplayName("Test toString")
    void testtoString(){
        for(int i=0;i<9;i++)
            se.insert(i,i+1);
        String expected="| 1 2 3 | 4 5 6 | 7 8 9 |";
        assertEquals(expected,se.toString());
    }

}