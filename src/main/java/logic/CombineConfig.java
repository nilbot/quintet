package logic;

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
    public static final int MATRIARCH = 1 << 4;
    public static final int MERGEBEST = 2 << 4;
    public static final int PRIORITISE = 4 << 4;
    private boolean mutation = false;

    public CombineConfig(int socialClass, int strategy){
        if (socialClass<RANDOM || socialClass > POOR) {
            socialClass = 0;
        }
        Class = socialClass;
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
