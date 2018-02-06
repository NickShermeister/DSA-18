import java.util.*;

public class Problems {

    public static class Node {
        int val;
        Node next;

        Node(int d) {
            this.val = d;
            next = null;
        }
    }





    public static List<Integer> removeKDigits(int[] A, int k) {
        //what if there are 0
        //BENJAMIN GODDAMNIT.
        //in = new int[]{1, 2, 3, 1, 5, 6, 7, 2, 9};
        //1, 1, 5, 2, 9
        int counter = 0;
        List<Integer> l = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();

        if(A.length == 0){
            return l;
        }

        stack.push(A[0]);
        for(int x = 1; x <A.length; x++){
            if(counter < k){
                while(stack.peek() > A[x]){
                    stack.pop();
                    counter++;
                    if(counter == k || stack.empty()){
                        break;
                    }
                }
            }
            stack.push(A[x]);

        }
        while(counter < k){
            stack.pop();
            counter++;
        }

        for (int i = 0; i < A.length-k; i++) l.add(0,stack.pop());

        return l;
    }


    public static boolean isPalindrome(Node n) {
        //step 1: determine size
        //step 2: reverse second half of the list
        //step 3: remove (last) node from first list if odd
        //step 4: compare two half lists.
        //step 5: profit?????

        int size = 0;
        Node curr_node = n;
        Node last_node = null;
        Node prev = null;

        //Get size
        while(curr_node != null){
            size++;
            prev = curr_node;
            curr_node=curr_node.next;
        }
        if(size < 2){
            return true;
        }

        //Set last node
        last_node = prev;

        if(size < 4){
            return (n.val == last_node.val);

        }

        //Reset start
        curr_node = n;
        Node next_node = curr_node.next;
        prev = null;

        //Get to the halfway point
        for(int x = 0; x < size/2; x++){
//            System.out.println("Current val: " + curr_node.val);
            prev = curr_node;
            curr_node = next_node;
            next_node = curr_node.next;
        }

        //If odd
        if(size%2 == 1){
//            System.out.println("Odd");
           curr_node = null;
           prev.next = null;   //Set the last next to null
           prev = curr_node;
           curr_node = next_node;
           next_node = curr_node.next;
        }
        else { //if even
//            System.out.println("Even");
            prev.next = null;
            prev = null;
        }


        //Reverse second half
        while(curr_node != null){
            curr_node.next = prev;
            prev = curr_node;
            curr_node = next_node;
            try {
                next_node = curr_node.next;
            }   catch(NullPointerException e) {
                break;
            }
        }

        //VALIDATION CODE:
//        Node temp_node = last_node;
//        while(temp_node != null){
//            System.out.println("Temp node val:" + temp_node.val);
//            temp_node = temp_node.next;
//        }
//        temp_node = n;
//        System.out.println("forward");
//        while(temp_node != null){
//            System.out.println("Temp2 node val:" + temp_node.val);
//            temp_node = temp_node.next;
//        }

        //Reset
        curr_node = n;

        while(curr_node != null && last_node != null){
//            System.out.println("Curr: " + curr_node.val + "  Last: " + last_node.val);
            if(curr_node.val != last_node.val){
                return false;
            }
            else {
                curr_node = curr_node.next;
                last_node = last_node.next;
            }
        }

        return true;
    }




    public static String infixToPostfix(String s) {
        String[] broken = s.split(" ");
        Stack<String> stack = new Stack<>();
        String finalString = "";

        for (String x : broken)  {
            if (x.equals("(")){ //x is (/)
                }
            else if (x.equals(")")){
                finalString= finalString + stack.pop() + " ";
            }
            else if ("+/*-".indexOf(x) < 0) { //x is a number
                finalString= finalString + x + " ";
            }
            else {              //x is an operator
                stack.push(x);
            }
        }
        finalString = finalString.substring(0, finalString.length()-1);
        return finalString;
    }

}
