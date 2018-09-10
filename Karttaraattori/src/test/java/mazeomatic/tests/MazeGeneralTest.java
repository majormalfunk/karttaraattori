/*
* Jaakko Vilenius 2018
*/

package mazeomatic.tests;

import mazeomatic.logic.Astar;
import mazeomatic.structures.AstarNode;
import mazeomatic.logic.Maze;
import mazeomatic.structures.Edge;
import mazeomatic.structures.MazeRandom;
import mazeomatic.structures.MazeRandomCongruential;
import mazeomatic.structures.PrimNode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkovilenius
 */
public class MazeGeneralTest {

    public Maze maze;
    public MazeRandom random;

    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int ROOMS = 5;

    public MazeGeneralTest() {

    }

    @Test
    public void testWidthOfMazeArray() {

        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, ROOMS, random);
        assertEquals(WIDTH, maze.map.length);
    }

    @Test
    public void testHeightOfMazeArray() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, ROOMS, random);
        assertEquals(HEIGHT, maze.map[0].length);
    }

    @Test
    public void testRoomCountInList() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, ROOMS, random);
        maze.chooseRoomLocations();
        assertEquals(ROOMS, maze.roomNodes.length);
    }

    @Test
    public void testToFindOneRoomInMap() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, ROOMS, random);
        maze.chooseRoomLocations();
        PrimNode room2 = new PrimNode(10, 13, 0, 0);
        maze.roomNodes[2] = room2;
        maze.placeRoomsInMaze();
        assertTrue(maze.elementAt(maze.roomNodes[2].x, maze.roomNodes[2].y) == 1);
    }

    @Test
    public void testToFindAllRoomsInMap() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, ROOMS, random);
        maze.chooseRoomLocations();
        maze.placeRoomsInMaze();
        for (int t = 0; t < maze.roomNodes.length; t++) {
            assertTrue(maze.elementAt(maze.roomNodes[t].x, maze.roomNodes[t].y) == 1);
        }
    }

    @Test
    public void testToNotToFindNonExistentRoomInMap() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, 1, random);
        maze.chooseRoomLocations();
        PrimNode room0 = new PrimNode(10, 13, 0, 0);
        maze.roomNodes[0] = room0;
        maze.placeRoomsInMaze();
        assertFalse(maze.elementAt(3, 3) == 1);
    }

    @Test
    public void testDistanceOfFirstAndLastRoom() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, 2, random);
        PrimNode room0 = new PrimNode(3, 3, 0, 0);
        maze.roomNodes[0] = room0;
        PrimNode room1 = new PrimNode(WIDTH - 3, HEIGHT - 3, 0, 1);
        maze.roomNodes[1] = room1;
        maze.placeRoomsInMaze();
        int dist = WIDTH - 6 + HEIGHT - 6;
        maze.buildGraph();
        Edge edge = maze.graph[0].get(0);
        assertEquals(dist, edge.weight);
    }


    
}
