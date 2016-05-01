package model;

import java.util.*;

import static model.service.UtilityService.TheRNG;

/**
 *
 */
public class CandidateSolution implements Comparable<CandidateSolution> {
    private static final int penalty = 1000;
    private final Collection<Student> prefs;
    protected List<CandidateAssignment> theList;
    protected Set<Project> set;

    // extremely dangerous, but
    // don't have time to redesign. so, whatever.
    protected CandidateSolution() {prefs = null;}


    /**
     * @param prefs
     */
    public CandidateSolution(Collection<Student> prefs) {
        this.prefs = prefs;
        this.theList = new ArrayList<CandidateAssignment>();
        try {
            for (Student student : prefs) {
                this.theList.add(new CandidateAssignment(student));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.set = new HashSet<Project>();
    }

    /**
     * @param poorGuy
     * @return
     */
    public CandidateAssignment getAssignmentFor(String poorGuy) {
        for (CandidateAssignment a : theList) {
            if (a.getStudentEntry().getStudentName().equals(poorGuy)) {
                return a;
            }
        }
        return null;
    }

    /**
     * @return
     */
    public CandidateAssignment getRandomAssignment() {
		Integer pick = TheRNG().nextInt(theList.size());
		return theList.get(pick);
    }

    /**
     * @return
     */
    public int getEnergy() {
        set.clear();
        int sum = 0;
        int pen = 0;
        for (CandidateAssignment a: theList) {
            if (!set.contains(a.getAssignedProject())) {
                set.add(a.getAssignedProject());
            } else {
                pen+=penalty;
            }
            sum+=a.getEnergy();
        }
        sum+=pen;
        return sum;
    }

    /**
     * @return inverse of the energy score
     */
    public double getFitness(){
        return 1/(double)getEnergy();
    }

    @Override
    public int compareTo(CandidateSolution o) {
        if (this.getFitness() < o.getFitness()) {
            return -1;
        }
        if (this.getFitness() > o.getFitness()) {
            return 1;
        }
        return 0;
    }

    public final List<CandidateAssignment> listOfAssignments() {return theList;}
}
