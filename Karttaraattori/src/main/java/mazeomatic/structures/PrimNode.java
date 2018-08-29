/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

/**
 * The PrimNode class. It will be used to represent maze elements in proximity
 * lists
 *
 * @author jaakkovilenius
 */
public class PrimNode implements Comparable<PrimNode> {

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
     * @param type The maze block type ()
     * @param id Id of the node. Used to identify node e.g. in heap
     *
     * @see mazeomatic.ui.MazeScene for block types.
     */
    public PrimNode(int x, int y, int type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;

    }

    /**
     * Getter for distance
     *
     * @return the distance of this node
     */
    public int getDistance() {
        return distance;
    }

    /**
     * Setter for distance
     *
     * @param distance
     */
    public void setDistance(int distance) {
        this.distance = distance;
    }

    /**
     * Implementation of compareTo method. Allows us to order instances of this
     * type
     *
     * @param o2 another instance of the same type
     * @return
     */
    @Override
    public int compareTo(PrimNode o2) {

        if (this.distance < o2.distance) {
            return -1;
        } else if (this.distance > o2.distance) {
            return 1;
        }
        return 0;

    }

    /**
     * Implemantation of equals method. Allows us to compare whether this
     * instance is equal to another instance ot the same type.
     *
     * @param o another object
     * @return
     */
    @Override
    public boolean equals(Object o) {

        try {
            PrimNode n = (PrimNode) o;
            if (n.id == this.id) {
                return true;
            }
            return false;
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * Override hashCode()
     *
     * @return the inner id of this instance
     */
    @Override
    public int hashCode() {
        return this.id;
    }

}
