package model;

import java.util.*;

import static model.service.UtilityService.*;

/**
 * Student class holds information of student's name ({@link #Name}) and the project names
 * this student has stated as preferences.
 */
public class Student implements Dodgy {
    private String Name;
    /** States number of original stated preferences
     * <p>
     * 		<li>-1 indicates first project is pre-assigned</li>
     * 		<li>any non-negative number indicates the number of ordered preferences</li>
     * <p>
     * this way all 3 requirements can be covered using 1 int variable.
     */
    private int orderedPreferences;
    private List<Project> projects;
    private boolean hasPreassigned;

    public Student(String studentname) {
        this.Name = studentname;
        this.orderedPreferences = 0;
        this.hasPreassigned = false;
        this.projects = new ArrayList<Project>();
    }

    /** append project (string) to the list of projects for this student
     * @param projectName project name
     */
    public void addProject(Project projectName) {
        this.projects.add(projectName);
    }

    /**Should the student has pre-assign projects this methods will return 1 regardless
     * how many projects the student has stated.
     * Otherwise return the originally stated number of preferences
     * @see #orderedPreferences
     */
    public int getNumberOfStatedPreferences() {
        if (hasPreassignedProject()) {
            return 1;
        } else {
            return orderedPreferences;
        }
    }

    /** According to requirements there is no indication whether the orderedPreference has to include
     * all subsequent projects or the original stated order. personally I think returning the entire
     * project list would make more sense.
     * @return entire project list
     */
    public List<Project> getOrderedPreferences() {
        return this.projects;
    }

    public String getStudentName() {
        return Name;
    }

    /**Return true if the student has pre-assign project
     * the criteria is if the internal number is set to -1
     */
    public boolean hasPreassignedProject() {
        return hasPreassigned;
    }

    /** without losing projects list information, set the project as pre-assignment
     * the {@link #orderedPreferences} will be set to -1
     * @param projectName the project to be assigned as pre-assignment
     */
    public void preassignProject(Project projectName) {
//		this.projects.clear();
        if (this.projects.size()==0 || projectName != this.projects.get(0))
            this.projects.add(0, projectName);
        hasPreassigned = true;
    }

    public void setNumberOfStatedPreferences(int number) {
        this.orderedPreferences = number;
    }

    public boolean hasPreference(Project preference) {
        return projects.contains(preference);
    }

    @Override
    public Project getRandomPreference() {
        Integer pick = TheRNG().nextInt(this.getProjects().size());
        return this.getProjects().get(pick);
    }

    public int getNumberOfPreferences() {
        return projects.size();
    }

    public List<Project> getProjects() {
        return this.projects;
    }

    public int getRanking(Project project) {
        int rank = projects.indexOf(project);
        if (rank < 0 || rank > 9) {
            return 10;
        } else {
            return rank;
        }
    }
}
