
    public class BinaryTree2<E extends Comparable<E>> {
        private Node<E> root;
        //private int size;

        public BinaryTree2() {
            this.root = null;
            //this.size = 0;
        }

        public int size() {

            return this.size(this.root);
        }

        private Node<E> rotate(Node<E> root){
            Node<E> pivot = root.left;
            root.left =  pivot.right;
            pivot.right = root;
            //root = pivot;
            return pivot;
        }

        private int size(Node<E> root) {
            if(root == null) {
                return 0;
            }
            return 1 + size(root.left)+ size(root.right);
        }

        public void add(E item) {
            this.root = add(this.root, item);

        }

        private Node<E> add(Node<E> root, E item) {
            if (root == null) {
                return new Node<E>(item);
            }
            int comparisonResult = item.compareTo(root.item);
            if (comparisonResult == 0) {
                root.left = add(root.left, item);
                return root;
            } else if (comparisonResult < 0) {
                root.left = add(root.left, item);
                return root;
            } else {
                root.right = add(root.right, item);
                return root;
            }

        }

        public void remove(E item) {
            this.root = remove(this.root, item);
        }

        private Node<E> remove(Node<E> root, E item) {
            if (root == null) {
                return null;
            }
            int comparisonResult = item.compareTo(root.item);
            if (comparisonResult < 0) {
                root.left = remove(root.left, item);
                return root;
            } else if (comparisonResult > 0) {
                root.right = remove(root.right, item);
                return root;
            } else {  // root is the item we want to delete AKA comparison == 0

                // case 1, root is leaf
                if (root.left == null && root.right == null) {
                    return null; //turns root (the desired node) into null
                } // case 2, root has only left child
                else if (root.left != null && root.right == null) {
                    return root.left; //turns desired Node into its own left subtree
                } else if (root.left == null && root.right != null) {
                    return root.right;
                } else {
                    Node<E> rootOfLeftSubtree = root.left;
                    Node<E> parentOfPredecessor = null;
                    Node<E> predecessor = null;

                    if (rootOfLeftSubtree.right == null) {
                        root.item = rootOfLeftSubtree.item;
                        root.left = rootOfLeftSubtree.left;
                        return root;
                    } else {
                        parentOfPredecessor = rootOfLeftSubtree;
                        Node<E> current = rootOfLeftSubtree.right;
                        while (current.right != null) {
                            parentOfPredecessor = current;
                            current = current.right;
                        }

                        predecessor = current;
                        root.item = predecessor.item;
                        parentOfPredecessor.right = predecessor.left;
                        return root;

                    }
                }

            }
        }

        public boolean contains(E item) {
            return contains(this.root, item);
        }

        private boolean contains(Node<E> root, E item) {
            if (root == null) {
                return false;
            }
            int comparisonResult = item.compareTo(root.item);
            if (comparisonResult == 0) {
                return true;
            } else if (comparisonResult < 0) {
                return contains(root.left, item);
            } else {
                return contains(root.right, item);
            }
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            preOrderTraverse(root, 1, sb);
            return sb.toString();
        }

        private String toString(Node<E> root) {
            if (root == null) {
                return "";
            }
            String output = "";

            output += root.item + " ";
            output += toString(root.left);

            output += toString(root.right);
            return output;

        }


        private void preOrderTraverse(Node<E> root, int depth, StringBuilder sb) {
            for (int i = 1; i < depth; i++) {
                sb.append("  "); // indentation
            }
            if (root == null) {
                sb.append("null\n");
            } else {
                sb.append(root.toString());
                sb.append("\n");
                preOrderTraverse(root.left, depth + 1, sb);
                preOrderTraverse(root.right, depth + 1, sb);
            }
        }

        private static class Node<E extends Comparable<E>> {
            private E item;
            private Node<E> left;  // left child
            private Node<E> right; // right child
            public Node(E item) {
                this.item = item;
            }

            public String toString() {
                return item.toString();
            }
        }

        public static int sumTree(Node<Integer> root){
            if (root == null) {
                return 0;
            }
            int rootVal = root.item;
            int leftVal = sumTree(root.left);
            int rightVal = sumTree(root.right);
            int sum = rootVal + leftVal + rightVal;

            return sum;
        }
        public boolean equals(Node<E> treeA,Node<E> treeB){
            if(treeA == null && treeB == null){
                return true;
            }
            if(treeA != null && treeB == null){
                return false;
            }
            if(treeA == null && treeB != null){
                return false;
            }
            else {
                if(treeA.item.equals(treeB.item)){
                    boolean leftSide = equals(treeA.left, treeB.left);
                    boolean rightSide = equals(treeA.right, treeB.right);
                    return true;
                }
                else {
                    return false;
                }
            }
        }

        public static String combineStrings(Node<String> root){
            return  " ";
        }
        public static int numberOfChars(Node<String> root){
            if (root == null) {
                return 0;
            }
            int numCharsinRoot = root.item.length();
            int numCharsinLeft = numberOfChars(root.left);
            int numCharsinRight = numberOfChars(root.right);

            int num = numCharsinLeft + numCharsinRight + numCharsinRoot;

            return num;
        }

        /* Create a method that, given the root of a binary search tree,
        * *  finds the smallest item in the tree that is larger than the root.*
        * *  My solution wasn't recursive**  25 points*/
        public static<E extends Comparable<E>> E smallestLarger(Node<E> root){
            Node<E> rootOfRightSubtree = root.right;
            Node<E> current = rootOfRightSubtree.left;

            while (current.left != null) {
                current = current.left;
            }

            E end = current.item;
            return end;
        }

        public static void main(String[] args) {

            BinaryTree2<Integer> tree = new BinaryTree2<Integer>();
            tree.add(5);
            tree.add(1);
            tree.add(0);
            tree.add(2);

            tree.add(4);
            tree.add(3);

            tree.add(12);
            tree.add(7);
            tree.add(15);
            tree.add(14);
            tree.add(20);


            System.out.println(tree);
        //    tree.root.left = tree.rotate(tree.root.left);
        }

    }

