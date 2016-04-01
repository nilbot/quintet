package logic;

import presentation.StupidBruteForceResult;
import model.DataSource;
import model.Project;
import model.Student;
import presentation.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * logic.BruteForce feasibility
 */
public class BruteForce implements Solver{
    private Map<Integer,Project>  projectPermute;
    private Map<Integer,Student> studentPermute;
    private Map<String,Student> studentPool;

    public BruteForce(){
        this.projectPermute = new HashMap<>();
        this.studentPermute = new HashMap<>();
        this.studentPool = new HashMap<>();
    }

    public ArrayList<ArrayList<Integer>>
    permute(Integer[] projects, Integer limit) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<>();
        if (projects == null || projects.length == 0) {
            return rst;
        }
        ArrayList<Integer> list = new ArrayList<>();
        helper(rst, list, projects, limit);
        return rst;
    }

    private void
    helper(ArrayList<ArrayList<Integer>> rst,
                               ArrayList<Integer> list,
                               Integer[] projectIds, Integer limit) {
        if (list.size() == limit) {
            rst.add(new ArrayList<>(list));
            return;
        }

        for (int i = 0; i < projectIds.length; i++) {
            if (list.contains(projectIds[i])) {
                continue;
            }
            list.add(projectIds[i]);
            helper(rst, list, projectIds, limit);
            list.remove(list.size() - 1);
        }
    }

    // allocate projects to student, the index of students are fix, ofc you can
    // reverse the relation and build backwards
    private int
    cost(List<Integer> allocation, List<Student> students) {
        if (allocation == null || allocation.size() == 0) {
            return Integer.MAX_VALUE;
        }
        int score = 0;
        for (int i = 0; i < students.size(); i++) {
            // WORKAROUND, ROOT CAUSE NOT FOUND (HAD AN IDEA, TOO SLEEPY)
            Project p = projectPermute.get(i);
            if (p == null) {
                p = new Project("NON EXISTS");
            }
            score += students.get(i).getRanking(p);
        }
        return score;
    }

    private StupidBruteForceResult
    minCost(ArrayList<ArrayList<Integer>> states, List<Student> students) {
        int min = Integer.MAX_VALUE;
        List<Integer> best = null;
        for (List<Integer> state : states) {
            int score = cost(state, students);
            if (min > score) {
                min = score;
                best = state;
            }
        }
        int len = best.size();
        HashMap<Student, Project> allocation = new HashMap<>();
        for (int i = 0; i < len; i++) {
            allocation.put(studentPermute.get(i), projectPermute.get(best.get
                    (i)));
        }
        return new StupidBruteForceResult(min, allocation);
    }

    @Override
    public void InjectData(DataSource repo) {
        this.projectPermute.clear();
        this.studentPermute.clear();
        this.studentPool.clear();
        int idx = 0;

        try {
            for (Project p : repo.ProjectRepo()) {
                projectPermute.put(idx++,p);
            }

            studentPool = repo.StudentRepo();

            idx = 0;
            for (Student s : repo.StudentRepo().values()) {
                studentPermute.put(idx++,s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result Solve() {
        int poolSize = studentPool.size();
        int pondSize = projectPermute.size();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < poolSize; i++) {
            students.add(new ArrayList<>(studentPool.values()).get(i));
        }
        Integer[] ia = new Integer[pondSize];
        ArrayList<ArrayList<Integer>> states =
                permute(projectPermute.keySet().toArray(ia), students.size());
        return minCost(states,students);
    }
}
