package model;

import static model.service.UtilityService.TheRNG;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class StudentTest {

    private Student s;
    private Project testProject_1;
    private List<Project> testProject_rest;
    private Student preped_s;

    @Before
    public void setUp() throws Exception {
        testProject_1 = new Project("Test The Project of the First Non-Sense");
        s = new Student("test student");
        testProject_rest = new ArrayList<Project>();
        Random r = new Random();
        for (int i = 0; i < 50; i++)
            testProject_rest.add(new Project("test project another " + r.nextInt
                    ()));
        preped_s = new Student("I'm prepared");
        for (Project p : testProject_rest){
            preped_s.addProject(p);
        }
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testAddProject() {
        int lastIndex = s.getNumberOfPreferences() - 1;
        s.addProject(testProject_1);
        assertEquals("expected addProject to append object at the end of the array.",
                lastIndex + 1, s.getProjects().indexOf(testProject_1));
        for (Project project : testProject_rest) {
            s.addProject(project);
        }
        assertEquals("expect to add %d projects.",
                testProject_rest.size()+1, s.getNumberOfPreferences());
    }

    @Test
    public void testGetNumberOfStatedPreferences() {
        assertEquals(0, s.getNumberOfStatedPreferences());
        s.addProject(testProject_1);
        assertEquals(0, s.getNumberOfStatedPreferences());
        for (Project p: testProject_rest) {
            s.addProject(p);
        }
        assertEquals(0, s.getNumberOfStatedPreferences());
        s.setNumberOfStatedPreferences(100);
        assertEquals(100, s.getNumberOfStatedPreferences());
        s.preassignProject(testProject_1);
        assertEquals(1, s.getNumberOfStatedPreferences());
    }

    @Test
    public void testGetOrderedPreferences() throws Exception {
        for (Project str: testProject_rest){
            s.addProject(str);
        }
        assertEquals("expect equal",testProject_rest,s.getOrderedPreferences());
    }

    @Test
    public void testGetStudentName() throws Exception {
        assertEquals("expect equal","test student",s.getStudentName());
    }

    @Test
    public void testHasPreassignedProject() throws Exception {
        s.preassignProject(testProject_1);
        assertEquals("expect equal",true,s.hasPreassignedProject());
    }

    @Test
    public void testPreassignProject() throws Exception {
        s.preassignProject(testProject_1);
        s.preassignProject(s.getRandomPreference());
        assertEquals("there shouldn't be 2 preassigned project",
                1,s.getOrderedPreferences().size());
        assertEquals("expect true",true,s.hasPreassignedProject());
    }

    @Test
    public void testSetNumberOfStatedPreferences() throws Exception {
        Integer t = 5;
        s.setNumberOfStatedPreferences(t);
        assertEquals("expect 5",5,s.getNumberOfStatedPreferences());
    }

    @Test
    public void testHasReference() throws Exception {
        s.addProject(testProject_1);
        assertEquals("should have this assignment",
                true,s.hasReference(testProject_1));
    }

    @Test
    public void testGetRandomPreference() throws Exception {
        int seed = 123;
        int num = 1;
        List<Project> expected = TheRNG().TestRandomPreferenceUtility(preped_s,
                seed, num);
        TheRNG().setSeed(seed);
        Project got = preped_s.getRandomPreference();
        assertEquals("should equal",expected.get(0),got);
    }

    @Test
    public void testGetNumberOfPreferences() throws Exception {
        assertEquals("should equal",testProject_rest.size(),preped_s.getNumberOfPreferences());
    }

    @Test
    public void testGetProjects() throws Exception {

    }

    @Test
    public void testGetRanking() throws Exception {

    }
}
