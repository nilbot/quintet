package logic;

import app.Config;
import model.DataSource;
import model.GeneticCandidateSolution;
import model.Student;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

/**
 * CombineTest:
 */
public class CombineTest {
    @Test
    public void crossover() throws Exception {
        DataSource data = new Config("Memory","input.tsv","SimulatedAnnealing")
                .GetData();
        Collection<Student> studs = data.StudentRepo().values();
        GeneticCandidateSolution f = new GeneticCandidateSolution(studs);
        GeneticCandidateSolution m = new GeneticCandidateSolution(studs);
        Combine c = new Combine();
        GeneticCandidateSolution o = c.crossover(f, m);
        assertEquals("not equal to father",o.listOfAssignments().size(), f
                .listOfAssignments()
                .size());
        assertEquals("not equal to mother",o.listOfAssignments().size(), m
                .listOfAssignments()
                .size());
        System.out.println(f.getEnergy());
        System.out.println(m.getEnergy());
        System.out.println(o.getEnergy());
    }

}