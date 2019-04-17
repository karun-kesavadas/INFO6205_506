package geneticalgorithm.crossover;

import geneticalgorithm.Individual;

public interface Crossover
{
    public void addParent(Individual individual);

    public boolean needsParent();

    public Individual cross();
}
