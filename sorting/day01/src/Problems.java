import java.util.*;

public class Problems {

    private static PriorityQueue<Integer> minPQ() {
        return new PriorityQueue<>(11);
    }

    private static PriorityQueue<Integer> maxPQ() {
        return new PriorityQueue<>(11, Collections.reverseOrder());
    }

    private static double getMedian(List<Integer> A) {
        double median = (double) A.get(A.size() / 2);
        if (A.size() % 2 == 0)
            median = (median + A.get(A.size() / 2 - 1)) / 2.0;
        return median;
    }

    // Runtime of this algorithm is O(N^2). Sad! We provide it here for testing purposes
    public static double[] runningMedianReallySlow(int[] A) {
        double[] out = new double[A.length];
        List<Integer> seen = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int j = 0;
            while (j < seen.size() && seen.get(j) < A[i])
                j++;
            seen.add(j, A[i]);
            out[i] = getMedian(seen);
        }
        return out;
    }


    /**
     *
     * @param inputStream an input stream of integers
     * @return the median of the stream, after each element has been added
     */
    public static double[] runningMedian(int[] inputStream) {
        double[] runningMedian = new double[inputStream.length];
        double above;
        double below;
        double temp;
        PriorityQueue<Integer> lows = maxPQ();
        PriorityQueue<Integer> highs = minPQ();
        if(inputStream.length == 0){
        }
        else if(inputStream.length == 1){
            runningMedian[0] = inputStream[0];
        }
        else {
            runningMedian[0] = inputStream[0];
            if(inputStream[0] > inputStream[1]){
                highs.offer(inputStream[0]);
                lows.offer(inputStream[1]);
            }
            else {
                highs.offer(inputStream[1]);
                lows.offer(inputStream[0]);
            }
            below = lows.peek();
            above = highs.peek();
            temp = ((below +above))/(2.0);
            runningMedian[1] = temp;
            for(int x = 2; x < inputStream.length; x++){
                if(inputStream[x] < below){
                    lows.offer(inputStream[x]);
                    if (lows.size() > highs.size()+1){
                        highs.offer(lows.poll());
                        above = highs.peek();
                    }

                    below = lows.peek();
                }
                else if (inputStream[x] > above){
                    highs.offer(inputStream[x]);
                    if (highs.size() >= lows.size()+1){
                        lows.offer(highs.poll());
                        below = lows.peek();
                    }

                    above = highs.peek();
                }
                else {
                    if(lows.size() <= highs.size()){
                        lows.offer(inputStream[x]);

                    }
                    else {
                        highs.offer(inputStream[x]);

                    }
                    below = lows.peek();
                    above = highs.peek();
                }
                if (x%2 == 1){
                    temp = ((below +above))/(2.0);
                    runningMedian[x] = temp;
                }
                else {
                    runningMedian[x] = below;
                }
//                System.out.println(below + "  " + above);
//                System.out.println(lows.size() + "    " + highs.size());
            }

        }
//        System.out.println(Arrays.toString(runningMedian));

        return runningMedian;
    }

}
