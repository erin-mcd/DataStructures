import java.util.HashSet;
import java.util.LinkedList;

public class TimedLab3 {
    /* Remove all duplicate items from the given LinkedList
     *  ***  Example input:*  [1,4,6,3,1,3,4,5]*
     *  will be changed to*  [1,4,6,3,5]*
     *  although*  [3,5,6,4,1] or any permutation is also valid!**
     *  You may create and use any additional data structures*  (in fact I recommend it for the best score)**
     *  Scoring based on runtime complexity*
     *  O(n) - 50 points*
     *  O(n^2) - 40 points*
     *  O(n^3) or worse - 30 points**  Remember to use the for each*
     *  No, you CANNOT use head or tail or nodes in this solution.*/
    public static <E> void removeDuplicates(LinkedList<E> list) {
        HashSet<E> checklist = new HashSet();

        for (E item: list) {
            checklist.add(item);
        }
        list.clear();
        for (E item: checklist) {
            list.add(item);
        }
        System.out.println(checklist);
    }

    /* Create a method that, given the root of a binary search tree,
     * *  finds the smallest item in the tree that is larger than the root.*
     * *  My solution wasn't recursive**  25 points*/

    public static<E extends Comparable<E>> E smallestLarger(TreeNode root){
        TreeNode<E> rootOfRightSubtree = root.right;
        TreeNode<E> current = rootOfRightSubtree.left;

        while (current.left != null) {
            current = current.left;
        }

        E end = current.item;
        return end;
    }

    /* Write a method that determines if the given root of a tree or subtree*  is a binary search tree*
    * *
    * *  You will need to use .compareTo() on the items.*
    * *  25 points*/

    public static <E> boolean isBST(TreeNode root) {

        if(root == null){
            return true;
        }
        if(root.item.compareTo(root.left.item) >= 0 && root.item.compareTo(root.right.item) < 0){// if the root.item is both greater than or equal to its left child and less than its right child
            return true;
        }

        if(isBST(root.left) == true && isBST(root.right) == true){
            return true;
        }

        else{
        return false;
    }
    }


        // useful for writing your trees
        private static class TreeNode<T extends Comparable<T>> {
            T item;
            TreeNode left;
            TreeNode right;
    }

    public static void main(String[] args) {
        LinkedList list = new LinkedList();
        list.add(2);
        list.add(4);
        list.add(20);
        list.add(2);
        list.add(27);
        list.add(42);
        list.add(4);
        list.add(2);

        removeDuplicates(list);
        System.out.println(list);
    }
}