package logic;

import java.util.ArrayList;

/**
 * Created by asha on 26/04/16.
 */
public class Combine implements Combinable {


    @Override
    //returns best of two solutions with one assignment improved
    public CandidateSolution combine(CandidateSolution cs1, CandidateSolution cs2) {
        //return null;
        CandidateSolution result;
        CandidateSolution substitute;
        if (cs1.getFitness()>cs2.getFitness()) {
            result = new CandidateSolution(cs1);
            substitute = new CandidateSolution(cs2);
        }
        else {
            result = new CandidateSolution(cs2);
            substitute = new CandidateSolution(cs1);
        }
        //ArrayList names = new ArrayList(result.getStudentNames());
        ArrayList<String> names = (ArrayList) result.getStudentNames().clone();
        CandidateSolution tries = new CandidateSolution(result);
        for (int i=0; i<names.size(); i++)
        {
            if (result.getAssignmentFor(names.get(i)).getEnergy()
                    > substitute.getAssignmentFor(names.get(i)).getEnergy()) {
                tries.getAssignmentFor(names.get(i)).
                        swapProjects(substitute.getAssignmentFor(names.get(i)));
                if (tries.getEnergy()<result.getEnergy())
                    return tries;
                else tries = new CandidateSolution(result);
            }
        }
        return result;
    }
}
