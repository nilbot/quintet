package model;

import model.service.ProjectRepositoryServiceProvider;
import model.service.RepositoryService;
import org.kohsuke.randname.RandomNameGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * RandomTestGenerator:
 */
public class RandomTestGenerator {

    public final List<Student> Students;
    public final RepositoryService Repo;

    public RandomTestGenerator(int maxProjectCount, int studentCount, int maxPrefCount) {
        if (studentCount > maxProjectCount) {
            maxProjectCount = studentCount*2;
        }
        ProjectRepositoryServiceProvider repo = new ProjectRepositoryServiceProvider();
        Random r = new Random();
        RandomNameGenerator rng = new RandomNameGenerator();
        for (int i = 0; i<maxProjectCount;i++) {
            int projectId = r.nextInt();
            String projectName = rng.next();
            repo.put(projectId,projectName);
        }
        List<Student> students = new ArrayList<Student>();
        Set<Integer> pool = repo.keySet();
        for (int i = 0; i < studentCount; i++) {
            String name = rng.next();
            int prefCount = r.nextInt(maxPrefCount) + 1;
            int[] prefs = new int[prefCount];
            for (int j = 0; j < prefCount; j++) {
                // i am relying on java's "correct" implementation of random iterator in this case
                prefs[j] = pool.iterator().next();
            }
            students.add(new Student(name, prefs, repo));
        }
        this.Students = students;
        this.Repo = repo;
    }
}
