package logic;

import model.DataSource;
import presentation.Result;

/**
 * Solver:
 */
public interface Solver {
    void InjectData(DataSource repo);
    Result Solve();
}
