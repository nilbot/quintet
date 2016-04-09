package logic;

import model.Student;

import java.util.Comparator;
import java.util.List;

class FitnessComparator implements Comparator<List<Student>> {
    // the fitness function atm is totally useless and doesn't return any
    // sensible fitness rating, but I just need the comparator to return
    // *some* value,
    public int compare(List<Student> x, List<Student> y) {
        if (fitness(x) < fitness(y)) {
            return -1;
        }
        if (fitness(x) > fitness(y)) {
            return 1;
        }
        return 0;
    }

    // TODO: make this not return sensible values
    // the comparator has it's own fitness function
    private int fitness(List<Student> solution) {
        return (int) Math.floor(Math.random()) * 100;
    }
}