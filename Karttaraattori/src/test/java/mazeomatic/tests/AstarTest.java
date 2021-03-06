/*
* Jaakko Vilenius 2018
*/

package mazeomatic.tests;

import mazeomatic.logic.Astar;
import mazeomatic.logic.Maze;
import mazeomatic.structures.AstarNode;
import mazeomatic.structures.MazeRandom;
import mazeomatic.structures.MazeRandomCongruential;
import mazeomatic.structures.PrimNode;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jaakkovilenius
 */
public class AstarTest {

    public Maze maze;
    public MazeRandom random;

    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int ROOMS = 5;

    public AstarTest() {

    }

    @Test
    public void testRunSimpleObstacledAstar() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, 2, random);
        PrimNode room0 = new PrimNode(2, 2, 0, 0);
        maze.roomNodes[0] = room0;
        PrimNode room1 = new PrimNode(2, 9, 0, 1);
        maze.roomNodes[1] = room1;
        maze.placeRoomsInMaze();
        maze.buildGraph();
        maze.runPrim(0);
        // We should have a maze with two rooms and one edge
        maze.map[0][5] = 4; // Astar should go around this
        maze.map[1][5] = 4; // Astar should go around this
        maze.map[2][5] = 4; // Astar should go around this
        maze.runAstar();
        // We check only launch, target and obstacle on the way
        // because her we only care that Astar goes around the obstacle.
        // We don't know for sure what steps it takes before and after the obstacle.
        assertTrue(maze.map[2][2] == 1); // Center
        assertFalse(maze.map[2][5] == 3); // Obstacle
        assertTrue(maze.map[2][6] == 3); // Corridor (center of room)
    }

    @Test
    public void testRunSimpleStraightAstar() {
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, 2, random);
        PrimNode room0 = new PrimNode(2, 2, 0, 0);
        maze.roomNodes[0] = room0;
        PrimNode room1 = new PrimNode(2, 9, 0, 1);
        maze.roomNodes[1] = room1;
        maze.placeRoomsInMaze();
        maze.buildGraph();
        maze.runPrim(0);
        // We should have a maze with two rooms and one edge
        maze.runAstar();
        assertTrue(maze.map[2][2] == 1); // Center
        assertTrue(maze.map[2][3] == 3); // Corridor (in room)
        assertTrue(maze.map[2][4] == 3); // Corridor (edge around room)
        assertTrue(maze.map[2][5] == 3); // Corridor
        assertTrue(maze.map[2][6] == 3); // Corridor (edge around room)
        assertTrue(maze.map[2][7] == 3); // Corridor (in room)
        assertTrue(maze.map[2][8] == 3); // Corridor (center of room)
    }

    @Test
    public void testAstarPathLength() {
        //System.out.println("Testing A* path length:");
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, 2, random);
        PrimNode room0 = new PrimNode(2, 2, 0, 0);
        maze.roomNodes[0] = room0;
        PrimNode room1 = new PrimNode(2, 9, 0, 1);
        maze.roomNodes[1] = room1;
        maze.placeRoomsInMaze();
        maze.buildGraph();
        maze.runPrim(0);
        // We should have a maze with two rooms and one edge
        maze.map[0][5] = 4; // Astar should go around this
        maze.map[1][5] = 4; // Astar should go around this
        maze.map[2][5] = 4; // Astar should go around this
        maze.runAstar();
        //System.out.println("Test path length: " + maze.corridors.size());
        //for (int c = 0; c < maze.corridors.size(); c++) {
        //    System.out.println("STEP:" + maze.corridors.get(c).toString());
        //}
        assertEquals(8, maze.corridors.size());
    }

    @Test
    public void testAstarPermformance() {
        //testAstarPerformance1();
    }

    public void testAstarPerformance1() {
        System.out.println("Testing A* performance:");
        MazeRandom random = new MazeRandomCongruential();
        for (int size = 100; size <= 2000; size += 100) { // Side length of maze
            long average = 0;
            long repeats = 10;
            for (int rep = 1; rep <= repeats; rep++) { // repeats for dimension
                maze = new Maze(size, size, 2, random);
                PrimNode room0 = new PrimNode(10, 10, 0, 0);
                maze.roomNodes[0] = room0;
                PrimNode room1 = new PrimNode(88, 88 - 11, 0, 1);
                maze.roomNodes[1] = room1;
                maze.placeRoomsInMaze();
                maze.buildGraph();
                maze.runPrim(0);
                for (int wall = 30; wall < 60; wall++) {
                    maze.map[50][wall] = 4;
                }
                long start = System.currentTimeMillis();
                maze.runAstar();
                long end = System.currentTimeMillis();
                average += (end - start);
            }
            average /= repeats;
            System.out.println("" + (size * size) + ";" + average);
        }
    }

}
