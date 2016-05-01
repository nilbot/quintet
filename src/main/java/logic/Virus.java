package logic;

import config.Mutagen;
import model.GeneticCandidateSolution;

import static model.service.UtilityService.TheRNG;

/**
 * Virus:
 */
public class Virus implements Mutagen {
    @Override
    public GeneticCandidateSolution mutate(GeneticCandidateSolution original,
     double probability) {
        if (TheRNG().nextDouble() < probability)
            original.getRandomAssignment().randomizeAssignment();
        return original;
    }
}
