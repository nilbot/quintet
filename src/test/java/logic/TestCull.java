package logic;

import config.CullConfig;
import data.GenePool;
import org.junit.Before;
import org.junit.Test;
import static model.service.UtilityService.TheRNG;

import static org.junit.Assert.*;

public class TestCull {
    private CullConfig cullConfig;
    private Cull cull;
    private GenePool testPool;
    private MockSolution mockSol;

    @Before
    public void setup() {
        TheRNG().setSeed(42);
        cull = new Cull();
        cullConfig = new CullConfig(5, 1.0);
        mockSol = new MockSolution(1);
        testPool = new GenePool();

        // generate 25 random solutions (need to choose 52 for this)
        for (int i = 0; i < 25; i++) {
            mockSol = new MockSolution(i);
            testPool.addToPool(mockSol);
        }
    }

    // logic tests
    @Test
    public void testCullMaxProbability() throws Exception {
        cullConfig.setCullAmount(10);
        cullConfig.setCullProbability(1.0);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(15, testPool.size());
    }

    @Test
    public void testCullMediumProbability() throws Exception {
        cullConfig.setCullAmount(5);
        cullConfig.setCullProbability(1.0);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(20, testPool.size());
    }

    @Test
    public void testCullLowProbability() throws Exception {
        cullConfig.setCullAmount(3);
        cullConfig.setCullProbability(1.0);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(22, testPool.size());
    }

    // smoke testing
    @Test(expected = IllegalArgumentException.class)
    public void TestCullSmoke() {
        GenePool mock = null;
        cull.cull(cullConfig, mock);
    }
}