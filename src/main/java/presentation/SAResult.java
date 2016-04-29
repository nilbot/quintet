package presentation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.CandidateAssignment;
import model.ImprovableCandidateSolution;
import model.DataSource;

import java.util.Vector;

/**
 * SAResult:
 */
public class SAResult extends GsonSerialzable {
    private final Vector<CandidateAssignment> pairs;
    private double score;

    public SAResult(ImprovableCandidateSolution singleton) {
        pairs = new Vector<>();
        for (CandidateAssignment assignment : singleton.listOfAssignments()) {
            pairs.add(assignment);
        }
    }

    @Override
    public String toString() {
        return toJson();
    }

    @Override
    public String toJson() {
        return GSON.toJson(this, SAResult.class);
    }
}
