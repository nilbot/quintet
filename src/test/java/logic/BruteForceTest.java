package logic;

import app.Config;
import logic.Solver;
import model.DataSource;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import presentation.InputMeta;
import presentation.Result;

import java.nio.file.Path;
import java.nio.file.Paths;

import static java.lang.Thread.sleep;
import static org.junit.Assert.assertNotNull;

/**
 * test cases
 */
public class BruteForceTest {
    private Solver bruteForce;
    private DataSource data;
    private Config testConfig;

    public void setup() throws Exception{
        testConfig = new Config();
        testConfig.GetConfig();
        data = testConfig.GetData();
        bruteForce = testConfig.GetSolver();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bruteForce.InjectData(data);
    }

    @Test
    public void testFileExists() throws Exception {
        Path path = Paths.get("mini.tsv");
        assert path!=null;
    }


    @Test
    public void testPermute() throws Exception {

    }

    @Test
    public void testCost() throws Exception {

    }

    @Test
    public void testMinCost() throws Exception {

    }

    @Test
    public void testDryRun() throws Exception {
        setup();
        long begin = System.currentTimeMillis();

        Result res = bruteForce.Solve();

        System.out.printf("\n\nPermute all possible arrangements and " +
                "calculate minimum cost of all on the tidy dataset used %d ms" +
                "\n----\n\n", System.currentTimeMillis() - begin);
        System.out.println("Input meta:\n"+data.getMeta().getTlDr());
        System.out.println("Result meta of sift arrangement: " + res.getMeta());
        System.out.println("\n\n\n\n");
    }
}