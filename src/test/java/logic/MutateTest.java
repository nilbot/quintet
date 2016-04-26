package logic;

import static org.junit.Assert.*;
import model.DataSource;
import model.Project;

import org.junit.Before;
import org.junit.Test;

import app.Config;

/**
 * MutateTest:
 */
public class MutateTest {
    private Config testConfig;
    private DataSource data;
    private CandidateSolution testSol;

    @Before
    public void setup() throws Exception {
        testConfig = new Config("Memory", "mutate_test.tsv", "GeneticAlgorithm");
        data = testConfig.GetData();
        /*try {
            sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //TheRNG().setSeed(124);
        testSol = new CandidateSolution(data);
    }

    @Test
    public void mutate() throws Exception {
        //int enPrev = testSol.getEnergy();
        //if (testSol.improve())
        //assertTrue("WTF? Y U NO WORK?!", testSol.getEnergy() < enPrev);
	//TODO: implement just randomization with radiation variable
	Project a = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	//assertEquals("")
	testSol.mutate(5);
	Project b = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	
	assertNotSame("Mutate is not mutating", a, b);
	//fail("not implemented yet");
    }
}
/*
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
        Result res = SA.Solve();
        System.out.println(res.String());
        assertTrue(res!=null);
    }

    @Test
    public void unrelated() throws Exception {
        ImprovableCandidateSolution object = new ImprovableCandidateSolution
                (data);
        ImprovableCandidateSolution before = object;
        if (object.improve()) {
            ImprovableCandidateSolution after = object;
            assertEquals(before, after);
            assertEquals(before.getFitness(), after.getFitness(), 0.0001);
        }
    }
}*/