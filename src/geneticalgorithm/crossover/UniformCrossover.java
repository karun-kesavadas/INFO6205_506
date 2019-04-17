package geneticalgorithm.crossover;

import config.Constants;
import geneticalgorithm.Individual;

public class UniformCrossover implements Crossover
{
    private Individual child;
    private Individual[] parents;
    private int parentIndex;

    public UniformCrossover()
    {
        child = new Individual();
        this.parents = new Individual[Constants.NUMBER_OF_PARENTS];
        this.parentIndex = 0;
    }

    @Override
    public void addParent(Individual individual)
    {
        if(individual==null)
            throw new IllegalArgumentException("Parent cannot be null");
        this.parents[this.parentIndex++]=individual;
    }

    @Override
    public boolean needsParent()
    {
        return this.parentIndex<Constants.NUMBER_OF_PARENTS;
    }

    @Override
    public Individual cross()
    {
        if(this.needsParent())
            throw new RuntimeException("Insufficient number of parents for crossover");
        for(int i = 0; i < this.child.getChromosome().length ; i++)
        {
            int parentIndex = (int) (Math.random() * this.parents.length);
            this.child.setChromosome(i , this.parents[parentIndex].getChromosomeFromIndex(i));
        }
        this.parentIndex = 0;
        return child;
    }
}
