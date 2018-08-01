package karttaraattori;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import karttaraattori.ui.StartScene;


public class Karttaraattori extends Application {

    private Stage stage;
    private StartScene scene;
    private Group root;


    @Override
    public void start(Stage stage) throws Exception {
        
        Group root = new Group();
        StartScene scene = new StartScene(root);
        stage.setTitle("Karttaraattori");
        stage.setScene(scene);
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
