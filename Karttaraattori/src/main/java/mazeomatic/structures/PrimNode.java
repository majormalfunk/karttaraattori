/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.structures;

/**
 * The PrimNode class. It will be used to represent maze elements in proximity lists
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

    public int getDistance() {
        return this.distance;
    }

    @Override
    public int compareTo(PrimNode o2) {
        
        if (this.distance < o2.distance) {
            return -1;
        } else if (this.distance > o2.distance) {
            return 1;
        }
        return 0;
        
    }

    @Override
    public boolean equals(Object o) {
        
        PrimNode n = (PrimNode) o;
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
