package model;

import java.util.List;

/**
 * AllocationResult
 */
public class AllocationResult {
    private final int cost;
    private final List<Integer> plan;

    public AllocationResult(int cost, List<Integer> arrangement) {
        this.cost = cost;
        this.plan = arrangement;
    }
    @Override
    public String toString() {
        return "Cost: " + this.cost + " Plan: {" + plan + "}";
    }
}
