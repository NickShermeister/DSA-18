import jdk.nashorn.api.tree.Tree;

public class AVLTree<T extends Comparable<T>> extends BinarySearchTree<T> {

    /**
     * Delete a key from the tree rooted at the given node.
     */
    @Override
    TreeNode<T> delete(TreeNode<T> n, T key) {
        n = super.delete(n, key);       //Returns root node of edited tree.

        if (n != null) {
            // TODO
            // update the height of the tree using the height of the left and right child
//            redoHeights(n);
            n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
            n = balance(n);

        }
        return n;
    }

    /**
     * Insert a key into the tree rooted at the given node.
     */
    @Override
    TreeNode<T> insert(TreeNode<T> n, T key) {
//        System.out.println("inserting:");
//        System.out.println(key);
        n = super.insert(n, key);


//        redoHeights(root);
        if (n != null) {
            // TODO
            // update the height of the tree using the height of the left and right child
            // return balance(n)

            n.height = Math.max(height(n.leftChild), height(n.rightChild)) + 1;
            n = balance(n);
//            redoHeights(n);
        }
        return n;
    }

    /**
     * Delete the minimum descendant of the given node.
     */
    @Override
    TreeNode<T> deleteMin(TreeNode<T> n) {
        n = super.deleteMin(n);
        if (n != null) {
            n.height = 1 + Math.max(height(n.leftChild), height(n.rightChild));
            return balance(n);
        }
        return null;
    }

    // Return the height of the given node. Return -1 if null.
    private int height(TreeNode<T> n) {
        // TODO
        if(n == null) {return -1;}

        return n.height;
    }

    public int height() {
        return Math.max(height(root), 0);
    }

    private TreeNode<T> findParent(TreeNode<T> n, T key){
        TreeNode<T> last = null;
        int diff;

        while(n != null){
            diff = n.key.compareTo(key);
            if(diff == 0){
                return last;
            }
            else {
                last = n;
                if(diff < 0){
                    n = n.rightChild;
                }
                else {
                    n = n.leftChild;
                }
            }
        }
        return null;
    }


    // Restores the AVL tree property of the subtree. Return the head of the new subtree
    public TreeNode<T> balance(TreeNode<T> n) {
        // TODO: (if you're having trouble, use pseudocode provided in slides)
//        if(n == null){return null;}
//        redoHeights(root);
//        int balancefactor = balanceFactor(n);
//        System.out.println(balancefactor);
//        System.out.println(n.key);
//        System.out.println("");

        if(balanceFactor(n) <= -2){      //left heavy
//            System.out.println("leftheavy1");

            if(balanceFactor(n.leftChild)>0){  //NOT left heavy
//                System.out.println("leftheavy2");
                n.leftChild = rotateLeft(n.leftChild);
            }
            n = rotateRight(n);

        }

        else if(balanceFactor(n) >= 2) {
//            System.out.println("rightheavy1");
            if(balanceFactor(n.rightChild)<0){     //NOT right heavy
                n.rightChild = rotateRight(n.rightChild);
            }
            n = rotateLeft(n);
        }
        return n;


    }

    /**
     * Returns the balance factor of the subtree. The balance factor is defined
     * as the difference in height of the left subtree and right subtree, in
     * this order. Therefore, a subtree with a balance factor of -1, 0 or 1 has
     * the AVL property since the heights of the two child subtrees differ by at
     * most one.
     */
    private int balanceFactor(TreeNode<T> n) {

        return height(n.rightChild) - height(n.leftChild);

    }

    /**
     * Perform a right rotation on node `n`. Return the head of the rotated tree.
     * Should be able to assume that n has a left/right child?
     */
    private TreeNode<T> rotateRight(TreeNode<T> n) {
        // TODO
        System.out.println("Rotated right");
//        if(n.equals(root)) {
//            root = n.leftChild;
//        }
        TreeNode<T> temp = n.leftChild;

        n.leftChild = temp.rightChild;
        temp.rightChild = n;

        temp.rightChild.height = 1 + Math.max(height(temp.rightChild.leftChild), height(temp.rightChild.rightChild));
        temp.height = 1 + Math.max(height(temp.leftChild), height(temp.rightChild));
//        redoHeights(temp);      //do 1+ max(children heights)
        return temp;
    }

    private void redoHeights(TreeNode<T> n){
//        System.out.println("redoing");
        if(n == null){
            return;
        }
        else {
            n.height = height(n);
            redoHeights(n.leftChild);
            redoHeights(n.rightChild);
            return;
        }
    }

    /**
     * Perform a left rotation on node `n`. Return the head of the rotated tree.
     */
    private TreeNode<T> rotateLeft(TreeNode<T> n) {
        // TODO
//        if(n.equals(root)) {
//            root = n.rightChild;
//        }
        System.out.println("Rotatedleft");
        TreeNode<T> temp = n.rightChild;
        n.rightChild = temp.leftChild;
        temp.leftChild = n;
        temp.leftChild.height = 1 + Math.max(height(temp.leftChild.leftChild), height(temp.leftChild.rightChild));
        temp.height = 1 + Math.max(height(temp.leftChild), height(temp.rightChild));
        return temp;
    }
}
