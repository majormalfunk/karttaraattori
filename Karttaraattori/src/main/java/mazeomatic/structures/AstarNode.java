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
     */
    public AstarNode(int x, int y, int type, int id) {
        this.x = x;
        this.y = y;
        this.type = type;
        this.id = id;
        distToLaunch = Integer.MAX_VALUE;
        distToTarget = Integer.MAX_VALUE;
        path = null;
    }
    
    public void setAsLaunch() {
        distToLaunch = 0;
    }
    
    public void setAsTarget() {
        distToTarget = 0;
    }

    @Override
    public int compareTo(AstarNode o2) {
        if (this.distToLaunch+this.distToTarget < o2.distToLaunch+o2.distToTarget) {
            return -1;
        } else if (this.distToLaunch+this.distToTarget > o2.distToLaunch+o2.distToTarget) {
            return 1;
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        AstarNode n = (AstarNode) o;
        //System.out.println("Testing equality");
        if (n.id == this.id) {
            return true;
        }

        return false;
    }

    @Override
    public int hashCode() {
        // E.g. x = 9 and y 30 => (x*10^2)+y = 9*100+30 = 930
        // return (this.x*(10^(String.valueOf(this.y).length())))+this.y;
        return this.id;
    }

}
