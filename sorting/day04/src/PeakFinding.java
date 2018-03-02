import java.util.Arrays;

public class PeakFinding {

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakOneD(int i, int[] nums) {
        if (i > 0 && nums[i] < nums[i - 1])
            return -1;
        if (i < nums.length - 1 && nums[i] < nums[i + 1])
            return 1;

        return 0;
    }

    // Return -1 if left is higher, 1 if right is higher, 0 if peak
    private static int peakX(int x, int y, int[][] nums) {
        if (x > 0 && nums[y][x] < nums[y][x - 1])
            return -1;
        if (x < nums[0].length - 1 && nums[y][x] < nums[y][x + 1])
            return 1;
        return 0;
    }

    // Return -1 if up is higher, 1 if down is higher, 0 if peak
    private static int peakY(int x, int y, int[][] nums) {
        if (y > 0 && nums[y][x] < nums[y - 1][x])
            return -1;
        if (y < nums.length - 1 && nums[y][x] < nums[y + 1][x])
            return 1;
        return 0;
    }

    // These two functions return the index of the highest value along the X or Y axis, with the given
    // value for the other axis. Searches between hi (exclusive) and lo (inclusive)

    //FOR ROWS
    private static int maxXIndex(int y, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int x = lo; x < hi; x++) {
            if (maxIndex == -1 || nums[y][x] > nums[y][maxIndex])
                maxIndex = x;
        }
        return maxIndex;
    }

    //FOR COLUMNS
    private static int maxYIndex(int x, int lo, int hi, int[][] nums) {
        int maxIndex = -1;
        for (int y = lo; y < hi; y++) {
            if (maxIndex == -1 || nums[y][x] > nums[maxIndex][x])
                maxIndex = y;
        }
        return maxIndex;
    }


    public static int findOneDPeak(int[] nums) {
        int low = 0;
        int high = nums.length-1;
        int med = nums.length/2;
        int val;
        if(nums.length < 2){
            return nums.length;
        }
        while(low != med && high != med){
            val = peakOneD(med, nums);
            if(val == 0){
                return med;
            }
            else if (val == 1){     //right is higher
                if(nums[med] >= nums[high]){
                    high = med;
                }
                else {
                    low = med;
                }
            }
            else if (val == -1){    //left is higher
                if(nums[med] >= nums[low]){
                    low = med;
                }
                else {
                    high = med;
                }
            }
            med = (high+low)/2;
        }
        if(low == med && med > 0){
            return nums.length-1;
        }
        else {
            return 0;
        }
    }

    public static int[] findTwoDPeak(int[][] nums) {
        // TODO
        //RETURN [ROW, COL]
        int lowcol = 0;
        int highcol = nums[0].length;
        int lowrow = 0;
        int highrow = nums.length;
        int medcol = (highcol + lowcol)/2;
        int medrow = (highrow + lowrow)/2;
        boolean curr_search = true;
        int temp, tempval;
        int[] stuff = new int[2];

        stuff[0] = medrow;
        stuff[1] = medcol;


        while(lowcol < medcol || lowrow < medrow ){
            if(curr_search){ //do a column search

                temp = maxYIndex(medcol, lowrow, highrow, nums);
                tempval = peakX(medcol, temp, nums);

                if(tempval == 0){
                    System.out.println("finished in col   ");
                    stuff[0] = temp;
                    System.out.println(Arrays.toString(stuff));
                    return stuff;
                }
                else if (tempval == 1){     //right is bigger
                    lowcol = medcol+1;
                }
                else {
                    highcol = medcol;
                }

                medcol = (lowcol + highcol)/2;
                stuff[1] = medcol;
            }

            else {           //do a row search
                temp = maxXIndex(medrow, lowcol, highcol, nums);
                tempval = peakY(temp, medrow, nums);

                if(tempval == 0){
                    System.out.println("finished in row");
                    stuff[1] = temp;
                    System.out.println(Arrays.toString(stuff));
                    return stuff;
                }

                else if (tempval == 1){     //down is bigger
                    lowrow = medrow+1;
                }

                else {
                    highrow = medrow;
                }


                medrow = (lowrow + highrow)/2;
                stuff[0] = medrow;
            }

            curr_search = !curr_search;

        }
        System.out.println("hi");
        System.out.println(Arrays.toString(stuff));
        return stuff;
    }

}
