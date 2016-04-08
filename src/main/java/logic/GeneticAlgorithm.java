package logic;

import java.util.Comparator;
import java.util.PriorityQueue;
import model.DataSource;
import model.Project;
import model.Student;
import presentation.Result;

import java.util.*;

public class GeneticAlgorithm implements Solver {
    private Map<Integer,Project>  projectPermute;
    private Map<Integer,Student> studentPermute;
    private Map<String,Student> studentPool;
    List<Student> casualties = new ArrayList<>();
    List<Student> survivors = new ArrayList<>();

    public GeneticAlgorithm(){
        this.projectPermute = new HashMap<>();
        this.studentPermute = new HashMap<>();
        this.studentPool = new HashMap<>();
    }

    // the strongest members of a population are more likely to survive, by
    // virtue of their fitness. however! it is not certain. even the weakest
    // members of a population have a tiny chance that they will survive.
    // conversely, a healthy specimen close to max fitness can sometimes die,
    // and less fit specimens can sometimes live and breed.
    //
    // calculating fitness (and culling) can be done with:
    //   - a priority queue + comparator, then culling the lowest ordered (or
    //   other variations on this theme)
    //   - a specific fitness function that returns the relative fitness of
    //   a student against the rest of the other objects in the set. if the
    //   student is not fit enough, they are dropped from the collection
    private List<Student> cull(List<Student> students) {
        int survivalChance = 0;

        // create a student comparator to act as a temporary fitness function
        Comparator<Student> comparator = new FitnessComparator();

        // create a priority queue to hold a collection of sorted students
        PriorityQueue<Student> orderedStudents = new PriorityQueue<>(students.size(), comparator);

        // add all students to the priority queue
        for (Student s : students) {
            orderedStudents.add(s);
        }

        // strategy 0:
        // cull students based on their order in the priority queue
        for (int i = 0; i < students.size(); i++) {
            Student curr = orderedStudents.poll();
            survivalChance = (100 / students.size()) * i;

            // only keep the top 80% of student solutions
            if (survivalChance > 20) {
                survivors.add(curr);
            } else {
                casualties.add(curr);
            }
        }

        // strategy 1:
        // cull students based on a fitness function - not implemented!
        // note: an iterator returned from a priority queue does not guarantee
        // ordered traversal!
        Iterator<Student> it = orderedStudents.iterator();

        return survivors;
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
        // blatantly using Nil's code
        int poolSize = studentPool.size();
        int pondSize = projectPermute.size();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            students.add(new ArrayList<>(studentPool.values()).get(i));
        }

        Set<Integer> projects = projectPermute.keySet();
        cull(students);

        // there is no presentation class for genetic algorithmss yet,
        // so I can't return a proper result
        System.out.println("=========================");
        System.out.println("Battle report - Survivors");
        System.out.println("=========================");
        for (Student s: survivors) {
            System.out.println(s.getStudentName());
        }
        System.out.println("==========================");
        System.out.println("Battle report - Casualties");
        System.out.println("==========================");
        for (Student s: casualties) {
            System.out.println(s.getStudentName());
        }

        return null;
    }
}

class FitnessComparator implements Comparator<Student> {
    // the fitness function atm is totally useless and doesn't return any
    // sensible fitness rating, but I just need the comparator to return
    // *some* value, so, currently, the more preferences a student has,
    // the higher their fitness! :)
    public int compare(Student x, Student y) {
        if (x.getNumberOfPreferences() < y.getNumberOfPreferences()) {
            return -1;
        }
        if (x.getNumberOfPreferences() > y.getNumberOfPreferences()) {
            return 1;
        }
        return 0;
    }
}