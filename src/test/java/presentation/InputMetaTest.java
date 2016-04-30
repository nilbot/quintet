package presentation;

import app.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static model.service.UtilityService.TheRNG;
import static org.junit.Assert.*;

/**
 * InputMetaTest:
 */
public class InputMetaTest {
    private Config testConfig;
    private DataSource data;

    @Before
    public void setUp() throws Exception {
        testConfig = new Config("Memory", "input.tsv", "SimulatedAnnealing");
        data = testConfig.GetData();
        TheRNG().setSeed(124);
    }

    @After
    public void tearDown() throws Exception {
        data = null;
        testConfig = null;
    }
    @Test
    public void toJson() throws Exception {
        List<Student> ls = new ArrayList<Student>(data.StudentRepo().values());
        InputMeta testObj = new InputMeta(ls, data.ProjectRepo());
        String expected = testObj.getHottestProject();
        String allRaw = testObj.toJson();
        assertTrue(allRaw.contains("hottestProject"));
        String head = "\"hottestProject\": \"";
        int strBegin = allRaw.indexOf(head)+head.length();
        int strEnd = strBegin + expected.length();
        String got = allRaw.substring(strBegin,strEnd);
        assertTrue(expected.equals(got));
    }

}