package sudoku;

import config.Constants;

import java.util.*;

public class SudokuGridHelper
{
    private SudokuGrid sudokuGrid;
    private Integer[] variableFields;
    private Map<Integer, Integer[]> variableFieldsPerRow;
    private Map<Integer, Integer[]> validValues;

    public SudokuGridHelper(SudokuGrid sudokuGrid)
    {
        this.sudokuGrid = sudokuGrid;
        identifyVariableFields();
        identifyValidValues();
        processVariableFieldsPerRow();
    }

    /**
     * @return variableFields
     */
    public Integer[] getVariableFields() {
        return this.variableFields;
    }

    /**
     * Creates an array of sudoku grid indexes of fields which are empty in the given puzzle.
     */
    private void identifyVariableFields()
    {
        this.variableFields = this.sudokuGrid.getVariableFields();
    }

    /**
     * Calculates an array for each empty field on the sudoku grid that
     * holds an array of values which do not interfere with the
     * values provided in the unsolved puzzle
     */
    private void identifyValidValues()
    {
        this.validValues = new HashMap<>();
        for (int gridIndex : this.getVariableFields())
        {
            Set<Integer> validValuesSet = new HashSet<>();
            for (int i = Constants.SUDOKU_MIN_VALUE; i <= Constants.SUDOKU_MAX_VALUE; i++)
            {
                this.sudokuGrid.insert(gridIndex, i);
                if (this.sudokuGrid.getConflictCount() == 0)
                {
                    validValuesSet.add(i);
                }
                this.sudokuGrid.insert(gridIndex, 0);
            }
            this.validValues.put(gridIndex, validValuesSet.toArray(new Integer[0]));
        }
    }

    /**
     * Calculates for each row, column and block which fields need to be filled
     */
    private void processVariableFieldsPerRow()
    {
        this.variableFieldsPerRow = new HashMap<>();
        List<Integer> variableFieldsList = Arrays.asList(variableFields);
        for (int rowIndex = 0; rowIndex < Constants.SUDOKU_SIDE_LENGTH; rowIndex++)
        {
            ArrayList<Integer> variableFieldsForRow = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < Constants.SUDOKU_SIDE_LENGTH; columnIndex++)
            {
                int rowField = this.sudokuGrid.getGridIndexByRow(rowIndex, columnIndex);
                if (variableFieldsList.contains(rowField)) {
                    variableFieldsForRow.add(rowField);
                }
            }
            this.variableFieldsPerRow.put(rowIndex, variableFieldsForRow.toArray(new Integer[0]));
        }
    }

    /**
     * @param gridIndex the grid index of the field
     * @param value the value which gets tested for validity
     * @return true if a given value is valid for a field with at given grid index
     */
    public boolean valueIsValidForGridIndex(int gridIndex, int value)
    {
        List<Integer> validNumbersList = Arrays.asList(this.validValues.get(gridIndex));
        return validNumbersList.contains(value);
    }

    /**
     * @param gridIndex the gridIndex to return the valid values for
     * @return array of valid values for a given index
     */
    public Integer[] getValidValuesForGridIndex(int gridIndex)

    {
        return this.validValues.get(gridIndex);
    }

    /**
     * @param rowIndex the index of the row to get the variable fields for
     * @return Array of variable fields in a given row
     */
    public Integer[] getVariableFieldsForRow(int rowIndex)
    {
        return this.variableFieldsPerRow.get(rowIndex);
    }
}
