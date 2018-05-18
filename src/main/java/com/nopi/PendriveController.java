package com.nopi;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class PendriveController extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        ControlData controlData;
        primaryStage.setTitle("ArminBet-ControlData-V1.1-ALPHA");
        String pendriveSerial = "";

        try {
            PendriveUtility pendriveUtility = new PendriveUtility();
            pendriveSerial = pendriveUtility.getValidPendriveSerial();
            controlData = new ControlData();
            ControlGUI gui = new ControlGUI(pendriveSerial, primaryStage, controlData);
            primaryStage.setScene(gui.menu);

        } catch (WmicException e) {
            ControlGUI gui = new ControlGUI(pendriveSerial, primaryStage, null);
            primaryStage.setScene(gui.errorWindow(e.getMessage()));
        } catch (IOException e) {
            ControlGUI gui = new ControlGUI(pendriveSerial, primaryStage, null);
            if (e.getMessage().equals("No such host is known (ipinfo.io)"))
                primaryStage.setScene(gui.errorWindow("No Internet"));
            else primaryStage.setScene(gui.errorWindow(e.getMessage()));
        } catch (NoPendriveException e) {
            ControlGUI gui = new ControlGUI(pendriveSerial, primaryStage, null);
            primaryStage.setScene(gui.errorWindow(e.getMessage()));
        }
        primaryStage.show();
    }
}
