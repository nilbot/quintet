package logic;

import java.util.Comparator;

public class GeneticComparator implements Comparator<CandidateSolutionForGA> {
    public int compare(CandidateSolutionForGA x, CandidateSolutionForGA y) {
        if (x.getFitness() < y.getFitness()) {
            return -1;
        }
        if (x.getFitness() > y.getFitness()) {
            return 1;
        }
        return 0;
    }
}