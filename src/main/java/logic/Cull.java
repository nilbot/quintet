package logic;

import data.GenePool;

public class Cull implements Cullable {

    public GenePool cull(CullConfig config, GenePool input) {
        double probability = config.getCullProbability();
        double rand;

        if (input == null || input.Size() == 0) {
            throw new IllegalArgumentException("input is null");
        }

        // establish that the config is sane
        if (config.getCullAmount() >= input.Size()) {
            System.err.println(config.getCullAmount());
            System.err.println(input.Size());
            throw new IllegalArgumentException("Cull amount can not be larger" +
                    " than population");
        }

        // cull solutions if they fall inside the range of probability
        for (int i = 0; i < config.getCullAmount(); i++) {
            rand = config.getRNG();
            if (rand <= probability) {
                // remove the least fittest solution
                input.getTop();
            }
        }
        return input;
    }
}