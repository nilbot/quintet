package logic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Cull implements Cullable {
    // cull the weak solutions
    public List<CandidateSolutionForGA> cull(GAData gaData) {
        Comparator<CandidateSolutionForGA> geneticComparator = new GeneticComparator();
        List<CandidateSolutionForGA> casualties = new ArrayList<>();
        List<CandidateSolutionForGA> survivors = new ArrayList<>();
        double survivalChance;

        // a priority queue to hold a collection of sorted solutions
        PriorityQueue<CandidateSolutionForGA> orderedSolutions = new PriorityQueue<>(gaData.MAX_POPULATION, geneticComparator);

        // add all solutions to the priority queue, in preparation for being ranked
        for (CandidateSolutionForGA c : gaData.getPopulation()) {
            orderedSolutions.add(c);
        }

        // "All strategy can be squeezed into a single loop with parameter.
        // Introduce an option, say, probability of mutation, and you random
        // on the first line inside the loop, when your random is greater
        // than that probability threshold, you don't pop from top, remove
        // from random index etc...
        // cull solutions based on their order in the priority queue
        for (int i = 0; i < gaData.MAX_POPULATION; i++) {
            survivalChance = Math.random();
            CandidateSolutionForGA currentSolution = orderedSolutions.poll();

            // cull the bottom 20% of student solutions
            if (survivalChance > 0.2) {
                survivors.add(currentSolution);
            } else {
                casualties.add(currentSolution);
            }
        }

        gaData.setPopulation(survivors);

        return survivors;
    }
}
