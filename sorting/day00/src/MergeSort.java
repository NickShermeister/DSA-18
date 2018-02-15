import java.util.Arrays;

public class MergeSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;

    /**
     * This is the recursive step in which you split the array up into
     * a left and a right portion, sort them, and then merge them together.
     * Use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * TODO
     * Best-case runtime: O(nlog(n))
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity: O(n)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO
        if (array.length <= 1) {
            return array;
        }
        int[] left_array = Arrays.copyOfRange(array, 0, array.length/2);
        int[] right_array = Arrays.copyOfRange(array, array.length/2, array.length);
        left_array = sort(left_array);
        right_array = sort(right_array);
        return merge(left_array, right_array);
    }

    /**
     * Given two sorted arrays a and b, return a new sorted array containing
     * all elements in a and b. A test for this method is provided in `SortTest.java`
     */
    public int[] merge(int[] a, int[] b) {
        int lcount = 0;
        int rcount = 0;
        int[] full_array = new int[a.length + b.length];
        while(lcount < a.length && rcount < b.length){
//            System.out.println("lcount " + a[lcount] + "   rcount: " + b[rcount]);
            if (a[lcount] < b[rcount]){
                full_array[lcount+rcount] = a[lcount];
                lcount++;
            }
            else {
                full_array[lcount+rcount] = b[rcount];
                rcount++;
            }
        }
        while(lcount < a.length){
            full_array[lcount+rcount] = a[lcount];
            lcount++;
        }
        //the right array has things left
        while (rcount < b.length){
            full_array[lcount+rcount] = b[rcount];
            rcount++;
        }
        return full_array;
    }

}
