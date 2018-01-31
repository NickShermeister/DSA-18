package your_code;

public class MyLinkedList {

    private Node head;
    private Node tail;
    private int size;

    private class Node {
        Chicken val;
        Node prev;
        Node next;

        private Node(Chicken d, Node prev, Node next) {
            this.val = d;
            this.prev = prev;
            this.next = next;
        }
    }

    public MyLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(Chicken c) {
        addLast(c);
    }

    public Chicken pop() {
        return removeLast();
    }

    public void addLast(Chicken c) {
        Node tempNode = new Node(c, null, null);
        if(size == 0) {
            head = tempNode;
            tail = tempNode;
        }
        else {
            tempNode.prev = tail;
            tail.next = tempNode;
            tail = tempNode;
        }
        size++;
    }

    public void addFirst(Chicken c) {
        Node tempNode = new Node(c, null, null);
        if(size == 0) {
            head = tempNode;
            tail = tempNode;
        }
        else {
            tempNode.next = head;
            head.prev = tempNode;
            head = tempNode;
        }
        size++;
    }

    public Chicken get(int index) {
        if((index > size) || (index < 0)) {
            return null;
        }
        else {
            int loc = 0;
            Node current = head;
            while (loc != index){
                current = current.next;
                loc++;
            }
            return current.val;
        }
    }

    public Chicken remove(int index) {
        Chicken tempChick = null;
        if (size == 0){                         //No array
            return null;
        }
        else if (size == 1){                    //Size 1 array
            tempChick = head.val;
            head = null;
            tail = null;
        }
        else {
            if(index >= size || index < 0) {     //Invalid inputs
                return null;
            }
            else if (index == size-1){            //Edge case: tail
                return removeLast();
            }
            else if (index == 0){               //Edge case: head
                return removeFirst();
            }
            else {                              //Normal case
                int loc = 0;
                Node current = head;
                Node nextNode;
                while (loc != index){
                    nextNode = current.next;
                    current = nextNode;
                    loc++;
                }
                current.prev.next = current.next;
                current.next.prev = current.prev;
                tempChick = current.val;
            }
        }
        size--;
        return tempChick;
    }

    public Chicken removeFirst() {
        Chicken tempChick = null;
        if (size == 0){
            return null;
        }
        else if (size == 1){
            tempChick = head.val;
            head = null;
            tail = null;
        }
        else {
            tempChick = head.val;
            head = head.next;
            head.prev = null;
        }
        size--;
        return tempChick;
    }

    public Chicken removeLast() {
        Chicken tempChick = null;
        if (size == 0){
            return null;
        }
        else if (size == 1){
            tempChick = head.val;  //WHY DOES THIS HAVE TO BE HEAD AND NOT TAIL?
            head = null;
            tail = null;
        }
        else {
            tempChick = tail.val;
            tail = tail.prev;
//            tail.next = null; //WHY DO I HAVE TO COMMENT THIS?
        }
        size--;
        return tempChick;
    }
}