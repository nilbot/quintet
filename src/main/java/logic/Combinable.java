package logic;

import config.CombineConfig;
import data.GenePool;
import model.GeneticCandidateSolution;

/**
 * Combinable:
 */
public interface Combinable {
    GenePool combine(CombineConfig cfg, GenePool<GeneticCandidateSolution> data, int children);
}
