import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {


    /**
     * Checks the 45° and 135° diagonals for an existing queen. For example, if the board is a 5x5
     * and you call checkDiagonal(board, 3, 1), The positions checked for an existing queen are
     * marked below with an `x`. The location (3, 1) is marked with an `o`.
     *
     * ....x
     * ...x.
     * x.x..
     * .o...
     * .....
     *
     * Returns true if a Queen is found.
     *
     * Do not modify this function (the tests use it)
     */
    public static boolean checkDiagonal(char[][] board, int r, int c) {
        int y = r - 1;
        int x = c - 1;
        while (y >= 0 && x >= 0) {
            if (board[y][x] == 'Q') return true;
            x--;
            y--;
        }
        y = r - 1;
        x = c + 1;
        while (y >= 0 && x < board[0].length) {
            if (board[y][x] == 'Q') return true;
            x++;
            y--;
        }
        return false;
    }

    public static boolean checkColumn(char[][] board, int c){
        for(int i = 0; i < board.length; i++){
            if(board[i][c]=='Q'){
                return true;
            }
        }
        return false;
    }


    /**
     * Creates a deep copy of the input array and returns it
     */
    private static char[][] copyOf(char[][] A) {
        char[][] B = new char[A.length][A[0].length];
        for (int i = 0; i < A.length; i++)
            System.arraycopy(A[i], 0, B[i], 0, A[0].length);
        return B;
    }

    public static void recursive(int n, int row, char[][] board, List<char[][]> answers){
        if(row == n){   //base case
            answers.add(copyOf(board));
            return;
        }
        for(int x = 0; x < n; x++){
            if(!(checkColumn(board, x) || checkDiagonal(board, row, x))){
                board[row][x] = 'Q';
                recursive(n, row+1, board, answers);
                board[row][x] = '.';
            }
        }
    }


    public static List<char[][]> nQueensSolutions(int n) {
        // TODO
        List<char[][]> answers = new ArrayList<>();
        char[][] startboard = new char[n][n];
        for(char[] hi : startboard){
            Arrays.fill(hi, '.');
        }
        recursive(n,0, startboard, answers);        //Start in the 0 row
        return answers;
    }

}
