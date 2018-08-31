/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mazeomatic.ui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import mazeomatic.Mazeomatic;

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
    public StartScene(Parent root, Mazeomatic mzmtic) {
        super(root);

        final Mazeomatic mazeomatic = mzmtic;

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(5);
        grid.setHgap(5);

        final Label labelWidth = new Label("Leveys ruutuina:");
        GridPane.setConstraints(labelWidth, 0, 0);
        grid.getChildren().add(labelWidth);

        final Label labelHeight = new Label("Korkeus ruutuina:");
        GridPane.setConstraints(labelHeight, 0, 1);
        grid.getChildren().add(labelHeight);

        final Label labelRooms = new Label("Huoneita:");
        GridPane.setConstraints(labelRooms, 0, 2);
        grid.getChildren().add(labelRooms);

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

        final TextField valueRooms = new TextField();
        valueRooms.setPromptText("Huoneiden lkm");
        valueRooms.setPrefColumnCount(15);
        GridPane.setConstraints(valueRooms, 1, 2);
        grid.getChildren().add(valueRooms);

        final Label labelWidthHint = new Label("(>= " + Mazeomatic.MIN_SIZE + ")");
        labelWidthHint.setTextFill(Color.GRAY);
        GridPane.setConstraints(labelWidthHint, 2, 0);
        grid.getChildren().add(labelWidthHint);

        final Label labelHeightHint = new Label("(>= " + Mazeomatic.MIN_SIZE + ")");
        labelHeightHint.setTextFill(Color.GRAY);
        GridPane.setConstraints(labelHeightHint, 2, 1);
        grid.getChildren().add(labelHeightHint);

        final Label labelRoomsHint = new Label("(>= " + Mazeomatic.MIN_ROOMS + ")");
        labelRoomsHint.setTextFill(Color.GRAY);
        GridPane.setConstraints(labelRoomsHint, 2, 2);
        grid.getChildren().add(labelRoomsHint);

        Button submit = new Button("Käynnistä");
        GridPane.setConstraints(submit, 1, 3);
        grid.getChildren().add(submit);

        final Label labelStatus = new Label();
        GridPane.setConstraints(labelStatus, 1, 4);
        GridPane.setColumnSpan(labelStatus, 3);
        grid.getChildren().add(labelStatus);

        //Setting an action for the Submit button
        submit.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                if (sizeValid(valueWidth) && sizeValid(valueHeight) && roomsValid(valueRooms)) {
                    labelStatus.setText("Karttaa luodaan...");
                    try {
                        mazeomatic.mazeWidth = parseInput(valueWidth);
                        mazeomatic.mazeHeight = parseInput(valueHeight);
                        mazeomatic.rooms = parseInput(valueRooms);
                    } catch (Exception ex) {

                    }
                } else {
                    labelStatus.setText("Syötä validit arvot");
                }
            }
        });

        this.setRoot(grid);
    }

    /**
     * Validates the maze size input.
     *
     * @param input
     * @return true if textfield is not null, not empty and contains only
     * numbers and is > 2
     */
    boolean sizeValid(TextField input) {
        //
        // WE SHOULD ALSO ADD A MAX SIZE
        //
        try {
            return parseInput(input) >= Mazeomatic.MIN_SIZE;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * Checks that the number of rooms is valid
     * 
     * @param input
     * @return true if it passes the validity tests false othewise
     */
    boolean roomsValid(TextField input) {
        //
        // WE NEED TO ADD CHECKS ALSO FOR MAX ROOMS
        // MAYBE WITH A FILL FACTOR CONSIDERING THE MAZE SIZE
        //
        try {
            return parseInput(input) >= Mazeomatic.MIN_ROOMS;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * This tries to make an int of the text input
     * 
     * @param input
     * @return
     * @throws Exception 
     */
    int parseInput(TextField input) throws Exception {
        return Integer.parseInt(input.getText());
    }

}
