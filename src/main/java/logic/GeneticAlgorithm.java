package logic;

import java.util.Comparator;
import java.util.PriorityQueue;
import model.DataSource;
import model.Project;
import model.Student;
import presentation.Result;

import java.util.*;

// TODO: singleton random
public class GeneticAlgorithm implements GeneticInterface, Solver {
    private List<CandidateSolutionForGA> population;
    private Map<Integer,Project>  projects;
    DataSource myRepo;
    private Map<Integer,Student> students;
    private Map<String,Student> studentRepo;
    private static int MAX_POPULATION = 10;

    public GeneticAlgorithm(){
        this.population = new ArrayList<>();
        this.projects = new HashMap<>();
        this.students = new HashMap<>();
        this.studentRepo = new HashMap<>();
    }

    public CandidateSolutionForGA combine() {
        return null;
    }

    // create an initial population consisting of random solutions
    public void createInitialPopulation() {
        this.population = new ArrayList<>();
        for (int i = 0; i < MAX_POPULATION; i++) {
            this.population.add(createRandomSolution());
        }
    }

    // return a CandidateSolution containing candidates that have had their
    // project preferences randomised
    public CandidateSolutionForGA createRandomSolution() {
        List<CandidateAssignment> randomisedCandidates = new ArrayList<>();

        // this object uses the same repo data that was used to inject data
        // into this solver. The intention is to pull out a candidate from
        // the repo, randomise their project preferences, and return them
        CandidateSolutionForGA solution = new CandidateSolutionForGA(myRepo);

        // iterate through every student that appears in the entrySet
        for (Map.Entry<Integer, Student> pair : students.entrySet()) {
            // get the candidate
            CandidateAssignment cand = solution.getAssignmentFor(pair.getValue().getStudentName());
            // randomise their project preferences
            CandidateAssignment rand = randomiseCandidatePreferences(cand);
            // add them to the list
            randomisedCandidates.add(rand);
        }

        //  update the solution with the list of randomised candidate preferences
        solution.setList(randomisedCandidates);

        return solution;
    }

    // cull the weak solutions
    public List<CandidateSolutionForGA> cull() {
        List<CandidateSolutionForGA> casualties = new ArrayList<>();
        Comparator<CandidateSolutionForGA> geneticComparator = new GeneticComparator();
        List<CandidateSolutionForGA> survivors = new ArrayList<>();
        double survivalChance;

        // a priority queue to hold a collection of sorted solutions
        PriorityQueue<CandidateSolutionForGA> orderedSolutions = new PriorityQueue<>(MAX_POPULATION, geneticComparator);

        // add all solutions to the priority queue, in preparation for being ranked
        for (CandidateSolutionForGA c : this.population) {
            orderedSolutions.add(c);
        }

        // "All strategy can be squeezed into a single loop with parameter.
        // Introduce an option, say, probability of mutation, and you random
        // on the first line inside the loop, when your random is greater
        // than that probability threshold, you don't pop from top, remove
        // from random index etc...
        // cull solutions based on their order in the priority queue
        for (int i = 0; i < MAX_POPULATION; i++) {
            survivalChance = Math.random();
            CandidateSolutionForGA currentSolution = orderedSolutions.poll();

            // cull the bottom 20% of student solutions
            if (survivalChance > 0.2) {
                survivors.add(currentSolution);
            } else {
                casualties.add(currentSolution);
            }
        }

        return survivors;
    }

    @Override
    public void InjectData(DataSource repo) {
        this.projects.clear();
        this.students.clear();
        this.studentRepo.clear();
        int idx = 0;
        this.myRepo = repo;

        try {
            for (Project p : repo.ProjectRepo()) {
                projects.put(idx++,p);
            }

            studentRepo = repo.StudentRepo();

            idx = 0;
            for (Student s : repo.StudentRepo().values()) {
                students.put(idx++,s);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CandidateAssignment mutate() {
        return null;
    }

    public CandidateAssignment randomiseCandidatePreferences(CandidateAssignment c) {
        // clear the candidates current projects
        c.getStudentEntry().getProjects().clear();

        // add some random projects, don't add duplicates!
        while (c.getStudentEntry().getProjects().size() < c.getStudentEntry().getNumberOfStatedPreferences()) {
            int r = (int) Math.floor(Math.random() * 100) % projects.size();

            if (!c.getStudentEntry().hasPreference(projects.get(r))) {
                c.getStudentEntry().addProject(projects.get(r));
            }
        }
        return c;
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

        List<CandidateSolutionForGA> survivors = cull();

        // there is no presentation class for genetic algorithmss yet,
        // so I can't return a proper result
        // ... but I will triumph and create one!... after I do chores
        System.out.println("===================================");
        System.out.println("Battle report - Surviving solutions");
        System.out.println("===================================");
        for (CandidateSolutionForGA c: survivors) {
            System.out.println(c.toString()  + "\tFitness: " + c.getFitness());
        }
        System.out.println("==========================================");
        System.out.println("Battle report - Number of culled solutions");
        System.out.println("==========================================");
        System.out.println(population.size() - survivors.size());

        return null;
    }
}