import java.util.Arrays;

public class HeapSort extends SortAlgorithm {
    int size;
    int[] heap;

    private int parent(int i) {
        return (i-1) / 2;
    }

    private int leftChild(int i) {
        return 2*i + 1;
    }

    private int rightChild(int i) {
        return 2 * (i + 1);
    }

    // Check children, and swap with larger child if necessary.
    // Corrects the position of element indexed i by sinking it.
    // Use either recursion or a loop to then sink the child
    public void sink(int i) {
        if(this.size == 0){
            return;
        }

        int targetChild;
        if(rightChild(i)<this.size) {
            if (this.heap[leftChild(i)] <= this.heap[rightChild(i)]) {
                targetChild = rightChild(i);

            } else {
                targetChild = leftChild(i);
            }
            if (this.heap[i] < this.heap[targetChild]) {
                int tempval = this.heap[i];
                this.heap[i] = this.heap[targetChild];
                this.heap[targetChild] = tempval;
                sink(targetChild);
            }
        }
        else if (leftChild(i)<this.size){
            targetChild = leftChild(i);
            if(this.heap[leftChild(i)] > this.heap[i]){
                int tempval = this.heap[i];
                this.heap[i] = this.heap[targetChild];
                this.heap[targetChild] = tempval;
                sink(targetChild);
            }
        }
    }

    // Given the array, build a heap by correcting every non-leaf's position, starting from the bottom, then
    // progressing upward
    public void heapify(int[] array) {
        this.heap = array;
        this.size = array.length;
        System.out.println("hi");

        for (int i = this.size / 2 - 1; i>=0; i--) {
            sink(i);
        }

    }

    /**
     * Best-case runtime: O(n)
     * Worst-case runtime: O(nlog(n))
     * Average-case runtime: O(nlog(n))
     *
     * Space-complexity: O(1)
     */
    @Override
    public int[] sort(int[] array) {
        heapify(array);
        int temp = 0;
        for (int i=size-1; i>0; i--) {
            temp = array[0];
            array[0] = array[i];
            array[i]=temp;
            size--;
            this.sink(0);
        }
        System.out.println(Arrays.toString(heap));

        return heap;
    }
}
