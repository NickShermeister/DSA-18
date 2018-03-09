import jdk.nashorn.api.tree.Tree;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTree<T extends Comparable<T>> {
    TreeNode<T> root;
    private int size;
    private List<T> ordered;

    public int size() {
        return size;
    }

    public boolean contains(T key) {
        return find(root, key) != null;
    }

    /**
     * Add a node to the BST. Internally calls insert to recursively find the new node's place
     */
    public boolean add(T key) {
        if (find(root, key) != null) return false;
        root = insert(root, key);
        size++;
        return true;
    }

    public void addAll(T[] keys) {
        for (T k : keys)
            add(k);
    }

    public List<T> inOrderTraversal() {
        // TODO
        ordered = new ArrayList<T>();
        inOrderTraversal1(root);
        return ordered;
    }

    private void inOrderTraversal1(TreeNode<T> currNode){
        if (currNode == null){
            return;
        }
        else if(currNode.hasChild()){
            inOrderTraversal1(currNode.leftChild);
            ordered.add(currNode.key);
            inOrderTraversal1(currNode.rightChild);
            return;
        }
        else {
            ordered.add(currNode.key);
        }
    }

    /**
     * Deletes a node from the BST using the following logic:
     * 1. If the node has a left child, replace it with its predecessor
     * 2. Else if it has a right child, replace it with its successor
     * 3. If it has no children, simply its parent's pointer to it
     */
    public boolean delete(T key) {
        TreeNode<T> toDelete = find(root, key);
        if (toDelete == null) {
            System.out.println("Key does not exist");
            return false;
        }
        TreeNode<T> deleted = delete(toDelete);
        if (toDelete == root) {
            root = deleted;
        }
        size--;
        return true;
    }

    private TreeNode<T> delete(TreeNode<T> n) {
        // Recursive base case
        if (n == null) {return null;}

        TreeNode<T> replacement;
        TreeNode<T> temp = null;

        if (n.isLeaf()) {
            // Case 1: no children
            replacement = null;
            n.replaceWith(replacement);
        }
        else if (n.hasRightChild() != n.hasLeftChild()) {
            // Case 2: one child
            replacement = (n.hasRightChild()) ? n.rightChild : n.leftChild; // replacement is the non-null child
            n.replaceWith(replacement);
            return replacement;
        }
        else {
            // Case 3: two children
            // TODO
            replacement = findPredecessor(n);
            temp = delete(replacement);

            n.replaceWith(replacement);
            replacement.moveChildrenFrom(n);


        }

        // Put the replacement in its correct place, and set the parent.


        return replacement;
    }

    public T findPredecessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> predecessor = findPredecessor(n);
            if (predecessor != null)
                return predecessor.key;
        }
        return null;
    }

    public T findSuccessor(T key) {
        TreeNode<T> n = find(root, key);
        if (n != null) {
            TreeNode<T> successor = findSuccessor(n);
            if (successor != null)
                return successor.key;
        }
        return null;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> n) {
        // TODO
        // Step 1: get the predecessor key using the sorted array
        // Step 2: loop through to the predecessor value
        // Step 3: account for "nulls"



        if(n == null) {return null;}

        if(n.hasLeftChild()){
            n=n.leftChild;
            while(n.rightChild != null){
                n = n.rightChild;
            }
            return n;
        }
        else if(n.isRightChild()){
            n=n.parent;
            return n;
        }
        else if (n.isLeftChild()) {
            while(n.isLeftChild()){
                n = n.parent;
            }
            if(n.isRightChild()){
                n = n.parent;
                return n;
            }
            else{
                return null;
            }
        }

        else {
            return null;
        }




    }

    private TreeNode<T> findSuccessor(TreeNode<T> n) {
        // TODO
        List<T> ordered = inOrderTraversal();
        T nextKey = null;
        for(int x = 0; x < ordered.size()-1; x++){
            if (ordered.get(x) == n.key){
                nextKey = ordered.get(x+1);
                break;
            }
        }
        if(nextKey == null){
            return null;
        }
        else {
            System.out.println("nextkay:" + nextKey);
            return find(root, nextKey);
        }
    }

    /**
     * Returns a node with the given key in the BST, or null if it doesn't exist.
     */
    private TreeNode<T> find(TreeNode<T> currentNode, T key) {
        if (currentNode == null)
            return null;
        int cmp = key.compareTo(currentNode.key);
        if (cmp < 0)
            return find(currentNode.leftChild, key);
        else if (cmp > 0)
            return find(currentNode.rightChild, key);
        return currentNode;
    }

    /**
     * Recursively insert a new node into the BST
     */
    private TreeNode<T> insert(TreeNode<T> node, T key) {
        if (node == null) return new TreeNode<>(key);

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.leftChild = insert(node.leftChild, key);
            node.leftChild.parent = node;
        } else {
            node.rightChild = insert(node.rightChild, key);
            node.rightChild.parent = node;
        }
        return node;
    }
}
