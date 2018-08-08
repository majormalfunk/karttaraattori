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
public class Node {
    
    public int x;
    public int y;
    public int type;
    
    /**
     * The constructor
     * 
     * @param x The x coordinate
     * @param y The y coordinate
     * @param type The maze block type
     */
    public Node(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }
    
}
