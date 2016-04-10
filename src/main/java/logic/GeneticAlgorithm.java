package logic;

import java.util.Comparator;
import java.util.PriorityQueue;
import model.DataSource;
import model.Project;
import model.Student;
import presentation.Result;

import java.util.*;

public class GeneticAlgorithm implements Solver {
    private List<CandidateSolutionExtended> population;
    private Map<Integer,Project>  projects;
    private DataSource repo;
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
            this.population.add(createRandomSolution());
        }
    }

    // return a CandidateSolution containing candidates that have had their
    // project preferences randomised
    private CandidateSolutionExtended createRandomSolution() {
        List<CandidateAssignment> randomisedCandidates = new ArrayList<>();

        // this object uses the same repo data that was used to inject data
        // into this solver. The intention is to pull out a candidate from
        // the repo, randomise their project preferences, and return them
        CandidateSolutionExtended solution = new CandidateSolutionExtended(repo);

        // iterate through every student that appears in the entrySet
        for (Map.Entry<Integer, Student> pair : students.entrySet()) {
            // get the candidate
            CandidateAssignment cand = solution.getAssignmentFor(pair.getValue().getStudentName());
            // randomise them
            CandidateAssignment rand = randomiseAssignment(cand);
            // add them to the list
            randomisedCandidates.add(rand);
        }

        //  update the solution with the list of randomised candidates
        solution.setList(randomisedCandidates);

        return solution;
    }

    private CandidateAssignment randomiseAssignment(CandidateAssignment c) {
        // clear the candidates current projects
        c.getStudentEntry().getProjects().clear();

        // TODO: implement singleton random
        // add some random projects, don't add duplicates!
        while (c.getStudentEntry().getProjects().size() < c.getStudentEntry().getNumberOfStatedPreferences()) {
            int r = (int) Math.floor(Math.random() * 100) % projects.size();

            if (!c.getStudentEntry().hasPreference(projects.get(r))) {
                c.getStudentEntry().addProject(projects.get(r));
            }
        }
        return c;
    }

    // the strongest members of a population are more likely to survive, by
    // virtue of their fitness. however! it is not certain. even the weakest
    // members of a population have a tiny chance that they will survive.
    // conversely, a healthy specimen close to max fitness can sometimes die,
    // and less fit specimens can sometimes live and breed.
    private List<CandidateSolutionExtended> cull(List<CandidateSolutionExtended> population) {
        List<CandidateSolutionExtended> casualties = new ArrayList<>();
        List<CandidateSolutionExtended> survivors = new ArrayList<>();

        int survivalChance = 0;

        // TODO: implement Nil's suggestion below
        // "All strategy can be squeezed into a single loop with parameter.
        // Introduce an option, say, probability of mutation, and you random
        // on the first line inside the loop, when your random is greater
        // than that probability threshold, you don't pop from top, remove
        // from random index etc...

        // create a student comparator to act as a temporary fitness function
        Comparator<CandidateSolutionExtended> comparator = new CandidateSolutionForGA();

        // create a priority queue to hold a collection of sorted solutions
        PriorityQueue<CandidateSolutionExtended> orderedSolutions = new PriorityQueue<>(MAX_POPULATION, comparator);

        // add all solutions to the priority queue, in preparation for being ranked
        for (CandidateSolutionExtended c : population) {
            orderedSolutions.add(c);
        }

        // cull solutions based on their order in the priority queue
        for (int i = 0; i < MAX_POPULATION; i++) {
            CandidateSolutionExtended curr = orderedSolutions.poll();
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

            studentRepo = repo.StudentRepo();
            this.repo = repo;

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
        List<CandidateSolutionExtended> survivors = cull(this.population);

        // there is no presentation class for genetic algorithmss yet,
        // so I can't return a proper result
        // ... but I will triumph and create one!... after I do chores
        System.out.println("===================================");
        System.out.println("Battle report - Surviving solutions");
        System.out.println("===================================");
        for (CandidateSolutionExtended c: survivors) {
            System.out.println(c.toString()  + "\tFitness: " + c.getFitness());
        }
        System.out.println("==========================================");
        System.out.println("Battle report - Number of culled solutions");
        System.out.println("==========================================");
        System.out.println(population.size() - survivors.size());

        return null;
    }
}