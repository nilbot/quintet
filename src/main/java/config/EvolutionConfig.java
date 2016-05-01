package config;

import logic.CombineConfig;
import logic.Virus;
import model.DataSource;

/**
 * EvolutionConfig:
 */
public class EvolutionConfig {
    public final CombineConfig combineConfig;
    private final int killAmount;
    private final double probablityToKillGoodGuys;
    public final CullConfig cullConfig;
    private final int generationMax;
    private final int populationSize;
    private final DataSource repo;

    public EvolutionConfig(int iters, int popSz, int killCount, double
            indiscriminateKilling, DataSource repo) {
        this.generationMax = 100;
        this.populationSize = 3000;
        this.probablityToKillGoodGuys = 0.2;
        this.killAmount = 200;
        this.repo = repo;
        this.cullConfig = new CullConfig(killAmount, probablityToKillGoodGuys);
        Mutagen mutagen = new Virus();
        this.combineConfig = new CombineConfig(CombineConfig.NOBLE,
                CombineConfig.MERGEBEST, mutagen);
    }
    public int generationMax() {return generationMax;}

    public int populationSize() {
        return populationSize;
    }

    public DataSource dataSrc() {
        return repo;
    }
}
