package presentation;

import logic.CandidateAssignment;
import model.Project;
import model.Student;

/**
 * AssignmentPair:
 */
public class AssignmentPair
{
    private Student LHS;
    private Project RHS;
    public AssignmentPair(Student student, Project project){
        this.LHS = student;
        this.RHS = project;
    }
    public AssignmentPair(CandidateAssignment assignment) {
        this.LHS = assignment.getStudentEntry();
        this.RHS = assignment.getAssignedProject();
    }
    @Override
    public String toString() {
        return "\"" + LHS.getStudentName() + "\": {" + RHS + "}";
    }
}
