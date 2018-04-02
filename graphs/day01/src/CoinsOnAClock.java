import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CoinsOnAClock {

    public static void backtrack(List<char[]> results, int pennies, int nickels, int dimes, char[] curr_state, int loc){
        //If everything's full
        if(pennies+nickels+dimes == 0){
            System.out.println(curr_state);
            results.add(Arrays.copyOf(curr_state, curr_state.length));
            return;
        }
        //If there's something on that part of the clock already
        if(curr_state[loc] != 'x'){
            return;
        }

        if (dimes > 0){
            dimes--;      //Decrement coin
            curr_state[loc] = 'd';      //Set value for the current state
            backtrack(results, pennies, nickels, dimes, curr_state, (loc+10)%curr_state.length);   //run recursively
            dimes++;      //add back penny
            curr_state[loc] = 'x';      //change location's value back to x
        }

        if (nickels > 0){
            nickels--;      //Decrement coin
            curr_state[loc] = 'n';      //Set value for the current state
            backtrack(results, pennies, nickels, dimes, curr_state, (loc+5)%curr_state.length);   //run recursively
            nickels++;      //add back penny
            curr_state[loc] = 'x';      //change location's value back to x
        }
        if(pennies > 0){
            pennies--;      //Decrement coin
            curr_state[loc] = 'p';      //Set value for the current state
//            loc = (loc+1)%curr_state.length;       //Set new location
            backtrack(results, pennies, nickels, dimes, curr_state, (loc+1)%curr_state.length);   //run recursively
            pennies++;      //add back penny
            curr_state[loc] = 'x';      //change location's value back to x
//            loc = if(loc-1);          //change loc back to original value
        }
    }

    public static List<char[]> coinsOnAClock(int pennies, int nickels, int dimes, int hoursInDay) {
        // TODO

        List<char[]> result = new ArrayList<>();
        char[] start_state = new char[hoursInDay];
        for(int x = 0; x < hoursInDay; x++){
            start_state[x] = 'x';
        }

        //Unnecessary, but I wrote it before I realized that
        if(pennies + nickels + dimes < hoursInDay){
            return result;
        }
        backtrack(result, pennies, nickels, dimes, start_state, 0);

        return result;
    }
}
