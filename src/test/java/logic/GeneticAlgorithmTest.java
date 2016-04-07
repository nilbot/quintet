package logic;

import app.Config;
import logic.Solver;
import model.DataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import presentation.Result;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertNotNull;

public class GeneticAlgorithmTest {
    private Solver genetic;
    private DataSource data;
    private Config testConfig;

    public void setup() throws Exception{
        testConfig = new Config();
        testConfig.GetConfig();
        data = testConfig.GetData();
        genetic = testConfig.GetSolver();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        genetic.InjectData(data);
    }

    @Test
    public void testDryRun() throws Exception {
        setup();
        long begin = System.currentTimeMillis();

        Result res = genetic.Solve();

        System.out.println("Time to solve: " + (System.currentTimeMillis() - begin));
        System.out.println("Result of best arrangement: " + res);
    }
}