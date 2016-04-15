package logic;

import model.DataSource;
import model.Project;
import model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GAData {
    private List<CandidateSolutionForGA> population;
    private Map<Integer, Project> projects;
    private Map<Integer, Student> students;
    private Map<String,Student> studentRepo;
    public static int MAX_POPULATION = 10;
    DataSource myRepo;

    public GAData() {
        this.projects = new HashMap<>();
        this.students = new HashMap<>();
        this.studentRepo = new HashMap<>();
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

    public List<CandidateSolutionForGA> getPopulation() {
        return population;
    }

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

    public List<CandidateSolutionForGA> setPopulation(List<CandidateSolutionForGA> population) {
        this.population = population;
        return this.population;
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
}