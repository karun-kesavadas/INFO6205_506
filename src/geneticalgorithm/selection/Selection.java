package geneticalgorithm.selection;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;

public interface Selection
{
    public Individual selectParent(Population population);
}
