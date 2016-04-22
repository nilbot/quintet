package logic;

import model.DataSource;
import model.Project;
import model.Student;

import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static model.service.UtilityService.TheRNG;

/**
 *
 */
public class CandidateSolution implements Comparable<CandidateSolution> {
    private static final int penalty = 1000;
    private final DataSource prefs;
    private List<CandidateAssignment> theList;
    private Set<Project> set;


    /**
     * @param prefs
     */
    public CandidateSolution(DataSource prefs) {
        this.prefs = prefs;
        this.theList = new ArrayList<CandidateAssignment>();
        try {
            for (Student student : prefs.StudentRepo().values()) {
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
     * @return
     */
    public double getFitness(){
        return 1/getEnergy();
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
}
