package com.nopi;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class PendriveController extends Application {


    public static void main(String[] args) {

        try {
            PendriveUtility pendriveUtility = new PendriveUtility();
            if (pendriveUtility.serialInList("4B0188D051C9")||true) {
                launch(args);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public void start(Stage primaryStage){
        Control control;
        primaryStage.setTitle("ArminBet-Control-V1.1-ALPHA");

        try{
            control = new Control("dev");
            ControlGUI gui = new ControlGUI(primaryStage, control);
            primaryStage.setScene(gui.menu);
        }catch (IOException e){
            ControlGUI gui = new ControlGUI(primaryStage, null);
            primaryStage.setScene(gui.errorWindow(e.getMessage()));
        }
        primaryStage.show();
    }
}
