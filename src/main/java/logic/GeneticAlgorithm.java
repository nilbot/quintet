package logic;

import config.EvolutionConfig;
import data.GenePool;
import model.DataSource;
import model.GeneticCandidateSolution;
import model.Student;

import java.util.Collection;

/**
 * GeneticAlgorithm:
 */
public class GeneticAlgorithm {
    private final GenePool<GeneticCandidateSolution> genePool;
    private final EvolutionConfig config;
    private Cullable cullImplementor;
    private Combinable combineImplementor;
    private final Collection<Student> src;

    public GeneticAlgorithm(EvolutionConfig cfg) {
        this.genePool = new GenePool<>();
        this.src = cfg.dataSrc().StudentRepo().values();
        this.config = cfg;
        this.cullImplementor = new Cull();
        this.combineImplementor = new Combine();

    }

    public GeneticCandidateSolution templateSolve() {
        populationInitialSeeding(config.populationSize());
        for (int i = 0; i < config.generationMax(); i++){
            cullImplementor.cull(config.cullConfig, this.genePool);
            int sz = this.genePool.size();
            combineImplementor.combine(config.combineConfig,this.genePool,
                    config.cullConfig.getCullAmount());
            int szp = this.genePool.size();
            if (szp != sz) {
                throw new IllegalStateException("The population is not stable");
            }
        }
        return this.genePool.getBest();
    }

    // do some initial step to stem up the population. thrive my minions, i
    // grant you 3 years of time to snag without natural predator
    private void populationInitialSeeding(int todo) {
        while (todo-- >= 0) {
            this.genePool.addToPool(new GeneticCandidateSolution(src));
        }
        if (this.genePool.size() != config.populationSize()) {
            throw new RuntimeException("population init failed to init the " +
                    "required the size");
        }
    }
}
