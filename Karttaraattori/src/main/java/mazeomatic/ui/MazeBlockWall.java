/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.ui;

import javafx.scene.paint.Color;

/**
 * Maze block representing a wall in the maze.
 * 
 * @author jaakkovilenius
 */
public class MazeBlockWall extends MazeBlock {
    
    public MazeBlockWall() {
        super();
        
        this.setFill(Color.BROWN);
        this.setStroke(Color.DARKRED);
        this.setStrokeWidth(3.0);
        
    }
    
}
