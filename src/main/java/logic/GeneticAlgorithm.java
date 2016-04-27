package logic;

import config.CullConfig;
import config.EvolutionConfig;
import data.GenePool;
import model.GeneticCandidateSolution;

/**
 * GeneticAlgorithm:
 */
public class GeneticAlgorithm {
    private static final int max = 3000000;

    private final GenePool<GeneticCandidateSolution> genepool;
    private final CullConfig cullConfig;
    private final EvolutionConfig config;
    private Cullable cullImplementor;
//    private final Combine combineImplementor;
    private final Object combineConfig;
    private final int killAmount = 5;
    private final double probablityToKillGoodGuys = 0.2;

    public GeneticAlgorithm(EvolutionConfig cfg) {
        this.genepool = new GenePool<GeneticCandidateSolution>();

        this.cullConfig = new CullConfig(killAmount, probablityToKillGoodGuys);
        // cullconfig
        // injection
        this.combineConfig = new Object();
        this.config = cfg;
        //TODO pull request some of the interface first please
        this.cullImplementor = new Cull();
//        this.combineImplementor = new Combine(combineConfig, genepool);

    }

    public GeneticCandidateSolution templateSolve() {
        populationInitialSeeding();
        for (int i = 0; i < config.evolutionStepMax; i++){
            cullImplementor.cull(this.cullConfig, this.genepool);
//            combineConfig.combine(this.combineConfig,this.genepool);
        }
        return this.genepool.getBest();
    }

    // do some initial step to stem up the population. thrive my minions, i
    // grant you 3 years of time to snag without natural predator
    private void populationInitialSeeding() {
        //TODO
    }
}
