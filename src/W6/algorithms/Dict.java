package W6.algorithms;

/**
 * Dictionary interface supporting operations like searching, inserting, removing, getting min and max element
 *
 * @param <T> type of elements extending Comparable
 */
interface Dict<T extends Comparable<T>> {
    /**
     * Searches for element in dictionary.
     * If element is not found, returns null
     *
     * @param element element to search
     * @return element if dictionary contains element, null otherwise
     */
    T search(T element);

    /**
     * Inserts element into the dictionary
     *
     * @param element element to insert
     * @throws NullPointerException if element == null
     */
    void insert(T element) throws NullPointerException;

    /**
     * Removes element from dictionary
     *
     * @param element element to remove
     */
    void remove(T element);

    /**
     * Clears the container
     */
    void clear();


    /**
     * Returns minimal element in dictionary according to compareTo() method
     *
     * @return minimal element
     */
    T min();

    /**
     * Returns maximal element in dictionary according to compareTo() method
     *
     * @return maximal element
     */
    T max();

    /**
     * Returns the number of elements in dictionary
     *
     * @return elements
     */
    int size();

    /**
     * Returns true if there are no elements in dictionary
     *
     * @return true if size()==0
     */
    boolean isEmpty();

}
