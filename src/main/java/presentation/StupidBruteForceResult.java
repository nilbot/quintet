package presentation;

import model.CandidateAssignment;
import model.Project;
import model.Student;

import java.util.*;
import java.util.Map.*;

/**
 * StupidBruteForceResult
 */
public class StupidBruteForceResult extends GsonSerialzable implements Result {
    private final int cost;
    private Vector<CandidateAssignment> assignments;

    public StupidBruteForceResult(int cost, Map<Student,Project> map) {
        this.cost = cost;
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
        return GSON.toJson(this, StupidBruteForceResult.class);
    }

    @Override
    public int energyScore() {
        return cost;
    }
}
