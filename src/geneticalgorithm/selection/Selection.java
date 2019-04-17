package geneticalgorithm.selection;

import geneticalgorithm.Individual;
import geneticalgorithm.Population;

public interface Selection
{
    Individual selectParent(Population population);
}
