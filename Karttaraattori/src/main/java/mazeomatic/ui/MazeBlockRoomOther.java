/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.ui;

import javafx.scene.paint.Color;

/**
 *
 * @author jaakkovilenius
 */
public class MazeBlockRoomOther extends MazeBlock {

    public MazeBlockRoomOther() {
        super();

        this.setArcWidth(5);
        this.setArcHeight(5);
        this.setFill(Color.LIGHTGRAY);
        this.setStroke(Color.DARKGRAY);
        this.setStrokeWidth(3.0);

    }

}
