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

    public PrimNode[] roomNodes; // List of the room nodes

    public Prim prim;

    public Astar astar;

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
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == 0 || j == 0 || i == map.length - 1 || j == map[0].length - 1) {
                    map[i][j] = 4; // Maze edges
                } else {
                    map[i][j] = 0;
                }
            }
        }

        graph = new ArrayList[rooms];

        random = new Random();

        roomNodes = new PrimNode[rooms];

    }

    /**
     * This function marks the rooms in the maze
     */
    public void placeRoomsInMaze() {
        for (int k = 0; k < roomNodes.length; k++) {
            PrimNode node = roomNodes[k];
            for (int l = node.x - 2; l < node.x + 3; l++) {
                for (int m = node.y - 2; m < node.y + 3; m++) {
                    if (l == node.x && m == node.y) {
                        // Room center = room node position
                        map[l][m] = 1;
                    } else if (map[l][m] != 1) {
                        if (l == node.x - 2 || l == node.x + 2 || m == node.y - 2 || m == node.y + 2) {
                            // Room edges
                            if (map[l][m] != 1 && map[l][m] != 2) {
                                map[l][m] = 4;
                            }
                        } else {
                            // Other parts of room
                            map[l][m] = 2;
                        }
                    }
                }
            }
        }

    }

    /**
     * This function builds a list of the room nodes
     */
    public void chooseRoomLocations() {
        //System.out.println("Placing rooms");
        for (int i = 0; i < rooms; i++) {
            // HERE WE MIGHT HAVE A PROBLEM IF 2 ROOMS GET PLACED IN THE SAME PLACE
            int roomX = random.nextInt(width - 4) + 2;
            int roomY = random.nextInt(height - 4) + 2;
            PrimNode room = new PrimNode(roomX, roomY, 1, i);
            //System.out.println("Room #" + i + " @ " + roomX + "," + roomY);
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
        //System.out.println("Filling graph");
        for (int i = 0; i < rooms; i++) {
            ArrayList<Edge> proxyList = new ArrayList<>();
            graph[i] = proxyList;
            for (int j = 0; j < rooms; j++) {
                if (i != j) {
                    // Don't include the room itself in the proximity list
                    int weight = Math.abs(roomNodes[i].x - roomNodes[j].x) + Math.abs(roomNodes[i].y - roomNodes[j].y);
                    Edge edge = new Edge(i, j, weight);
                    proxyList.add(edge);
                }
            }
        }
        /**
        System.out.println("Proximity list:");
        for (int g = 0; g < graph.length; g++) {
            System.out.print(g + " : ");
            for (Edge e : graph[g]) {
                System.out.print(e.a + ">" + e.b + "=" + e.weight + " ");
            }
            System.out.print("\n");
        }
        **/

    }

    /**
     * This function runs the Prim's algorithm.
     *
     * @param start The node from which to start the tree building. Must be less
     * than the count of rooms
     */
    public void runPrim(int start) {
        prim = new Prim(graph, roomNodes, start);
        spanner = prim.buildSpanningTree();
        /**
        System.out.println("Prim's result:");
        System.out.println("==============");
        int total = 0;
        for (Edge e : spanner) {
            System.out.println("E: " + e.a + " -> " + e.b + " : " + e.weight);
            total += e.weight;
        }
        System.out.println("Total weight: " + total);
        **/
    }

    /**
     * This function runs the A* algorithm.
     *
     * It first builds the parameters for Astar. The map object must contain the
     * rooms before we can build corridors between then. Also Prim's has to be
     * run first so we have pairs of rooms as launch node and target node.
     *
     *
     */
    public void runAstar() {
        for (Edge edge : spanner) {
            AstarNode launch = new AstarNode(roomNodes[edge.a].x, roomNodes[edge.a].y, 0);
            launch.setAsLaunch();
            AstarNode target = new AstarNode(roomNodes[edge.b].x, roomNodes[edge.b].y, 0);
            target.setAsTarget();
            AstarNode[][] astarGraph = new AstarNode[map.length][map[0].length];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map[0].length; j++) {
                    AstarNode node = new AstarNode(i, j, 1); // Impassable
                    if (map[i][j] == 0 || map[i][j] == 3
                            || (i >= launch.x - 2 && i <= launch.x + 2 && j >= launch.y - 2 && j <= launch.y + 2)
                            || (i >= target.x - 2 && i <= target.x + 2 && j >= target.y - 2 && j <= target.y + 2)) { // Wall or corridor
                        node.type = 0; // Passable
                    }
                    astarGraph[i][j] = node;
                }
            }
            
            //System.out.println("A* graph");
            //logAstarGraph(astarGraph);
            
            astar = new Astar(astarGraph, launch, target);
            AstarNode[][] shortestPath = astar.buildShortestPath();
            int x = target.x;
            int edx = target.x;
            int y = target.y;
            int edy = target.y;
            map[x][y] = 3;
            while (true) {
                if (shortestPath[x][y] == null) {
                    break;
                }
                if (shortestPath[x][y].x == launch.x && shortestPath[x][y].y == launch.y) {
                    break;
                }
                x = shortestPath[x][y].x;
                y = shortestPath[x][y].y;
                if (Math.abs(x-edx) + Math.abs(y-edy) != 1) {
                    System.out.println("VIRHE! Liikkuu kulmittain x: " + x + " vs " + edx + " y: " + y + " vs " +edy);
                }
                map[x][y] = 3;
                edx = x;
                edy = y;
            }

        }
        //AstarNode launch
        //AstarNode target
    }
    
    private void logAstarGraph(AstarNode[][] astarGraph) {
        for (int i = 0; i < astarGraph.length; i++) {
            for (int j = 0; j < astarGraph[0].length; j++) {
                if (astarGraph[i][j] != null) {
                    System.out.print("|" + astarGraph[i][j].type);
                } else {
                    System.out.print("| ");
                }
            }
            System.out.print("|\n");
            for (int j = 0; j < astarGraph[0].length; j++) {
                System.out.print("--");
            }
            System.out.print("\n");
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
