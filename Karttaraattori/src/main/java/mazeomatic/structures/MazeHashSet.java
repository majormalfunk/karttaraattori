/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

/**
 * Implementation of a HashSet.
 * 
 * 
 * 
 * @author jaakkovilenius
 *
 * @param <T> The type of items to be stored in this HashSet
 */
public class MazeHashSet<T> {

    private final static int INITIAL_LIST_LENGTH = 22;
    private MazeArrayList<T>[] items;
    private int counter; // Count of lists in use
    //private int modder; // A variable which we take the modulus with
    private int maxLists; // Max number of lists at the moment

    
    /**
     * Constructor with initialCapacity. We should tweak this so that the fill
     * factor and not just the distance of the two nodes drives the initial
     * maxLists factor. This is run when two rooms are connected to each other
     * so therefore the fill factor i.e. how close the rooms are to each other
     * determines how long a list of searched nodes between the nodes grows.
     *
     *
     * @param initialCapacity 
     */
    public MazeHashSet(int initialCapacity) {

        this.maxLists = Math.max(INITIAL_LIST_LENGTH, initialCapacity / INITIAL_LIST_LENGTH);
        this.items = new MazeArrayList[maxLists];
        this.counter = 0;

    }
    
    /**
     * Add an item to the list
     * 
     * @param item
     * @return true if the item was not already in the set. False otherwise.
     */
    public boolean add(T item) {
        int hash = hashValue(item);
        if (items[hash] == null) {
            MazeArrayList<T> nodes = new MazeArrayList<>();
            nodes.add(item);
            items[hash] = nodes;
            counter++;
            if (4*counter > 3*maxLists) { // If fill factor > 0.75 (Don't want to cast to double)
                addCapacity();
            }
            return false;
        } else if (!contains(item)) {
            items[hash].add(item);
            counter++;
            return true;
        }
        return false;
    }

    /**
     * Checks to see if an item already exists in the set.
     * 
     * @param item
     * @return True if it exists, false otherwise
     */
    public boolean contains(T item) {
        int hash = hashValue(item);
        if (items[hash] == null) {
            return false;
        }
        MazeArrayList<T> nodes = items[hash];
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(item)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Removes an item from this set.
     * @param item
     * @return True if item existed in this set.
     */
    public boolean remove(T item) {
        int hash = hashValue(item);
        if (items[hash] == null) {
            return false;
        }
        MazeArrayList<T> nodes = items[hash];
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).equals(item)) {
                nodes.remove(i);
                counter--;
                return true;
            }
        }
        return false;
    }

    /**
     * This adds capacity to the set. At this point it just doubles the
     * capacity and adjusts the hash function accordingly
     */
    private void addCapacity() {
        System.out.println("Adding capacity from " + maxLists);
        maxLists *= 2;
        MazeArrayList<T>[] newItems = new MazeArrayList[items.length * 2];
        for (int i = 0; i < items.length; i++) {
            copyArray(newItems, i);
        }
        items = newItems;
        System.out.println("                  to " + maxLists);
    }

    /**
     * This calculates the hashvalue from the item's hash code. THe hashvalue
     * determines to which list the item is placed in.
     *
     * @param item
     * @return the hash value as an int
     */
    private int hashValue(T item) {
        return Math.abs(item.hashCode() % maxLists);
    }

    /**
     * During the addition of capacity the array of items must be
     * copied to a new array. This does that.
     * 
     * @param newItems The new Array of lists
     * @param atIndex The index of the Array we are copying
     */
    private void copyArray(MazeArrayList<T>[] newItems, int atIndex) {
        if (items[atIndex] != null) {
            for (int i = 0; i < items[atIndex].size(); i++) {
                T item = items[atIndex].get(i);
                int hash = hashValue(item);
                if (newItems[hash] == null) {
                    newItems[hash] = new MazeArrayList<>();
                }
                newItems[hash].add(item);
            }
        }
    }
    
    /**
     * Returns the number of items in this set.
     * @return number of items in this set.
     */
    public int size() {
        return counter;
    }

}
