package presentation;

import java.util.*;
import model.*;

/**
 * InputMeta:
 */
public class InputMeta extends GsonSerialzable {
    private final String MessageType = "InputMeta";
    private final int NumberOfStudents;
    private final int NumberOfProjects;
    private final String hottestProject;
    private final List<Project> freqListSorted;
    private final Map<Project, Integer> projectFrequencies;

    public InputMeta(List<Student> ls, Set<Project> lp) {
        int ns = ls.size();
        int np = lp.size();
        NumberOfStudents = ns;
        NumberOfProjects = np;
        ///////////////////////
        // count histogram
        // step 1. count frequencies
        // for all students, (trust the projects are in the set) count
        // projects occurrences
        projectFrequencies = new HashMap<>();
        for (Student std: ls) {
            for (Project p : std.getProjects()) {
                projectFrequencies.put(p, 1 + (projectFrequencies
                        .containsKey(p) ? projectFrequencies.get(p) : 0));
            }
        }
        // step 2. sort frequency list
        freqListSorted = new ArrayList<>(projectFrequencies.keySet());
        Collections.sort(freqListSorted, new Comparator<Project>() {
            @Override
            public int compare(Project o1, Project o2) {
                return projectFrequencies.get(o2) - projectFrequencies.get(o1);
            }
        });
        hottestProject = freqListSorted.get(0).toString();

    }

    public String toJson() {
        return GSON.toJson(this, InputMeta.class);
    }

    public String getHottestProject() {
        return hottestProject;
    }
}
