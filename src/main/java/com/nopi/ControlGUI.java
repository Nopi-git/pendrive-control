package com.nopi;

import com.sun.tools.javac.comp.Check;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ControlGUI {

    public Scene menu, controlPage, cashPage, errorPage;
    Stage stage;
    List<CheckBox> systemCheckBoxes;
    List<CheckBox> paperCheckBoxes;
    Integer armId;

    public ControlGUI(Stage stage){
        this.menu = menuScene();
        this.controlPage = new Scene(controlScene(), 800, 300);
        this.stage = stage;
    }

    private static List<CheckBox> makeCheckBoxes(String... systemElements){
        List<CheckBox> checkBoxes = new ArrayList<>();
        for(String systemElement: systemElements){
            checkBoxes.add(new CheckBox(systemElement));
        }
        return checkBoxes;
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
        gridPane.setHgap(50);
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

        this.systemCheckBoxes = makeCheckBoxes("PC",
                "Monitor",
                "Ticket printer",
                "Offerta printer",
                "Scanner",
                "Raspberry Pi",
                "Casa");
        this.systemCheckBoxes.forEach(checkBox -> checkBox.setVisible(false));

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

        toggleFixedOrNotFixedButtons(this.systemCheckBoxes, notOkButton, fixed, notFixed);

        fixedGroupEventHandler(this.systemCheckBoxes, fixed);
        fixedGroupEventHandler(this.systemCheckBoxes, notFixed);

        this.systemCheckBoxes.forEach(checkBox -> checkBox.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if(this.systemCheckBoxes.stream().noneMatch(CheckBox::isSelected)){
                fixed.setSelected(false);
                notFixed.setSelected(false);
            }
        }));

        vbox1.getChildren().addAll(okButton, notOkButton);
        vbox1.getChildren().addAll(this.systemCheckBoxes);
        vbox1.getChildren().addAll(fixed, notFixed);

        gridPane.add(vbox1, 1, 2);

        Label paper = new Label("Paper");
        paper.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox vbox2 = new VBox(paper);

        this.paperCheckBoxes = makeCheckBoxes("checkbox1",
                "checkbox2",
                "checkbox3",
                "checkbox4",
                "checkbox5",
                "checkbox6",
                "checkbox7");
        this.paperCheckBoxes.forEach(checkBox -> checkBox.setVisible(false));

        ToggleGroup paperGroup = new ToggleGroup();
        RadioButton paperOkRadioButton = new RadioButton("All papers OK");
        paperOkRadioButton.setToggleGroup(paperGroup);
        paperOkRadioButton.setSelected(true);
        paperOkRadioButton.setPadding(new Insets(5,0,5,0));
        RadioButton paperNotOkRadioButton = new RadioButton("papers not OK");
        paperNotOkRadioButton.setToggleGroup(paperGroup);

        ToggleGroup paperFixedGroup = new ToggleGroup();
        RadioButton paperFixed = new RadioButton("paper fixed");
        paperFixed.setToggleGroup(paperFixedGroup);
        paperFixed.setPadding(new Insets(5,0,5,0));
        RadioButton paperNotFixed = new RadioButton("not fixed");
        paperNotFixed.setToggleGroup(paperFixedGroup);
        paperFixed.setVisible(false);
        paperNotFixed.setVisible(false);

        toggleFixedOrNotFixedButtons(this.paperCheckBoxes, paperNotOkRadioButton, paperFixed, paperNotFixed);

        fixedGroupEventHandler(this.paperCheckBoxes, paperFixed);
        fixedGroupEventHandler(this.paperCheckBoxes, paperNotFixed);

        this.paperCheckBoxes.forEach(checkBox -> checkBox.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if(this.paperCheckBoxes.stream().noneMatch(CheckBox::isSelected)){
                paperFixed.setSelected(false);
                paperNotFixed.setSelected(false);
            }
        }));

        vbox2.getChildren().addAll(paperOkRadioButton, paperNotOkRadioButton);
        vbox2.getChildren().addAll(this.paperCheckBoxes);
        vbox2.getChildren().addAll(paperFixed, paperNotFixed);

        gridPane.add(vbox2, 2, 2);


        Label casa = new Label("Casa");
        casa.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox vBox3 = new VBox(casa);

        ToggleGroup casaGroup = new ToggleGroup();
        RadioButton casaOkButton = new RadioButton("Everything ok");
        casaOkButton.setToggleGroup(casaGroup);
        casaOkButton.setSelected(true);
        casaOkButton.setPadding(new Insets(5,0,5,0));
        RadioButton casaNotOkButton = new RadioButton("not ok");
        casaNotOkButton.setToggleGroup(casaGroup);

        vBox3.getChildren().addAll(casaOkButton, casaNotOkButton);

        gridPane.add(vBox3, 3, 2);
        
        return gridPane;

    }

    private void toggleFixedOrNotFixedButtons(List<CheckBox> checkBoxes, RadioButton notOkButton, RadioButton fixed, RadioButton notFixed) {
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
