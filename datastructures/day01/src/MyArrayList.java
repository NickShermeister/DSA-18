public class MyArrayList {
    private Cow[] elems;
    private int size;

    // TODO: Runtime: O(1)
    public MyArrayList() {
        elems = new Cow[10];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public MyArrayList(int capacity) {
        elems = new Cow[capacity];
        size = 0;
    }

    // TODO: Runtime: O(1)
    public void add(Cow c) {
        if(size >= elems.length) {
            Cow[] tempCowArray = elems;
            elems = new Cow[tempCowArray.length * 2];
            System.arraycopy(tempCowArray, 0, elems, 0, size);
        }
        elems[size] = c;
        size++;

    }

    // TODO: Runtime: O(1)
    public int size() {
        // TODO
        return size;
    }

    // TODO: Runtime: O(1)
    public Cow get(int index) {
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }
        else{
            return elems[index];
        }
    }

    // TODO: Runtime: O(n)
    public Cow remove(int index) {
        Cow tempCow = null;
        if(index >= size){
            throw new IndexOutOfBoundsException();
        }
        else {
            tempCow = elems[index];
            for(int x = index; x < size-1; x++){
                elems[x] = elems[x+1];
            }
            elems[size-1] = null;
            size--;
        }
        if(size < elems.length/4) {
            Cow[] tempCowArray = elems;
            elems = new Cow[tempCowArray.length / 2];
            System.arraycopy(tempCowArray, 0, elems, 0, size);
        }
        return tempCow;
    }

    // TODO: Runtime: O(n)
    public void add(int index, Cow c) {
        if(index >= size+1){
            throw new IndexOutOfBoundsException();
        }
        if(index >= size || size == elems.length){
            Cow[] tempCowArray = elems;
            elems = new Cow[tempCowArray.length * 2];
            System.arraycopy(tempCowArray, 0, elems, 0, size);
        }

        for(int x = size; x > index; x--){
            elems[x] = elems[x-1];
        }
        elems[index] = c;
        size++;
    }
}
