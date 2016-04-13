package logic;

import java.util.List;

/**
 * Created by aaron on 12/04/16.
 */
public interface GeneticInterface {
    public CandidateSolutionForGA combine();
    public void createInitialPopulation();
    public CandidateSolutionForGA createRandomSolution();
    public CandidateAssignment randomiseCandidatePreferences(CandidateAssignment c);
    public List<CandidateSolutionForGA> cull();
    public CandidateAssignment mutate();
}
