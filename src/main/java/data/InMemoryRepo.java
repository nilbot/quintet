package data;

import model.DataSource;
import model.Dodgy;
import model.Project;
import model.Student;
import presentation.InputMeta;

import java.util.*;

import static model.service.UtilityService.*;

/**
 * InMemoryRepo:
 */
public class InMemoryRepo implements DataSource, Dodgy {
    private Map<String, Student> studentRepo;
    private Set<Project> projectRepo;
    private boolean ready;
    private static final String repo_not_ready_error_msg = "Repo not ready " +
            "for query";

    public InMemoryRepo() {
        this.studentRepo = new HashMap<>();
        this.projectRepo = new HashSet<>();
        this.ready = false;
    }

    public void injectContent(Vector<Vector<String>> content) {
        if (ready) {
            System.err.println("WARNING: wiping content!");
        }
        for (Vector<String> studentData : content.subList(1, content.size())) {
            String studentName = studentData.get(0);
            Student studentObj = new Student(studentName);

            int orderedPreference = studentData.size() - 2;
            studentObj.setNumberOfStatedPreferences(orderedPreference);

            if (studentData.get(1).toLowerCase().equalsIgnoreCase("yes")) {
                String projectString = studentData.get(2);
                Project project = new Project(projectString);
                this.projectRepo.add(project);
                studentObj.preassignProject(project);
            } else {
                for (int p = 0; p < orderedPreference; p++) {
                    String projectString = studentData.get(p + 2);
                    Project project = new Project(projectString);
                    this.projectRepo.add(project);
                    studentObj.addProject(project);
                }
            }
            this.studentRepo.put(studentName, studentObj);
        }
        ready = true;
    }

    public InMemoryRepo(Vector<Vector<String>> content){
        this();
        if (content == null || content.size() == 0 ){
            throw new IllegalArgumentException("constructor parameter content" +
                    " is empty");
        }

        injectContent(content);
    }

    // TODO discuss pick your poison, handle null on call or try catch on call

    @Override
    public Student RandomStudent() {
        if (!ready) {
            throw new IllegalStateException(repo_not_ready_error_msg);
        }
        Integer pick = TheRNG().nextInt(StudentRepo().values().size());
        return (Student) StudentRepo().values().toArray()[pick];
    }

    @Override
    public Student GetStudent(String studentName) {
        if (!ready) {
            throw new IllegalStateException("Repo not ready for query");
        }
        return StudentRepo().get(studentName);
    }

    @Override
    public Set<Project> ProjectRepo() {
        if (!ready) {
            throw new IllegalStateException("Repo not ready for query");
        }
        return this.projectRepo;
    }

    @Override
    public Map<String,Student> StudentRepo() {
        if (!ready) {
            throw new IllegalStateException("Repo not ready for query");
        }
        return this.studentRepo;
    }

    @Override
    public boolean Ready() {
        return this.ready;
    }

    @Override
    public InputMeta getMeta() {
        if (!ready) {
            throw new IllegalStateException("Repo not ready for query");
        }
        List<Student> ls = new ArrayList<>(StudentRepo().values());
        return new InputMeta(ls,ProjectRepo());
    }

    @Override
    public Project getRandomPreference() {
        if (!ready) {
            throw new IllegalStateException("Repo not ready for query");
        }
        Integer pick = TheRNG().nextInt(ProjectRepo().size());
        return (Project) ProjectRepo().toArray()[pick];
    }
}
