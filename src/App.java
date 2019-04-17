import config.Config;
import config.Constants;
import geneticalgorithm.GeneticAlgorithm;
import geneticalgorithm.Individual;
import io.StandardOutputWriter;
import io.SudokuGridLoader;
import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

public class App
{
    public static void main(String[] args) {
        StandardOutputWriter.printTitle();
        SudokuGrid sudokuGrid = SudokuGridLoader.loadGrid();
        if(sudokuGrid!=null)
        {
            StandardOutputWriter.printProblemGrid(sudokuGrid);
            StandardOutputWriter.printAppConfiguration();
            Config.getInstance().setConfig(Constants.SUDOKU_GRID,sudokuGrid);
            Config.getInstance().setConfig(Constants.SUDOKU_GRID_HELPER,new SudokuGridHelper(sudokuGrid));
            GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
            Individual solution = geneticAlgorithm.solve();
            StandardOutputWriter.printSolution(solution);
        }
    }
}
