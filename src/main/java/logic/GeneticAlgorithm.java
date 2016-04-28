package logic;

import config.CullConfig;
import config.EvolutionConfig;
import data.GenePool;
import model.GeneticCandidateSolution;

/**
 * GeneticAlgorithm:
 */
public class GeneticAlgorithm {
    private final GenePool<GeneticCandidateSolution> genepool;
    private final EvolutionConfig config;
    private Cullable cullImplementor;
    private Combinable combineImplementor;

    public GeneticAlgorithm(EvolutionConfig cfg) {
        this.genepool = new GenePool<>();
        this.config = cfg;
        this.cullImplementor = new Cull();
        this.combineImplementor = new Combine();

    }

    public GeneticCandidateSolution templateSolve() {
        populationInitialSeeding();
        for (int i = 0; i < config.generationMax(); i++){
            cullImplementor.cull(config.cullConfig, this.genepool);
            combineImplementor.combine(config.combineConfig,this.genepool);
        }
        return this.genepool.getBest();
    }

    // do some initial step to stem up the population. thrive my minions, i
    // grant you 3 years of time to snag without natural predator
    private void populationInitialSeeding() {
        //TODO
    }
}
