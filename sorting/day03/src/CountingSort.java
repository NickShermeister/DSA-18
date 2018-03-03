public class CountingSort {

    /**
<<<<<<< HEAD
     * Use counting sort to sort positive integer array A.
     * Runtime: O(n + k)
     * k is the largest number in the array
=======
     * Use counting sort to sort non-negative integer array A.
     * Runtime: TODO
>>>>>>> 6ef5cd060b5c2a2e260f93ca04e6a62dc4bfacbc
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
