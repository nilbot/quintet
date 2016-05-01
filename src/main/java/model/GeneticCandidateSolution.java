package model;

import model.CandidateSolution;
import model.DataSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * GeneticCandidateSolution:
 */
public class GeneticCandidateSolution extends CandidateSolution {
    /**
     * @param prefs
     */
    public GeneticCandidateSolution(Collection<Student> prefs) {
        super(prefs);
    }

    public GeneticCandidateSolution(List<CandidateAssignment> fh, List<CandidateAssignment> mh) {
        set = new HashSet<>();
        theList = new ArrayList<>(fh);
        theList.addAll(mh);
    }
}
