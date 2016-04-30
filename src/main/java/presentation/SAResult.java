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
    public String getMeta() {
        StringBuilder sb = new StringBuilder("{\n");
        sb.append("\tenergyScore: "+energyScore()+",\n");
        sb.append("\tfitness: "+fitness+",\n");
        sb.append("\titerationPerformed: "+iterationPerformed+",\n");
        sb.append("\tsolvingStrategy: "+solvingStrategy+",\n");
        sb.append("}\n");
        return sb.toString();
    }

    public int energyScore() {
        return energyScore;
    }
}
