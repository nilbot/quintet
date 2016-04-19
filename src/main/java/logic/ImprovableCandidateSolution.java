package logic;

import java.util.*;

import model.DataSource;
import model.Student;

import java.util.Map;

/**
 * ImprovableCandidateSolution:
 */
public class ImprovableCandidateSolution extends CandidateSolution {
    /**
     * @param prefs
     */
    public ImprovableCandidateSolution(DataSource prefs) {
        super(prefs);
    }

    private static final int timeout = 2000;
    // improve will return true if any random assignment can be improved
    // (reduced energy) and return true if it tries 2 second and none of the
    // random assignment can be improved.
    public boolean improve() {
        long curr = System.currentTimeMillis();
        long due = curr + timeout;
        while (curr <= due) {
            CandidateAssignment labrat = getRandomAssignment();
            int previous = getEnergy();
            labrat.randomizeAssignment();
            if (getEnergy() < previous) {
                // improve success
                return true;
            } else {
                labrat.undoChange();
            }
            curr = System.currentTimeMillis();
        }
        // timed out, failed to improve
        return false;
    }
}
