package logic;

import model.Student;

import java.util.Comparator;
import java.util.List;

class CandidateSolutionForGA implements Comparator<CandidateSolutionExtended> {
    // the fitness function atm is totally useless and doesn't return any
    // sensible fitness rating, but I just need the comparator to return
    // *some* value,
    public int compare(CandidateSolutionExtended x, CandidateSolutionExtended y) {
        if (fitness(x) < fitness(y)) {
            return -1;
        }
        if (fitness(x) > fitness(y)) {
            return 1;
        }
        return 0;
    }

    // TODO: make this not return sensible values
    // the comparator has it's own fitness function
    private int fitness(CandidateSolutionExtended solution) {
        return (int) Math.floor(Math.random()) * 100;
    }
}