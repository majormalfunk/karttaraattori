/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.ui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import mazeomatic.Mazeomatic;
import static mazeomatic.Mazeomatic.BLOCK_SIZE;
import mazeomatic.structures.Edge;
import mazeomatic.structures.PrimNode;
import mazeomatic.structures.MazeArrayList;

/**
 * This scene shows the constructed maze
 *
 * @author jaakkovilenius
 */
public class MazeScene extends Scene {

    private Pane pane;
    private double paneWidth;
    private double paneHeight;

    /**
     * Constructor method
     *
     * @param root
     * @param mzomtic
     */
    public MazeScene(Parent root, Mazeomatic mzomtic, int mazeWidth, int mazeHeight) {
        super(root);

        final Mazeomatic mazeomatic = mzomtic;

        pane = new Pane();
        
        paneWidth = (mazeWidth*BLOCK_SIZE) + 100;
        paneHeight = (mazeHeight*BLOCK_SIZE) + 100;

        pane.setPrefSize(paneWidth, paneHeight);

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
                double layoutX = paneWidth/2.0 - (map.length * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * i);
                double layoutY = paneHeight/2.0 - (map[i].length * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * j);
                switch (map[i][j]) {
                    case 0:
                        addBlock(new MazeBlockWall(), layoutX, layoutY);
                        break;
                    case 1:
                        addBlock(new MazeBlockRoomCenter(), layoutX, layoutY);
                        break;
                    case 2:
                        addBlock(new MazeBlockRoomOther(), layoutX, layoutY);
                        break;
                    case 3:
                        addBlock(new MazeBlockCorridor(), layoutX, layoutY);
                        break;
                    case 4: // Maze edges
                        addBlock(new MazeBlockWall(), layoutX, layoutY);
                        break;
                    
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
        MazeArrayList<Edge> spanner = mzomtic.maze.spanner;
        PrimNode[] roomNodes = mzomtic.maze.roomNodes;

        for (int i = 0; i < spanner.size(); i++) {
            Edge e = spanner.get(i);
            double startX = paneWidth/2.0 - (width * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.a].x + 0.5));
            double startY = paneHeight/2.0 - (height * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.a].y + 0.5));
            double endX = paneWidth/2.0 - (width * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.b].x + 0.5));
            double endY = paneHeight/2.0 - (height * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.b].y + 0.5));

            Line line = new Line();
            line.setStartX(startX);
            line.setStartY(startY);
            line.setEndX(endX);
            line.setEndY(endY);
            line.setStroke(Color.YELLOW);
            line.setOpacity(0.5);
            line.setStrokeWidth(5);
            pane.getChildren().add(line);
        }

    }

}
