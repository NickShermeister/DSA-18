import java.util.HashMap;
import java.util.Map;

public class LargestSubArray {
    static int[] largestSubarray(int[] nums) {
        // TODO
        //Keep a running sum
        //Use a hashmap to keep linear time
        //whenever the sum differs by more than the absolute value of 1, make that down in a separate key
        Map<Integer, Integer> hmap = new HashMap<Integer, Integer>();
        int runningval = 0;
        int longest = -1;
        int longest_end_loc = -1;
        int longest_val = -1;

        for (int x = 0; x < nums.length; x++){
            if(nums[x] == 0){
                runningval = runningval -1;
            }
            else {
                runningval = runningval + 1;
            }

            if(runningval == 0){
                longest = x+1;
                longest_end_loc = x;
                longest_val = 0;
            }
            else {
                if (hmap.containsKey(runningval)) {
                    if (x - hmap.get(runningval) > longest) {
                        longest = x - hmap.get(runningval)+1;
                        longest_end_loc = x;
                        longest_val = runningval;
                    }
                } else {
                    hmap.put(runningval, x);
                }
            }


        }
        if(longest_val == 0){
            return new int[]{0, longest_end_loc};
        }
        else {
            return new int[]{hmap.get(longest_val)+1, longest_end_loc};
        }


    }
}
