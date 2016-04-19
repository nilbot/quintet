package presentation;

import com.google.gson.Gson;
import model.Project;
import model.Student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** TODO DON'T EXTEND ME OR COPY PASTE ME, YOU R DOING IT WRONG, GOOGLE PROPER
 *  TODO SERIALIZATION EXAMPLE
 * StupidBruteForceResult
 */
public class StupidBruteForceResult implements Result{
    private final int cost;
    private Map<Student,Project> plan;

    public StupidBruteForceResult(int cost, Map<Student,Project> arrangement) {
        this.cost = cost;
        int len = arrangement.size();
        plan = new HashMap<>();
        if (arrangement != null) {
            plan = arrangement;
        }
    }
    @Override
    public String toString() {
        return String();
    }

    @Override
    public Gson JSON() {
        return null;
    }

    @Override
    public String String() {
        return "\"cost\": " + this.cost + ", \"plan\": {" + serialize(plan) +
                "\n}";
    }

    private String serialize(Map<Student, Project> plan) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Student,Project> key : plan.entrySet()) {
            Project p = key.getValue();
            sb.append("\"" + key.getKey().getStudentName() + "\": { \"");
            sb.append(p);
            sb.append("\",");
        }
        return sb.toString();
    }
}
