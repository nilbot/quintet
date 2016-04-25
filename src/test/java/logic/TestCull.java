package logic;

import data.GenePool;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCull {
    private CullConfig cullConfig;
    private Cull cull;
    private GenePool testPool;
    private MockSolution_setAssignment mockSol;
    private Random rand;

    @Before
    public void setup() {
        cull = new Cull();
        cullConfig = new CullConfig(5, 1.0);
        mockSol = new MockSolution_setAssignment(0);
        rand = new Random();
        testPool = new GenePool();

        // populate the genepool with 50 mock solutions
        int n = 0;
        for (int i = 0; i < 50; i++) {
            mockSol = new MockSolution_setAssignment(n);
            testPool.addToPool(mockSol);
            n += 2;
        }
    }

    // logic tests
    @Test
    public void testCullMaxProbability() throws Exception {
        cullConfig.setCullAmount(10);
        cullConfig.setCullProbability(1.0);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(40, testPool.Size());
    }

    @Test
    public void testCullMediumProbability() throws Exception {
        cullConfig.setCullAmount(10);
        cullConfig.setCullProbability(0.5);
        cullConfig.setCullSeed(this.rand, 420);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(43, testPool.Size());
    }

    @Test
    public void testCullLowProbability() throws Exception {
        cullConfig.setCullAmount(10);
        cullConfig.setCullProbability(0.2);
        cullConfig.setCullSeed(this.rand, 420);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(47, testPool.Size());
    }

    // smoke testing
    @Test(expected = IllegalArgumentException.class)
    public void TestCullSmoke() {
        GenePool mock = null;
        cull.cull(cullConfig, mock);
    }
}