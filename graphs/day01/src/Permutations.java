import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Permutations {

    private static void backtrack(LinkedList<Integer> currr, Set<Integer> unuse, List<List<Integer>> subsets) {
        //hi
        if (unuse.isEmpty()) //If there are no unused integers, add the completed list to the subset
            subsets.add(new LinkedList<>(currr));
        for (int u : new LinkedList<>(unuse)) {    //For each integer in unused
            currr.addLast(u);
            unuse.remove(u);
            backtrack(currr, unuse, subsets);
            unuse.add(u);
            currr.removeLast();
        }
    }

    public static List<List<Integer>> permutations(List<Integer> A) {
        // TODO
        List<List<Integer>> permutations = new LinkedList<>(); //aka subsets
        Set<Integer> unused = new HashSet<>(A);
        backtrack(new LinkedList<Integer>(), unused, permutations);
        return permutations;
    }

}
