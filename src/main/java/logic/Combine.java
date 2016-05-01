package logic;

import java.util.*;

import config.CombineConfig;
import data.GenePool;
import model.CandidateAssignment;
import model.GeneticCandidateSolution;

import static model.service.UtilityService.TheRNG;

/**
 * combine:
 */
public class Combine implements Combinable {
    @Override
    public GenePool
    combine(CombineConfig cfg, GenePool<GeneticCandidateSolution> data, int nChild) {
        int originalSz = data.size();
        while (data.size() < originalSz+nChild) {
            List<GeneticCandidateSolution> parents = new ArrayList<>();
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
                    husband = data.getBest();
                    wife =  data.getBest();
                    break;
                case CombineConfig.RANDOM:
                    parents = data.getRandomCouple();
                    husband = parents.get(0);
                    wife = parents.get(1);
                    break;
            }
            GeneticCandidateSolution rst = null;
            switch (cfg.Strategy) {
                case CombineConfig.CROSSOVER:
                    rst = crossover(husband, wife);
                    break;
                case CombineConfig.SIFT:
                    rst = sift(husband, wife);
                    break;
            }

            if (cfg.allowMutation()) {
                rst = cfg.Mutagen().mutate(rst, 1/(double)data.size());
            }

            if (rst == null) {
                throw new RuntimeException("Couldn't get a result from combine");
            }
            data.addToPool(rst);
        }
        return data;
    }

    protected GeneticCandidateSolution
    crossover(GeneticCandidateSolution father, GeneticCandidateSolution
            mother) {
        if (father==null){
            throw new RuntimeException("father null");
        }
        if (mother == null) {
            throw new RuntimeException("mother null");
        }
        List<CandidateAssignment> lf = father.listOfAssignments();
        if (lf == null || lf.size() == 0) {
            throw new RuntimeException("lf null");
        }
        List<CandidateAssignment> lm = new ArrayList<>();
        for (CandidateAssignment ca : lf) {
            CandidateAssignment as = mother.getAssignmentFor(ca.getStudentEntry().getStudentName());
            lm.add(as);
        }
        int pivot = TheRNG().nextInt(lf.size());
        List<CandidateAssignment> fh = lf.subList(0, pivot);
        List<CandidateAssignment> mh = lm.subList(pivot, lm.size());
        if (fh.size()+mh.size()!=lf.size()){
            throw new RuntimeException(String.format("pivot split merge sz " +
                    "incorret %d, " +
                    "want %d",fh.size()+mh.size(),lf.size()));
        }
        GeneticCandidateSolution rst = new GeneticCandidateSolution(fh, mh);
        return rst;
    }
    protected GeneticCandidateSolution
    sift(GeneticCandidateSolution father, GeneticCandidateSolution
            mother) {
        int scoreFather = father.getEnergy();
        int scoreMother = mother.getEnergy();
        List<CandidateAssignment> lf = father.listOfAssignments();
        List<CandidateAssignment> lm = new ArrayList<>();
        for (CandidateAssignment ca : lf) {
            lm.add(mother.getAssignmentFor(ca.getStudentEntry()
                    .getStudentName()));
        }
        List<CandidateAssignment> rstList = new ArrayList<>();
        for (int idx = 0; idx < lf.size(); idx++) {
            CandidateAssignment af = lf.get(idx);
            int prevf = af.getEnergy();
            CandidateAssignment am = lm.get(idx);
            int prevm = am.getEnergy();
            rstList.add(prevf < prevm ? af : am);
        }
        GeneticCandidateSolution rst = new GeneticCandidateSolution(rstList);
        int scoreOff = rst.getEnergy();

        if (scoreOff < scoreFather || scoreOff < scoreMother) return rst;
        else return scoreFather < scoreMother? father:mother;
    }
}
