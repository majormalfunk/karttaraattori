/*
* Jaakko Vilenius 2018
*/

package mazeomatic.tests;

import mazeomatic.structures.MazeHashSet;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkovilenius
 */
public class MazeHashSetTest {
    
    public MazeHashSetTest() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void testMazeHashSetWithOneItem() {
        
        MazeHashSet<String> set = new MazeHashSet<>(30);
        assertEquals(0, set.size());
        set.add("TEST1");
        assertEquals(1, set.size());
        
    }
    
    @Test
    public void testMazeHashSetWithSameItemTwice() {
        
        MazeHashSet<String> set = new MazeHashSet<>(30);
        assertEquals(0, set.size());
        set.add("TEST1");
        assertEquals(1, set.size());
        set.add("TEST1");
        assertEquals(1, set.size());
        
    }

    @Test
    public void testMazeHashSetWithMoreItems() {
        
        MazeHashSet<String> set = new MazeHashSet<>(30);
        assertEquals(0, set.size());
        set.add("TEST1");
        set.add("TEST2");
        set.add("TEST3");
        set.add("TEST4");
        set.add("TEST5");
        set.add("TEST6");
        assertEquals(6, set.size());
        
    }
    
    @Test
    public void testMazeHashSetWithRemoval() {
        
        MazeHashSet<String> set = new MazeHashSet<>(30);
        String test1 = "TEST1";
        String test2 = "TEST2";
        set.add(test1);
        assertEquals(1, set.size());
        set.remove(test2);
        assertEquals(1, set.size());
        set.remove(test1);
        assertEquals(0, set.size());
        set.remove(test1);
        assertEquals(0, set.size());

    }
    
    @Test
    public void testMazeHashSetToAddCapacity() {
        
        int lot = 500;
        MazeHashSet<String> set = new MazeHashSet<>(30);
        for (int i = 0; i < lot; i++) {
            set.add("TEST" + i);
        }
        assertEquals(lot, set.size());
        for (int i = 499; i > 0; i--) {
            set.remove("TEST" + i);
        }
        assertEquals(1, set.size());
        
    }
    
}
