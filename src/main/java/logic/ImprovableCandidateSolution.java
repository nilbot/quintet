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
//        double threshold = 0.3;
//        double incrementalImprovement = 0.0;
        long curr = System.currentTimeMillis();
        long due = curr + 2000;
        while (curr <= due) {
            CandidateAssignment labrat = getRandomAssignment();
            int previous = getEnergy();
            labrat.randomizeAssignment();
            if (getEnergy() < previous) {
//                incrementalImprovement += 0.1;
                return true;
            } else {
                labrat.undoChange();
            }
//            if (incrementalImprovement >= 0.3) {
//                return true;
//            }
            curr = System.currentTimeMillis();
        }

        return false;
    }
}
