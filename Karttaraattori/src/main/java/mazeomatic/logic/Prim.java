/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

import java.util.ArrayList; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN
import java.util.PriorityQueue; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN

/**
 * Implementation of Prim's algorithm
 *
 * @author jaakkovilenius
 */
public class Prim {

    ArrayList<Edge>[] graph;
    Node[] nodes;
    int start;

    int distance[];
    int parent[];
    PriorityQueue<Node> heap;

    /**
     * Constructor for the class
     *
     * @param graph The graph as a proximity list of Edges
     * @param nodes An array of the nodes
     * @param start The starting node from which to start building the spanning
     * tree
     *
     * @see mazeomatic.logic.Edge
     * @see mazeomatic.logic.Node
     */
    public Prim(ArrayList<Edge>[] graph, Node[] nodes, int start) {

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
    public ArrayList<Edge> buildSpanningTree() {
        ArrayList<Edge> spanner = new ArrayList<>();

        for (int i = 0; i < distance.length; i++) {
            distance[i] = Integer.MAX_VALUE;
            parent[i] = -1;
        }
        distance[start] = 0;

        for (int n = 0; n < nodes.length; n++) {
            //System.out.println("Adding to heap " + n + " = " + nodes[n].id);
            nodes[n].distance = distance[n];
            heap.add(nodes[n]);
            //System.out.println("Heap size = " + heap.size());
        }

        while (!heap.isEmpty()) {
            Node u = heap.poll();
            //System.out.println("Polled " + u.id);
            //System.out.println("Heap size = " + heap.size());
            if (parent[u.id] != -1) {
                spanner.add(new Edge(parent[u.id], u.id, distance[u.id]));
                //System.out.println("Added to list " + parent[u.id] + " -> " + u.id);
            }
            for (Edge e : graph[u.id]) {
                //System.out.println("Looping through : " + e.a + "(" + u.id+ ") : " + e.b);
                //System.out.println("Heap contains " + heap.contains(nodes[e.b]));
                //System.out.println("Weight " + e.a + " -> " + e.b + " : " + e.weight);
                if (heap.contains(nodes[e.b]) && e.weight < distance[e.b]) {
                    //System.out.println("Node " + nodes[e.b] + " in heap");
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

}
