package config;

import logic.Virus;
import model.DataSource;

/**
 * EvolutionConfig:
 */
public class EvolutionConfig {
    public final CombineConfig combineConfig;
    public final CullConfig cullConfig;
    private final int generationMax;
    private final int populationSize;

    public EvolutionConfig(int iters, int popSz, CullConfig cullcfg, CombineConfig
            combinecfg) {
        this.generationMax = iters;
        this.populationSize = popSz;
        this.cullConfig = cullcfg;
        this.combineConfig = combinecfg;
    }
    public int generationMax() {return generationMax;}

    public int populationSize() {
        return populationSize;
    }

}
