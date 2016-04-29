package model;

import model.CandidateAssignment;
import model.CandidateSolution;
import model.DataSource;

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

    // improve once, if success, return silently, if fail, rewind result
    public boolean improve() {
        CandidateAssignment labrat = getRandomAssignment();
        int previous = getEnergy();
        labrat.randomizeAssignment();
        if (getEnergy() >= previous) {
            labrat.undoChange();
            return false;
        }
        return true;
    }
}
