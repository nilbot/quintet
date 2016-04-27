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
    private Combinable combineImplementor;
    private final CombineConfig combineConfig;
    private final int killAmount = 5;
    private final double probablityToKillGoodGuys = 0.2;

    public GeneticAlgorithm(EvolutionConfig cfg) {
        this.genepool = new GenePool<>();
        this.cullConfig = new CullConfig(killAmount, probablityToKillGoodGuys);
        Mutagen mutagen = new Virus();
        this.combineConfig = new CombineConfig(CombineConfig.NOBLE,
                CombineConfig.MERGEBEST, mutagen);
        this.config = cfg;
        this.cullImplementor = new Cull();
        this.combineImplementor = new Combine();

    }

    public GeneticCandidateSolution templateSolve() {
        populationInitialSeeding();
        for (int i = 0; i < config.evolutionStepMax; i++){
            cullImplementor.cull(this.cullConfig, this.genepool);
            combineImplementor.combine(this.combineConfig,this.genepool);
        }
        return this.genepool.getBest();
    }

    // do some initial step to stem up the population. thrive my minions, i
    // grant you 3 years of time to snag without natural predator
    private void populationInitialSeeding() {
        //TODO
    }
}
