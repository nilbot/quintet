package presentation;

import com.google.gson.Gson;
import logic.ImprovableCandidateSolution;
import model.DataSource;

import java.util.Vector;

/**
 * SAResult:
 */
public class SAResult implements Result {
    private final Vector<AssignmentPair> pairs;

    public SAResult(ImprovableCandidateSolution singleton, DataSource repo) {
        pairs = new Vector<>();
        try {
            for (String stdName : repo.StudentRepo().keySet()){
                pairs.add(new AssignmentPair(singleton.getAssignmentFor
                        (stdName)));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Gson JSON() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String String() {
        return pairs.toString();
    }
}
