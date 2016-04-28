package logic;

import static org.junit.Assert.*;
import model.DataSource;
import model.GeneticCandidateSolution;
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
    private GeneticCandidateSolution testSol; //use genetic instead

    @Before
    public void setup() throws Exception {
        testConfig = new Config("Memory", "mutate_test.tsv", "GeneticAlgorithm");
        data = testConfig.GetData();
        testSol = new GeneticCandidateSolution(data);
    }

    @Test
    public void mutate() throws Exception {
	Mutate m = new Mutate(1.0);
	Project a = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	testSol = m.mutate(testSol);
	Project b = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	assertNotSame("Mutate is not mutating", a, b);
	
	m.changeMutationChance(0.0);
	testSol = m.mutate(testSol);
	Project c = testSol.getAssignmentFor("Loki Laufeyson").getAssignedProject();
	assertSame("Mutate is mutating when it shouldn't", c, b);
    }
}