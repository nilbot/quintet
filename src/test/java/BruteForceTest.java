import model.AllocationResult;
import model.RandomTestGenerator;
import org.junit.Test;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * test cases
 */
public class BruteForceTest {

    @Test
    public void testPermute() throws Exception {

    }

    @Test
    public void testCost() throws Exception {

    }

    @Test
    public void testMinCost() throws Exception {

    }

    @Test
    public void testDryRun() throws Exception {
        long begin = System.currentTimeMillis();
        RandomTestGenerator generatedTests = new RandomTestGenerator(8,5,3);
        System.out.printf("Test generation cost %d ms \n",System.currentTimeMillis() - begin);
        Object[] a = generatedTests.Repo.keySet().toArray();
        begin = System.currentTimeMillis();
        ArrayList<ArrayList<Integer>> rst = BruteForce.permute(Arrays.copyOf(a, a.length, Integer[].class));
        System.out.printf("Permute all possible arrangements cost %d ms \n\n----\n", System.currentTimeMillis() - begin);

        begin = System.currentTimeMillis();
        AllocationResult result = BruteForce.minCost(rst, generatedTests.Students);
        System.out.println("Result of best arrangement: " + result.toString());
        System.out.printf("min cost %d ms", System.currentTimeMillis() - begin);
    }
}