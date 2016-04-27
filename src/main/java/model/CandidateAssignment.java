package model;

import model.Project;
import model.Student;

/**
 *
 */
public class CandidateAssignment {
	private Student theStudent;
	private Project theAssignment;
	private Project actuallyLastAss;

	/**
	 */
	public void randomizeAssignment() {
		this.actuallyLastAss = this.theAssignment;
		this.theAssignment = this.theStudent.getRandomPreference();
	}

	/**
	 * @param poorGuy
	 */
	public CandidateAssignment(Student poorGuy) {
		this.theStudent = poorGuy;
		randomizeAssignment();
	}

	/**
	 * @return
	 */
	public Student getStudentEntry(){
		return this.theStudent;
	}

	/**
	 *
	 */
	public void undoChange(){
		if (this.actuallyLastAss!=null)
		this.theAssignment = this.actuallyLastAss;
	}

	/**
	 * @return
	 */
	public Project getAssignedProject(){
		return this.theAssignment;
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
		return this.actuallyLastAss;
	}
}
