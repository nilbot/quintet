package logic;

import model.DataSource;
import presentation.Result;
import presentation.SAResult;

import java.util.*;
import java.util.concurrent.*;

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

        SAResult res = new SAResult(singleton, repo);
        return res;
    }

    private void runTill(long timeOut, ImprovableCandidateSolution singleton) {
//        long now = System.currentTimeMillis();
//        long due = now + timeOut;
//        while (System.currentTimeMillis() <= due) {
        while (timeOut >= 0 && singleton.improve()) {
            System.out.println("run countdown "+ timeOut);
            timeOut--;
        }
    }
}
