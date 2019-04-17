package io;

import config.Constants;
import sudoku.SudokuGrid;

import java.util.Scanner;

public class SudokuGridLoader
{
    public static SudokuGrid loadGrid(String problemPath)
    {
        SudokuGrid grid = new SudokuGrid();
        Scanner scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream(problemPath));
        int line = 0;
        while (scanner.hasNextLine()) {
            if (line >= Constants.SUDOKU_SIDE_LENGTH) {
                throw new IllegalArgumentException("More than 9 rows were passed.");
            }
            loadGridRow(grid, line, scanner.nextLine());
            line += 1;
        }
        if (line < Constants.SUDOKU_SIDE_LENGTH) {
            throw new IllegalArgumentException("Less than 9 rows were passed.");
        }
        return grid;
    }

    private static void loadGridRow(SudokuGrid grid, int rowIndex, String row)
    {
        if (!row.contains(" ")) {
            row = row.replaceAll(".(?!$)", "$0 ").trim();
        }
        Scanner scanner = new Scanner(row);
        int columnIndex = 0;
        while (scanner.hasNextInt()) {
            if (columnIndex >= Constants.SUDOKU_SIDE_LENGTH) {
                throw new IllegalArgumentException("More than 9 numbers passed in row '" + rowIndex + "'.");
            }
            grid.insert(columnIndex, rowIndex, scanner.nextInt());
            columnIndex += 1;
        }
        if (columnIndex < Constants.SUDOKU_SIDE_LENGTH) {
            throw new IllegalArgumentException("Less than 9 numbers passed in row '" + rowIndex + "'.");
        }
    }
}
