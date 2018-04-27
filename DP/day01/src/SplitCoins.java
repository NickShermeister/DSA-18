import java.util.Arrays;

public class SplitCoins {

    /*
    Bottom Up
     Identify subproblems: looking at the next spot in the memeo
     guess solution: Where the first true appears in the coins.leng
          recurrence relation:
     memoize/DP array: Boolean table of if the sum at a specific j includes i if a sum is achieved of that value
     solve original problem: diff = cointotal - 2*j;

     Time Complexity: O(n^2) n = coins.length
     Space Complexity: O(n^2)

     */

    public static int splitCoins(int[] coins) {
        // TODO
        int cointotal = 0;
        for (int x : coins){
            cointotal += x;
        }

        boolean[][] memeo = new boolean[coins.length+1][cointotal+1];

        for(int i = 0; i <= coins.length; i++){
            memeo[i][0] = true;
        }
        for(int i = 1; i <= cointotal; i++){
            memeo[0][i] = false;
        }


        // bottom-up table filling
        for (int i = 1; i <= coins.length; i++)
        {
            for (int j = 1; j <= cointotal; j++)
            {
                memeo[i][j] = memeo[i - 1][j];

                if (coins[i - 1] <= j)
                    memeo[i][j] |= memeo[i - 1][j - coins[i - 1]];
            }
        }


        int diff = Integer.MAX_VALUE;

        // Find the largest j such that dp[n][j] because it is a balancing thing
        for (int j = cointotal / 2; j >= 0; j--)
        {

            if (memeo[coins.length][j] == true)
            {
                diff = cointotal - 2 * j;   //Aka if it's the middle, then there's a 0 difference
                break;
            }
        }
        return diff;


    }





    private static int dPfailed(int[] memeo, int[] coins, int loc){
        if(loc == coins.length-1){
            memeo[loc] = coins[loc];
            return memeo[loc];
        }
        int temp;
        if(memeo[loc+1] == Integer.MAX_VALUE){
            temp = dPfailed(memeo, coins, loc+1);
        }
        else {
            temp = memeo[loc+1];
        }

        if(temp < 0){
            memeo[loc] = temp + coins[loc];
        }
        else {
            memeo[loc] = temp - coins[loc];
        }

        return memeo[loc];
    }
}
