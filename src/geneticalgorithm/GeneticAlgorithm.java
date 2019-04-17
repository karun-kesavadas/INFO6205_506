package geneticalgorithm;

import config.Constants;
import geneticalgorithm.crossover.Crossover;
import geneticalgorithm.crossover.UniformCrossover;
import geneticalgorithm.mutation.Mutation;
import geneticalgorithm.mutation.MutationImpl;
import geneticalgorithm.selection.Selection;
import geneticalgorithm.selection.TournamentSelection;
import io.StandardOutputWriter;

import java.util.ArrayList;
import java.util.List;

public class GeneticAlgorithm
{
    private Population population;
    private Population restartPopulation;
    private Mutation mutation;
    private Crossover crossover;
    private Selection selection;
    private Population generationElites;
    private List<Double> bestFitnessOfGeneration;
    private int generation;
    private int epoch;

    public GeneticAlgorithm()
    {
        this.selection = new TournamentSelection();
        this.crossover = new UniformCrossover();
        this.mutation = new MutationImpl();
        this.population = new Population();
        this.population.createRandom();
        this.restartPopulation = new Population(this.population);
        this.generationElites = new Population();
        this.bestFitnessOfGeneration = new ArrayList<>();
        this.generation = 1;
        this.epoch = 1;
    }

    public Individual solve()
    {
        while(this.population.getBestIndividual().getConflictCount() > 0)
        {
            evolve();
            this.bestFitnessOfGeneration.add(this.population.getBestIndividual().getFitness());
            if(this.bestFitnessOfGeneration.size() > Constants.POPULATIONS_BEFORE_RESTART &&
                this.population.getBestIndividual().getFitness()
                        <= this.bestFitnessOfGeneration.get(this.bestFitnessOfGeneration.size() - Constants.POPULATIONS_BEFORE_RESTART))
            {
                restart();
            }
        }
        return this.population.getBestIndividual();
    }

    private void evolve()
    {
        Population nextGeneration = new Population();
        nextGeneration.addIndividuals(this.population.getElites());
        addDescendants(nextGeneration);
        this.population = nextGeneration;
        this.generation++;
    }

    private void addDescendants(Population nextGeneration)
    {
        int currentPopulationSize = Constants.NUMBER_OF_ELITES;
        for (Individual individual : this.population)
        {
            this.crossover.addParent(individual);
            while (this.crossover.needsParent())
            {
                this.crossover.addParent(this.selection.selectParent(this.population));
            }
            nextGeneration.addIndividual(this.mutation.mutate(this.crossover.cross()));
            if (++currentPopulationSize == Constants.POPULATION_SIZE)
                break;
        }
    }

        private void restart()
        {
            StandardOutputWriter.printStatus(this.population.getBestIndividual(), this.epoch, this.generation, this.population.getAvgFitness(), this.population.getAvgConflicts());
            if (this.generationElites.isFull())
            {
                this.restartPopulation = new Population(this.generationElites);
                this.generationElites = new Population();
            }
            else
            {
                this.generationElites.addIndividuals(this.population.getElites(Constants.POPULATION_SIZE / Constants.POPULATIONS_BEFORE_RESTART));
            }
            this.population = new Population(this.restartPopulation);
            this.bestFitnessOfGeneration = new ArrayList<>();
            this.epoch++;
            this.generation = 1;
        }
}
