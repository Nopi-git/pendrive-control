package com.nopi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class ControlGUI {

    public Scene menu, controlPage, cashPage, errorPage;
    Stage stage;

    public ControlGUI(Stage stage){
        this.menu = menuScene();
        this.controlPage = controlScene();
        this.stage = stage;
    }

    public Scene menuScene(){

        return new Scene(addMenuButtons(),320, 80);
    }

    private HBox addMenuButtons(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25, 12, 15, 12));
        hBox.setSpacing(10);

        //Control
        Button controlButton = new Button("Control");
        controlButton.setPrefWidth(100);
        controlButton.setOnAction(e -> this.stage.setScene(controlPage));

        //Cash
        Button cashButton = new Button("Cash");
        cashButton.setPrefWidth(100);

        //Error
        Button errorButton = new Button("Error");
        errorButton.setPrefWidth(100);


        hBox.getChildren().addAll(controlButton, cashButton, errorButton);

        return hBox;
    }


    public Scene controlScene(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5,5,5,5));

        Label armId = new Label("ARM:");
        armId.setAlignment(Pos.CENTER);
        armId.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        TextField armValue = new TextField();
        armId.setLabelFor(armValue);
        armValue.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d{0,4}?")) {
                    armValue.setText(oldValue);
                }
            }
        });
        armValue.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        armValue.setMaxWidth(45);

        gridPane.add(armId, 0, 1);
        gridPane.add(armValue, 1, 1);

        gridPane.setStyle("-fx-background-color: #C0C0C0;");

        return new Scene(gridPane, 800, 300);

    }

}
