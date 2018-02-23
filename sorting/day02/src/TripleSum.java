import java.util.Arrays;

public class TripleSum {

    static int tripleSum(int arr[], int sum) {
        // TODO
        //Sort
        //Loop through every value looking for a pair that matches the right number
        Arrays.sort(arr);
        int first, second, third, goal;
        int left, right;
        int total_sums = 0;
        Boolean unbreakable = true;
        for (int x = 0; x < arr.length; x++){
            first = arr[x];
            goal = sum - arr[x];
            left = 0;
            right = arr.length-1;
            while (unbreakable){
                System.out.println("right: " + right + "      left: " + left);
                if(left == x){
                    left++;
                }
                else if (right == x){
                    right--;
                }
                if(left >= right) {
                    unbreakable = false;
                }
                else if(arr[left] + arr[right] < goal){
                    left++;
                }
                else if (arr[left] + arr[right] > goal){
                    right--;
                }
                else {
                    total_sums++;
                    left++;
                }


            }
            unbreakable = true;
        }
        return total_sums/3;
    }
}
