package com.nopi;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

public class ControlGUI {

    public static HBox addMenuButtons(){
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25, 12, 15, 12));
        hBox.setSpacing(10);

        //Control
        Button controlButton = new Button("Control");
        controlButton.setPrefWidth(100);

        //Cash
        Button cashButton = new Button("Cash");
        cashButton.setPrefWidth(100);

        //Error
        Button errorButton = new Button("Error");
        errorButton.setPrefWidth(100);

        hBox.getChildren().addAll(controlButton, cashButton, errorButton);

        return hBox;
    }

}
