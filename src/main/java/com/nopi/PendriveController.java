package com.nopi;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class PendriveController extends Application {


    public static void main(String[] args) {

        try {
            PendriveUtility pendriveUtility = new PendriveUtility();
            if (pendriveUtility.serialInList("6B01A8414EC9Da") || true) {
                launch(args);
            }
        } catch (IOException e) {
            System.out.println(e.getStackTrace());
        }
    }

    @Override
    public void start(Stage primaryStage) throws IOException {


        Control control = new Control("dev");

        primaryStage.setTitle("ArminBet-Control");

        ControlGUI gui = new ControlGUI(primaryStage, control);

        primaryStage.setScene(gui.menu);
        primaryStage.show();
    }
}
