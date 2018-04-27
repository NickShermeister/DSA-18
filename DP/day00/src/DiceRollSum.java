import java.util.HashMap;

public class DiceRollSum {

    // Runtime: O(Cn); C=6 therefore, O(n)
    // Space: O(n)

    // Subproblems: Removing one die roll value from the current value and go forward; each different value is a subproblem
    // Guess: You aren't guessing here? You're just systematically moving forward? Technically, it's each of the 6 paths
    // Recurrence Relation: Lines 36-38; calling it recursively with 1 through 6 subtracted and summing the totals.
    // Memoize: The memeo of HashMap, saving the solved numbers
    // Solution: Memeo.get(N) contains the solution at the end? recursivePart(N, memeo)

    public static int diceRollSum(int N ) {
        // TODO
        int total = 0;
        HashMap<Integer, Integer> memeo = new HashMap<Integer, Integer>();
        memeo.put(1, 1);

        total = recursivePart(N, memeo);
        return total;
    }

    private static int recursivePart(int N, HashMap<Integer, Integer> memeo){
        int total = 0;
        if(N < 0){
            return 0;
        }
        if(N == 0){
            return 1;
        }
        if(memeo.containsKey(N)){
           return memeo.get(N);
        }

        for(int i = 6; i >= 1; i--){
            total += recursivePart(N-i, memeo);
        }
        memeo.put(N, total);
        return memeo.get(N);
    }

}
