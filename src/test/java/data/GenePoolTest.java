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



    @Test
    public void getCoupleFromTop25Percent() throws Exception {
        TheRNG().setSeed(124);
        int numOfSol = TheRNG().nextInt(8,100);
        final int crazyNum = 65535;
        List<MockSolution> referenceList = new ArrayList<>();
        int quarter;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < numOfSol; i++) {
            MockSolution mo = new MockSolution(TheRNG().nextInt(crazyNum));
            int f = mo.getEnergy();
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
            if (set.contains(f))continue;
            set.add(f);
            referenceList.add(mo);
            testPool.addToPool(mo);
        }
        Collections.sort(referenceList, new Comparator<MockSolution>() {
            @Override
            public int compare(MockSolution o1, MockSolution o2) {
                return o2.getEnergy() - o1.getEnergy();
            }
        });
        quarter = referenceList.size()/4;
        MockSolution cutoff = referenceList.get(quarter);
        List<MockSolution> parents = testPool.getCoupleFromTop25Percent();
        assertEquals(parents.size(), 2);
        assertTrue(String.format("got %d, want %d",parents.get(0).getEnergy(),
                cutoff.getEnergy()),parents.get(0).getEnergy() <
                cutoff.getEnergy());
        assertTrue(parents.get(1).getEnergy() <= cutoff.getEnergy());
    }

    @Test
    public void getCoupleFromBottom25Percent() throws Exception {

    }

    @Test
    public void getCoupleFromMiddle50Percent() throws Exception {

    }

    @Test
    public void getRandomCouple() throws Exception {

    }

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
            int f = mo.getEnergy();
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
            referenceList.add(mo);
            testPool.addToPool(mo);
        }
        int b = testPool.getWorst().getEnergy();
        assertEquals(max,b);
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
            int f = mo.getEnergy();
            if (f < min) {
                min = f;
            }
            if (f > max) {
                max = f;
            }
            referenceList.add(mo);
            testPool.addToPool(mo);
        }
        int t = testPool.getBest().getEnergy();
        assertEquals(min,t);
    }
}