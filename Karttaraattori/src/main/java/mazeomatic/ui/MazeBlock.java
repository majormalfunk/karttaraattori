/*
* Jaakko Vilenius 2018
*/

package mazeomatic.ui;

import javafx.scene.shape.Rectangle;
import mazeomatic.Mazeomatic;

/**
 * Maze block class to be inherited
 *
 * @author jaakkovilenius
 */
public abstract class MazeBlock extends Rectangle {

    /**
     * Constructor
     */
    public MazeBlock() {
        super();

        setWidth(Mazeomatic.BLOCK_SIZE);
        setHeight(Mazeomatic.BLOCK_SIZE);

    }

}
