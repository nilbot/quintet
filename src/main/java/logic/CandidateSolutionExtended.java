package logic;

import model.DataSource;
import model.Project;
import model.Student;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static model.service.UtilityService.TheRNG;

/**
 * Extra functionality adding 'getList' and 'setList', useful for when the
 * internal list of candidate assignments need to be randomised
 *
 */
public class CandidateSolutionExtended {
    private final int penalty = 1000;
    private final DataSource prefs;
    private List<CandidateAssignment> theList;
    private Set<Project> set;


    /**
     * @param prefs
     */
    public CandidateSolutionExtended(DataSource prefs) {
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

    public List<CandidateAssignment> getList() {
        return theList;
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

    public List<CandidateAssignment> setList(List<CandidateAssignment> theList) {
        this.theList = theList;
        return this.theList;
    }

    /**
     * @return
     */
    public int getFitness(){
        return -getEnergy();
    }
}
