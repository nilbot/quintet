package model;

import java.util.*;

/**
 * DataSource:
 */
public interface DataSource {
    Student RandomStudent() throws Exception;
    Student GetStudent(String studentName) throws Exception;
    Set<Project> ProjectRepo() throws Exception;
    Map<String,Student> StudentRepo() throws Exception;
    boolean Ready();
}
