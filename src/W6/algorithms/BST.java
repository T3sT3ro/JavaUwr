package W6.algorithms;

/**
 * Binary search tree implementing dictionary operations from interface Dict.
 * Every Node on the left of its parent compares less than than its parent.
 * Every Node on the right of its parent compares greater or equal than its parent.
 *
 * @param <T> type of elements extending Comparable
 */
public class BST<T extends Comparable<T>> implements Dict<T> {

    int size = 0;
    private Node root;

    /**
     * Constructs empty BST
     */
    public BST() {
        root = new Node();
        root.parent = root;
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
        if (!delete(root, element))
            size--;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public T min() {
        return leftmost(root).value;
    }

    @Override
    public T max() {
        return rightmost(root).value;
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
        return "{" + (isEmpty() ? "" : toStringTraverse(root, "")) + "}";
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
     * Deletes node from BST
     *
     * @param removeNode root of the tree to start searching
     * @param value      value of the node we want to remove
     * @return false if deletion was successful, true if element don't exist in BST
     */
    private boolean delete(Node removeNode, T value) {
        if (removeNode == null)
            return true;
        else if (value.compareTo(removeNode.value) < 0)
            return delete(removeNode.left, value);
        else if (value.compareTo(removeNode.value) > 0)
            return delete(removeNode.right, value);
        else {
            if (removeNode.left != null && removeNode.right != null) {
                Node leftmostNode = leftmost(removeNode.right);
                removeNode.value = leftmostNode.value;
                delete(leftmostNode, leftmostNode.value);
            }
            replaceCurrent(removeNode, removeNode.left != null ? removeNode.left : removeNode.right);
            return false;
        }
    }

    /**
     * Replaces current Node with swapNode
     *
     * @param currentNode node to replace
     * @param swapNode    node we want to put in place of the currentNode
     */
    private void replaceCurrent(Node currentNode, Node swapNode) {
        if (currentNode.parent != null) {
            if (currentNode == currentNode.parent.left)
                currentNode.parent.left = swapNode;
            else
                currentNode.parent.right = swapNode;
        }
        if (swapNode != null)
            swapNode.parent = currentNode.parent;
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
     * Returns leftmost descendant of Node parent
     *
     * @param parent start searching Node
     * @return leftmost Node descendant of parent
     */
    private Node leftmost(Node parent) {
        Node tmp = parent;
        while (tmp.left != null)
            tmp = tmp.left;
        return tmp;
    }

    /**
     * Returns rightmost descendant of Node parent
     *
     * @param parent start searching Node
     * @return rightmost Node descendant of parent
     */
    private Node rightmost(Node parent) {
        Node tmp = parent;
        while (tmp.right != null)
            tmp = tmp.right;
        return tmp;
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

        public Node() {
        }

        public Node(T value) {
            this.value = value;
        }
    }
}
