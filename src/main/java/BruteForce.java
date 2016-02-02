import model.AllocationResult;
import model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * BruteForce feasibility
 */
public class BruteForce {
    public static ArrayList<ArrayList<Integer>> permute(Integer[] nums) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<ArrayList<Integer>>();
        if (nums == null || nums.length == 0) {
            return rst;
        }
        ArrayList<Integer> list = new ArrayList<Integer>();
        helper(rst, list, nums);
        return rst;
    }

    private static void helper(ArrayList<ArrayList<Integer>> rst, ArrayList<Integer> list, Integer[] projectIds) {
        if (list.size() == projectIds.length) {
            rst.add(new ArrayList<Integer>(list));
            return;
        }

        for (int i = 0; i < projectIds.length; i++) {
            if (list.contains(projectIds[i])){
                continue;
            }
            list.add(projectIds[i]);
            helper(rst,list,projectIds);
            list.remove(list.size() - 1);
        }
    }
    // allocate projects to student, the index of students are fix, ofc you can reverse the relation and build backwards
    public static int cost(List<Integer> allocation, List<Student> students) {
        if (allocation == null || allocation.size() == 0) {
            return Integer.MAX_VALUE;
        }
        int score = 0;
        for (int i= 0; i < students.size(); i++) {
            score += students.get(i).getGrudge(allocation.get(i));
        }
        return score;
    }

    public static AllocationResult minCost(ArrayList<ArrayList<Integer>> states, List<Student> students) {
        int min = Integer.MAX_VALUE;
        List<Integer> best = null;
        for (List<Integer> state: states) {
            int score = cost(state, students);
            if (min > score) {
                min = score;
                best = state;
            }
        }
        return new AllocationResult(min, best);
    }

}
