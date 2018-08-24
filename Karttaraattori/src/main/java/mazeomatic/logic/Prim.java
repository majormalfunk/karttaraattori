/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

import mazeomatic.structures.Edge;
import mazeomatic.structures.PrimNode;
import mazeomatic.structures.MazeArrayList; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN
import java.util.PriorityQueue; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN

/**
 * Implementation of Prim's algorithm
 *
 * @author jaakkovilenius
 */
public class Prim {

    MazeArrayList<Edge>[] graph;
    PrimNode[] nodes;
    int start;

    int distance[];
    int parent[];
    PriorityQueue<PrimNode> heap;

    /**
     * Constructor for the class
     *
     * @param graph The graph as a proximity list of Edges
     * @param nodes An array of the nodes
     * @param start The starting node from which to start building the spanning
     * tree
     *
     * @see mazeomatic.logic.Edge
     * @see mazeomatic.logic.PrimNode
     */
    public Prim(MazeArrayList<Edge>[] graph, PrimNode[] nodes, int start) {

        this.graph = graph;
        this.nodes = nodes;
        this.start = start;

        this.distance = new int[nodes.length];
        this.parent = new int[nodes.length];
        this.heap = new PriorityQueue<>();

    }

    /**
     * This builds the spanning tree using Prim's algorithm.
     *
     * @return An ArrayList of the edges making up the spanning tree
     */
    public MazeArrayList<Edge> buildSpanningTree() {
        MazeArrayList<Edge> spanner = new MazeArrayList<>();

        initArrays();
        addNodesToHeap();
        
        while (!heap.isEmpty()) {
            // Poll the nearest node from the heap
            PrimNode u = heap.poll();
            // If it already has a parent we add it to the spanning tree
            if (parent[u.id] != -1) {
                spanner.add(new Edge(parent[u.id], u.id, distance[u.id]));
            }
            // Then we loop through the nodes on the proximity list
            for (int g = 0; g < graph[u.id].size(); g++) {
                Edge e = graph[u.id].get(g);
                if (heap.contains(nodes[e.b]) && e.weight < distance[e.b]) {
                    // This will change when we implement ou minimum heap
                    // Java's PriorityQueue doesn't have a decrease key command.
                    parent[e.b] = u.id;
                    distance[e.b] = e.weight;
                    heap.remove(nodes[e.b]);
                    nodes[e.b].distance = distance[e.b];
                    heap.add(nodes[e.b]);
                }
            }
        }

        return spanner;
    }
    
    private void initArrays() {
        // Set all nodes' distance value to MAX and
        // distance array values to MAX and
        // all parent array values to -1 (= no parent yet)
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].distance = Integer.MAX_VALUE;
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        nodes[start].distance = 0;
        distance[start] = 0;
    }
    
    private void addNodesToHeap() {
        // Add all nodes to heap
        for (int n = 0; n < nodes.length; n++) {
            heap.add(nodes[n]);
        }

    }

}
