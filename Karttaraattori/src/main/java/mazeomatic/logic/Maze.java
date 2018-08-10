/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

import java.util.ArrayList; // WE NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN
import java.util.Random; // WE MAY NEED TO REPLACE THIS WITH AN IMPLEMENTATION OF OUR OWN

/**
 * This class represents the maze. It is basically a 2D int array. At this point
 * 0 means no wall and 1 means a wall.
 *
 * @author jaakkovilenius
 */
public class Maze {

    int width; // Width of the maze
    int height; // Height of the maze
    int rooms; // Number of rooms in the maze

    public int[][] map; // The final map
    public ArrayList<Edge>[] graph; // Graph of the edges as a proximity list

    private Random random; // Random number generator

    public Node[] roomNodes; // List of the room nodes

    public Prim prim;

    public ArrayList<Edge> spanner;

    /**
     * Constructor
     *
     * @param width Width of the maze in grid blocks
     * @param height Height of the maze in grid blocks
     * @param rooms Number of rooms on the maze
     */
    public Maze(int width, int height, int rooms) {
        this.width = width;
        this.height = height;
        this.rooms = rooms;

        map = new int[width][height];
        graph = new ArrayList[rooms];

        random = new Random();

        roomNodes = new Node[rooms];

    }

    /**
     * This function fills the 2D array representing the maze
     */
    public void fillMaze() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = 1;
            }
        }
        for (int k = 0; k < roomNodes.length; k++) {
            Node node = roomNodes[k];
            for (int l = node.x - 1; l < node.x + 2; l++) {
                for (int m = node.y - 1; m < node.y + 2; m++) {
                    map[l][m] = node.type;
                }
            }
        }

    }

    /**
     * This function builds a list of the room nodes
     */
    public void placeRooms() {
        System.out.println("Placing rooms");
        for (int i = 0; i < rooms; i++) {
            int roomX = random.nextInt(width - 4) + 2;
            int roomY = random.nextInt(height - 4) + 2;
            Node room = new Node(roomX, roomY, 0, i);
            System.out.println("Room #" + i + " @ " + roomX + "," + roomY);
            roomNodes[i] = room;
        }

    }

    /**
     * This function builds a graph of the room nodes as a proximity list with
     * each the indeces referring to the nodes' index in roomNodes list. The
     * weights in the edges are the Manhattan distances of the nodes. Note that
     * we are measuring the distance from the center block of the room (which is
     * the actual node location).
     */
    public void buildGraph() {
        System.out.println("Filling graph");
        for (int i = 0; i < rooms; i++) {
            ArrayList<Edge> proxyList = new ArrayList<>();
            graph[i] = proxyList;
            for (int j = i + 1; j < rooms; j++) {
                int weight = Math.abs(roomNodes[i].x - roomNodes[j].x) + Math.abs(roomNodes[i].y - roomNodes[j].y);
                Edge edge = new Edge(i, j, weight);
                proxyList.add(edge);
            }
        }

        prim = new Prim(graph, roomNodes, 0);
        spanner = prim.buildSpanningTree();

        for (Edge e : spanner) {
            System.out.println("E: " + e.a + " -> " + e.b + " : " + e.weight);
        }

    }

    /**
     * This function returns the element at the given coordinates
     *
     * @param width The width coordinate
     * @param height The height coordinate
     * @return Value of the element at coordinates (0 = no wall, 1 = wall)
     */
    public int elementAt(int width, int height) {
        return map[width][height];
    }

}
