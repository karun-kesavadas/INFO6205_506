package geneticalgorithm.crossover;

import geneticalgorithm.Individual;

public interface Crossover
{
    void addParent(Individual individual);

    boolean needsParent();

    Individual cross();
}
