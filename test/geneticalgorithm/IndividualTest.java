package geneticalgorithm;

import config.Config;
import config.Constants;
import io.SudokuGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.SudokuElement;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import static org.junit.jupiter.api.Assertions.*;

class IndividualTest {

    Individual indi;
    SudokuGrid sg;
    SudokuGridHelper sgh;

    @BeforeEach
    public void init()
    {
        sg= SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        sgh=new SudokuGridHelper(sg);
        Config.getInstance().setConfig("grid",sg);
        Config.getInstance().setConfig("gridHelper",sgh);
        indi=new Individual ();
    }

    @Test
    void insertValue() {

        indi.insertValue(6,5);
        assertEquals(5,indi.getGene()[6]);
    }

    @Test
    void readValueFromIndex() {
        indi.insertValue(6,5);
       assertEquals(5,indi.readValueFromIndex(6));
    }

}