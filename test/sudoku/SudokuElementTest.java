package sudoku;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuElementTest {

    SudokuElement sudokuElement;
    @BeforeEach
    public void init(){
       sudokuElement =new SudokuElement ();
    }

    @Test
    @DisplayName("Test getConflictCount")
    void getConflictCount() {
        sudokuElement.insert(0,2);
        sudokuElement.insert(3,2);
        sudokuElement.insert(1,4);
        sudokuElement.insert(2,4);
        assertEquals(2, sudokuElement.getConflictCount());
    }

    @Test
    @DisplayName("Test setGridIndex")
    void setGridIndex() {
        for(int i=0;i<9;i++) {
            sudokuElement.insert(i, i + 1);
            sudokuElement.setGridIndex(i, i);
        }
        int [] actual= sudokuElement.toArray();
        assertAll(()-> assertEquals(3,actual[2]),
                  ()-> assertEquals(6,actual[5]));}

    @Test
    @DisplayName("Test insert")
    void insert() {
        for(int i=0;i<9;i++)
            sudokuElement.insert(i,i+1);
        int actual= sudokuElement.fetch(6);
        assertEquals(7,actual);
    }

    @Test
    @DisplayName("Test fetch")
    void fetch() {
        sudokuElement.insert(0,2);
       // sudokuElement.insert(1,4);
        int actual= sudokuElement.fetch(0);
        assertEquals(2,actual);
    }

    @Test
    @DisplayName("Test toArray")
    void testtoArray(){
        for(int i=0;i<9;i++)
            sudokuElement.insert(i,i+1);
        int[] expected=new int[] {1,2,3,4,5,6,7,8,9};
        assertArrayEquals(expected, sudokuElement.toArray());

    }

    @Test
    @DisplayName("Test toString")
    void testtoString(){
        for(int i=0;i<9;i++)
            sudokuElement.insert(i,i+1);
        String expected="| 1 2 3 | 4 5 6 | 7 8 9 |";
        assertEquals(expected, sudokuElement.toString());
    }

}