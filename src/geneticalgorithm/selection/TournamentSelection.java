package geneticalgorithm.selection;

import config.Constants;
import geneticalgorithm.Individual;
import geneticalgorithm.Population;

public class TournamentSelection implements Selection
{
    @Override
    public Individual selectParent(Population population)
    {
        Population tournament = new Population();
        population.shuffle();
        for(int i=0; i < Constants.TOURNAMENT_SIZE; i++)
        {
            tournament.addIndividual(population.getIndividual(i));
        }
        return tournament.getBestIndividual();
    }
}
