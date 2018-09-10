/*
* Jaakko Vilenius 2018
*/

package mazeomatic.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
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

    private GridPane grid;
    private Pane pane;
    private double paneWidth;
    private double paneHeight;

    /**
     * Constructor for the Maze Scene
     * @param root the root node
     * @param mzomtic reference to the application
     * @param mazeWidth width of the maze
     * @param mazeHeight height of the maze
     */
    public MazeScene(Parent root, Mazeomatic mzomtic, int mazeWidth, int mazeHeight) {
        super(root, (Math.min(mazeWidth, 100)* BLOCK_SIZE) + 150, (Math.min(mazeHeight, 60)* BLOCK_SIZE) + 150);

        final Mazeomatic mazeomatic = mzomtic;

        grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        Button startNew = new Button("Tee uusi");
        GridPane.setConstraints(startNew, 0, 0);
        grid.getChildren().add(startNew);

        ScrollPane scroll = new ScrollPane();
        GridPane.setConstraints(scroll, 0, 1);
        GridPane.setColumnSpan(scroll, 3);
        grid.getChildren().add(scroll);

        pane = new Pane();
        paneWidth = (mazeWidth * BLOCK_SIZE) + 100;
        paneHeight = (mazeHeight * BLOCK_SIZE) + 100;
        pane.setPrefSize(paneWidth, paneHeight);

        scroll.setContent(pane);

        //grid.getChildren().add(pane);

        //Setting an action for the Start New button
        startNew.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                try {
                    mazeomatic.resetParameters();
                } catch (Exception ex) {

                }
            }
        });

        this.setRoot(grid);

        // Walls
        addBlocks(mzomtic);
        overlayEdgesOnScreen(mzomtic);

    }

    /**
     * Adds maze blocks to the maze scene
     *
     * @param mzomtic A reference to application as an injection
     */
    public void addBlocks(Mazeomatic mzomtic) {
        int[][] map = mzomtic.maze.map;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                double layoutX = paneWidth / 2.0 - (map.length * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * i);
                double layoutY = paneHeight / 2.0 - (map[i].length * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * j);
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

    /**
     * Adds blocks to the Pane on stage
     * @param block Block to be added
     * @param layoutX x coordinate
     * @param layoutY y coordinate
     */
    private void addBlock(MazeBlock block, double layoutX, double layoutY) {
        block.setLayoutX(layoutX);
        block.setLayoutY(layoutY);
        pane.getChildren().add(block);
    }

    /**
     * This overlays the Prims edges on the screen
     * @param mzomtic A reference to the application
     */
    private void overlayEdgesOnScreen(Mazeomatic mzomtic) {

        int width = mzomtic.maze.map.length;
        int height = mzomtic.maze.map[0].length;
        MazeArrayList<Edge> spanner = mzomtic.maze.spanner;
        PrimNode[] roomNodes = mzomtic.maze.roomNodes;

        for (int i = 0; i < spanner.size(); i++) {
            Edge e = spanner.get(i);
            double startX = paneWidth / 2.0 - (width * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.a].x + 0.5));
            double startY = paneHeight / 2.0 - (height * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.a].y + 0.5));
            double endX = paneWidth / 2.0 - (width * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.b].x + 0.5));
            double endY = paneHeight / 2.0 - (height * Mazeomatic.BLOCK_SIZE * 0.5) + (Mazeomatic.BLOCK_SIZE * (roomNodes[e.b].y + 0.5));

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
