package presentation;

import model.CandidateAssignment;
import model.Project;
import model.Student;

import java.util.*;
import java.util.Map.*;

/**
 * StupidBruteForceResult
 */
public class StupidBruteForceResult extends AbstractResult implements Result {
    private final int energyScore;
    private double fitness;
    private Vector<CandidateAssignment> assignments;

    public StupidBruteForceResult(int energyScore, Map<Student,Project> map) {
        solvingStrategy = "Brute Force";
        this.energyScore = energyScore;
        assignments = new Vector<>();
        if (map != null) {
            for (Entry<Student,Project> e : map.entrySet()) {
                CandidateAssignment a = new CandidateAssignment(e.getKey(), e
                        .getValue());
                assignments.add(a);
            }
        }
    }
    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        fitness = 1 / (double) energyScore;
        return GSON.toJson(this, StupidBruteForceResult.class);
    }

    @Override
    public int energyScore() {
        return energyScore;
    }
}
