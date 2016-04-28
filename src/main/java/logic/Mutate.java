package logic;

import java.util.Random;

import model.GeneticCandidateSolution;

public class Mutate implements Mutable {
    private double mutation_chance = 0.0;
    private Random r = new Random();

    /**
     * Default mutation chance is 10%
     */
    public Mutate() {
	mutation_chance = 0.1;
    }

    /**
     * Mutation chance can be specified when creating object
     * 
     * @param mutation_probability
     */
    public Mutate(double mutation_probability) {
	if (mutation_probability > 1.0) mutation_probability = 1.0;
	if (mutation_probability < 0.0) mutation_probability = 0.0;
	this.mutation_chance = mutation_probability;
    }

    /**
     * Causes a mutation in the provided Solution depending on chance.
     * The mutation follows nature's model: it is random and uncontrolled
     * and no way here to undo it. 
     * 
     * @param c
     */
    @Override
    public GeneticCandidateSolution mutate(GeneticCandidateSolution c) {
	double p = r.nextDouble();
	if (p<mutation_chance) {
	    c.getRandomAssignment().randomizeAssignment();
	}
	return c;
    }
    
    /**
     * Allows updating of mutation chance
     * 
     * @param new_chance
     */
    @Override
    public void changeMutationChance(double new_chance) {
	if (new_chance > 1.0) new_chance = 1.0;
	if (new_chance < 0.0) new_chance = 0.0;
	this.mutation_chance = new_chance;
    }

}
