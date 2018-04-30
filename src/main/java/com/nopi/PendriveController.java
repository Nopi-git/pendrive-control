package com.nopi;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PendriveController extends Application {

    public static void main(String[] args) {

        try{
            PendriveUtility pendriveUtility = new PendriveUtility();
            if(pendriveUtility.serialInList("6B01A8414EC9Da")){
                launch(args);
            }
        } catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        Control control = new Control("6B01A8414EC9Da");

        primaryStage.setTitle("ArminBet-Control");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Control");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("ARM:");
        gridPane.add(userName, 0, 1);

        TextField armTextField = new TextField();

        armTextField.setMaxWidth(50);

        gridPane.add(armTextField, 1, 1);

        Label descTextField = new Label("Description:");
        gridPane.add(descTextField, 0, 2);

        TextArea descBox = new TextArea();
        descBox.setMinHeight(50);
        descBox.setWrapText(true);
        descBox.setMaxWidth(300);

        gridPane.add(descBox, 1, 2);

        Button btn = new Button("Submit");
        btn.setStyle("-fx-background-color: #ff0000");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        btn.setDisable(true);
        gridPane.add(hbBtn, 1, 4);
        armTextField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                if (!newValue.matches("\\d*")) {
                    armTextField.setText(newValue.replaceAll("[^\\d]", ""));
                }
                changeBtnColor(armTextField.getText(), descBox.getText(), btn, descBox, armTextField);
            }
        });
        descBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue,
                                String newValue) {
                changeBtnColor(descBox.getText(), armTextField.getText(), btn, descBox, armTextField);
            }
        });

        final Text actiontarget = new Text();
        gridPane.add(actiontarget, 1, 6);

        if(control.getPublicIp().equals("0.0.0.0")) actiontarget.setText("No connection!");

        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                control.setArmId(Integer.parseInt(armTextField.getText()));
                control.setDescription(descBox.getText());
                try {
                    int status = NetworkUtility.sendPost(control);
                    if(status == 200) System.exit(0);
                } catch (IOException e1) {
                    e1.printStackTrace();
                    actiontarget.setText("Cannot reach server");
                }
            }
        });

        Scene scene = new Scene(gridPane, 500,275);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void changeBtnColor(String text, String text2, Button btn, TextArea descBox, TextField armTextField) {
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
