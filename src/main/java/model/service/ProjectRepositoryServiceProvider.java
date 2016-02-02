package model.service;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

/**
 * ProjectRepositoryServiceProvider
 */
public class ProjectRepositoryServiceProvider implements RepositoryService{

    private HashMap<Integer,String> map;
    private Random random;

    @Override
    public String get(int key) {
        return map.get(key);
    }

    @Override
    public String getRandom() {
        random = new Random();
        return map.get(random.nextInt(map.keySet().size()+1));
    }

    @Override
    public void put(Integer key, String value) {
        map.put(key,value);
    }

    @Override
    public Set keySet() {
        return map.keySet();
    }

    public ProjectRepositoryServiceProvider() {
        map = new HashMap<Integer, String>();
    }
}
