package logic;

import config.CullConfig;
import data.GenePool;

import java.util.Iterator;
import java.util.TreeSet;

import static model.service.UtilityService.TheRNG;

public class Cull implements Cullable {

    public GenePool cull(CullConfig config, GenePool input) {
        double probability = config.getCullProbability();
        TreeSet<Integer> cullIndex = new TreeSet<>();

        if (input == null || input.size() == 0) {
            throw new IllegalArgumentException("input is null");
        }

        // establish that the config is sane
        if (config.getCullAmount() >= input.size()) {
            System.err.println(config.getCullAmount());
            System.err.println(input.size());
            throw new IllegalArgumentException("Cull amount can not be larger" +
                    " than population");
        }

        // generate a set of random values to cull, ranging over the size
        // of the pool. this is just uniform distribution for now
        while (cullIndex.size() < config.getCullAmount()) {
            cullIndex.add(TheRNG().nextInt(input.size()));
        }

        // use the values in cullIndex to cull elements in the genepool
        Iterator<Integer> ci = cullIndex.iterator();
        Iterator pi = input.getPool().iterator();
        int currVal = ci.next();
        int i = 0;

        while (pi.hasNext()) {
            // only retrieve the iterator value once at the start of the loop
            pi.next();
            if (i == currVal) {
                pi.remove();
                if (ci.hasNext()) {
                    currVal = ci.next();
                } else { break; }
            }
            i++;
        }
        return input;
    }
}