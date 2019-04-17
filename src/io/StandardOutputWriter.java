package io;

import config.Constants;
import geneticalgorithm.Individual;
import sudoku.SudokuGrid;

/**
 * Class for printing information to the standard output
 */
public class StandardOutputWriter
{
    /**
     * Prints the application title to the console
     */
    public static void printTitle()
    {
        String title =
        "╔═╗┬ ┬┌┬┐┌─┐┬┌─┬ ┬  ╔═╗┌─┐┬ ┬  ┬┌─┐┬─┐\n" +
        "╚═╗│ │ │││ │├┴┐│ │  ╚═╗│ ││ └┐┌┘├┤ ├┬┘\n" +
        "╚═╝└─┘─┴┘└─┘┴ ┴└─┘  ╚═╝└─┘┴─┘└┘ └─┘┴└─\n";
        System.out.printf(title);
        System.out.println("    by Karun Kesavadas, Kumari Deepshikha and Bhashmi Fatnani");
        System.out.println();
    }

    /**
     * Prints the user configured properties to the standard output
     */
    public static void printAppConfiguration()
    {
        System.out.println("Application Configuration:");
        System.out.println("ELITISM RATE : "+ Constants.ELITISM_RATE);
        System.out.println("MUTATION RATE : "+Constants.MUTATION_RATE);
        System.out.println("POPULATION SIZE : "+Constants.POPULATION_SIZE);
        System.out.println("POPULATIONS BEFORE RESTART : "+Constants.POPULATIONS_BEFORE_RESTART);
        System.out.println("NUMBER OF PARENTS : "+Constants.NUMBER_OF_PARENTS);
    }

    public static void printProblemGrid(SudokuGrid sudokuGrid)
    {
        System.out.println("Solving the following sudoku puzzle : ");
        System.out.println(sudokuGrid);
    }

    public static void printStatus(Individual elite, int epoch, int generation, double avgFitness, double avgConflicts)
    {
        System.out.println("EPOCH : " + epoch);
        System.out.println("GENERATION : " + generation);
        System.out.println("CURRENT BEST INDIVIDUAL : ");
        System.out.println(elite);
        System.out.println("CONFLICTS IN BEST INDIVIDUAL : " + elite.getConflictCount());
        System.out.println("AVG CONFLICTS IN POPULATION : " + avgConflicts);
        System.out.println("FITNESS IN BEST INDIVIDUAL : " + elite.getFitness());
        System.out.println("AVG FITNESS IN POPULATION : " + avgFitness);
        System.out.println();
    }

    public static void printSolution(SudokuGrid grid)
    {
        System.out.println("SOLUTION TO PROBLEM : ");
        System.out.println(grid);
        System.out.println("CONFLICTS : " + grid.getConflictCount());
    }
}
