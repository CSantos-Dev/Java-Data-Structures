package tree;

/**
 * Implementation of BinarySearchTree
 * Does not store nulls and duplicated values
 * @author csantos
 */
public class BinarySearchTree<T extends Comparable> {

    private int size = 0;
    private Node root;

    /**
     * Add a new element to the tree
     */
    public void add(T data) {
        if(data == null) {
            throw new NullPointerException();
        }
        root = addRecursive(root, data);
    }

    private Node addRecursive(Node current, T data) {
        if(current == null) {
            size++;
            return new Node(data);
        } else {
            if(data.compareTo(current.data) > 0){
                current.right = addRecursive(current.right, data);
            } else if (data.compareTo(current.data) < 0){
                current.left = addRecursive(current.left, data);
            } else {
                return current;
            }
        }

        return current;
    }

    /**
     * Removes the current element from the tree
     */
    public void remove(T data) {
        if(data == null) {
            return;
        }

       root = removeRecursive(root, data);
    }

    private Node removeRecursive(Node current, T data) {
        if(current == null) {
            return null;
        }

        // Match was found
        if(current.data.equals(data)) {
            //Case 1: Current node has no children
            if(current.left == null && current.right == null) {
                size--;
                return null;
            }
            //Case 2: It has only one child
            if(current.left == null) {
                size--;
                return current.right;
            }

            if(current.right == null) {
                size--;
                return current.left;
            }

            //Case 3: Node has both children
            T smallest = minRecursive(current.right);
            current.data = smallest;
            current.right = removeRecursive(current.right, smallest);
            return current;
        }

        if(data.compareTo(current.data) > 0) {
            current.right = removeRecursive(current.right, data);
        }

        if(data.compareTo(current.data) < 0) {
            current.left = removeRecursive(current.left, data);
        }
        return current;
    }

    /**
     * Returns whether the tree contains the given value or not
     */
    public boolean contains(T data) {
        return containsRecursive(root, data);
    }

    private boolean containsRecursive(Node current, T data) {
        if(current == null) {
            return false;
        }

        if (current.data.equals(data)) {
            return true;
        }

        if(data.compareTo(current.data) > 0) {
            return containsRecursive(current.right, data);
        } else {
            return containsRecursive(current.left, data);
        }
    }

    /**
     * Returns the smallest value in the tree
     */
    public T min() {
        return minRecursive(root);
    }


    private T minRecursive(Node current) {
        while (current.left != null) {
            current = current.left;
        }

        return current.data;
    }

    /**
     * Returns the greatest value in the tree
     */
    public T max() {
        Node current = root;
        while (current.right != null) {
            current = current.right;
        }

        return current.data;
    }

    public int size() {
        return size;
    }

    private class Node {
        private T data;
        private Node left;
        private Node right;

        private Node(T data) {
            this.data = data;
        }
    }
}
