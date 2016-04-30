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
        updateFitness();
        return GSON.toJson(this, StupidBruteForceResult.class);
    }

    private void updateFitness() {
        fitness = 1 / (double) energyScore;
    }

    @Override
    public String getMeta() {
        updateFitness();
        StringBuilder sb = new StringBuilder("{\n");
        sb.append("\tenergyScore: "+energyScore()+",\n");
        sb.append("\tfitness: "+fitness+",\n");
        sb.append("\tsolvingStrategy: "+solvingStrategy+",\n");
        sb.append("}\n");
        return sb.toString();
    }

    public int energyScore() {
        return energyScore;
    }
}
