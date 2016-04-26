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
        testSol = new CandidateSolution(data);
    }

    @Test
    public void mutate() throws Exception {
	Project a = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	testSol.mutate(5);
	Project b = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	
	assertNotSame("Mutate is not mutating", a, b);
    }
}