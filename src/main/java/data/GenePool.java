package data;

import java.util.PriorityQueue;
import logic.CandidateSolution;

/**
 * Created by Kevin on 15/04/2016.
 */
public class GenePool {
    private PriorityQueue<CandidateSolution> pool;

    void addToPool(CandidateSolution solution){ pool.add(solution); }

    PriorityQueue getPool(){ return pool; }
}
