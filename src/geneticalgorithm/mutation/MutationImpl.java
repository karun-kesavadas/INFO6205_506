package geneticalgorithm.mutation;

import config.Constants;
import geneticalgorithm.Individual;

public class MutationImpl implements Mutation
{
    @Override
    public Individual mutate(Individual individual)
    {
        for(int geneIndex = 0; geneIndex < individual.getChromosome().length ; geneIndex++)
        {
            if(Constants.MUTATION_RATE > Math.random())
            {
                individual.insertRandomValue(geneIndex);
            }
        }
        return individual;
    }
}
