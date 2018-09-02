/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.tests;

import mazeomatic.structures.Edge;
import mazeomatic.structures.MazeArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkovilenius
 */
public class MazeArrayListTest {

    public MazeArrayListTest() {
    }

    /**
     * Testing MazeArrayList with one item - create new list - test that list
     * size == 0 - add item to list - check that list contains that item - check
     * index of said item - get said item from list - remove item from list with
     * index - test that list size == 0 - try to remove item that is not on the
     * list - add item to list - remove item from list - test that list size ==
     * 0
     */
    @Test
    public void testMazeArrayListWithOneItem() {

        MazeArrayList<Edge> list = new MazeArrayList();
        testMazeArrayListSize(list, 0);
        Edge object = new Edge(0, 1, 1);
        testMazeArrayListAddItem(list, object, 1);
        testMazeArrayListContainsItem(list, object);
        testMazeArrayListIndexOf(list, object, 0);
        testMazeArrayListGetItem(list, object, 0);
        testMazeArrayListRemoveItemAtIndex(list, object, 0);
        testMazeArrayListSize(list, 0);
        testMazeArrayListRemoveItemExistsNot(list, object);
        testMazeArrayListAddItem(list, object, 1);
        testMazeArrayListRemoveItemExists(list, object);
        testMazeArrayListSize(list, 0);

    }

    @Test
    public void testMazeArrayListWithNullItem() {

        MazeArrayList<Edge> list = new MazeArrayList();
        Edge object = new Edge(1, 2, 1);
        list.add(object);
        list.add(null);
        testMazeArrayListIndexOf(list, null, 1);

    }

    /**
     * Testing MazeArrayList with more items - create new list - add several
     * items to list (full initial capacity) - add 1 more item to end of list -
     * check that list contains that item - get said item from list at index -
     * remove said item from list at index - test that list size == original
     * size - test that index of said item == -1 (doesn't exist on list) - try
     * to remove item that is not on the list
     */
    @Test
    public void testMazeArrayListWithMoreItems() {

        int items = 10;
        MazeArrayList<Edge> list = new MazeArrayList();
        for (int i = 0; i < items; i++) {
            Edge object = new Edge(i, i + 1, 1);
            testMazeArrayListAddItem(list, object, i + 1);
        }
        Edge object = new Edge(98, 99, 1);
        testMazeArrayListAddItem(list, object, items + 1);
        testMazeArrayListContainsItem(list, object);
        testMazeArrayListGetItem(list, object, items);
        testMazeArrayListRemoveItemAtIndex(list, object, items);
        testMazeArrayListSize(list, items);
        testMazeArrayListIndexOf(list, object, -1);
        testMazeArrayListRemoveItemExistsNot(list, object);

    }
    
    /**
     * Test MazeArrayList with more items
     * - create new list
     * - add several items to list (full initial capacity)
     * - add 1 more item at index in between to see that capacity is grown
     */
    @Test
    public void testMazeArrayListWithMoreItems2() {
        
        int items = 10;
        MazeArrayList<Edge> list = new MazeArrayList();
        for (int i = 0; i < items; i++) {
            Edge object = new Edge(i, i + 1, 1);
            testMazeArrayListAddItem(list, object, i + 1);
        }
        Edge object = new Edge(98, 99, 1);
        testMazeArrayListAddItemAtIndex(list, object, 5, items + 1);

    }

    /**
     * Testing MazeArrayList with more items - create new list - add several
     * items to list (more than full initial capacity) - add first extra item to
     * list - check that list contains that item - get said item from list - add
     * second extra item to list - check that first extra item has moved in
     * index by one - remove said second extra item from list - test that index
     * of second extra item == -1 (doesn't exist on list) - check list size -
     * check that first extra item is back on its original place - remove item
     * from list - try to remove item that is not on the list - test that list
     * size == original
     */
    @Test
    public void testMazeArrayListWithMoreItems3() {

        int items = 20;
        int firstExtraIndex = 15;
        int secondExtraIndex = 10;
        MazeArrayList<Edge> list = new MazeArrayList();
        for (int i = 0; i < items; i++) {
            Edge object = new Edge(i, i + 1, 1);
            testMazeArrayListAddItem(list, object, i + 1);
        }
        Edge object1 = new Edge(97, 98, 1);
        testMazeArrayListAddItemAtIndex(list, object1, firstExtraIndex, items + 1);
        testMazeArrayListContainsItem(list, object1);
        testMazeArrayListGetItem(list, object1, firstExtraIndex);
        Edge object2 = new Edge(98, 99, 1);
        testMazeArrayListAddItemAtIndex(list, object2, secondExtraIndex, items + 2);
        testMazeArrayListGetItem(list, object1, firstExtraIndex + 1);
        testMazeArrayListRemoveItemAtIndex(list, object2, secondExtraIndex);
        testMazeArrayListIndexOf(list, object2, -1);
        testMazeArrayListSize(list, items + 1);
        testMazeArrayListGetItem(list, object1, firstExtraIndex);
        testMazeArrayListRemoveItemExists(list, object1);
        testMazeArrayListRemoveItemExistsNot(list, object1);
        testMazeArrayListSize(list, items);

    }
    
    @Test
    public void testMazeArrayListWithMoreItems4() {
        int lot = 500;
        MazeArrayList<String> list = new MazeArrayList();
        for (int i = 0; i < lot; i++) {
            list.add("TEST" + i);
        }
        assertEquals(lot, list.size());
        for (int i = 499; i > 0; i--) {
            list.remove("TEST" + i);
        }
        assertEquals(1, list.size());
        

    }

    /**
     * Test exceptions exception when adding with wrong index
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void TestMazeArrayListAddException() {
        MazeArrayList<Edge> list = new MazeArrayList();
        Edge object = new Edge(0, 1, 1);
        list.add(list.size() + 1, object);
    }

    /**
     * Test exceptions exception when removing with wrong index
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void TestMazeArrayListRemoveException() {
        MazeArrayList<Edge> list = new MazeArrayList();
        list.remove(list.size() + 1);
    }

    /**
     * Test exceptions exception when getting with wrong index
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void TestMazeArrayListGetException() {
        MazeArrayList<Edge> list = new MazeArrayList();
        list.get(list.size() + 1);
    }

    public void testMazeArrayListSize(MazeArrayList list, int expectedSize) {
        assertEquals(expectedSize, list.size());
    }

    public void testMazeArrayListAddItem(MazeArrayList list, Object o, int expectedSize) {
        assertTrue(list.add(o));
        assertEquals(expectedSize, list.size());
    }

    public void testMazeArrayListAddItemAtIndex(MazeArrayList list, Object o, int index, int expectedSize) {
        list.add(index, o);
        assertEquals(expectedSize, list.size());
    }

    public void testMazeArrayListIndexOf(MazeArrayList list, Object o, int expectedIndex) {
        assertEquals(list.indexOf(o), expectedIndex);
    }

    public void testMazeArrayListContainsItem(MazeArrayList list, Object o) {
        assertTrue(list.contains(o));
    }

    public void testMazeArrayListGetItem(MazeArrayList list, Object o, int index) {
        assertEquals(o, list.get(index));
    }

    public void testMazeArrayListRemoveItemAtIndex(MazeArrayList list, Object o, int index) {
        assertEquals(o, list.remove(index));
    }

    public void testMazeArrayListRemoveItemExists(MazeArrayList list, Object o) {
        assertTrue(list.remove(o));
    }

    public void testMazeArrayListRemoveItemExistsNot(MazeArrayList list, Object o) {
        assertFalse(list.remove(o));
    }
}
