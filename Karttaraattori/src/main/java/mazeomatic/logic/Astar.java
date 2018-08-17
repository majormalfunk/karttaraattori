/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

import java.util.HashSet; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN
import java.util.PriorityQueue; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN

/**
 * Implementation of A* algorithm
 *
 * @author jaakkovilenius
 */
public class Astar {

    AstarNode[][] graph;
    AstarNode launch;
    AstarNode target;

    AstarNode[][] path;
    PriorityQueue<AstarNode> heap;
    HashSet<AstarNode> set;
    
    static final int X_DIFF[] = {0, -1, 1, 0};
    static final int Y_DIFF[] = {-1, 0, 0, 1};

    /**
     * Constructor for the class
     *
     * In this implementation the graph is a 2D array. Impassable node types
     * are 1's and passable node's are 0's.
     *
     * @param graph The graph as a 2D array i.e. the map
     * @param launch The node from which to launch the path building
     * @param target The target node
     *
     * @see mazeomatic.logic.Edge
     * @see mazeomatic.logic.PrimNode
     */
    public Astar(AstarNode[][] graph, AstarNode launch, AstarNode target) {

        this.graph = graph;
        this.launch = launch;
        this.target = target;

        this.path = new AstarNode[graph.length][graph[0].length];
        this.heap = new PriorityQueue<>();
        this.set = new HashSet<>();

    }

    /**
     * This builds the shortest path using A* algorithm.
     *
     * @return An ArrayList of the edges making up the shortest
     */
    public AstarNode[][] buildShortestPath() {

        for (int i = 1; i < graph.length; i++) {
            for (int j = 1; j < graph[0].length; j++) {
                if (i == launch.x && j == launch.y) {
                    graph[i][j].distToLaunch = 0;
                } else {
                    graph[i][j].distToLaunch = Integer.MAX_VALUE - (graph.length * graph[0].length); // No overflow
                }
                graph[i][j].distToTarget = Math.abs(target.x - i) + Math.abs(target.y - j);
                heap.add(graph[i][j]);
            }
        }
        //System.out.println("FROM " + launch.x + ", " + launch.y + " TO " + target.x + ", " + target.y);
        while (!set.contains(target)) {
            AstarNode u = heap.poll();
            //System.out.println("Polled " + u.x + ", " + u.y);
            //System.out.println("Heap size = " + heap.size());
            set.add(u);
            
            for (int d = 0; d < X_DIFF.length; d++) {
                if ((u.x > 1 && u.x < graph.length-2 && u.y > 1 && u.y < graph[0].length-2)
                        && graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]].type != 1
                        && graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]].distToLaunch > u.distToLaunch + distance(u, graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]])) {
                    heap.remove(graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]]);
                    graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]].distToLaunch = u.distToLaunch + distance(u, graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]]);
                    path[u.x + X_DIFF[d]][u.y + Y_DIFF[d]] = u;
                    heap.add(graph[u.x + X_DIFF[d]][u.y + Y_DIFF[d]]);
                }
            }
        }

        return path;
    }
    
    private int distance(AstarNode from, AstarNode to) {
        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
    }
    

}
