package logic;

import app.Config;
import model.DataSource;
import model.ImprovableCandidateSolution;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import presentation.Result;

import static java.lang.Thread.sleep;
import static model.service.UtilityService.TheRNG;
import static org.junit.Assert.*;

/**
 * SimulatedAnnealingTest:
 */
public class SimulatedAnnealingTest {
    private Config testConfig;
    private DataSource data;
    private Solver SA;

    @Before
    public void setUp() throws Exception {
        testConfig = new Config("Memory", "input.tsv", "SimulatedAnnealing");
        data = testConfig.GetData();
        SA = testConfig.GetSolver();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SA.InjectData(data);
        TheRNG().setSeed(124);
    }

    @After
    public void tearDown() throws Exception {
        SA = null;
        data = null;
        testConfig = null;
    }

    @Test(expected = IllegalArgumentException.class)
    public void injectData() throws Exception {
        Solver sa = new SimulatedAnnealing();
        sa.InjectData(null);
        sa.InjectData(new Config().GetData());

    }

    @Test
    public void solve() throws Exception {
        ((SimulatedAnnealing)SA).setIteration(1000);
        ((SimulatedAnnealing)SA).setTimeout(10000);
        Result res = SA.Solve();
        System.out.println(res.getMeta());
        assertTrue(res!=null);
    }

    @Test
    public void unrelated() throws Exception {
        ImprovableCandidateSolution object = new ImprovableCandidateSolution
                (data.StudentRepo().values());
        ImprovableCandidateSolution before = object;
        if (object.improve()) {
            ImprovableCandidateSolution after = object;
            assertEquals(before, after);
            assertEquals(before.getFitness(), after.getFitness(), 0.0001);
        }
    }
}