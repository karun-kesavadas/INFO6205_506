package geneticalgorithm.crossover;

import config.Config;
import config.Constants;
import geneticalgorithm.Individual;
import io.SudokuGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import static org.junit.jupiter.api.Assertions.*;

class UniformCrossoverTest {
    private UniformCrossover crossover;
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
        crossover = new UniformCrossover();
    }

    @Test
    void addParent()
    {
        for(int i=0;i<Constants.NUMBER_OF_PARENTS;i++)
        {
            crossover.addParent(new Individual());
        }
        assertEquals(crossover.needsParent(),false);
    }

    @Test
    void needsParent()
    {
        assertEquals(true,true);
        addParent();
    }

    @Test
    void cross()
    {
        addParent();
        assertNotNull(crossover.cross());
    }
}