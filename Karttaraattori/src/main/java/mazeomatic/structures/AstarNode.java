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
public class AstarNode implements Comparable<AstarNode> {

    public int x;
    public int y;
    public int type;
    public int id;
    public int distToLaunch;
    public int distToTarget;
    AstarNode path;

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
    public AstarNode(int x, int y, int type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
        this.distToLaunch = Integer.MAX_VALUE;
        this.distToTarget = Integer.MAX_VALUE;
        this.path = null;
    }

    /**
     * Sets this node as the launch node by setting it distance to launch as 0
     */
    public void setAsLaunch() {
        distToLaunch = 0;
    }

    /**
     * Sets this node as the target node by setting its distance to target as 0
     */
    public void setAsTarget() {
        distToTarget = 0;
    }

    /**
     * Implementation of compareTo method. Allows us to order instances of this
     * type
     *
     * @param o2 another instance of the same type
     * @return
     */
    @Override
    public int compareTo(AstarNode o2) {
        if (this.distToLaunch + this.distToTarget < o2.distToLaunch + o2.distToTarget) {
            return -1;
        } else if (this.distToLaunch + this.distToTarget > o2.distToLaunch + o2.distToTarget) {
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
        AstarNode n = (AstarNode) o;
        //System.out.println("Testing equality");
        if (n.id == this.id) {
            return true;
        }

        return false;
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
