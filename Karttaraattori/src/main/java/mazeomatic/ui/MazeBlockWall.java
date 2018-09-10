/*
* Jaakko Vilenius 2018
*/

package mazeomatic.ui;

import javafx.scene.paint.Color;

/**
 * Maze block representing a wall in the maze.
 *
 * @author jaakkovilenius
 */
public class MazeBlockWall extends MazeBlock {

    /**
     * Constructor
     */
    public MazeBlockWall() {
        super();

        this.setFill(Color.BROWN);
        //this.setStroke(Color.DARKRED);
        //this.setStrokeWidth(3.0);

    }

}
