/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

import java.util.Arrays;

/**
 *
 * @author jaakkovilenius
 * @param <T> The type of items to be stored in this heap
 */
public class MazeMinHeap<T extends Comparable<T>> {

    private static final int INITIAL_CAPACITY = 20;
    private T[] items;
    private int size;

    public MazeMinHeap() {
        items = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    public void add(T item) {
        if (item != null) {
            if (size >= items.length - 1) {
                addCapacity();
            }
            size++;
            int i = size-1;
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

    public T poll() {
        if (size == 0) {
            return null;
        }
        T min = items[0];
        items[0] = items[size-1];
        size--;
        heapify(0);
        return min;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public boolean contains(T item) {
        if (isEmpty()) {
            return false;
        }
        return indexOf(item) != -1;
    }
    
    public boolean remove(T item) {
        int i = indexOf(item);
        if (i == -1) {
            return false;
        }
        items[i] = items[size-1];
        items[size-1] = null;
        size--;
        heapify(i);
        return true;
    }
    
    private int indexOf(T item) {
        if (isEmpty()) {
            return -1;
        }
        int i = 0;
        while(i < size) {
            if (items[i].equals(item)) {
                return i;
            }
            i++;
        }
        return -1;
    }
    
    public void heapDecKey() {

    }

    private void addCapacity() {
        items = Arrays.copyOf(items, items.length * 2);
    }

    private int parent(int i) {
        return i / 2;
    }

    private int left(int i) {
        return 2 * i;
    }

    private int right(int i) {
        return 2 * i + 1;
    }

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
        } else if (l == size-1 && items[i].compareTo(items[l]) > 0) {
            swap(i, l);
        }

    }

    private void swap(int index1, int index2) {
        T tmp = items[index1];
        items[index1] = items[index2];
        items[index2] = tmp;
    }
}
