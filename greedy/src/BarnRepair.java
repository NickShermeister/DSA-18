import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BarnRepair {
    public static int solve(int M, int[] occupied) {
        // TODO
//        System.out.println("M is: " + M);
//        System.out.println("New Test");
//        for(int x : occupied){
//            System.out.println(x);
//        }
        Arrays.sort(occupied);
        int val;
        PriorityQueue<Integer> diff = new PriorityQueue<>();
        for(int i = 0; i < occupied.length-1; i++){
            val = occupied[i+1]-occupied[i]-1;
            if(val != 0){
//                System.out.println(val);
                diff.add(val);
            }
            else {
//                System.out.println("Non-0!");
            }
        }
//        System.out.println(diff[0]);
        int count = occupied.length;
//        System.out.println("Pre-polled count:" + count);
//        System.out.println("Diff size: " + diff.size());
        while(M-1 < diff.size()){
            count += diff.poll();
        }
//        System.out.println("Count is: " + count);
        return count;
    }
}