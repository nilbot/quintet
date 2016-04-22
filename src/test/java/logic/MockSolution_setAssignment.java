package logic;

import java.util.ArrayList;
import java.util.List;

/**
 * MockSolution:
 */
public class MockSolution_setAssignment implements Comparable {
    private List<MockAssignment> theList;

    @Override
    public int compareTo(Object o) {
        int score = ((MockSolution_setAssignment) o).getFitness();
        if (this.getFitness() < score) {
            return -1;
        }
        if (this.getFitness() > score) {
            return 1;
        }
        return 0;
    }

    public MockSolution_setAssignment(int n) {
        MockAssignment assignment1 = new MockAssignment(n);
        MockAssignment assignment2 = new MockAssignment(n + 1);
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
