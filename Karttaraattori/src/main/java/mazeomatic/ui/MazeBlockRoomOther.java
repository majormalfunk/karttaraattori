/*
* Jaakko Vilenius 2018
*/

package mazeomatic.ui;

import javafx.scene.paint.Color;

/**
 *
 * @author jaakkovilenius
 */
public class MazeBlockRoomOther extends MazeBlock {

    /**
     * 
     */
    public MazeBlockRoomOther() {
        super();

        this.setArcWidth(5);
        this.setArcHeight(5);
        this.setFill(Color.LIGHTGRAY);
        this.setStroke(Color.DARKGRAY);
        this.setStrokeWidth(3.0);

    }

}
