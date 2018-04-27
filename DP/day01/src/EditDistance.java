public class EditDistance {

    /*
    Bottom Up
     Identify subproblems: Making a change to a current string
     guess solution: We either change the current char to the target char, delete the char, or insert the correct char
     recurrence relation:
     memoize/DP array:
     solve original problem:

     Time Complexity: O()
     Space Complexity: O()

     */

    public static int minEditDist(String a, String b) {
        // TODO: Your code here
        int[][] memeo = new int[a.length()+1][b.length()+1];

        for(int x = 0; x < memeo[0].length; x++){
            memeo[0][x] = x;
        }
        for(int y = 0; y < memeo.length; y++){
            memeo[y][0] = y;
        }
        int change;
        for (int x = 1; x <= a.length(); x++){
            for(int y = 1; y <= b.length(); y++){
                if(a.charAt(x-1) != b.charAt(y-1)){
                    change = 1;
                }
                else {
                    change = 0;
                }
                memeo[x][y] = Math.min(Math.min(memeo[x-1][y-1] + change, memeo[x-1][y] + 1), memeo[x][y-1]+1);
            }
        }
        return memeo[memeo.length-1][memeo[0].length-1];
    }


}
