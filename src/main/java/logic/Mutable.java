package logic;

import model.GeneticCandidateSolution;

public interface Mutable {
    GeneticCandidateSolution mutate(GeneticCandidateSolution c);
    void changeMutationChance(double new_chance);
}
