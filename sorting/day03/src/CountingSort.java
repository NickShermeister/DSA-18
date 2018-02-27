public class CountingSort {

    /**
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + k)
     * k is the largest number in the array
     *
     * k: maximum element in array A
     */
    static void countingSort(int[] A) {
        int max = -1;
        for (int x : A){
            if (max < x){
                max = x;
            }
        }
        int[] tracker = new int[max+1];

        for(int y : A){
            tracker[y]++;
        }
        int loc = 0;
        for(int z = 0; z < tracker.length; z++){
            while(tracker[z] > 0){
                A[loc] = z;
                tracker[z]--;
                loc++;
            }
        }
        return ;
    }

}
