package logic;

public interface GAData {
    int getPopulationSize();
    int initPopulation(int n);
    int removeWeakest();
    }