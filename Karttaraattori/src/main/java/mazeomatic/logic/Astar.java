/*
* Jaakko Vilenius 2018
*/

package mazeomatic.logic;

import mazeomatic.structures.AstarNode;
import mazeomatic.structures.MazeMinHeap;
import mazeomatic.structures.MazeHashSet;

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
    MazeMinHeap<AstarNode> heap;
    MazeHashSet<AstarNode> set;

    // We use X_DIFF and Y_DIFF when checking horizontally and vertically around the currentNode node.
    static final int X_DIFF[] = {0, -1, 1, 0};
    static final int Y_DIFF[] = {-1, 0, 0, 1};

    /**
     * Constructor for the class
     *
     * In this implementation the graph is a 2D array. Impassable node types are
     * 1's and passable node's are 0's.
     *
     * @param graph The graph as a 2D array i.e. the map
     * @param launch The node from which to launch the path building
     * @param target The target node
     *
     * @see mazeomatic.structures.Edge
     * @see mazeomatic.structures.PrimNode
     */
    public Astar(AstarNode[][] graph, AstarNode launch, AstarNode target) {

        this.graph = graph;
        this.launch = launch;
        this.target = target;

        this.path = new AstarNode[graph.length][graph[0].length];
        this.heap = new MazeMinHeap<>();
        this.set = new MazeHashSet<>(distance(this.launch, this.target));

    }

    /**
     * This builds the shortest path using A* algorithm.
     *
     * @return An ArrayList of the edges making up the shortest path
     */
    public AstarNode[][] buildShortestPath() {

        initGraph();

        while (!set.contains(target)) {
            AstarNode currentNode = heap.poll();
            set.add(currentNode);

            for (int d = 0; d < X_DIFF.length; d++) {
                int vX = currentNode.x + X_DIFF[d];
                int vY = currentNode.y + Y_DIFF[d];
                // We should make this cleaner looking:
                // What we're checking here is
                // 1) we're inside the limits
                // 2) we're not trying to walk through other rooms
                // 3) the actual A* condition that have we found a shorter path
                if ((currentNode.x > 1 && currentNode.x < graph.length - 2 && currentNode.y > 1 && currentNode.y < graph[0].length - 2)
                        && graph[vX][vY].type != 1
                        && graph[vX][vY].distToLaunch > currentNode.distToLaunch + distance(currentNode, graph[vX][vY])) {
                    // We could implement a decrease key method in the heap but then we'd loose
                    // generality which allows us to first test with say Strings.
                    // So we'll leave it like this for now.
                    heap.remove(graph[vX][vY]);
                    graph[vX][vY].distToLaunch = currentNode.distToLaunch + distance(currentNode, graph[vX][vY]);
                    path[vX][vY] = currentNode;
                    heap.add(graph[vX][vY]);
                }
            }
        }

        return path;
    }

    /**
     * This initializes the graph for A*. It sets the default values for the beginning in a 2D array
     */
    private void initGraph() {
        //long start = System.currentTimeMillis();
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[0].length; j++) {
                if (i == launch.x && j == launch.y) {
                    graph[i][j].distToLaunch = 0;
                } else {
                    graph[i][j].distToLaunch = Integer.MAX_VALUE - (graph.length * graph[0].length); // No overflow
                }
                graph[i][j].distToTarget = Math.abs(target.x - i) + Math.abs(target.y - j);
                heap.add(graph[i][j]);
            }
        }
        //long end = System.currentTimeMillis();
        //System.out.println("Building heap took " + (end - start));

    }

    /**
     * This calculates a Manhattan distance between two nodes
     *
     * @param from The starting (launch) node
     * @param to The ending (target) node
     * @return distance in block units
     */
    private int distance(AstarNode from, AstarNode to) {
        return Math.abs(from.x - to.x) + Math.abs(from.y - to.y);
    }

}
