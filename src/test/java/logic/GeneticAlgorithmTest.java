package logic;

import app.Config;
import org.junit.Ignore;
import org.junit.Test;
import presentation.Result;

import static org.junit.Assert.*;

/**
 * GeneticAlgorithmTest:
 */
public class GeneticAlgorithmTest {
    @Ignore //because it takes too long
    public void solve() throws Exception {
        Config cfg = new Config("Memory","input.tsv","GeneticAlgorithm");

        Solver ga = cfg.GetSolver();
        Result rst = ga.Solve();
        System.out.println(rst.toJson());
    }

}