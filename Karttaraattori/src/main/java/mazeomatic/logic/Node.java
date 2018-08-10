/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.logic;

/**
 * The Node class. It will be used to represent maze elements in proximity lists
 *
 * @author jaakkovilenius
 */
public class Node implements Comparable<Node> {

    public int x;
    public int y;
    public int type;
    public int id;
    public int distance;

    /**
     * The constructor
     *
     * @param x The x coordinate
     * @param y The y coordinate
     * @param type The maze block type
     */
    public Node(int x, int y, int type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
    }

    @Override
    public int compareTo(Node o2) {
        if (this.distance < o2.distance) {
            return -1;
        } else if (this.distance > o2.distance) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        Node n = (Node) o;
        //System.out.println("Testing equality");
        if (n.id == this.id) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

}
