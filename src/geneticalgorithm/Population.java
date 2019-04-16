package geneticalgorithm;

import config.Constants;

import java.util.Iterator;
import java.util.TreeSet;

public final class Population implements Iterable<Individual>
{
    private double fitnessCount;
    private int conflictsCount;
    private TreeSet<Individual> individuals;
    private int populationSize;

    public Population()
    {
        this.fitnessCount = 0.0;
        this.conflictsCount = 0;
        this.individuals = new TreeSet<>();
        this.populationSize = Constants.POPULATION_SIZE;
    }

    public Population(Population other)
    {
        this();
        this.fitnessCount = other.fitnessCount;
        this.conflictsCount = other.conflictsCount;
        for (Individual individual : other)
        {
            this.individuals.add(new Individual(individual));
        }
    }

    public void createRandom()
    {
        for (int i = 0; i < Constants.POPULATION_SIZE; i++)
        {
            this.addIndividual(new Individual());
        }
    }

    public void addIndividual(Individual individual) {
        if (this.individuals.size() < this.populationSize) {
            this.fitnessCount += individual.getFitness();
            this.conflictsCount += individual.getConflictCount();
            this.individuals.add(individual);
        }
    }

    public void addIndividuals(Individual[] individuals)
    {
        for (Individual individual : individuals)
        {
            this.addIndividual(individual);
        }
    }

    /**
     * Returns the total fitness of the population
     * @return
     */
    public double getTotalFitness()
    {
        return this.fitnessCount;
    }

    /*Returns the average no of conflicts of a grid in a population*/
    public double getAvgConflicts()
    {
        return this.conflictsCount / (double) this.individuals.size();
    }

    /*Returns the average fitness of a grid in a population*/
    public double getAvgFitness()
    {
        return this.fitnessCount / (double) this.individuals.size();
    }


    public boolean isFull()
    {
        return this.individuals.size() == this.populationSize;
    }

    /**
     *  @return returns the the best individuals
     */
    public Individual[] getElites()
    {
        Individual[] elite = new Individual[Constants.NUMBER_OF_ELITES];
        int index = 0;
        if (Constants.NUMBER_OF_ELITES > 0)
        {
            for (Individual individual : this)
            {
                elite[index] = new Individual(individual);
                if (++index == elite.length) break;
            }
        }
        return elite;
    }

    public Individual getBestIndividual()
    {
        return this.individuals.first();
    }

    @Override
    public Iterator<Individual> iterator()
    {
        return this.individuals.iterator();
    }
}
