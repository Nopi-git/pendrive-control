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
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class PendriveController extends Application {

    public static void main(String[] args) {

        try{
            PendriveUtility pendriveUtility = new PendriveUtility();
            if(pendriveUtility.serialInList("6B01A8414EC9Da") || true){
                launch(args);
            }
        } catch (IOException e){
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        Control control = new Control("dev");

        primaryStage.setTitle("ArminBet-Control");

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(25,25,25,25));

        Text scenetitle = new Text("Control");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        gridPane.add(scenetitle, 0, 0, 2, 1);
        

        Scene scene = new Scene(gridPane, 500,275);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
