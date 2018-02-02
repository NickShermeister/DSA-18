package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    private LinkedList<Integer> maxes;
    int max;

    public MyStack() {
        ll = new LinkedList<>();
        maxes = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {

        if(ll.size() == 0){
            max =e;
            maxes.addFirst(e);
        }
        else {
            if (max <= e){
                max = e;
                maxes.addFirst(e);
            }
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if(pop == max){
            maxes.removeFirst();
            if(maxes.size() != 0) {
                max = maxes.peekFirst();
            }
            else {
                max = 0;
            }
        }
        return pop;
    }

    @Override
    public boolean isEmpty() {
        return ll.isEmpty();
    }

    @Override
    public Integer peek() {
        return ll.getFirst();
    }

    public Integer maxElement() {
        return max;
    }

}
