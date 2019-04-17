package sudoku;

import config.Constants;

import java.util.HashSet;
import java.util.Set;

public class SudokuGrid
{
    private final SudokuElement[] rows;
    private final SudokuElement[] columns;
    private final SudokuElement[] blocks;
    private int conflictCount;

    /**
     * No argument constructor that creates an empty sudoku grid.
     */
    public SudokuGrid()
    {
        this.rows = new SudokuElement[Constants.SUDOKU_SIDE_LENGTH];
        this.columns = new SudokuElement[Constants.SUDOKU_SIDE_LENGTH];
        this.blocks = new SudokuElement[Constants.SUDOKU_SIDE_LENGTH];
        this.conflictCount = 0;
        for(int i = 0; i < Constants.SUDOKU_SIDE_LENGTH; i++)
        {
            this.rows[i] = new SudokuElement();
            this.columns[i] = new SudokuElement();
            this.blocks[i] = new SudokuElement();
        }
    }

    /**
     * Copy constructor
     * @param sudokuGrid Sudoku Grid to be copied.
     */
    protected SudokuGrid(SudokuGrid sudokuGrid)
    {
        this.conflictCount = sudokuGrid.conflictCount;
        this.rows = copyElements(sudokuGrid.rows);
        this.columns = copyElements(sudokuGrid.columns);
        this.blocks = copyElements(sudokuGrid.blocks);
    }

    /**
     * Used to create a deep copy of sudoku element arrays.
     * @param sudokuElements Sudoku element array to be copied
     * @return A copy of the sudoku element array
     */
    private SudokuElement[] copyElements(SudokuElement[] sudokuElements)
    {
        SudokuElement[] copy = new SudokuElement[sudokuElements.length];
        for(int i = 0; i < sudokuElements.length; i++)
        {
            copy[i] = new SudokuElement(sudokuElements[i]);
        }
        return  copy;
    }

    public int getConflictCount()
    {
        return this.conflictCount;
    }

    public void insert(int columnIndex, int rowIndex, int value)
    {
        int blockNum = ((rowIndex / Constants.SUDOKU_BLOCK_SIZE) * Constants.SUDOKU_BLOCK_SIZE) + ((columnIndex / Constants.SUDOKU_BLOCK_SIZE));
        int blockIndex = ((rowIndex % Constants.SUDOKU_BLOCK_SIZE) * Constants.SUDOKU_BLOCK_SIZE) + (columnIndex % Constants.SUDOKU_BLOCK_SIZE);

        this.conflictCount -= this.rows[rowIndex].getConflictCount();
        this.conflictCount -= this.columns[columnIndex].getConflictCount();
        this.conflictCount -= this.blocks[blockNum].getConflictCount();

        this.rows[rowIndex].insert(columnIndex, value);
        this.rows[rowIndex].setGridIndex(columnIndex, this.getGridIndexByRow(rowIndex, columnIndex));
        this.columns[columnIndex].insert(rowIndex, value);
        this.columns[columnIndex].setGridIndex(rowIndex, this.getGridIndexByColumn(columnIndex, rowIndex));
        this.blocks[blockNum].insert(blockIndex, value);
        this.blocks[blockNum].setGridIndex(blockIndex, this.getGridIndexByBlock(blockNum, blockIndex));

        this.conflictCount += this.rows[rowIndex].getConflictCount();
        this.conflictCount += this.columns[columnIndex].getConflictCount();
        this.conflictCount += this.blocks[blockNum].getConflictCount();
    }

    public void insert(int gridIndex, int value)
    {
        this.insert(gridIndex % Constants.SUDOKU_SIDE_LENGTH, gridIndex / Constants.SUDOKU_SIDE_LENGTH, value);
    }

    public int fetch(int gridIndex)
    {
        return this.rows[gridIndex / Constants.SUDOKU_SIDE_LENGTH].fetch(gridIndex % Constants.SUDOKU_SIDE_LENGTH);
    }

    public int getGridIndexByRow(int rowIndex, int columnIndex)
    {
        return rowIndex * Constants.SUDOKU_SIDE_LENGTH + columnIndex;
    }

    private int getGridIndexByColumn(int columnIndex, int rowIndex)
    {
        return rowIndex * Constants.SUDOKU_SIDE_LENGTH + columnIndex;
    }

    private int getGridIndexByBlock(int blockNum, int blockIndex)
    {
        return ((blockNum / Constants.SUDOKU_BLOCK_SIZE) * Constants.SUDOKU_SIDE_LENGTH * Constants.SUDOKU_BLOCK_SIZE) +
                (blockIndex / Constants.SUDOKU_BLOCK_SIZE) * Constants.SUDOKU_SIDE_LENGTH +
                ((blockNum % Constants.SUDOKU_BLOCK_SIZE) * Constants.SUDOKU_BLOCK_SIZE) +
                (blockIndex % Constants.SUDOKU_BLOCK_SIZE);
    }

    @Override
    public String toString()
    {
        String line = "-------------------------\n";
        StringBuilder gridStringBuilder = new StringBuilder();
        for(int i = 0; i < this.rows.length; i++)
        {
            if (i % Constants.SUDOKU_BLOCK_SIZE == 0)
            {
                gridStringBuilder.append(line);
            }
            gridStringBuilder.append(this.rows[i].toString()).append('\n');
        }
        gridStringBuilder.append(line);
        return gridStringBuilder.toString();
    }

    public Integer[] getVariableFields()
    {
        Set<Integer> variableFields = new HashSet<>();
        for (int rowIndex = 0; rowIndex < this.rows.length; rowIndex++)
        {
            int[] row = this.rows[rowIndex].toArray();
            for (int colIndex = 0; colIndex < row.length; colIndex++)
            {
                if (row[colIndex] == 0)
                {
                    variableFields.add((rowIndex * Constants.SUDOKU_SIDE_LENGTH) + colIndex);
                }
            }
        }
        return variableFields.toArray(new Integer[0]);
    }
}
