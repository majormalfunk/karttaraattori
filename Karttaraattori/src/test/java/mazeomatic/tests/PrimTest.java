/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.tests;

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
public class PrimTest {
    
    public Maze maze;
    public MazeRandom random;

    final static int WIDTH = 20;
    final static int HEIGHT = 20;
    final static int ROOMS = 5;

    public PrimTest() {
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
    public void testPrimPerformance() {
        //testPrimPerformance1(); // So we can comment it out easily when not wanted 
    }
    
    /**
     * Here we do a performance test. We do 10 runs on each parameter set
     * and take an average of the runtime for the 10 runs.
     * We start with 100 vertices which means there are 9900 edges.
     * Then we increment the vertice count by 100 until 2000 vertices and
     * 3998000 edges. If there are n vertices, then there are n-1 edges per vertice.
     */
    private void testPrimPerformance1() {
        int repeats = 10;
        System.out.println("TESTING PRIM PERFORMANCE:");
        System.out.println("Nodes;Average time");
        MazeRandom random = new MazeRandomCongruential();
        for (int nodes = 100; nodes <= 2000; nodes = nodes + 100) { // Number of nodes
            long average = 0;
            for (int rep = 1; rep <= repeats; rep++) { // repeats for dimension
                maze = new Maze(10, 10, nodes, random);
                maze.chooseRoomLocations();
                maze.placeRoomsInMaze();
                maze.buildGraph();
                long start = System.currentTimeMillis();
                maze.runPrim(0);
                long end = System.currentTimeMillis();
                average += (end-start);
            }
            average /= repeats;
            System.out.println("" + nodes+ ";" + average);
        }
        
    }


}
