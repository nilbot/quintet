package model.service;

import model.*;

import java.util.*;

/**
 * UtilityService:
 */
public class UtilityService {
    private Random r;
    private UtilityService() {r = new Random();}
    private static UtilityService instance;
    public static UtilityService TheRNG() {
        if (instance == null)instance = new UtilityService();
        return instance;
    }
    public int nextInt(int sz){return instance.r.nextInt(sz);}
    public void setSeed(int seed){instance.r.setSeed(seed);}

    // ==== for test ==== //
    public List<Project> TestRandomPreferenceUtility(Dodgy input,
                                                    Integer Seed,
                                                    Integer number){
        List<Project> rst = new ArrayList<Project>();
        this.setSeed(Seed);
        for (int i = 0; i<number;i++) {
            rst.add(input.getRandomPreference());
        }
        return rst;
    }
}
