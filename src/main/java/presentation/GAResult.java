package presentation;

import com.google.gson.Gson;
import logic.CandidateAssignment;
import logic.CandidateSolution;
import logic.CandidateSolutionForGA;

import java.util.ArrayList;
import java.util.List;

public class GAResult implements Result {
    List<CandidateSolutionForGA> ca;

    public GAResult(List<CandidateSolutionForGA> ca) {
        this.ca = ca;
    }

    @Override
    public Gson JSON() {
        return null;
    }

    @Override
    public String String() {
        return "===================================\n" +
                "Battle report - Surviving solutions\n" +
                "===================================\n" +
                serialize(ca) +
                "Count: " + ca.size();
    }

    private String serialize(List<CandidateSolutionForGA> ca) {
        StringBuilder sb = new StringBuilder();
        for (CandidateSolutionForGA c: ca) {
            sb.append(c.toString()  + "\tFitness: " + c.getFitness() + "\n");
        }
        return sb.toString();
    }
}
