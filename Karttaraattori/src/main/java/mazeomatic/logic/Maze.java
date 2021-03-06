/*
* Jaakko Vilenius 2018
*/

package mazeomatic.logic;

import mazeomatic.structures.Edge;
import mazeomatic.structures.AstarNode;
import mazeomatic.structures.PrimNode;
import mazeomatic.structures.MazeRandom;
import mazeomatic.structures.MazeArrayList;

/**
 * This class represents the maze. The actual maze data is stored as a 2D int
 * array.
 *
 *
 * @author jaakkovilenius
 *
 */
public class Maze {

    int width; // Width of the maze
    int height; // Height of the maze
    int rooms; // Number of rooms in the maze

    public int[][] map; // The final map
    public MazeArrayList<Edge>[] graph; // Graph of the edges as a proximity list

    private MazeRandom random; // Random number generator

    public PrimNode[] roomNodes; // List of the room nodes

    public Prim prim;

    public Astar astar;

    public MazeArrayList<Edge> spanner;
    
    public MazeArrayList<AstarNode> corridors; // MazeArrayList of corridor nodes. Not in use yet!

    /**
     * Constructor
     *
     * @param width Width of the maze in grid blocks
     * @param height Height of the maze in grid blocks
     * @param rooms Number of rooms on the maze
     * @param random A random number generator
     */
    public Maze(int width, int height, int rooms, MazeRandom random) {
        this.width = width;
        this.height = height;
        this.rooms = rooms;

        initMap();
        
        this.graph = new MazeArrayList[rooms];

        this.random = random;

        this.roomNodes = new PrimNode[rooms];
        this.corridors = new MazeArrayList<>();

    }
    
    /**
     * This initializes the map. It fills a 2D array with value 4 (edge) on the edges and otherwise with zeros.
     */
    public final void initMap() {
        
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

    }

    /**
     * This function marks the rooms in the maze
     */
    public void placeRoomsInMaze() {
        for (int k = 0; k < roomNodes.length; k++) {
            PrimNode node = roomNodes[k];
            // We'll go through two blocks in every direction of the room node.
            // The nearest 1 blocks around the center belong to the room.
            // The farthest 1 blocks around the center mark room edges. (Not the same as graph edges)
            // Corridors not involving this room must go around the edges and not
            // pass through the room.
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
            // We don't mind if rooms overlap. It just brings variation to
            // visible room sizes and shapes
            // But we should check that rooms don't get placed on top of each other
            // (Thanks, Granigan!)
            // We could perhaps use our own HashSet once it exists to
            // check whether a room already exists at the given location.
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
            MazeArrayList<Edge> proxyList = new MazeArrayList<>();
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
        /*
         * System.out.println("Prim's result:");
         * System.out.println("=============="); int total = 0; for (Edge e :
         * spanner) { System.out.println("E: " + e.a + " -> " + e.b + " : " +
         * e.weight); total += e.weight; } System.out.println("Total weight: " +
         * total);
         *
         */
    }

    /**
     * This function runs the A* algorithm.
     *
     * It first builds the parameters for Astar. The map object must contain the
     * rooms before we can build corridors between then. Also Prim's has to be
     * run first so we have pairs of rooms as a launch node and a target node.
     * 
     * !!!! We need to fix this so that we can test path lengths individually and
     * maybe animate the pathfinding on screen if project schedule allows. !!!!
     */
    public void runAstar() {
        for (int e = 0; e < spanner.size(); e++) {
            Edge edge = spanner.get(e);
            //System.out.println("Path for edge " + edge.a + " to " + edge.b);
            int launchX = roomNodes[edge.a].x;
            int launchY = roomNodes[edge.a].y;
            int targetX = roomNodes[edge.b].x;
            int targetY = roomNodes[edge.b].y;
            AstarNode launch = new AstarNode(launchX, launchY, 0, ((launchX * map.length) + launchY));
            launch.setAsLaunch();
            AstarNode target = new AstarNode(targetX, targetY, 0, ((targetX * map.length) + targetY));
            target.setAsTarget();

            AstarNode[][] astarGraph = buildGraphForAstar(launchX, launchY, targetX, targetY);
            astar = new Astar(astarGraph, launch, target);
            AstarNode[][] shortestPath = astar.buildShortestPath();
            
            traceShortestPathOntoMap(shortestPath, launchX, launchY, targetX, targetY);
            
        }

    }
    
    /**
     * This traces the spanning tree by Prim on to the map
     * @param shortestPath Graph of Astar nodes
     * @param launchX x-coordinate of starting point
     * @param launchY y-coordinate of starting point
     * @param targetX x-coordinate of ending point
     * @param targetY y-coordinate of ending point
     */
    public void traceShortestPathOntoMap(AstarNode[][] shortestPath, int launchX, int launchY, int targetX, int targetY) {
        // We also add the corridors to corridors list
        int x = targetX;
        int prevX = targetX;
        int y = targetY;
        int prevY = targetY;
        map[x][y] = 3;
        while (true) {
            // We trace the path backwards to extract the shortest path
            if (shortestPath[x][y] == null) {
                // The path is null, stop looping
                break;
            }
            if (shortestPath[x][y].x == launchX && shortestPath[x][y].y == launchY) {
                // The path was traced back to launch point
                break;
            }
            AstarNode node = shortestPath[x][y];
            // We also add the corridors to the corridors list
            // for a later reference to the corridors
            corridors.add(node);
            //System.out.println("Corridors size " + corridors.size());
            x = node.x;
            y = node.y;
            map[x][y] = 3;
            prevX = x;
            prevY = y;
        }
        
    }
    
    /**
     * This builds the graph for A* to use
     * @param launchX x-coordinate of starting point
     * @param launchY y-coordinate of starting point
     * @param targetX x-coordinate of ending point
     * @param targetY y-coordinate of ending point
     * @return A graph for A* to use
     */
    public AstarNode[][] buildGraphForAstar(int launchX, int launchY, int targetX, int targetY) {
        AstarNode[][] astarGraph = new AstarNode[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                AstarNode node = new AstarNode(i, j, 1, ((i * map.length) + j)); // Impassable
                if (map[i][j] == 0 || map[i][j] == 3 // Wall or corridor
                        || (i >= launchX - 2 && i <= launchX + 2 && j >= launchY - 2 && j <= launchY + 2)
                        || (i >= targetX - 2 && i <= targetX + 2 && j >= targetY - 2 && j <= targetY + 2)) {
                    node.type = 0; // Passable
                }
                astarGraph[i][j] = node;
            }
        }
        return astarGraph;
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
