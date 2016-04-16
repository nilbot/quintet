package logic;

import java.util.*;

/**
 * MockSolution:
 */
public class MockSolution implements Comparable {
    private List<MockAssignment> theList;

    @Override
    public int compareTo(Object o) {
        int score = ((MockSolution) o).getFitness();
        return 0;
    }

    public MockSolution() {
        MockAssignment assignment1 = new MockAssignment(12);
        MockAssignment assignment2 = new MockAssignment(5);
        theList = new ArrayList<>();
        theList.add(assignment1);
        theList.add(assignment2);
    }

    public int getFitness() {
        int rst = 0;
        for (int i = 0; i < 2; i++) {
            rst += theList.get(i).getFitness() * (1 << i);
        }
        return rst;
    }
}
