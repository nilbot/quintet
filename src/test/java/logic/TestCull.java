package logic;

import data.GenePool;
import data.MockDataSource;
import model.DataSource;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestCull {
    private CullConfig cullConfig;
    private Cull cull;
    private GenePool<MockSolution_setAssignment> testPool;
    private DataSource mockSource;
    private MockSolution_setAssignment mockSol;

    @Before
    public void setup() {
        cull = new Cull();
        cullConfig = new CullConfig();
        mockSource = new MockDataSource();
        mockSol = new MockSolution_setAssignment(0);
        testPool = new GenePool();
    }

    // logic tests
    @Test
    public void TestCull() throws Exception {
        int n = 0;
        for (int i = 0; i < 20; i++) {
            mockSol = new MockSolution_setAssignment(n);
            testPool.addToPool(mockSol);
            n += 2;
        }
        cullConfig.setCullAmount(5);
        testPool = cull.cull(cullConfig, testPool);
        assertEquals(15, testPool.Size());
    }

    // smoke testing
    @Test(expected = IllegalArgumentException.class)
    public void TestCullSmoke() {
        GenePool mock = null;
        cull.cull(cullConfig, mock);
    }
}