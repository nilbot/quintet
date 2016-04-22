package logic;

import java.util.*;

import static model.service.UtilityService.TheRNG;

/**
 * MockSolution:
 */
public class MockSolution implements Comparable {
    private List<MockAssignment> theList;

    @Override
    public int compareTo(Object o) {
        int score = ((MockSolution) o).getFitness();
        if (this.getFitness() < score) {
            return -1;
        }
        if (this.getFitness() > score) {
            return 1;
        }
        return 0;
    }

    private final int numberOfAssignments = 4; // 4 * 4bits = 16 bytes = +int32
    private final int maxScore = 7; // 111 = 3 bits
    public MockSolution(int seed) {
        TheRNG().setSeed(seed);
        theList = new ArrayList<>();
        for (int i = 0; i < numberOfAssignments; i++) {
            theList.add(new MockAssignment(TheRNG().nextInt(maxScore)));
        }
    }

    public int getFitness() {
        int rst = 0;
        for (int i = 0; i < numberOfAssignments; i++) {
            rst += theList.get(i).getFitness() * (1 << numberOfAssignments);
        }
        return rst;
    }
}
