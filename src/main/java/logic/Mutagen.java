package logic;

import model.GeneticCandidateSolution;

/**
 * Mutagen:
 */
public interface Mutagen {
    GeneticCandidateSolution mutate(GeneticCandidateSolution original);
}
