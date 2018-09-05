package mazeomatic;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.stage.Stage;
import mazeomatic.logic.Maze;
import mazeomatic.structures.MazeRandom;
import mazeomatic.structures.MazeRandomCongruential;
import mazeomatic.ui.MazeScene;
import mazeomatic.ui.StartScene;

public class Mazeomatic extends Application {

    private Group root;
    private Stage stage;

    public boolean stopLoop;
    public boolean parametersSet;
    private boolean redo;
    public int mazeWidth;
    public int mazeHeight;
    public int rooms;
    public Maze maze;
    public MazeRandom random;

    public static final int MIN_SIZE = 8;
    public static final int MIN_ROOMS = 2;
    public static final double BLOCK_SIZE = 10;

    @Override
    public void start(Stage stage) throws Exception {

        stopLoop = false;
        parametersSet = false;
        redo = false;
        mazeWidth = 0;
        mazeHeight = 0;
        rooms = 0;
        
        random = new MazeRandomCongruential();

        root = new Group();
        this.stage = stage;
        stage.setTitle("Karttaraattori");
        showStartScene();

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
                                redo = false;
                                parametersSet = true;
                                System.out.println("Parameters set correctly!");
                                makeMaze();
                                chooseRoomLocations();
                                placeRoomsInMaze();
                                buildGraph();
                                runPrim();
                                runAstar();
                                showMaze();
                                //stop();
                            }
                        }
                        if (redo) {
                            showStartScene();
                        }
                        
                    }

                }

            }.start();

        } catch (Exception ex) {

        }

    }
    
    public void resetParameters() {
        mazeWidth = 0;
        mazeHeight = 0;
        rooms = 0;
        parametersSet = false;
        redo = true;
    }
    
    private void showStartScene() {
        redo = false;
        StartScene startScene = new StartScene(root, this);
        stage.setScene(startScene);
        stage.show();
    }
    
    /**
     * Calls the constructor of the Maze class
     */
    private void makeMaze() {
        maze = new Maze(mazeWidth, mazeHeight, rooms, random);
    }

    /**
     * Calls the function that places the rooms in the maze
     */
    private void chooseRoomLocations() {
        maze.chooseRoomLocations();
    }

    /**
     * Calls the maze filling function
     */
    private void placeRoomsInMaze() {
        maze.placeRoomsInMaze();
    }

    /**
     * Calls the graph building function
     */
    private void buildGraph() {
        maze.buildGraph();
    }

    /**
     * Calls the function to run Prim's algorithm
     */
    private void runPrim() {
        maze.runPrim(0);
    }
    
    /**
     * Calls the function to run A* algorithm
     */
    private void runAstar() {
        maze.runAstar();
    }
    
    /**
     * Calls the constructor of the visual representation of the maze
     */
    private void showMaze() {
        MazeScene mazeScene = new MazeScene(root, this, mazeWidth, mazeHeight);
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
