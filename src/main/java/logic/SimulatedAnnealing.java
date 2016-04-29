package logic;

import model.DataSource;
import model.ImprovableCandidateSolution;
import presentation.Result;
import presentation.SAResult;


/**
 * SimulatedAnnealing:
 */
public class SimulatedAnnealing implements Solver {
    private DataSource repo;

    @Override
    public void InjectData(DataSource repo) {
        if (repo != null && repo.Ready())
            this.repo = repo;
        else throw new IllegalArgumentException("DataSource not ready");
    }

    @Override
    public Result Solve() {
        ImprovableCandidateSolution singleton = new
                ImprovableCandidateSolution(repo);

        // some threshold
        long timeOut = 50;
        runTill(timeOut, singleton);

        SAResult res = new SAResult(singleton);
        return res;
    }

    private void runTill(long timeOut, ImprovableCandidateSolution singleton) {
        // either iteration reaches 0 or solution can't be improved.
        while (timeOut >= 0 && singleton.improve()) {
            timeOut--;
        }
    }
}
