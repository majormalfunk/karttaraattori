/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.tests;

import mazeomatic.logic.Maze;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkovilenius
 */
public class MazeTest {
    
    public Maze maze;
    
    public MazeTest() {
        maze = new Maze(20, 20, 5);
        
    }

    @Test
    public void testWidthOfMazeArray() {
        assertEquals(20, maze.map.length);
    }
    
    @Test
    public void testHeightOfMazeArray() {
        assertEquals(20, maze.map[0].length);
    }
    

}
