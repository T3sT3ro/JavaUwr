package W6.algorithms;

/**
 * Binary search tree implementing dictionary operations from interface Dict.
 * Every Node on the left of its parent compares less than than its parent.
 * Every Node on the right of its parent compares greater or equal than its parent.
 *
 * @param <T> type of elements extending Comparable
 */
public class BST<T extends Comparable<T>> implements Dict<T> {

    private int size = 0;
    private Node root; // root's parent is always null

    /**
     * Constructs empty BST
     */
    public BST() {
        root = new Node();
        root.parent = null;
        size = 0;
    }

    @Override
    public T search(T element) {
        Node found = find(element);
        return found == null ? null : found.value;
    }

    @Override
    public void insert(T element) throws NullPointerException {
        if (element == null)
            throw new NullPointerException("Cannot insert null into the collection!");

        Node insertNode = new Node(element);
        if (isEmpty()) {
            root = insertNode;
        } else {
            Node parent = null;
            Node current = root;
            while (current != null) {
                parent = current;
                if (element.compareTo(current.value) < 0)
                    current = current.left;
                else
                    current = current.right;
            }
            if (element.compareTo(parent.value) < 0)
                parent.left = insertNode;
            else
                parent.right = insertNode;
            insertNode.parent = parent;
        }
        size++;
    }

    @Override
    public void remove(T element) {
        if (root.delete(element) == 0)
            size--;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public T min() {
        return isEmpty() ? null : root.leftmost().value;
    }

    @Override
    public T max() {
        return isEmpty() ? null : root.rightmost().value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        return "[" + (isEmpty() ? "" : toStringTraverse(root, "")) + "]";
    }

    private String toStringTraverse(Node node, String list) {
        if (node.left != null) {
            list = toStringTraverse(node.left, list);
            list += ", ";
        }
        list += node.value;
        if (node.right != null) {
            list += ", ";
            list = toStringTraverse(node.right, list);
        }
        return list;
    }

    /**
     * Returns node which value is equal to element, or null if such doesn't exist
     *
     * @param element searched value
     * @return Node containing element as value
     */
    private Node find(T element) {
        if (isEmpty())
            return null;
        Node current = root;
        while (current != null && !current.value.equals(element)) {
            if (element.compareTo(current.value) < 0)
                current = current.left;
            else
                current = current.right;
        }
        return current;
    }

    /**
     * Class node representing a node inside BST.
     * It has a value of type T and pointers to its parent, right son and left son.
     */
    private class Node {
        T value;
        Node left;
        Node right;
        Node parent;

        private Node() {
        }

        private Node(T value) {
            this.value = value;
        }

        /**
         * Returns node with smallest value in this node's subtree
         *
         * @return deepest leftmost descendant of this node
         */
        private Node leftmost() {
            Node current = this;
            while (current.left != null)
                current = current.left;
            return current;
        }

        /**
         * Returns node with biggest value in this node's subtree
         *
         * @return deepest rightmost descendant of this node
         */
        private Node rightmost() {
            Node current = this;
            while (current.right != null)
                current = current.right;
            return current;
        }

        /**
         * Swaps given node with swap node preserving pointers in the tree
         *
         * @param swapNode the node we want to replace with
         */
        private void replaceInParent(Node swapNode) {
            if (this.parent != null) {
                if (this == this.parent.left)
                    this.parent.left = swapNode;
                else
                    this.parent.right = swapNode;
            }
            if (swapNode != null) {
                swapNode.parent = this.parent;
                if (swapNode.parent == null) // update root
                    root = swapNode;
            }
        }

        /**
         * Deletes a node with given key from the tree
         *
         * @param value node with this value will be removed
         * @return 0 if deleted successfully, 1 if the element didn't exist
         */
        private int delete(T value) {
            if (value.compareTo(this.value) < 0)
                return this.left != null ? this.left.delete(value) : 1;
            else if (value.compareTo(this.value) > 0)
                return this.right != null ? this.right.delete(value) : 1;
            else {
                if (this.left != null && this.right != null) {
                    Node successor = this.right.leftmost();
                    this.value = successor.value;
                    successor.delete(successor.value);
                } else if (this.left != null)
                    this.replaceInParent(this.left);
                else if (this.right != null)
                    this.replaceInParent(this.right);
                else
                    this.replaceInParent(null);
                return 0;
            }
        }
    }
}
