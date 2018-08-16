/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.tests;

import mazeomatic.logic.Maze;
import mazeomatic.logic.Edge;
import mazeomatic.logic.Node;
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

    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int ROOMS = 5;

    public MazeTest() {
    }

    @Test
    public void testWidthOfMazeArray() {
        maze = new Maze(WIDTH, HEIGHT, ROOMS);
        maze.placeRooms();
        maze.fillMaze();
        assertEquals(WIDTH, maze.map.length);
    }

    @Test
    public void testHeightOfMazeArray() {
        maze = new Maze(WIDTH, HEIGHT, ROOMS);
        maze.placeRooms();
        maze.fillMaze();
        assertEquals(HEIGHT, maze.map[0].length);
    }

    @Test
    public void testRoomCountInList() {
        maze = new Maze(WIDTH, HEIGHT, ROOMS);
        maze.placeRooms();
        maze.fillMaze();
        assertEquals(ROOMS, maze.roomNodes.length);
    }

    @Test
    public void testToFindRoomsInFinalMap() {
        maze = new Maze(WIDTH, HEIGHT, ROOMS);
        maze.placeRooms();
        maze.fillMaze();
        for (int t = 0; t < maze.roomNodes.length; t++) {
            assertTrue(maze.elementAt(maze.roomNodes[t].x, maze.roomNodes[t].y) == 0);
        }
    }

    @Test
    public void testDistanceOfFirstAndLastRoom() {
        maze = new Maze(WIDTH, HEIGHT, 2);
        Node room0 = new Node(2, 2, 0, 0);
        maze.roomNodes[0] = room0;
        Node room1 = new Node(WIDTH - 2, HEIGHT - 2, 0, 1);
        maze.roomNodes[1] = room1;
        maze.fillMaze();
        int dist = WIDTH - 4 + HEIGHT - 4;
        maze.buildGraph();
        Edge edge = maze.graph[0].get(0);
        assertEquals(dist, edge.weight);
    }

    @Test
    public void testPrim1() {
        // This is just a basic test to find out that Prim builds a predefined
        // spanning tree correctly.
        maze = new Maze(WIDTH, HEIGHT, 5);
        Node room0 = new Node(2, 2, 0, 0);
        maze.roomNodes[0] = room0;
        Node room1 = new Node(10, 2, 0, 1);
        maze.roomNodes[1] = room1;
        Node room2 = new Node(2, 8, 0, 2);
        maze.roomNodes[2] = room2;
        Node room3 = new Node(10, 10, 0, 3);
        maze.roomNodes[3] = room3;
        Node room4 = new Node(14, 4, 0, 4);
        maze.roomNodes[4] = room4;
        maze.fillMaze();
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
        maze = new Maze(WIDTH, HEIGHT, ROOMS);
        maze.placeRooms();
        maze.fillMaze();
        maze.buildGraph();
        maze.runPrim(0);
        //        int total = 0;
        for (Edge e : maze.spanner) {
            System.out.println("E: " + e.a + " -> " + e.b + " : " + e.weight);
            firstTotal += e.weight;
        }
        for (int r = 1; r < ROOMS; r++) {
            int total = 0;
            maze.runPrim(r);
            for (Edge e : maze.spanner) {
                total += e.weight;
            }
            assertEquals(firstTotal, total);
        }
       
    }

}
