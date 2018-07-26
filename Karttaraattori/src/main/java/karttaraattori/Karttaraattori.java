package karttaraattori;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Karttaraattori extends Application {

    private Stage stage;
    private Scene scene;
    private Group root;


    @Override
    public void start(Stage stage) throws Exception {
        
        this.stage = stage;
        this.scene = new Scene(new Group());
        this.root = (Group) scene.getRoot();
        
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
