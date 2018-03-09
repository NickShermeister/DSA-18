public class Problems {

    public static int leastSum(int[] A) {
        //TODO
        if(A.length == 0){
            return 0;
        }
        if (A.length == 1){
            return A[0];
        }

        int[] counter = new int[10];
        boolean add = true;
        for(int x : A){
            counter[x]++;
        }


        String one = "";
        String two = "";



        for(int x = 0; x < counter.length; x ++){
            while(counter[x]>0){
                if(add){
                    one = one + Integer.toString(x);
                    counter[x]--;
                }
                else {
                    two = two + Integer.toString(x);
                    counter[x]--;
                }
                add = !add;
            }
        }

        int sum1 = Integer.valueOf(one);
        int sum2 = Integer.valueOf(two);

        return sum1 + sum2;
    }
}
