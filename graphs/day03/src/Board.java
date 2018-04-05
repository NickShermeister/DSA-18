import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Board definition for the 8 Puzzle challenge
 */
public class Board {

    private int n;
    public int[][] tiles;

    //TODO
    // Create a 2D array representing the solved board state
    private int[][] goal = {{}};

    /*
     * Set the global board size and tile state
     */
    public Board(int[][] b) {
        // TODO: Your code here
        tiles = b;
        n = b.length;
        goal = new int[n][n];

        int counter = 1;
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                goal[x][y] = counter;
                counter++;
            }
        }
        goal[n-1][n-1] = 0;
    }

    /*
     * Size of the board 
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    /*
     * Size of the board
     (equal to 3 for 8 puzzle, 4 for 15 puzzle, 5 for 24 puzzle, etc)
     */
    private int size() {
        return n;
    }

    /*
     * Sum of the manhattan distances between the tiles and the goal
     */
    public int manhattan() {
        int dist = 0;
        for(int x = 0; x < n; x++) {
            for(int y = 0; y < n; y++) {
                if(tiles[x][y] != goal[x][y] && tiles[x][y] != 0) {
                    int target = tiles[x][y] -1;
                    int dest_row = target/n;
                    int dest_col = target%n;
                    dist += Math.abs((x-dest_col)) + Math.abs(y-dest_row);
                }
            }
        }
        System.out.println(dist);
        return dist;
    }

    /*
     * Compare the current state to the goal state
     */
    public boolean isGoal() {
        return Arrays.deepEquals(tiles, goal);
    }

    /*
     * Returns true if the board is solvable
     * Research how to check this without exploring all states
     */
    public boolean solvable() {
        // TODO: Your code here
        // TODO: NICK
        return false;
    }

    /*
     * Return all neighboring boards in the state tree
     */
    public Iterable<Board> neighbors() {
        // TODO: Your code here
        // TODO: NICK
//        int[] base = blankFinder();
//        LinkedList<int[]> neighbors = neighborFinder();
        return null;
    }

    private int[] blankFinder(){
        for(int x = 0; x < n; x++){
            for(int y = 0; y < n; y++){
                if(tiles[x][y] == 0){
                    return new int[]{x, y};
                }
            }
        }
        return null;
    }

    private List<int[]> neighborFinder(){
        LinkedList<int[]> neighborhood= new LinkedList<>();
        int[] curr_loc = blankFinder();
//        if(curr_loc[0] == 0){
//
//        }
//
//        else if(curr_loc[0] == tiles.length){
//
//        }
//        else{
//
//        }

        return null;
    }

    /*
     * Check if this board equals a given board state
     */
    @Override
    public boolean equals(Object x) {
        // Check if the board equals an input Board object
        if (x == this) return true;
        if (x == null) return false;
        if (!(x instanceof Board)) return false;
        // Check if the same size
        Board y = (Board) x;
        if (y.tiles.length != n || y.tiles[0].length != n) {
            return false;
        }
        // Check if the same tile configuration
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (this.tiles[i][j] != y.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        // DEBUG - Your solution can include whatever output you find useful
        int[][] initState = {{1, 2, 3}, {4, 0, 6}, {7, 8, 5}};
        Board board = new Board(initState);

        System.out.println("Size: " + board.size());
        System.out.println("Solvable: " + board.solvable());
        System.out.println("Manhattan: " + board.manhattan());
        System.out.println("Is goal: " + board.isGoal());
        System.out.println("Neighbors:");
        Iterable<Board> it = board.neighbors();
    }
}
