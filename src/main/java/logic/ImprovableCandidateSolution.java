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


    public boolean improve() {
        long curr = System.currentTimeMillis();
        long due = curr + 2000; // This worth documenting
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
