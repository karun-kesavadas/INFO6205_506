package sudoku;

import config.Constants;

import java.util.Arrays;

/**
 * Represents a row,column or block in the Sudoku grid
 */
class SudokuElement

{
    private final int[] values;//Individual values (range 1 to 9) in the sudoku element
    private final int[] valueOccurrences;//Keeps track of the number of times values 1-9 occur in the element
    private final int[] gridIndices;//Tracks the grid index of the values
    private int conflictCount;

    /**
     * No Argument Constructor
     */
    public SudokuElement()
    {
        this.values = new int[Constants.SUDOKU_SIDE_LENGTH];
        this.valueOccurrences = new int[Constants.SUDOKU_SIDE_LENGTH];
        this.gridIndices = new int[Constants.SUDOKU_SIDE_LENGTH];
        this.conflictCount = 0;
    }

    /**
     * Copy Constructor
     * @param element Sudoku Element that needs to be duplicated
     */
    public SudokuElement(SudokuElement element)
    {
        this.values = Arrays.copyOf(element.values,element.values.length);
        this.valueOccurrences = Arrays.copyOf(element.valueOccurrences,element.valueOccurrences.length);
        this.gridIndices = Arrays.copyOf(element.gridIndices,element.gridIndices.length);
        this.conflictCount = element.conflictCount;
    }

    /**
     * Returns the number of conflicts present in the element
     * @return Conflict count
     */
    public int getConflictCount()
    {
        return this.conflictCount;
    }

    public void setGridIndex(int position, int gridIndex)
    {
        this.gridIndices[position] = gridIndex;
    }

    /**
     * Returns the number of times a value occurs in the sudoku element.
     * @param value Sudoku Element value
     * @return Value occurrence count
     */
    private int getValueOccurrences(int value)
    {
        return this.valueOccurrences[value - 1];
    }

    /**
     * Updates the number of times a value occurs in the sudoku element.
     * @param value Sudoku Element value
     * @param newOccurrenceCount Value occurrence count
     */
    private void setValueOccurrences(int value, int newOccurrenceCount)
    {
        this.valueOccurrences[value - 1] = newOccurrenceCount;
    }

    /**
     * Updates the value at the specified position
     * @param position Position in the element which needs to be updated
     * @param newValue New value to be inserted at the given position
     */
    public void insert(int position, int newValue)
    {
        int previousValue = this.values[position];
        if (Constants.SUDOKU_MIN_VALUE <= previousValue)
        {
            this.decrementConflictCount(previousValue);
        }
        this.values[position] = newValue;
        if (Constants.SUDOKU_MIN_VALUE <= newValue)
        {
            incrementConflictCount(newValue);
        }
    }

    /**
     * Returns the value at the specified position
     * @param position Position of the value to be fetched
     * @return Value
     */
    public int fetch(int position)
    {
        return this.values[position];
    }

    /**
     * Decrements the conflict count if required when a value is updated
     * @param previousValue Element value being updated
     */
    private void decrementConflictCount(int previousValue)
    {
        int valueOccurrence = this.getValueOccurrences(previousValue);
        if(valueOccurrence>1)
            this.conflictCount-=1;
        this.setValueOccurrences(previousValue,valueOccurrence-1);
    }

    /**
     * Increments the conflict count if required when a value is updated
     * @param newValue New value
     */
    private void incrementConflictCount(int newValue)
    {
        int valueOccurrence = this.getValueOccurrences(newValue)+1;
        this.setValueOccurrences(newValue,valueOccurrence);
        if(valueOccurrence>1)
            this.conflictCount+=1;
    }

    /**
     * Returns array of values
     * @return Array of element values
     */
    public int[] toArray()
    {
        return this.values;
    }

    /**
     * Returns the string representation of the sudoku element
     * @return String representation of element
     */
    @Override
    public String toString()
    {
        StringBuilder elementStringBuilder = new StringBuilder();
        for (int i = 0; i < this.values.length; i++)
        {
            if (i % Constants.SUDOKU_BLOCK_SIZE == 0)
            {
                elementStringBuilder.append("| ");
            }
            elementStringBuilder.append(this.values[i]).append(" ");
        }
        elementStringBuilder.append("|");
        return elementStringBuilder.toString();
    }
}


