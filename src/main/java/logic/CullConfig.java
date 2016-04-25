package logic;

import java.util.Random;

public class CullConfig {
    private int cullAmount;
    private double cullProbability;
    private Random rand;

    // constructor initialises default CullConfig values
    public CullConfig(int cullAmount, double cullProbability) {
        this.cullAmount = cullAmount;
        this.cullProbability = cullProbability;
        rand = new Random();
    }

    public int getCullAmount() {
        return this.cullAmount;
    }

    public double getCullProbability() {
        return this.cullProbability;
    }

    public double getRNG() {
        return (this.rand.nextDouble());
    }

    // manually set the amount of solutions that should be culled
    public int setCullAmount(int n) {
        this.cullAmount = n;
        return cullAmount;
    }

    // manually set the probability range that should be culled in
    public double setCullProbability(double n) {
        this.cullProbability = n;
        return this.cullProbability;
    }

    // set the RNG (for use with testing)
    public void setCullSeed(Random rand, long seed) {
        this.rand = rand;
        this.rand.setSeed(seed);
    }
}