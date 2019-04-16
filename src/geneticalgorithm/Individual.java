package geneticalgorithm;

import config.Config;

import sudoku.SudokuGrid;
import sudoku.SudokuGridHelper;

import java.util.Arrays;

public final class Individual extends SudokuGrid implements Comparable<Individual> {

    private SudokuGrid sudokuGrid;
    private SudokuGridHelper sudokuGridHelper;
    private int[] gene;

    public Individual() {
        super((SudokuGrid) Config.getInstance().getConfig("grid"));
        this.sudokuGridHelper = (SudokuGridHelper) Config.getInstance().getConfig("GridHelper");
        this.gene = new int[this.sudokuGridHelper.getVariableFields().length];
        this.fillGridWithRandomNumbers();
    }

    public Individual(Individual other) {
        super(other);
        this.sudokuGridHelper = other.sudokuGridHelper;
        this.gene = Arrays.copyOf(other.getGene(), other.getGene().length);
    }

    private void fillGridWithRandomNumbers() {
        for (int i = 0; i < this.gene.length; i++) {
            this.insertRandomValues(i);
        }
    }

        public void insertRandomValues(int index) {
            int gridIndex = this.sudokuGridHelper.getVariableFields()[index];
            Integer[] validNumbers = this.sudokuGridHelper.getValidNumbersForIndex(gridIndex);
            int randomIndex = (int) (Math.random() * validNumbers.length);
            this.insertValues(index, validNumbers[randomIndex]);
        }

    public void insertValues(int index, int number) {
        this.insert(this.sudokuGridHelper.getVariableFields()[index], number);
        this.gene[index] = number;
    }

    public int readValueFromIndex(int index) {

        return this.getGene()[index];
    }


    public int[] getGene() {

        return this.gene;
    }

    /**
     * @return the fitness of the individual
     */
    public double getFitness() {
        return Math.pow((1.0 / (this.getConflictCount() + 1)), 3);
    }


    @Override
    public int compareTo(Individual o) {
        return (this.getFitness() < o.getFitness()) ? 1 : -1;
    }
}
