/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package karttaraattori.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

/**
 * The start scene. 
 * 
 * Parameters are inputted and checked for validity here.
 * 
 * @author jaakkovilenius
 */
public class StartScene extends Scene {
    
    public static final String BACKGROUND_STYLE
            = "-fx-background-color: #cc3333; "
            + "-fx-background-radius: 0; "
            + "-fx-background-insets: 0; ";

    /**
     * Constructor
     * 
     * @param root 
     */
    public StartScene(Parent root) {
        super(root);
        
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);
        
        final Label labelWidth = new Label("Leveys:");
        GridPane.setConstraints(labelWidth, 0, 0);
        grid.getChildren().add(labelWidth);
        
        final Label labelHeight = new Label("Korkeus:");
        GridPane.setConstraints(labelHeight, 0, 1);
        grid.getChildren().add(labelHeight);
        
        final TextField valueWidth = new TextField();
        valueWidth.setPromptText("Syötä leveys");
        valueWidth.setPrefColumnCount(15);
        GridPane.setConstraints(valueWidth, 1, 0);
        grid.getChildren().add(valueWidth);
        
        final TextField valueHeight = new TextField();
        valueHeight.setPromptText("Syötä korkeus");
        valueHeight.setPrefColumnCount(15);
        GridPane.setConstraints(valueHeight, 1, 1);
        grid.getChildren().add(valueHeight);
        
        Button submit = new Button("Käynnistä");
        GridPane.setConstraints(submit, 1, 2);
        grid.getChildren().add(submit);
        
        final Label labelStatus = new Label();
        GridPane.setConstraints(labelStatus, 1, 3);
        GridPane.setColumnSpan(labelStatus, 2);
        grid.getChildren().add(labelStatus);

        //Setting an action for the Submit button
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (inputValid(valueWidth) && inputValid(valueHeight)) {
                    labelStatus.setText("Karttaa luodaan...");
                } else {
                    labelStatus.setText("Syötä validit leveys ja korkeus");
                }
            }
        });
        
        this.setRoot(grid);
    }

    /**
     * Validates the input
     * 
     * @param input
     * @return true if textfield is not null, not empty and contains only numbers
     */
    boolean inputValid(TextField input) {
        if (input.getText() != null && !input.getText().isEmpty()) {
            String text = input.getText();
            return text.matches("[0-9]*");
        }
        return false;
    }
    
}
