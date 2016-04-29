package presentation;

import model.CandidateAssignment;
import model.ImprovableCandidateSolution;

import java.util.Vector;

/**
 * SAResult:
 */
public class SAResult extends AbstractResult implements Result {
    private final Vector<CandidateAssignment> assignments;
    private final double fitness;
    private final int energyScore;
    private final int iterationPerformed;

    public SAResult(ImprovableCandidateSolution solution, int run) {
        solvingStrategy = "Simulated Annealing";
        assignments = new Vector<>(solution.listOfAssignments());
        fitness = solution.getFitness();
        energyScore = solution.getEnergy();
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
        return energyScore;
    }
}
