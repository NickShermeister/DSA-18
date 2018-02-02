package your_code;
import ADTs.StackADT;

import java.util.LinkedList;

/**
 * An implementation of the Stack interface.
 */
public class MyStack implements StackADT<Integer> {

    private LinkedList<Integer> ll;
    int max;

    public MyStack() {
        ll = new LinkedList<>();
    }

    @Override
    public void push(Integer e) {

        if(ll.size() == 0){
            max =e;
        }
        else {
            if (max < e){
                max = e;
            }
        }
        ll.addFirst(e);
    }

    @Override
    public Integer pop() {
        Integer pop = ll.removeFirst();
        if(pop == max){
            max = getMax();
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

    public Integer getMax() {
        int len = ll.size();
        if(len > 0) {
            max = ll.getFirst();
            for (int x = 1; x < len; x++) {
                if (ll.get(x) > max) {
                    max = ll.get(x);
                }
            }
        }
        else {
            return -1;
        }
        return max;

    }
}
