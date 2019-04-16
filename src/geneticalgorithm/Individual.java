package geneticalgorithm;

import config.Config;

import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import java.util.Arrays;

public final class Individual extends SudokuGrid implements Comparable<Individual>
{

    private SudokuGridHelper sudokuGridHelper;
    private int[] gene;

    public Individual()
    {
        super((SudokuGrid) Config.getInstance().getConfig("grid"));
        this.sudokuGridHelper = (SudokuGridHelper) Config.getInstance().getConfig("gridHelper");
        this.gene = new int[this.sudokuGridHelper.getVariableFields().length];
        this.fillGridWithRandomNumbers();
    }

    public Individual(Individual other)
    {
        super(other);
        this.sudokuGridHelper = other.sudokuGridHelper;
        this.gene = Arrays.copyOf(other.getGene(), other.getGene().length);
    }

    private void fillGridWithRandomNumbers()
    {
        for (int i = 0; i < this.gene.length; i++)
        {
            this.insertRandomValues(i);
        }
    }

        public void insertRandomValues(int geneIndex)
        {
            int gridIndex = this.sudokuGridHelper.getVariableFields()[geneIndex];
            Integer[] validValues = this.sudokuGridHelper.getValidValuesForGridIndex(gridIndex);
            int randomValueIndex = (int) (Math.random() * validValues.length);
            this.insertValue(geneIndex, validValues[randomValueIndex]);
        }

    public void insertValue(int geneIndex, int value)
    {
        this.insert(this.sudokuGridHelper.getVariableFields()[geneIndex], value);
        this.gene[geneIndex] = value;
    }

    public int readValueFromIndex(int index)
    {
        return this.getGene()[index];
    }


    public int[] getGene()
    {

        return this.gene;
    }

    /**
     * @return the fitness of the individual
     */
    public double getFitness()
    {
        return (81.0d-this.getConflictCount())/81.0d;
    }


    @Override
    public int compareTo(Individual o)
    {
        return Double.compare(o.getFitness(),this.getFitness());
    }
}
