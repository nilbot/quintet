package logic;

import java.util.*;
import data.GenePool;
import model.GeneticCandidateSolution;

/**
 * combine:
 */
public class Combine implements Combinable {
    @Override
    public GenePool
    combine(CombineConfig cfg, GenePool data, int nChild) {
        while (nChild-- >= 0) {
            List<GeneticCandidateSolution> parents;
            GeneticCandidateSolution husband = null;
            GeneticCandidateSolution wife = null;
            switch (cfg.Class) {
                case CombineConfig.NOBLE:
                    parents = data.getCoupleFromTop25Percent();
                    husband = parents.get(0);
                    wife = parents.get(1);
                    break;
                case CombineConfig.MIDDLE:
                    parents = data.getCoupleFromMiddle50Percent();
                    husband = parents.get(0);
                    wife = parents.get(1);
                    break;
                case CombineConfig.POOR:
                    parents = data.getCoupleFromBottom25Percent();
                    husband = parents.get(0);
                    wife = parents.get(1);
                    break;
                case CombineConfig.ELITE:
                    husband = (GeneticCandidateSolution) data.getBest();
                    wife = (GeneticCandidateSolution) data.getBest();
                    break;
                case CombineConfig.RANDOM:
                    parents = data.getRandomCouple();
                    husband = parents.get(0);
                    wife = parents.get(1);
                    break;
            }
            GeneticCandidateSolution rst = null;
            switch (cfg.Strategy) {
                case CombineConfig.MATRIARCH:
                    rst = wife;
                    break;
                case CombineConfig.MERGEBEST:
                    rst = mergeBest(husband, wife);
                    break;
                case CombineConfig.PRIORITISE:
                    rst = prioritise(husband, wife);
                    break;
            }

            if (cfg.allowMutation()) {
                rst = cfg.Mutagen().mutate(rst);
            }

            if (rst == null) {
                throw new RuntimeException("Couldn't get a result from combine");
            }
            data.addToPool(rst);
        }
        return data;
    }

    private GeneticCandidateSolution
    mergeBest(GeneticCandidateSolution father, GeneticCandidateSolution
            mother) {
        return null;
    }
    private GeneticCandidateSolution
    prioritise(GeneticCandidateSolution father, GeneticCandidateSolution
            mother) {
        return null;
    }
}
