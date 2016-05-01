package presentation;

import model.CandidateAssignment;
import model.GeneticCandidateSolution;
import model.ImprovableCandidateSolution;

import java.util.Vector;

/**
 * GAResult:
 */
public class GAResult extends AbstractResult implements Result {
    public GAResult(GeneticCandidateSolution rst, int run) {
        assignments = new Vector<>(rst.listOfAssignments());
        solvingStrategy = "Genetic Algorithm";
        fitness = rst.getFitness();
        energyScore = rst.getEnergy();
        iterationPerformed = run;
    }

    private final Vector<CandidateAssignment> assignments;
    private final double fitness;
    private final int energyScore;
    private final int iterationPerformed;


    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        return GSON.toJson(this, GAResult.class);
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
