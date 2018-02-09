package your_code;

import java.util.LinkedList;

/**
 * An implementation of a priority Queue
 */
public class MyPriorityQueue {
    private LinkedList<Integer> ll;

    public MyPriorityQueue() {
        ll = new LinkedList<Integer>();
    }
    public void enqueue(int item) {
        ll.add(item);
    }

    /**
     * Return and remove the largest item on the queue.
     */
    public int dequeueMax() {
        int len = ll.size();
        int max;
        int loc;
        if(len > 0) {
            max = ll.getFirst();
            loc = 0;
            for (int x = 1; x < len; x++) {
                if (ll.get(x) > max) {
                    max = ll.get(x);
                    loc = x;
                }
            }
            return ll.remove(loc);

        }
        else {
            return -1;
        }
    }

}