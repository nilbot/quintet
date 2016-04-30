package data;

import model.Project;
import model.Student;
import presentation.InputMeta;

import java.util.*;

/**
 * MockDataSource:
 */
public class MockDataSource implements model.DataSource {
    private List<Student> theStudList;
    public MockDataSource() {
        theStudList = new ArrayList<>();
        Student head = new Student("John Doe");
        head.setNumberOfStatedPreferences(3);
        head.addProject(new Project("Mock Project One"));
        head.addProject(new Project("Mock Project Two"));
        head.addProject(new Project("Mock Project Three"));
        theStudList.add(head);
        Student tail = new Student("Jane Doe");
        tail.setNumberOfStatedPreferences(2);
        tail.addProject(new Project("Mock Project Two"));
        tail.addProject(new Project("Mock Project Three"));
        theStudList.add(tail);
    }

    @Override
    public Student RandomStudent() throws Exception {
        // always return Jane Doe
        return theStudList.get(1);
    }

    @Override
    public Student GetStudent(String studentName) throws Exception {
        switch (studentName) {
            case "John Doe":
                return theStudList.get(0);
            case "Jane Doe":
                return theStudList.get(1);
            default:
                return null;
        }
    }

    @Override
    public Set<Project> ProjectRepo() throws Exception {
        return new HashSet<>(Arrays.asList(new Project[] {new Project("Mock " +
                "Project One"), new Project("Mock Project Two"),
                 new Project("Mock Project Three")}));
    }

    @Override
    public Map<String, Student> StudentRepo() throws Exception {
        HashMap<String, Student> rst = new HashMap<>();
        rst.put("John Doe", theStudList.get(0));
        rst.put("Jane Doe", theStudList.get(1));
        return rst;
    }

    @Override
    public boolean Ready() {
        return true;
    }

    @Override
    public InputMeta getMeta() {
        return null;
    }
}
