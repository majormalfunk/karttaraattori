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
public class MazeBlockCorridor extends MazeBlock {

    public MazeBlockCorridor() {
        super();

        this.setArcWidth(5);
        this.setArcHeight(5);
        this.setFill(Color.GRAY);
        this.setStroke(Color.DARKGRAY);
        this.setStrokeWidth(3.0);

    }

}
