package logic;

import config.EvolutionConfig;
import data.GenePool;
import model.DataSource;
import model.GeneticCandidateSolution;
import model.Student;
import presentation.GAResult;
import presentation.Result;

import java.util.Collection;

/**
 * GeneticAlgorithm:
 */
public class GeneticAlgorithm implements Solver{
    private final GenePool<GeneticCandidateSolution> genePool;
    private final EvolutionConfig config;
    private Cullable cullImplementor;
    private Combinable combineImplementor;
    private Collection<Student> src;

    public GeneticAlgorithm(EvolutionConfig cfg) {
        this.genePool = new GenePool<>();
        this.config = cfg;
        this.cullImplementor = new Cull();
        this.combineImplementor = new Combine();

    }

    public GeneticCandidateSolution templateSolve() {
        populationInitialSeeding(config.populationSize());
        for (int i = 0; i < config.generationMax(); i++){
            int beforecull = this.genePool.size();
            cullImplementor.cull(config.cullConfig, this.genePool);
            int afterCull = this.genePool.size();
            combineImplementor.combine(config.combineConfig,this.genePool,
                    config.cullConfig.getCullAmount());
            int afterCombine = this.genePool.size();
            if (afterCombine != beforecull) {
                throw new IllegalStateException("The population is not " +
                        "stable"+String.format(" before cull %d, before " +
                        "combine %d, after combine %d",beforecull, afterCull,
                        afterCombine));
            }
        }
        return this.genePool.getBest();
    }

    // do some initial step to stem up the population. thrive my minions, i
    // grant you 3 years of time to snag without natural predator
    private void populationInitialSeeding(int todo) {
        while (this.genePool.size() < todo) {
            this.genePool.addToPool(new GeneticCandidateSolution(src));
        }
        if (this.genePool.size() != config.populationSize()) {
            throw new RuntimeException("population init failed to init the " +
                    "required the size" + String.format(" gen %d, want %d",
                    this.genePool.size(), config.populationSize()));
        }
    }

    @Override
    public void InjectData(DataSource repo) {
        this.src = repo.StudentRepo().values();
    }

    @Override
    public Result Solve() {
        GeneticCandidateSolution rst = templateSolve();
        return new GAResult(rst, config.generationMax());
    }
}
