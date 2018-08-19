/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
    
    private T[] items;
    private int counter;
    
    public MazeArrayList() {
        this.items = (T[]) new Object[10];
        this.counter = 0;
    }
    
    public boolean add(T item) {
        if (this.counter == this.items.length) {
            resize();
        }
        
        this.items[this.counter] = item;
        this.counter++;
        
        return true;
    }
    
    public void add(int index, T item) {
        if (index < 0 || index >= this.counter) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds [0, " + (this.counter-1) + "]");
        }

        if (this.counter == this.items.length) {
            resize();
        }
        
        moveRight(index);
        
        this.items[index] = item;
        this.counter++;
    }
    
    private void resize() {
        T[] newItems = (T[]) new Object[this.items.length * 3 / 2 + 1];
        System.arraycopy(this.items, 0, newItems, 0, this.items.length);
        this.items = newItems;
    }
    
    public boolean contains(T item) {
        return indexOf(item) >= 0;
    }
    
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
    
    private void moveLeft(int fromIndex) {
        for (int i = fromIndex; i < this.counter - 1; i++) {
            this.items[i] = this.items[i + 1];
        }
    }
    
    private void moveRight(int fromIndex) {
        for (int i = this.counter - 1; i >= fromIndex; i--) {
            this.items[i + 1] = this.items[i];
        }
    }

    public T remove(int index) {
        if (index < 0 || index >= this.counter) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds [0, " + (this.counter - 1) + "]");
        }
        T removable = get(index);
        moveLeft(index);
        this.counter--;
        return removable;
    }
    
    public boolean remove(T item) {
        int indexOfItem = indexOf(item);
        if (indexOfItem < 0) {
            return false; // ei löydy
        }
        moveLeft(indexOfItem);
        this.counter--;
        return true;
    }
    
    public T get(int index) {
        if (index < 0 || index >= this.counter) {
            throw new ArrayIndexOutOfBoundsException("Index " + index + " out of bounds [0, " + (this.counter-1) + "]");
        }
        return this.items[index];
    }
    
    public int size() {
        return this.counter;
    }
    
    
}
