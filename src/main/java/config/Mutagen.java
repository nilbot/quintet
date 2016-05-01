package config;

import model.GeneticCandidateSolution;

/**
 * Mutagen:
 */
public interface Mutagen {
    GeneticCandidateSolution mutate(GeneticCandidateSolution original, double
            mutatioinProbability);
}
