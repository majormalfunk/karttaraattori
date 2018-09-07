/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

//import java.util.Arrays;

/**
 * This is an implementation of a minimum heap or a priority queue. It
 * implements the Comparable interface.
 *
 * @author jaakkovilenius
 * @param <T> The type of items to be stored in this heap
 */
public class MazeMinHeap<T extends Comparable<T>> {

    private static final int INITIAL_CAPACITY = 200;
    private T[] items;
    private int size;

    /**
     * Constructor
     */
    public MazeMinHeap() {
        items = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * This adds an element to the heap
     *
     * @param item Element to be added
     */
    public void add(T item) {
        if (item != null) {
            if (size >= items.length - 1) {
                addCapacity();
            }
            size++;
            int i = size - 1;
            if (size > 1) {
                //System.out.println("This: " + item + " Parent["+ parent(i) +"]: " + items[parent(i)]);
                while (i > 0 && items[parent(i)].compareTo(item) > 0) {
                    items[i] = items[parent(i)];
                    i = parent(i);
                }
            }
            items[i] = item;
        }
    }

    /**
     * This returns and removes the element with the lowest key value from the
     * heap. That is the element on top of the heap.
     *
     * @return The first element
     */
    public T poll() {
        if (size == 0) {
            return null;
        }
        T min = items[0];
        items[0] = items[size - 1];
        size--;
        heapify(0);
        return min;
    }

    /**
     * Return true if the heap is empty. False otherwise.
     *
     * @return true or false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the size of the heap. That is how many elements there are in the
     * heap.
     *
     * @return Size of the heap
     */
    public int size() {
        return size;
    }

    /**
     * Returns true if the heap contains one or more of the given elements
     *
     * @param item the element to be tested
     * @return true or false
     */
    public boolean contains(T item) {
        if (isEmpty()) {
            return false;
        }
        return indexOf(item) != -1;
    }

    /**
     * Removes an element fromn the heap if it exists
     *
     * @param item The item to be removed
     * @return true if item was found and removed. False otherwise.
     */
    public boolean remove(T item) {
        int i = indexOf(item);
        if (i == -1) {
            return false;
        }
        items[i] = items[size - 1];
        items[size - 1] = null;
        size--;
        heapify(i);
        return true;
    }

    /**
     * This returns the index of the element in the heap's inner array
     *
     * @param item the element to be found
     * @return index of the element in the heap's inner array
     */
    private int indexOf(T item) {
        if (isEmpty()) {
            return -1;
        }
        int i = 0;
        while (i < size) {
            if (items[i].equals(item)) {
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * This adds capacity to the heap. It is called when the number of elements
     * in the heap's inner array reach maximum.
     */
    private void addCapacity() {
        T[] newItems = (T[]) new Comparable[this.items.length * 2];
        System.arraycopy(this.items, 0, newItems, 0, this.items.length);
        this.items = newItems;

//        items = Arrays.copyOf(items, items.length * 2);
    }

    /**
     * returns the index of the parent of the element in index i
     *
     * @param i
     * @return Index of the parent
     */
    private int parent(int i) {
        return i / 2;
    }

    /**
     * Returns the index of the left child of the element at index i
     *
     * @param i
     * @return the index of the left child
     */
    private int left(int i) {
        return 2 * i;
    }

    /**
     * Returns the index of the right child of the element at index i
     *
     * @param i
     * @return the index of the right child
     */
    private int right(int i) {
        return 2 * i + 1;
    }

    /**
     * This corrects the heap when something has happened at index i
     *
     * @param i index of the event in the inner array of the heap
     */
    private void heapify(int i) {

        int l = left(i);
        int r = right(i);
        int smallest = -1;
        if (r < size) {
            if (items[l].compareTo(items[r]) < 0) {
                smallest = l;
            } else {
                smallest = r;
            }
            if (items[i].compareTo(items[smallest]) > 0) {
                swap(i, smallest);
                heapify(smallest);
            }
        } else if (l == size - 1 && items[i].compareTo(items[l]) > 0) {
            swap(i, l);
        }

    }

    /**
     * This swaps the places of the elements in indexes 1 and 2
     *
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2) {
        T tmp = items[index1];
        items[index1] = items[index2];
        items[index2] = tmp;
    }
}
