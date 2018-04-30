package com.nopi;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlGUI {

    public Scene menu, controlPage, cashPage, errorPage;
    Stage stage;

    public ControlGUI(Stage stage){
        this.menu = menuScene();
        this.controlPage = new Scene(controlScene(), 800, 300);
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


    public GridPane controlScene(){
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5,5,5,5));

        Label armId = new Label("ARM:");
        armId.setAlignment(Pos.CENTER);
        armId.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        TextField armValue = new TextField();
        armId.setLabelFor(armValue);
        armValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}?")) {
                armValue.setText(oldValue);
            }
        });
        armValue.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        armValue.setMaxWidth(45);

        gridPane.add(armId, 0, 1);
        gridPane.add(armValue, 1, 1);

        gridPane.setStyle("-fx-background-color: #C0C0C0;");


        Label system = new Label("System");
        system.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        VBox vbox1 = new VBox(system);

        CheckBox checkBox1 = new CheckBox("PC");
        CheckBox checkBox2 = new CheckBox("Monitor");
        CheckBox checkBox3 = new CheckBox("Ticket printer");
        CheckBox checkBox4 = new CheckBox("Offerta printer");
        CheckBox checkBox5 = new CheckBox("Scanner");
        CheckBox checkBox6 = new CheckBox("Raspberry");
        CheckBox checkBox7 = new CheckBox("Casa");

        List<CheckBox> checkBoxes = Arrays.asList(checkBox1, checkBox2, checkBox3, checkBox4, checkBox5, checkBox6, checkBox7);
        checkBoxes.forEach(checkBox -> checkBox.setVisible(false));

        ToggleGroup problemGroup = new ToggleGroup();
        RadioButton okButton = new RadioButton("Everything ok");
        okButton.setToggleGroup(problemGroup);
        okButton.setSelected(true);
        okButton.setPadding(new Insets(5,0,5,0));
        RadioButton notOkButton = new RadioButton("not ok");
        notOkButton.setToggleGroup(problemGroup);


        ToggleGroup fixedGroup = new ToggleGroup();
        RadioButton fixed = new RadioButton("fixed");
        fixed.setToggleGroup(fixedGroup);
        fixed.setPadding(new Insets(5,0,5,0));
        RadioButton notFixed = new RadioButton("not fixed");
        notFixed.setToggleGroup(fixedGroup);
        fixed.setVisible(false);
        notFixed.setVisible(false);

        notOkButton.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (isNowSelected) {
                checkBoxes.forEach(checkBox -> checkBox.setVisible(true));
                fixed.setVisible(true);
                notFixed.setVisible(true);
            } else {
                checkBoxes.forEach(checkBox -> checkBox.setVisible(false));
                fixed.setVisible(false);
                notFixed.setVisible(false);
            }
        });

        fixedGroupEventHandler(checkBoxes, fixed);
        fixedGroupEventHandler(checkBoxes, notFixed);

        checkBoxes.forEach(checkBox -> checkBox.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if(checkBoxes.stream().noneMatch(CheckBox::isSelected)){
                fixed.setSelected(false);
                notFixed.setSelected(false);
            }
        }));

        vbox1.getChildren().addAll(okButton, notOkButton);
        vbox1.getChildren().addAll(checkBoxes);
        vbox1.getChildren().addAll(fixed, notFixed);

        gridPane.add(vbox1, 1, 2);

        return gridPane;

    }

    private void fixedGroupEventHandler(List<CheckBox> checkBoxes, RadioButton notFixed) {
        notFixed.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (checkBoxes.stream().anyMatch(CheckBox::isSelected)) {
                if(isNowSelected){
                    notFixed.setSelected(true);
                }
            } else {
                notFixed.setSelected(false);
            }
        });
    }

}
