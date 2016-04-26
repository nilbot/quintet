package logic;

import static model.service.UtilityService.TheRNG;

public class CullConfig {
    private int cullAmount;
    private double cullProbability;

    // constructor initialises default CullConfig values
    public CullConfig(int cullAmount, double cullProbability) {
        this.cullAmount = cullAmount;
        this.cullProbability = cullProbability;
    }

    public int getCullAmount() {
        return this.cullAmount;
    }

    public double getCullProbability() {
        return this.cullProbability;
    }

    public double getRandom() {
        return (TheRNG().nextDouble());
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
}