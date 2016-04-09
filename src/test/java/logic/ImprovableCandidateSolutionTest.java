package logic;

import app.Config;
import model.DataSource;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Thread.sleep;
import static model.service.UtilityService.TheRNG;
import static org.junit.Assert.*;

/**
 * ImprovableCandidateSolutionTest:
 */
public class ImprovableCandidateSolutionTest {
    private Config testConfig;
    private DataSource data;
    //    private Solver SA;
    private ImprovableCandidateSolution testSol;

    @Before
    public void setup() throws Exception {
        testConfig = new Config("Memory", "mini.tsv", "SimulatedAnnealing");
        data = testConfig.GetData();
//        SA = testConfig.GetSolver();
        try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        SA.InjectData(data);
        TheRNG().setSeed(124);
        testSol = new ImprovableCandidateSolution(data);
    }

    @Test
    public void improve() throws Exception {
        int enPrev = testSol.getEnergy();
        testSol.improve();
        assertTrue("WTF? Y U NO WORK?!", testSol.getEnergy() < enPrev);
    }

}