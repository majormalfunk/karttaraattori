/*
* Jaakko Vilenius 2018
*/

package mazeomatic.tests;

import mazeomatic.structures.PrimNode;
import mazeomatic.structures.MazeMinHeap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkovilenius
 */
public class MazeMinHeapTest {

    public MazeMinHeapTest() {

    }

    @Test
    public void testMazeMinHeapEmptyHeap() {
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        testMazeMinHeapEmptiness(heap, true);
    }

    @Test
    public void testMazweMinHeapAddNull() {
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        heap.add(null);
        testMazeMinHeapSize(heap, 0);
    }
    
    @Test
    public void testMinHeapPollEmptyHeap() {
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        assertTrue(heap.poll() == null);
    }

    @Test
    public void testRemoveFromEmptyHeap() {
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        assertFalse(heap.remove("THIS"));
    }
    
    @Test
    public void testMazeMinHeapAddOneString() {
        //System.out.println("*** testMazeMinHeapAddOneString ***");
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        heap.add("Test string");
        testMazeMinHeapEmptiness(heap, false);
        testMazeMinHeapSize(heap, 1);
    }

    @Test
    public void testMazeMinHeapAddTwoStrings() {
        //System.out.println("*** testMazeMinHeapAddTwoStrings ***");
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        heap.add("ABC");
        heap.add("DEF");
        testMazeMinHeapSize(heap, 2);
    }

    @Test
    public void testMazeMinHeapPollCorrectString1() {
        //System.out.println("*** testMazeMinHeapPollCorrectString1 ***");
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        String str1 = "ABC";
        String str2 = "DEF";
        heap.add(str1);
        heap.add(str2);
        testMazeMinHeapPoll(heap, str1);
    }

    @Test
    public void testMazeMinHeapPollCorrectString2() {
        //System.out.println("*** testMazeMinHeapPollCorrectString2 ***");
        MazeMinHeap<String> heap = new MazeMinHeap<>();
        String str1 = "DEF";
        String str2 = "ABC";
        heap.add(str1);
        heap.add(str2);
        testMazeMinHeapPoll(heap, str2);
    }

    public void testMazeMinHeapEmptiness(MazeMinHeap heap, boolean expectedValue) {
        assertEquals(heap.isEmpty(), expectedValue);
    }

    public void testMazeMinHeapSize(MazeMinHeap heap, int expectedSize) {
        assertEquals(expectedSize, heap.size());
    }

    public void testMazeMinHeapPoll(MazeMinHeap heap, Object o) {
        assertTrue(heap.poll().equals(o));
    }

}
