package presentation;

import model.CandidateAssignment;
import model.ImprovableCandidateSolution;

import java.util.Vector;

/**
 * SAResult:
 */
public class SAResult extends GsonSerialzable implements Result {
    private final Vector<CandidateAssignment> assignments;
    private final double fitness;
    private final int energy;
    private final int iterationPerformed;

    public SAResult(ImprovableCandidateSolution solution, int run) {

        assignments = new Vector<>(solution.listOfAssignments());
        fitness = solution.getFitness();
        energy = solution.getEnergy();
        iterationPerformed = run;
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        return GSON.toJson(this, SAResult.class);
    }

    @Override
    public int energyScore() {
        return energy;
    }
}
