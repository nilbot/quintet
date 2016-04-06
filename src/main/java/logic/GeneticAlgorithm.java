package logic;

import model.DataSource;
import model.Project;
import model.Student;
import presentation.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneticAlgorithm implements Solver {
    private Map<Integer,Project>  projectPermute;
    private Map<Integer,Student> studentPermute;
    private Map<String,Student> studentPool;

    public GeneticAlgorithm(){
        this.projectPermute = new HashMap<>();
        this.studentPermute = new HashMap<>();
        this.studentPool = new HashMap<>();
    }

    // allocate projects to student, the index of students are fix, ofc you can
    // reverse the relation and build backwards
    private int
    cost(List<Integer> allocation, List<Student> students) {
        /*
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
        */
        return 0;
    }

    // the strongest members of a CandidateSolution population are more
    // likely to survive - but it is not certain. the decision is based
    // on statistics. even the weakest members of a population have a tiny
    // chance that they will survive
    // TODO: discuss below points
    // - in the wild a healthy specimen (in our case, a candidate solution
    // close to MAX_FITNESS) can sometimes die, and an unhealthy
    // specimen can sometimes live
    private void cull() {
        /*
        public List<CandidateSolution> cull() {

            for each CandidateSolution c
                    fitness = c.getFitness()
            if fitness != MAX_FITNESS {
                chanceOfSurvival = fitness / MAX_FITNESS
                rand = Math.rand()
                if math.Rand() > chanceOfSurvival {
                    list.remove(c)
                }
            }
        }
        */

        // just messing around
        for (Map.Entry<Integer, Project> e : projectPermute.entrySet()) {
            System.out.println(e.getKey().toString() + e.getValue());
        }
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

    // make a simple permute
    public ArrayList<ArrayList<Integer>>
    permute(Integer[] projects, Integer limit) {
        /*
        ArrayList<ArrayList<Integer>> rst = new ArrayList<>();
        if (projects == null || projects.length == 0) {
            return rst;
        }
        ArrayList<Integer> list = new ArrayList<>();
        helper(rst, list, projects, limit);
        return rst;
        */
        return new ArrayList<>();
    }

    private void
    helper(ArrayList<ArrayList<Integer>> rst,
           ArrayList<Integer> list,
           Integer[] projectIds, Integer limit) {
        /*
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
        */

    }

    @Override
    public Result Solve() {
        cull();
        return null;
    }
}
