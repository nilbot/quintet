package config;

import logic.CombineConfig;
import logic.Virus;

/**
 * EvolutionConfig:
 */
public class EvolutionConfig {
    public final CombineConfig combineConfig;
    private final int killAmount = 5;
    private final double probablityToKillGoodGuys = 0.2;
    public final CullConfig cullConfig;
    private int generationMax;
    public EvolutionConfig() {
        this.cullConfig = new CullConfig(killAmount, probablityToKillGoodGuys);
        Mutagen mutagen = new Virus();
        this.combineConfig = new CombineConfig(CombineConfig.NOBLE,
                CombineConfig.MERGEBEST, mutagen);
    }
    public void setStepMax(int stepMax) {
        this.generationMax = stepMax;
    }
    public int generationMax() {return generationMax;}
}
