public class DungeonGame {

    /*
    Top down
     Identify subproblems: Moving one square and seeing which path is better
     guess solution: Guess whether we should move to the right or down
     recurrence relation: get the minimum of the next location(s) plus the current value and 0
     memoize/DP array: the memo of how much health needed
     solve original problem: -hi + 1 (hi = dP(map, memeo, 0, 0)

     Time Complexity: O(n^2) (visit every node)
     Space Complexity: O(n^2) n is length of map

     */

    public static int minInitialHealth(int[][] map) {
        // TODO: Your code here
        int[][] memeo = new int[map.length][map[0].length];


        int hi = dP(map, memeo, 0, 0);


        return -hi + 1;
    }

    public static int dP(int[][] map, int[][] memeo, int x, int y){

        if(x==memeo[0].length -1  && y == memeo.length-1){

            memeo[y][x] = map[y][x];
            return memeo[y][x];
        }

        if(y < map.length-1 && x < map[0].length-1){
            memeo[y][x] = Math.min(Math.max(dP(map, memeo, x+1, y), dP(map,memeo, x, y+1))+ map[y][x], 0) ;

        }

        else if(x < map[0].length-1){
            memeo[y][x] = Math.min(dP(map, memeo, x+1, y) + map[y][x], 0);


        }
        else {
            memeo[y][x] = Math.min(dP(map,memeo, x, y+1) + map[y][x], 0);
        }

        return memeo[y][x];
    }

}
