package logic;

import java.util.List;

public class GADataTestMock implements GAData {
    private int population;

    @Override
    public int getPopulationSize() {
        return population;
    }

    public int initPopulation(int n) {
        this.population = n;
        return n;
    }

    @Override
    public int removeWeakest() {
        population = population - 1;
        return population;
    }
}