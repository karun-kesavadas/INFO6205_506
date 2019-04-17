package geneticalgorithm.mutation;

import config.Config;
import config.Constants;
import geneticalgorithm.Individual;
import sudoku.SudokuGridHelper;

/**
 * Mutates an individual by swapping fields in sudoku rows
 */
public final class MutationImpl implements Mutation
{
    private final SudokuGridHelper sudokuGridHelper;

    public MutationImpl()
    {
        this.sudokuGridHelper = (SudokuGridHelper) Config.getInstance().getConfig(Constants.SUDOKU_GRID_HELPER);
    }

    @Override
    public Individual mutate(Individual individual)
    {
        if (Math.random() < Constants.MUTATION_RATE)
        {
            return individual;
        }
        for (int i = 0; i < (int) (Math.random() * Constants.SUDOKU_SIDE_LENGTH); i++)
        {
            int row = (int) (Math.random() * Constants.SUDOKU_SIDE_LENGTH);
            Integer[] variableFields = this.sudokuGridHelper.getVariableFieldsForRow(row);
            if (variableFields.length > 0)
            {
                int swapField1 = variableFields[(int) (Math.random() * variableFields.length)];
                for (int swapField2 : variableFields)
                {
                    if (individual.fetch(swapField1) != individual.fetch(swapField2) &&
                            this.sudokuGridHelper.valueIsValidForGridIndex(swapField1, individual.fetch(swapField2)) &&
                            this.sudokuGridHelper.valueIsValidForGridIndex(swapField2, individual.fetch(swapField1)))
                    {
                        int tempField = individual.fetch(swapField2);
                        individual.setChromosomeByGridIndex(swapField1, individual.fetch(swapField2));
                        individual.setChromosomeByGridIndex(swapField2, tempField);
                    }
                }
            }
        }
        return individual;
    }
}
