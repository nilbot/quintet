package logic;

import data.GenePool;

/**
 * GeneticAlgorithm:
 */
public class GeneticAlgorithm {
    private static final int max = 3000000;

    private final GenePool<GeneticCandidateSolution> genepool;
    private final Object cullConfig;
    //TODO require interface code pulled
//    private final Cull cullImplementor;
//    private final Combine combineImplementor;
    private final Object combineConfig;
    private final EvolutionConfig evolutionConfig;

    public GeneticAlgorithm() {
        this.genepool = new GenePool<GeneticCandidateSolution>();
        // note that all these are dependencies, the following lines are demo
        // only, don't copy paste
        this.cullConfig = new Object(); // cullconfig injection
        this.combineConfig = new Object();
        this.evolutionConfig = new EvolutionConfig();
        evolutionConfig.setStepMax(max);
        //TODO pull request some of the interface first please
//        this.cullImplementor = new Cull(cullConfig,genepool);
//        this.combineImplementor = new Combine(combineConfig, genepool);

    }

    public void templateSolve() {
        populationInitialSeeding();
        for (int i = 0; i < evolutionConfig.evolutionStepMax; i++){
            //TODO pull request some of the interface code please so that
            // this can compile
//            cullImplementor.cull(this.cullConfig, this.genepool);
//            combineConfig.combine(this.combineConfig,this.genepool);
        }
    }

    // do some initial step to stem up the population. thrive my minions, i
    // grant you 3 years of time to snag without natural predator
    private void populationInitialSeeding() {

    }
}
