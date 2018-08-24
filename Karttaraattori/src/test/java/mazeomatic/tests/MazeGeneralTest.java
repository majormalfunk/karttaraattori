/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    @Test
    public void testPrim1() {
        // This is just a basic test to find out that Prim builds a predefined
        // spanning tree correctly.
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, 5, random);
        PrimNode room0 = new PrimNode(2, 2, 0, 0);
        maze.roomNodes[0] = room0;
        PrimNode room1 = new PrimNode(10, 2, 0, 1);
        maze.roomNodes[1] = room1;
        PrimNode room2 = new PrimNode(2, 8, 0, 2);
        maze.roomNodes[2] = room2;
        PrimNode room3 = new PrimNode(10, 10, 0, 3);
        maze.roomNodes[3] = room3;
        PrimNode room4 = new PrimNode(14, 4, 0, 4);
        maze.roomNodes[4] = room4;
        maze.placeRoomsInMaze();
        maze.buildGraph();
        maze.runPrim(0);

        String[] solution = new String[4];
        solution[0] = "0->2:6";
        solution[1] = "0->1:8";
        solution[2] = "1->4:6";
        solution[3] = "1->3:8";

        for (int e = 0; e < 4; e++) {
            Edge edge = maze.spanner.get(e);
            assertTrue((edge.a + "->" + edge.b + ":" + edge.weight).equals(solution[e]));
        }

    }

    @Test
    public void testPrim2() {
        // Here we test to see that the total weight of a the spanning tree
        // based on a random graph is always the same with the same graph
        // no matter from which vertice we start building the tree.
        int firstTotal = 0;
        MazeRandom random = new MazeRandomCongruential();
        maze = new Maze(WIDTH, HEIGHT, ROOMS, random);
        maze.chooseRoomLocations();
        maze.placeRoomsInMaze();
        maze.buildGraph();
        maze.runPrim(0);
        //        int total = 0;
        for (int i = 0; i < maze.spanner.size(); i++) {
            Edge e = maze.spanner.get(i);
            System.out.println("E: " + e.a + " -> " + e.b + " : " + e.weight);
            firstTotal += e.weight;
        }
        for (int r = 1; r < ROOMS; r++) {
            int total = 0;
            maze.runPrim(r);
            for (int i = 0; i < maze.spanner.size(); i++) {
                Edge e = maze.spanner.get(i);
                total += e.weight;
            }
            assertEquals(firstTotal, total);
        }

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
    public void testAstarPathLength() {
        
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

        /// WORK IN PROGRESS TO TEST PATH LENGTH IN TEST CASES
            

    }
    
}
