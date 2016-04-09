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


    public int improve() {
        //TODO TEMP VAR await for refactor
        double threshold = 0.3;
        double incrementalImprovement = 0.0;
//        ImprovableCandidateSolution backup = backup();
        while (incrementalImprovement < threshold) {
            CandidateAssignment labrat = getRandomAssignment();
            int previous = getEnergy();
            labrat.randomizeAssignment();
            if (getEnergy() < previous) {
                incrementalImprovement += 0.1;
            } else {
                labrat.undoChange();
            }
        }

        return this.getFitness();
    }
}
