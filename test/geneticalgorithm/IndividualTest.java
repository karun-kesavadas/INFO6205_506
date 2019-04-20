package geneticalgorithm;

import config.Config;
import config.Constants;
import io.SudokuGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import static org.junit.jupiter.api.Assertions.*;

class IndividualTest {

    Individual individual;
    SudokuGrid sudokuGrid;
    SudokuGridHelper sudokuGridHelper;

    @BeforeEach
    public void init()
    {
        sudokuGrid = SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        sudokuGridHelper =new SudokuGridHelper(sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID, sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID_HELPER, sudokuGridHelper);
        individual =new Individual ();
    }

    @Test
    void insertValue() {

        individual.setChromosome(6,5);
        assertEquals(5, individual.getChromosome()[6]);
    }

    @Test
    void readValueFromIndex() {
        individual.setChromosome(6,5);
       assertEquals(5, individual.getChromosomeFromIndex(6));
    }

}