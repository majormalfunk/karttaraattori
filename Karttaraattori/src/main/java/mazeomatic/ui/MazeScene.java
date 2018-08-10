/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.ui;

import java.util.ArrayList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import mazeomatic.Mazeomatic;
import mazeomatic.logic.Edge;
import mazeomatic.logic.Node;

/**
 * This scene shows the constructed maze
 *
 * @author jaakkovilenius
 */
public class MazeScene extends Scene {

    private Pane pane;

    /**
     * Constructor method
     *
     * @param root
     * @param mzomtic
     */
    public MazeScene(Parent root, Mazeomatic mzomtic) {
        super(root);

        final Mazeomatic mazeomatic = mzomtic;

        pane = new Pane();

        pane.setPrefSize(640, 480);

        this.setRoot(pane);

        // Walls
        addBlocks(mzomtic);
        overlayEdgesForTesting(mzomtic);

    }

    /**
     * Adds maze wall blocks to the maze scene
     *
     * @param width
     * @param height
     */
    private void addBlocks(Mazeomatic mzomtic) {
        int[][] map = mzomtic.maze.map;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                double layoutX = 320.0 - (map.length * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * i);
                double layoutY = 240.0 - (map[i].length * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * j);
                switch (map[i][j]) {
                    case 0:
                        addBlock(new MazeBlockFloor(), layoutX, layoutY);
                        break;
                    case 1:
                        addBlock(new MazeBlockWall(), layoutX, layoutY);
                }
            }
        }
    }

    private void addBlock(MazeBlock block, double layoutX, double layoutY) {
        block.setLayoutX(layoutX);
        block.setLayoutY(layoutY);
        pane.getChildren().add(block);
    }

    /**
     * This is just for testing purposes. Remove from final.
     */
    private void overlayEdgesForTesting(Mazeomatic mzomtic) {

        int width = mzomtic.maze.map.length;
        int height = mzomtic.maze.map[0].length;
        ArrayList<Edge> spanner = mzomtic.maze.spanner;
        Node[] roomNodes = mzomtic.maze.roomNodes;

        for (Edge e : spanner) {
            double startX = 320.0 - (width * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.a].x + 0.5));
            double startY = 240.0 - (height * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.a].y + 0.5));
            double endX = 320.0 - (width * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.b].x + 0.5));
            double endY = 240.0 - (height * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.b].y + 0.5));

            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(endX);
            line.setEndY(endY);
            line.setStroke(Color.WHITE);
            line.setStrokeWidth(5);
            pane.getChildren().add(line);
        }

    }

}
