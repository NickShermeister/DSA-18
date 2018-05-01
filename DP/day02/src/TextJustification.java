import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TextJustification {

    /*
    Top down
     Identify subproblems: Where should I place the next line? (which place is the best place to put it?)
     guess solution: The next line should go after (this) word
     recurrence relation: If we put the next line here, the minimum number of lines will be created
     memoize/DP array:
     solve original problem:

     Time Complexity: O()
     Space Complexity: O()

     */

    private static double costFunc(String[] words, int lo, int hi, int m) {
        if (hi <= lo)
            throw new IllegalArgumentException("Hi must be higher than Lo");
        int length = hi-lo-1; // account for spaces;
        for (int i = lo; i < hi; i++) {
            length += words[i].length();
        }
        if (length > m)
            return Double.POSITIVE_INFINITY;
        return Math.pow(m-length, 3);
    }

    public static List<Integer> justifyText(String[] w, int m) {

        if(w.length == 0){
            return new ArrayList<>();
        }
        List<Integer> fin = new ArrayList<>();

        double[] memeo = new double[w.length];
        int[] nextbreak = new int[w.length];
        for(int i = 0; i < w.length; i++){
            nextbreak[i] = Integer.MIN_VALUE;
            memeo[i] = Double.POSITIVE_INFINITY;
        }

        dP(w, m, memeo, nextbreak, 0);

        int loc = 0;
        while(loc < w.length){
            fin.add(loc);
            loc = nextbreak[loc];
            System.out.println(loc);
        }

        return fin;
    }


    private static double dP(String[] w, int m, double[] memeo, int[] nextbreak, int curr){
        if(curr == w.length-1){
            memeo[curr] = costFunc(w, w.length-1, w.length, m);
            nextbreak[curr] = curr+1;
            return memeo[curr];
        }
        else if (curr >= w.length){
            return 0;
        }
        if(Double.isFinite(memeo[curr])){
            return memeo[curr];
        }


        double temp;
        for(int i = curr+1; i <= w.length; i++){

            temp = costFunc(w, curr, i, m) + dP(w, m, memeo, nextbreak, i);
            if(Double.isFinite(temp) && temp < memeo[curr]){
                memeo[curr] = temp;
                nextbreak[curr] = i;
            }
        }


        return memeo[curr];
    }



}