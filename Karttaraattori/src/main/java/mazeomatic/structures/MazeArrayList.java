/*
* Jaakko Vilenius 2018
*/

package mazeomatic.structures;


/**
 * This is a minimum implementation of an ArrayList like structure.
 * Does not implement Iterable so all "iterations" must be done e.g. with
 * a for loop and use the get(index) -method.
 * 
 * @author jaakkovilenius
 * @param <T> The type of data stored in this structure
 * 
 * 
 */
public class MazeArrayList<T> {
    
    private static final int INITIAL_CAPACITY = 100;
    private T[] items;
    private int counter;
    
    /**
     * The consturctor
     */
    public MazeArrayList() {
        this.items = (T[]) new Object[INITIAL_CAPACITY];
        this.counter = 0;
    }
    
    /**
     * Adds an item to the list
     * 
     * @param item item to be added
     * @return true as the item was added
     */
    public boolean add(T item) {
        if (this.counter == this.items.length) {
            addCapacity();
        }
        
        this.items[this.counter] = item;
        this.counter++;
        
        return true;
    }
    
    /**
     * Adds an item at a certain index
     * 
     * @param index index at which point to add the item
     * @param item item to be added
     */
    public void add(int index, T item) {
        if (index < 0 || index >= this.counter) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds [0, " + (this.counter-1) + "]");
        }

        if (this.counter == this.items.length) {
            addCapacity();
        }
        
        moveRight(index);
        
        this.items[index] = item;
        this.counter++;
    }
    
    /**
     * Adds capacity to the array.
     */
    private void addCapacity() {
        T[] newItems = (T[]) new Object[this.items.length * 3 / 2 + 1];
        System.arraycopy(this.items, 0, newItems, 0, this.items.length);
        this.items = newItems;
    }
    
    /**
     * Checks if an item exists in the list
     * 
     * @param item the item which is tried to find on the list
     * @return True if it exists, false otherwise
     */
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }
    
    /**
     * Returns the index of the item
     * 
     * @param item the item whose index we want to know
     * @return the index
     */
    public int indexOf(T item) {
        for (int i = 0; i < this.counter; i++) {
            if (item == null && this.items[i] == null) {
                return i;
            } else if (item == this.items[i] || this.items[i].equals(item)) {
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Shifts the items in the array left from the given index
     * 
     * @param fromIndex starting index
     */
    private void moveLeft(int fromIndex) {
        for (int i = fromIndex; i < this.counter - 1; i++) {
            this.items[i] = this.items[i + 1];
        }
    }
    
    /**
     * Shifts the items in the array right from the given index
     * @param fromIndex starting index
     */
    private void moveRight(int fromIndex) {
        for (int i = this.counter - 1; i >= fromIndex; i--) {
            this.items[i + 1] = this.items[i];
        }
    }

    /**
     * Removes an item from the array at an index
     * @param index the index from which we want to remove an item
     * @return The removed item
     */
    public T remove(int index) {
        if (index < 0 || index >= this.counter) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds [0, " + (this.counter - 1) + "]");
        }
        T removable = get(index);
        moveLeft(index);
        this.counter--;
        return removable;
    }
    
    /**
     * Removes a given item from the array if it was there
     * @param item the item we want to remove
     * @return true if it was removed
     */
    public boolean remove(T item) {
        int indexOfItem = indexOf(item);
        if (indexOfItem < 0) {
            return false; // Item was not found
        }
        moveLeft(indexOfItem);
        this.counter--;
        return true;
    }
    
    /**
     * Returns an item at the given index
     * @param index the index from which we want the item
     * @return the item in the given index
     */
    public T get(int index) {
        if (index < 0 || index >= this.counter) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds [0, " + (this.counter-1) + "]");
        }
        return this.items[index];
    }
    
    /**
     * Returns the number of items in the list.
     * @return the number of items in the list
     */
    public int size() {
        return this.counter;
    }
    
    
}
