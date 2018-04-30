package com.nopi;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class ControlGUI {

    public static Button submitButton(GridPane gridPane){
        Button btn = new Button("Submit");
        btn.setStyle("-fx-background-color: #ff0000");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setDisable(true);
        gridPane.add(hbBtn, 1, 4);
        return btn;
    }

    public static void changeBtnColor(String text, String text2, Button btn, TextArea descBox, TextField armTextField) {
        if(!text.equals("") && !text2.equals("")){
            btn.setDisable(false);
            btn.setStyle("-fx-background-color: #2aff0b");
        }
        else{
            btn.setDisable(true);
            btn.setStyle("-fx-background-color: #ff0000");
        }
    }

}
