package presentation;

import com.google.gson.Gson;
import model.Project;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * StupidBruteForceResult
 */
public class StupidBruteForceResult extends GsonSerialzable {
    private final int cost;
    private Map<Student,Project> plan;

    public StupidBruteForceResult(int cost, Map<Student,Project> arrangement) {
        this.cost = cost;
        plan = new HashMap<>();
        if (arrangement != null) {
            plan = arrangement;
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
}
