public class LongestIncreasingSubsequence {

    // Runtime: O(n^2)
    // Space: O(2n) = O(n)

    //Subproblem -problems you have to build up
    // Recurrence Relation: how the sub problems merge/how you decide between them


    // Subproblems: Find the longest increasing subsequence of the locations in front of where you currently are
    // Guess: Iterating through values later to find which ones you're less than
    // Recurrence Relation: Checking to see if the temp is higher than the total
    // Memoize: Using B to save what the total is at each location in A
    // Solution: myLIS(A, 0, B)

    public static int LIS(int[] A) {
        // TODO
        if(A.length == 0){
            return 0;
        }
        int[] B = new int[A.length];
        return myLIS(A, 0, B);
    }

    private static int myLIS(int[] A, int loc, int[] B){
        if(loc == A.length-1){
            B[loc] = 1;
            return 1;
        }
        if(B[loc] != 0){
            return B[loc];
        }
        int total = 1;
        int temp;
        for(int i = loc+1; i <= A.length-1; i++){
            if(A[i] > A[loc]) {
                temp = myLIS(A, i, B) + 1;
                if (temp > total) {
                    total = temp;
                }
            }

        }
        if(loc == 0){
            for(int i = loc+1; i <= A.length-1; i++){
                temp = myLIS(A, i, B);
                if(temp > total){
                    total = temp;
                }
            }
        }
        B[loc] = total;
        return B[loc];
    }
}