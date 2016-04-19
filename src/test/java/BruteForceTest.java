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
//        long begin = System.currentTimeMillis();
//        System.out.printf("Test generation cost %d ms \n",System.currentTimeMillis() - begin);

        setup();
        long begin = System.currentTimeMillis();

        Result res = bruteForce.Solve();

        System.out.printf("Permute all possible arrangements cost %d ms \n\n----\n", System.currentTimeMillis() - begin);
        System.out.println("Result of best arrangement: " + res);
        System.out.printf("min cost %d ms", System.currentTimeMillis() - begin);
    }
}