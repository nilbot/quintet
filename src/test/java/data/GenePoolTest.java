package data;

import logic.MockSolution;
import model.DataSource;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static model.service.UtilityService.TheRNG;
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
        mockSol = new MockSolution(124); // seed
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
        int before = testPool.size();
        testPool.addToPool(mockSol);
        assertTrue(testPool.size() > before);
    }

    @Test
    public void getWorst() throws Exception {
        TheRNG().setSeed(124);
        int numOfSol = TheRNG().nextInt(100);
        final int crazyNum = 65535;
        List<MockSolution> referenceList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numOfSol; i++) {
            MockSolution mo = new MockSolution(TheRNG().nextInt(crazyNum));
            int f = mo.getFitness();
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
            referenceList.add(mo);
            testPool.addToPool(mo);
        }
        int b = testPool.getWorst().getFitness();
        assertEquals(min,b);
    }

    @Test
    public void getBest() throws Exception {
        TheRNG().setSeed(124);
        int numOfSol = TheRNG().nextInt(100);
        final int crazyNum = 65535;
        List<MockSolution> referenceList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numOfSol; i++) {
            MockSolution mo = new MockSolution(TheRNG().nextInt(crazyNum));
            int f = mo.getFitness();
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
            referenceList.add(mo);
            testPool.addToPool(mo);
        }
        int t = testPool.getBest().getFitness();
        assertEquals(max,t);
    }

    @Test
    public void undo() throws Exception {
        TheRNG().setSeed(124);
        int numOfSol = TheRNG().nextInt(100);
        final int crazyNum = 65535;
        List<MockSolution> referenceList = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < numOfSol; i++) {
            MockSolution mo = new MockSolution(TheRNG().nextInt(crazyNum));
            int f = mo.getFitness();
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
            referenceList.add(mo);
            testPool.addToPool(mo);
        }
        MockSolution pick = referenceList.get(TheRNG().nextInt(referenceList.
                size()));
        MockSolution b = testPool.getBest();
//        p = testPool.peekBest();
        testPool.undo();
        MockSolution n = testPool.peekBest();
        assertEquals(b,n);
    }

}