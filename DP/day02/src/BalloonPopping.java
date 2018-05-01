import java.util.Arrays;

public class BalloonPopping {

        /*
    Top down
     Identify subproblems: What is the balloon we should pop next/last? (we're just iterating through though)
     guess solution: Guess thath we should pop each balloon next
     recurrence relation: memeo[start][end] = Math.max(memeo[start][end], coins)
     memoize/DP array: memeo :D
     solve original problem: memeo[0][B.length-1]

     Time Complexity: O(n^2) n = B.length
     Space Complexity: O(n^2)

     */

    public static int maxPoints(int[] B) {
        if (B == null || B.length == 0) return 0;

        int[][] memeo = new int[B.length][B.length];

        int end, coins;

        for (int len = 1; len <= B.length; len++) {     //Go through the vertical part of memeo
            for (int start = 0; start <= B.length - len; start++) { //Go through horizontal part of memeo
                end = start + len - 1;
                for (int i = start; i <= end; i++) {    //Iterate through the part of memeo we're looking at
                    coins = B[i] * getVal(B, start - 1) * getVal(B, end + 1);

                    if(i != start){
                        coins += memeo[start][i - 1];    //Go left
                    }
                    if(i != end){
                        coins += memeo[i + 1][end];     //Go right
                    }

                    memeo[start][end] = Math.max(memeo[start][end], coins);
                }
            }
        }
        return memeo[0][B.length - 1];
    }

    private static int getVal(int[] B, int i) { // Was sad with -1 (and len)
        if (i < 0 || i >= B.length) {
            return 1;
        }
        return B[i];
    }


}
