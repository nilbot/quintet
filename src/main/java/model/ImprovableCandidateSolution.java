package model;

import model.CandidateAssignment;
import model.CandidateSolution;
import model.DataSource;

import java.util.Collection;

/**
 * ImprovableCandidateSolution:
 */
public class ImprovableCandidateSolution extends CandidateSolution {
    /**
     * @param prefs
     */
    public ImprovableCandidateSolution(Collection<Student> prefs) {
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
