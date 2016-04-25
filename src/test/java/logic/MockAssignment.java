package logic;

/**
 * MockAssignment:
 */
public class MockAssignment {
    private final int score;

    public MockAssignment(int i) {
        score = i;
    }

    public int getFitness() {
        return score;
    }
}
