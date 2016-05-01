package config;

import config.Mutagen;

/**
 * CombineConfig:
 */
public class CombineConfig {
    private Mutagen muta;
    public int Class;
    public static final int RANDOM = 0;
    public static final int ELITE = 1;
    public static final int NOBLE = 2;
    public static final int MIDDLE = 4;
    public static final int POOR = 8;
    public int Strategy;
    public static final int CROSSOVER = 2 << 4;
    public static final int SIFT = 4 << 4;
    private boolean mutation = false;

    public CombineConfig(int socialClass, int strategy){
        if (socialClass<RANDOM || socialClass > POOR) {
            socialClass = RANDOM;
        }
        Class = socialClass;
        if (strategy<CROSSOVER || strategy > SIFT){
            strategy = CROSSOVER;
        }
        Strategy = strategy;
    }
    public CombineConfig(int socialClass, int strategy, Mutagen mutagen) {
        this(socialClass,strategy);
        if (mutagen != null) {
            this.muta = mutagen;
            this.mutation = true;
        }
    }
    public Mutagen Mutagen() {return muta;}
    public boolean allowMutation() {return mutation;}
}
