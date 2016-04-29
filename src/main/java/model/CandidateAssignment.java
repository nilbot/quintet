package model;

import presentation.GsonSerialzable;

/**
 *
 */
public class CandidateAssignment extends GsonSerialzable {
	private Student student;
	private Project assignedProject;

	transient private Project lastAssignedProject;

	/**
	 */
	public void randomizeAssignment() {
		this.lastAssignedProject = this.assignedProject;
		this.assignedProject = this.student.getRandomPreference();
	}

	// Deterministic Constructor
	public CandidateAssignment(Student s, Project p) {
		this.student = s;
		this.assignedProject = p;
	}

	/**
	 * @param poorGuy
	 */
	public CandidateAssignment(Student poorGuy) {
		this.student = poorGuy;
		randomizeAssignment();
	}

	/**
	 * @return
	 */
	public Student getStudentEntry(){
		return this.student;
	}

	/**
	 *
	 */
	public void undoChange(){
		if (this.lastAssignedProject !=null)
		this.assignedProject = this.lastAssignedProject;
	}

	/**
	 * @return
	 */
	public Project getAssignedProject(){
		return this.assignedProject;
	}

	/**
	 * @return
	 */
	public int getEnergy() {
		return (int) Math.pow(getStudentEntry().getRanking(
				getAssignedProject())+1,2);
	}

	/**
	 * @return
	 */
	public Project getPrevAssignment() {
		return this.lastAssignedProject;
	}

	public String toJson() {
		return GSON.toJson(this, CandidateAssignment.class);
	}
}
