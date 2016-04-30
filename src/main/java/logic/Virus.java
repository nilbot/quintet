package logic;

import config.Mutagen;
import model.GeneticCandidateSolution;

/**
 * Virus:
 */
public class Virus implements Mutagen {
    @Override
    public GeneticCandidateSolution mutate(GeneticCandidateSolution original) {
        original.getRandomAssignment().randomizeAssignment();
        return original;
    }
}
