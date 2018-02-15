
public class InsertionSort extends SortAlgorithm {
    /**
     * Use the insertion sort algorithm to sort the array
     *
     * TODO
     * Best-case runtime: O(n)
     * Worst-case runtime: O(n^2)
     * Average-case runtime: O(n^2)
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {

        int temp = 0;
        for(int x = 1; x < array.length; x++){
//            System.out.println(x);
            if(x > 0 && array[x] < array[x-1]){
                temp = array[x-1];
                array[x-1] = array[x];
                array[x] = temp;
                x= x-2;
            }
        }
        return array;
    }
}
