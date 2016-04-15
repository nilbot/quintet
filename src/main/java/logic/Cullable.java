package logic;

import java.util.List;

/**
 * Created by aaron on 13/04/16.
 */
public interface Cullable {
    public List<CandidateSolutionForGA> cull(GAData gaData);
}
