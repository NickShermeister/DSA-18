import java.util.Arrays;
import java.util.Collections;
import java.util.concurrent.ThreadLocalRandom;

public class QuickSort extends SortAlgorithm {

    private static final int INSERTION_THRESHOLD = 10;
    private void shuffleArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(i+1);
            swap(array, i, randIndex);
        }
    }

    /**
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(nlogn)
     *
     * Space-complexity: O(nlogn)
     */
    @Override
    public int[] sort(int[] array) {
        // TODO: Sort the array. Make sure you avoid the O(N^2) runtime worst-case
        Collections.shuffle(Arrays.asList(array));
        quickSort(array, 0, array.length-1);
        return array;
    }

    /**
     * Partition the array around a pivot, then recursively sort the left and right
     * portions of the array. A test for this method is provided in `SortTest.java`
     * Optional: use Insertion Sort if the length of the array is <= INSERTION_THRESHOLD
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public void quickSort(int[] a, int lo, int hi) {
        if (lo < hi) {
            int p = partition(a, lo, hi);
            quickSort(a, lo, p-1);
            quickSort(a, p+1, hi);
        }
        else {
            return;
        }
    }


    /**
     * Given an array, choose the array[low] element as the "pivot" element.
     * Place all elements smaller than "pivot" on "pivot"'s left, and all others
     * on its right. Return the final position of "pivot" in the partitioned array.
     *
     * @param lo The beginning index of the subarray being considered (inclusive)
     * @param hi The ending index of the subarray being considered (inclusive)
     */
    public int partition(int[] array, int lo, int hi) {

        int[] subarray = Arrays.copyOfRange(array, lo+1, hi+1);
        int pivot = array[lo];
        int[] lowarray = new int[array.length];
        int lowsize = 0;
        int[] higharray = new int[array.length];
        int highsize = 0;
        for(int x : subarray){
            if(x < pivot){      //go to lowarray
                lowarray[lowsize] = x;
                lowsize++;
            }
            else {
                higharray[highsize] = x;
                highsize++;
            }
        }
        for(int x = 0; x < lowsize; x++){
            array[x+lo] = lowarray[x];
        }
        array[lowsize+lo] = pivot;
        for(int y = lowsize; y < highsize+lowsize; y++){
            array[y+1+lo] = higharray[y-lowsize];
        }

        return lowsize+lo;
    }

}
