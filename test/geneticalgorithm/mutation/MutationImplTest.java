package geneticalgorithm.mutation;

import config.Config;
import config.Constants;
import geneticalgorithm.Individual;
import io.SudokuGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MutationImplTest {
    private MutationImpl mutation;
    private SudokuGrid sudokuGrid;
    private SudokuGridHelper sudokuGridHelper;

    @BeforeEach
    public void init()
    {
        sudokuGrid = SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        sudokuGridHelper =new SudokuGridHelper(sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID, sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID_HELPER, sudokuGridHelper);
        mutation = new MutationImpl();
    }

    @Test
    void mutate()
    {
        Individual individual = new Individual();
        assertNotNull(mutation.mutate(individual));
    }
}