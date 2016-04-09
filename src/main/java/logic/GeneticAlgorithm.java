package logic;

import data.InMemoryRepo;
import java.util.Comparator;
import java.util.PriorityQueue;
import model.DataSource;
import model.Project;
import model.Student;
import presentation.Result;

import java.util.*;

public class GeneticAlgorithm implements Solver {
    private List<List<Student>> population;
    private Map<Integer,Project>  projects;
    private InMemoryRepo repo;
    private Map<Integer,Student> students;
    private Map<String,Student> studentRepo;
    private static int MAX_POPULATION = 10;

    public GeneticAlgorithm(){
        this.population = new ArrayList<>();
        this.projects = new HashMap<>();
        this.students = new HashMap<>();
        this.studentRepo = new HashMap<>();
    }

    // create an initial population consisting of random solutions
    private void createInitialPopulation() {
        for (int i = 0; i < MAX_POPULATION; i++) {
            List<Student> rand = createRandomSolution();
            this.population.add(rand);
        }
    }

    private List<Student> createRandomSolution() {
        List<Student> randomSolution = new ArrayList<>();
        Set<Integer> studentKeySet = students.keySet();

        for (Integer k : studentKeySet) {
            randomSolution.add(createRandomAssignment(students.get(k)));
        }
        return randomSolution;
    }

    private Student createRandomAssignment(Student s) {
        Project randomPref;

        while (s.getNumberOfPreferences() < s.getNumberOfStatedPreferences()) {
            randomPref = this.repo.getRandomPreference();
            if (!s.hasPreference(randomPref)) {
                s.addProject(randomPref);
            }
        }
        return s;
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
    private List<List<Student>> cull(List<List<Student>> population) {
        List<List<Student>> casualties = new ArrayList<>();
        List<List<Student>> survivors = new ArrayList<>();

        int survivalChance = 0;

        // create a student comparator to act as a temporary fitness function
        Comparator<List<Student>> comparator = new FitnessComparator();

        // create a priority queue to hold a collection of sorted solutions
        PriorityQueue<List<Student>> orderedSolutions = new PriorityQueue<>(MAX_POPULATION, comparator);

        // add all solutions to the priority queue, in preparation for being ranked
        for (List <Student> l : population) {
                orderedSolutions.add(l);
        }

        // strategy 0:
        // cull solutions based on their order in the priority queue
        for (int i = 0; i < MAX_POPULATION; i++) {
            List<Student> curr = orderedSolutions.poll();
            survivalChance = (100 / MAX_POPULATION) * i;

            // cull the bottom 20% of student solutions
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
        // Iterator<List<Student>> it = population.iterator();

        return survivors;
    }

    // GA has it's own fitness function
    private int fitness(List<Student> solution) {
        return (int) Math.floor(Math.random()) * 100;

        // fitness function given in Tony's slides
        // fitness(TeamT) = ∑merit(PlayerT) - |∑cost(PlayerT) - 30|
        //                  ---------------------------------------
        //                              ∑cost(PlayerT)
    }

    @Override
    public void InjectData(DataSource repo) {
        this.projects.clear();
        this.students.clear();
        this.studentRepo.clear();
        int idx = 0;

        try {
            for (Project p : repo.ProjectRepo()) {
                projects.put(idx++,p);
            }

            this.repo = (InMemoryRepo) repo;
            studentRepo = repo.StudentRepo();

            idx = 0;
            for (Student s : repo.StudentRepo().values()) {
                students.put(idx++,s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Result Solve() {
        // blatantly using Nil's code
        int poolSize = students.size();
        int pondSize = projects.size();
        List<Student> students = new ArrayList<>();

        for (int i = 0; i < poolSize; i++) {
            students.add(new ArrayList<>(studentRepo.values()).get(i));
        }

        createInitialPopulation();
        List<List<Student>> survivors = cull(this.population);

        // there is no presentation class for genetic algorithmss yet,
        // so I can't return a proper result
        // ... but I will triumph and create one!... after I do chores
        System.out.println("====================================");
        System.out.println("Battle report - Surviving solutions");
        System.out.println("====================================");
        for (List l: survivors) {
            System.out.println(l.getClass());
        }
        System.out.println("==========================================");
        System.out.println("Battle report - Number of culled solutions");
        System.out.println("==========================================");
        System.out.println(population.size() - survivors.size());

        return null;
    }
}