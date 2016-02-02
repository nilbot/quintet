package model;

import model.service.ProjectRepositoryServiceProvider;
import model.service.RepositoryService;

/**
 * Student
 */
public class Student {
    public String Name() {
        return name;
    }
    public String Project(int priority) {
        if (priority <= preferences.length - 1) {
            return RepoService.get(preferences[priority - 1]);
        } else {
            return RepoService.getRandom();
        }
    }

    public int getGrudge(int projectId) {
        for (int i = 0; i < preferences.length; i++) {
            if (projectId == preferences[i]) {
                return i;
            }
        }
        return preferences.length + 1;
    }


    private String name;
    // position 0 is reserved for x marks
    private int[] preferences;


    private RepositoryService RepoService;

    public Student(String name, int[] projectPreferences, ProjectRepositoryServiceProvider service) {
        name = name;
        preferences = projectPreferences;
        RepoService = service;
    }
}
