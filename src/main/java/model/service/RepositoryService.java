package model.service;

import java.util.Set;

/**
 * Repo service
 */
public interface RepositoryService {
    String get(int key);
    String getRandom();
    void put(Integer key, String value);
    Set keySet();
}
