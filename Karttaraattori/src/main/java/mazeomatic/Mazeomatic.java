package mazeomatic;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import mazeomatic.logic.Maze;
import mazeomatic.ui.MazeScene;
import mazeomatic.ui.StartScene;

public class Mazeomatic extends Application {

    private Group root;
    private Stage stage;
    
    public boolean stopLoop;
    public boolean parametersSet;
    public int mazeWidth;
    public int mazeHeight;
    public int rooms;
    public Maze maze;
    
    public static final int MIN_SIZE = 8;
    public static final int MIN_ROOMS = 2;
    public static final double BLOCK_SIZE = 20;

    @Override
    public void start(Stage stage) throws Exception {

        stopLoop = false;
        parametersSet = false;
        mazeWidth = 0;
        mazeHeight = 0;
        rooms = 0;

        root = new Group();
        this.stage = stage;
        stage.setTitle("Karttaraattori");
        StartScene startScene = new StartScene(root, this);
        stage.setScene(startScene);
        stage.show();

        try {

            new AnimationTimer() {

                @Override
                public void handle(long now) {
                    // Handle loop stopping first
                    if (stopLoop) {
                        System.out.println("Stopping loop.");
                        stop();
                    } else {
                        // Handle application control here
                        if (!parametersSet) {
                            if (mazeWidth != 0 && mazeHeight != 0) {
                                parametersSet = true;
                                System.out.println("Parameters set correctly!");
                                makeMaze();
                                placeRoomsInMaze();
                                fillMaze();
                                showMaze();
                                stop();

                            }
                        }
                    }

                }

            }.start();

        } catch (Exception ex) {

        }

    }
    
    /**
     * Calls the constructor of the Maze class
     */
    private void makeMaze() {
        maze = new Maze(mazeWidth, mazeHeight, rooms);
    }
    
    /**
     * Calls the function that places the rooms in the maze
     */
    private void placeRoomsInMaze() {
        maze.placeRooms();
    }
    
    /**
     * Calls the maze filling function
     */
    private void fillMaze() {
        maze.fillMaze();
    }
    
    /**
     * Calls the constructor of the visual representation of the maze
     */
    private void showMaze() {
        MazeScene mazeScene = new MazeScene(root, this);
        stage.setScene(mazeScene);
        stage.show();

        
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    

}
