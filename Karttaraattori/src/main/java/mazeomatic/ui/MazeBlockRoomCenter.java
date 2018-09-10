/*
* Jaakko Vilenius 2018
*/

package mazeomatic.ui;

import javafx.scene.paint.Color;

/**
 *
 * @author jaakkovilenius
 */
public class MazeBlockRoomCenter extends MazeBlock {

    /**
     * Constructor
     */
    public MazeBlockRoomCenter() {
        super();

        this.setArcWidth(5);
        this.setArcHeight(5);
        this.setFill(Color.GRAY);
        this.setStroke(Color.DARKGRAY);
        this.setStrokeWidth(3.0);

    }

}
