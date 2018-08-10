/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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

    public MazeBlock() {
        super();

        setWidth(Mazeomatic.BLOCK_SIZE);
        setHeight(Mazeomatic.BLOCK_SIZE);

    }

}
