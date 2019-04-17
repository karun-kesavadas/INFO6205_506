import config.Constants;
import io.StandardOutputWriter;
import io.SudokuGridLoader;
import sudoku.SudokuGrid;

public class App
{
    public static void main(String[] args) {
        StandardOutputWriter.printTitle();
        SudokuGrid sudokuGrid = SudokuGridLoader.loadGrid(Constants.SUDOKU_PROBLEM_PATH);
        if(sudokuGrid!=null)
        {
            StandardOutputWriter.printProblemGrid(sudokuGrid);
            StandardOutputWriter.printAppConfiguration();
        }
    }
}
