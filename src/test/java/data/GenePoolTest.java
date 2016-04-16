package data;

import logic.CandidateSolution;
import logic.MockSolution;
import model.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * GenePoolTest:
 */
public class GenePoolTest {
    private GenePool<MockSolution> testPool;
    private DataSource mockSource;
    private MockSolution mockSol;
    @Before
    public void setUp() throws Exception {
        mockSource = new MockDataSource();
        mockSol = new MockSolution();
        testPool = new GenePool();
    }

    @After
    public void tearDown() throws Exception {
        testPool = null;
    }

    @Test
    public void addToPool() throws Exception {
        testPool.addToPool(mockSol);
        Set<MockSolution> rst = testPool.getPool();
        assertTrue(rst.contains(mockSol));
    }

    @Test
    public void getPool() throws Exception {
        Set<MockSolution> rst = testPool.getPool();
        assertTrue(!rst.contains(mockSol));
    }

    @Test
    public void size() throws Exception {
        int before = testPool.Size();
        testPool.addToPool(mockSol);
        assertTrue(testPool.Size() > before);
    }

    @Test
    public void getBottom() throws Exception {

    }

    @Test
    public void getTop() throws Exception {

    }

    @Test
    public void undo() throws Exception {

    }

}