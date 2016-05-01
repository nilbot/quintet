package model;

import presentation.InputMeta;

import java.util.*;

/**
 * DataSource:
 */
public interface DataSource {
    Student RandomStudent();
    Student GetStudent(String studentName);
    Set<Project> ProjectRepo();
    Map<String,Student> StudentRepo();
    boolean Ready();

    InputMeta getMeta();
}
