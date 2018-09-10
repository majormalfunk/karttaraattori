/*
* Jaakko Vilenius 2018
*/

package mazeomatic.structures;

/**
 * This represents an edge between nodes
 * @author jaakkovilenius
 */
public class Edge {

    public int a;
    public int b;
    public int weight;

    /**
     * Constructor
     * @param a first node
     * @param b second node
     * @param weight weight of the edge
     */
    public Edge(int a, int b, int weight) {
        this.a = a;
        this.b = b;
        this.weight = weight;
    }

}
