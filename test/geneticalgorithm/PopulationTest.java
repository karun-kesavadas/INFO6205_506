package geneticalgorithm;

import config.Config;
import config.Constants;
import io.SudokuGridLoader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import static org.junit.jupiter.api.Assertions.*;

class PopulationTest {

    Population population;
    SudokuGrid sudokuGrid;
    SudokuGridHelper sudokuGridHelper;

    @BeforeEach
    public void init()
    {
        sudokuGrid = SudokuGridLoader.loadGrid(Constants.TEST_SUDOKU_PROBLEM_PATH);
        sudokuGridHelper =new SudokuGridHelper(sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID, sudokuGrid);
        Config.getInstance().setConfig(Constants.SUDOKU_GRID_HELPER, sudokuGridHelper);
        population = new Population(5);
    }

    @Test
    void createRandom()
    {
        assertEquals(population.isFull(),false);
        population.createRandom();
        assertEquals(population.isFull(),true);
    }

    @Test
    void addIndividual()
    {
        assertEquals(population.isFull(),false);
        for(int i=0;i<5;i++)
            population.addIndividual(new Individual());
        assertEquals(population.isFull(),true);
    }

    @Test
    void addIndividuals()
    {
        assertEquals(population.isFull(),false);
        Individual[] individuals = new Individual[5];
        for(int i=0;i<5;i++)
            individuals[i] = new Individual();
        population.addIndividuals(individuals);
        assertEquals(population.isFull(),true);
    }

    @Test
    void getAvgConflicts()
    {
        population.createRandom();
        assertNotNull(population.getAvgConflicts());
    }

    @Test
    void getAvgFitness()
    {
        population.createRandom();
        assertNotNull(population.getAvgFitness());
    }

    @Test
    void getElites()
    {
        population.createRandom();
        assertEquals(population.getElites(1).length,1);
    }

    @Test
    void getIndividual()
    {
        population.createRandom();
        assertNotNull(population.getIndividual(0));
    }

    @Test
    void getBestIndividual()
    {
        population.createRandom();
        assertNotNull(population.getBestIndividual());
    }
}