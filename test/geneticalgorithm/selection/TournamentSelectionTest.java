package geneticalgorithm.selection;

import config.Config;
import config.Constants;
import geneticalgorithm.Population;
import io.SudokuGridLoader;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

class TournamentSelectionTest
{
    private Selection selection;
    private SudokuGrid sudokuGrid;
    private SudokuGridHelper sudokuGridHelper;

    @BeforeEach
    public void init()
    {
        sudokuGrid = SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        sudokuGridHelper =new SudokuGridHelper(sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID, sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID_HELPER, sudokuGridHelper);
        selection = new TournamentSelection();
    }

    @Test
    void selectParent()
    {
        Population population = new Population(100);
        population.createRandom();
        Assert.assertNotNull(selection.selectParent(population));
    }
}