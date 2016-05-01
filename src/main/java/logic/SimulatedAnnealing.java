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
    //default timeout 10 seconds
    private int timeout = 10000;
    //default iterations 1000
    private int iteration = 1000;

    @Override
    public void InjectData(DataSource repo) {
        if (repo != null && repo.Ready())
            this.repo = repo;
        else throw new IllegalArgumentException("DataSource not ready");
    }

    @Override
    public Result Solve() {
        ImprovableCandidateSolution singleton = new
                ImprovableCandidateSolution(repo.StudentRepo().values());

        int run = runTill(iteration, singleton);

        SAResult res = new SAResult(singleton, iteration - run);
        return res;
    }

    private int runTill(int iteration,
                         ImprovableCandidateSolution singleton) {
        // either iteration reaches 0 or solution can't be improved with in
        // timeout.
        long begin = System.currentTimeMillis();
        while (iteration-- >= 0 && !timedOut(begin)) {
            singleton.improve();
        }
        return iteration;
    }

    public void setTimeout(int t){
        this.timeout = t;
    }
    public void setIteration(int i) {
        this.iteration = i;
    }

    private boolean timedOut(long start) {
        return System.currentTimeMillis() > start + timeout;
    }
}
