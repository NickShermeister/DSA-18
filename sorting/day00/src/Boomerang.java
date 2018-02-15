import java.util.HashMap;
import java.util.Map;

public class Boomerang {

    public static int numberOfBoomerangs(int[][] points) {
        // TODO
        // Get distance between points
        //Find equal distances
        //boomerangs = n*(n-1)
        //Use hashmap to save distances
        HashMap<Double, Integer> mapping;
        double dist;
        int oldval;
        int total = 0;
        for (int[] x : points){
            mapping = new HashMap<Double, Integer>();
            for(int[] y : points){
                dist = Math.pow((x[0]-y[0]), 2) + Math.pow((x[1]-y[1]), 2);
                try {
                    oldval = mapping.get(dist);
                    mapping.put(dist, oldval+1);
                }
                catch (NullPointerException e) {
                    mapping.put(dist, 1);
                }

            }
            for(Map.Entry<Double, Integer> entry: mapping.entrySet()) {
                System.out.println(entry.getValue());
                total = total + entry.getValue()*(entry.getValue()-1);
            }
            System.out.println("Time for a new map!");
        }
        return total;
    }
}

