package geneticalgorithm;

import config.Config;

import java.util.Iterator;
import java.util.TreeSet;

public final class Population implements Iterable<Individual>{

    private double fitnessCount;
    private int conflictsCount;
    private TreeSet<Individual> representation;
    private int populationSize;

    public Population() {
        this.fitnessCount = 0.0;
        this.conflictsCount = 0;
        this.representation = new TreeSet<>();
        this.populationSize = (int) Config.getInstance().getConfig("population-size");
    }

    public Population(Population other) {
        this();
        this.fitnessCount = other.fitnessCount;
        this.conflictsCount = other.conflictsCount;
        for (Individual individual : other) {
            this.representation.add(new Individual(individual));
        }
    }
//filling population with random values
    public void createRandom() {
        for (int i = 0; i < (int) Config.getInstance().getConfig("population-size"); i++) {
            this.populateIndividual(new Individual());
        }
    }

    public void populateIndividual(Individual individual) {
        if (this.representation.size() < this.populationSize) {
            this.fitnessCount += individual.getFitness();
            this.conflictsCount += individual.getConflictCount();
            this.representation.add(individual);
        }
    }

    public void populateIndividual(Individual[] individuals) {
        for (Individual individual : individuals) {
            this.populateIndividual(individual);
        }
    }

    /*Returns the total fitness of the population*/
    public double getTotalFitness() {
        return this.fitnessCount;
    }

    /*Returns the average no of conflicts of a grid in a population*/
    public double getAvgConflicts() {

        return this.conflictsCount / (double) this.representation.size();
    }

    /*Returns the average fitness of a grid in a population*/
    public double getAvgFitness() {
        return this.fitnessCount / (double) this.representation.size();
    }


    public boolean isFull() {
        return this.representation.size() == this.populationSize;
    }

    /**
     * @param number number of individuals to return
     * @return returns the the best individuals
     */
    public Individual[] get(int number) {
        if (number > this.populationSize) {
            throw new IllegalArgumentException("Error: More individual than population.");
        }
        Individual[] elite = new Individual[number];
        int index = 0;
        if (elite.length > 0) {
            for (Individual individual : this) {
                elite[index] = new Individual(individual);
                if (++index == elite.length) break;
            }
        }
        return elite;
    }
    public Individual getBestIndividual() {

        return this.representation.first();
    }
    @Override
    public Iterator<Individual> iterator() {
        return this.representation.iterator();
    }
}
