package com.nopi;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ControlGUI {

    public Scene menu, controlPage, cashPage, errorPage;
    private Stage stage;
    private List<CheckBox> systemCheckBoxes;
    private List<CheckBox> paperCheckBoxes;
    private List<CheckBox> systemErrorCheckBoxes;
    private List<CheckBox> paperErrorCheckBoxes;
    private RadioButton systemOkRadioButton;
    private RadioButton systemNotOkRadioButton;
    private RadioButton paperOkRadioButton;
    private RadioButton paperNotOkRadioButton;
    private RadioButton casaOkRadioButton;
    private RadioButton casaNotOkRadioButton;
    private RadioButton systemFixedRadioButton;
    private RadioButton systemNotFixedRadioButton;
    private RadioButton paperFixedRadioButton;
    private RadioButton paperNotFixedRadioButton;
    Button submit;
    TextField armValue;
    Integer armId;

    public ControlGUI(Stage stage) {
        this.menu = menuScene();
        this.stage = stage;
    }

    private static List<CheckBox> makeCheckBoxes(String... systemElements) {
        List<CheckBox> checkBoxes = new ArrayList<>();
        for (String systemElement : systemElements) {
            checkBoxes.add(new CheckBox(systemElement));
        }
        return checkBoxes;
    }

    public Scene menuScene() {

        return new Scene(addMenuButtons(), 320, 80);
    }

    private HBox addMenuButtons() {
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(25, 12, 15, 12));
        hBox.setSpacing(10);

        //Control
        Button controlButton = new Button("Control");
        controlButton.setPrefWidth(100);
        controlButton.setOnAction(e -> this.stage.setScene(controlScene()));

        //Cash
        Button cashButton = new Button("Cash");
        cashButton.setOnAction(event -> this.stage.setScene(cashControlScene()));
        cashButton.setPrefWidth(100);

        //Error
        Button errorButton = new Button("Error");
        errorButton.setOnAction(e -> this.stage.setScene(errorScene()));
        errorButton.setPrefWidth(100);


        hBox.getChildren().addAll(controlButton, cashButton, errorButton);

        return hBox;
    }


    private Scene controlScene() {
        GridPane gridPane = controlPane(controlSystemVBox(), new Label("Control"), true);


        this.submit = new Button("send");
        submit.setDisable(true);

        systemOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        systemNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        casaOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        casaNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        systemCheckBoxes.forEach(e -> e.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit)));
        paperCheckBoxes.forEach(e -> e.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit)));
        systemFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        systemNotFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperNotFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));

        gridPane.add(submit, 4, 2);

        return new Scene(gridPane, 650, 600);
    }

    private Scene cashControlScene(){
        GridPane gridPane = controlPane(controlSystemVBox(), new Label("Control"), true);


        this.submit = new Button("Next");
        submit.setDisable(true);

        systemOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        systemNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        casaOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        casaNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        systemCheckBoxes.forEach(e -> e.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit)));
        paperCheckBoxes.forEach(e -> e.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit)));
        systemFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        systemNotFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperNotFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));
        paperFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeControlButtonToggleDisableProperty(submit));

        submit.setOnAction(event -> {
            this.stage.setScene(cashScene());
        });

        gridPane.add(submit, 4, 2);

        return new Scene(gridPane, 650, 600);
    }

    private Scene errorScene() {
        GridPane gridPane = controlPane(errorSystemVbox(), new Label("Error"), false);
        this.submit = new Button("send");
        submit.setDisable(true);

        paperOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        paperNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        casaOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        casaNotOkRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        systemCheckBoxes.forEach(e -> e.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit)));
        paperCheckBoxes.forEach(e -> e.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit)));
        systemFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        systemNotFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        paperNotFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));
        paperFixedRadioButton.selectedProperty().addListener((observable, oldValue, newValue) -> changeErrorButtonToggleDisableProperty(submit));


        gridPane.add(submit, 4, 2);
        return new Scene(gridPane, 650, 600);
    }

    private void changeErrorButtonToggleDisableProperty(Button button) {
        if ((casaOkRadioButton.isSelected() || casaNotOkRadioButton.isSelected()) &&
                systemCheckBoxes.stream().anyMatch(CheckBox::isSelected) &&
                (systemFixedRadioButton.isSelected() || systemNotFixedRadioButton.isSelected()) && !armValue.getText().equals("")) {
            if(paperOkRadioButton.isSelected()){
                button.setDisable(false);
            } else if(paperNotOkRadioButton.isSelected() && paperCheckBoxes.stream().anyMatch(CheckBox::isSelected) && (paperFixedRadioButton.isSelected() || paperNotFixedRadioButton.isSelected())){
                button.setDisable(false);
            } else button.setDisable(true);
        } else button.setDisable(true);
    }

    private void changeControlButtonToggleDisableProperty(Button button) {
        if (systemOkRadioButton.isSelected() &&
                paperOkRadioButton.isSelected() &&
                (casaOkRadioButton.isSelected() || casaNotOkRadioButton.isSelected()) && !armValue.getText().equals("")) {
            button.setDisable(false);
        } else {
            if(systemNotOkRadioButton.isSelected() && systemCheckBoxes.stream().anyMatch(CheckBox::isSelected) && (systemFixedRadioButton.isSelected() || systemNotFixedRadioButton.isSelected()) && paperOkRadioButton.isSelected()){
                button.setDisable(false);
            } else if(paperNotOkRadioButton.isSelected() && paperCheckBoxes.stream().anyMatch(CheckBox::isSelected) && (paperFixedRadioButton.isSelected() || paperNotFixedRadioButton.isSelected()) && systemOkRadioButton.isSelected()){
                button.setDisable(false);
            } else if((systemNotOkRadioButton.isSelected() && systemCheckBoxes.stream().anyMatch(CheckBox::isSelected) && (systemFixedRadioButton.isSelected() || systemNotFixedRadioButton.isSelected()) &&
                    (paperNotOkRadioButton.isSelected() && paperCheckBoxes.stream().anyMatch(CheckBox::isSelected) && (paperFixedRadioButton.isSelected() || paperNotFixedRadioButton.isSelected())))){
                button.setDisable(false);
            } else button.setDisable(true);
        }
    }

    public Scene cashScene(){

        GridPane gridPane = new GridPane();
        gridPane.setHgap(35);

        CheckBox outcome = new CheckBox("Outcome:");

        TextField outcomeValue = new TextField();
        outcomeValue.setDisable(true);
        outcome.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue) outcomeValue.setDisable(false);
            else outcomeValue.setDisable(true);
        }));

        CheckBox income = new CheckBox("Income:");
        TextField incomeValue = new TextField();
        incomeValue.setDisable(true);

        Label label = new Label("Chitanta:");
        label.setPadding(new Insets(0,0,0,35));

        TextField chitanta = new TextField();
        chitanta.setDisable(true);

        income.selectedProperty().addListener(((observable, oldValue, newValue) -> {
            if(newValue){
                incomeValue.setDisable(false);
                chitanta.setDisable(false);
            } else {
                incomeValue.setDisable(true);
                chitanta.setDisable(true);
            }
        }));

        gridPane.add(outcome, 1, 1);
        gridPane.add(outcomeValue, 2, 1);
        gridPane.add(income, 1, 2);
        gridPane.add(incomeValue, 2, 2);
        gridPane.add(label, 1, 3);
        gridPane.add(chitanta, 2, 3);

        return new Scene(gridPane, 500, 300);

    }

    public GridPane controlPane(VBox firstVbox, Label menu, boolean isControl) {
        GridPane gridPane = new GridPane();
        gridPane.setHgap(50);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(5, 5, 5, 5));

        Label armId = new Label("ARM:");
        armId.setAlignment(Pos.CENTER);
        armId.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        this.armValue = new TextField();
        armId.setLabelFor(armValue);
        this.armValue.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,4}?")) {
                armValue.setText(oldValue);
            }
            if(isControl) changeControlButtonToggleDisableProperty(submit);
            else changeErrorButtonToggleDisableProperty(submit);
        });
        this.armValue.setFont(Font.font("Arial", FontWeight.BOLD, 12));
        this.armValue.setMaxWidth(45);

        gridPane.add(armId, 0, 1);
        gridPane.add(armValue, 1, 1);

        gridPane.setStyle("-fx-background-color: #C0C0C0;");

        VBox vBox1 = firstVbox;

        gridPane.add(vBox1, 1, 2);

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
        this.paperOkRadioButton = new RadioButton("All papers OK");
        this.paperOkRadioButton.setToggleGroup(paperGroup);
        this.paperOkRadioButton.setPadding(new Insets(5, 0, 5, 0));
        this.paperNotOkRadioButton = new RadioButton("papers not OK");
        this.paperNotOkRadioButton.setToggleGroup(paperGroup);

        ToggleGroup paperFixedGroup = new ToggleGroup();
        this.paperFixedRadioButton = new RadioButton("paper fixed");
        this.paperFixedRadioButton.setToggleGroup(paperFixedGroup);
        this.paperFixedRadioButton.setPadding(new Insets(5, 0, 5, 0));
        this.paperNotFixedRadioButton = new RadioButton("not fixed");
        this.paperNotFixedRadioButton.setToggleGroup(paperFixedGroup);
        this.paperFixedRadioButton.setVisible(false);
        this.paperNotFixedRadioButton.setVisible(false);

        toggleFixedOrNotFixedButtons(this.paperCheckBoxes, paperNotOkRadioButton, this.paperFixedRadioButton, this.paperNotFixedRadioButton);
        this.paperErrorCheckBoxes = new ArrayList<>();
        this.paperCheckBoxes.forEach(checkBox -> checkBox.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            this.paperErrorCheckBoxes.forEach(checkBox1 -> vbox2.getChildren().remove(checkBox1));
            this.paperErrorCheckBoxes.clear();
            if (this.paperCheckBoxes.stream().noneMatch(CheckBox::isSelected)) {
                this.paperFixedRadioButton.setSelected(false);
                this.paperNotFixedRadioButton.setSelected(false);
            }
            this.paperCheckBoxes.stream().filter(CheckBox::isSelected).forEachOrdered(checkBox1 -> this.paperErrorCheckBoxes.add(new CheckBox(checkBox1.getText())));
            if(paperNotFixedRadioButton.isSelected()){
                this.paperErrorCheckBoxes.forEach(e->e.setVisible(true));
            } else paperErrorCheckBoxes.forEach(e->e.setVisible(false));
            vbox2.getChildren().addAll(this.paperErrorCheckBoxes);
        }));

        fixedGroupEventHandler(this.paperCheckBoxes, this.paperFixedRadioButton, false, paperErrorCheckBoxes);
        fixedGroupEventHandler(this.paperCheckBoxes, this.paperNotFixedRadioButton, true, paperErrorCheckBoxes);


        vbox2.getChildren().addAll(paperOkRadioButton, paperNotOkRadioButton);
        vbox2.getChildren().addAll(this.paperCheckBoxes);
        vbox2.getChildren().addAll(this.paperFixedRadioButton, this.paperNotFixedRadioButton);

        gridPane.add(vbox2, 2, 2);


        Label casa = new Label("Casa");
        casa.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        VBox vBox3 = new VBox(casa);

        ToggleGroup casaGroup = new ToggleGroup();
        this.casaOkRadioButton = new RadioButton("Everything ok");
        this.casaOkRadioButton.setToggleGroup(casaGroup);
        this.casaOkRadioButton.setPadding(new Insets(5, 0, 5, 0));
        this.casaNotOkRadioButton = new RadioButton("not ok");
        this.casaNotOkRadioButton.setToggleGroup(casaGroup);

        vBox3.getChildren().addAll(this.casaOkRadioButton, this.casaNotOkRadioButton);

        gridPane.add(vBox3, 3, 2);

        menu.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        gridPane.add(menu, 4, 1);

        return gridPane;

    }

    private VBox controlSystemVBox() {
        Label system = new Label("System");
        system.setFont(Font.font("Arial", FontWeight.BOLD, 20));

        CheckBox checkBox4 = new CheckBox("Install");
        VBox vbox1 = new VBox(system, checkBox4);

        this.systemCheckBoxes = makeCheckBoxes("PC",
                "Monitor",
                "Ticket printer",
                "Offerta printer",
                "Scanner",
                "Raspberry Pi",
                "Casa");
        this.systemCheckBoxes.forEach(checkBox -> checkBox.setVisible(false));

        ToggleGroup problemGroup = new ToggleGroup();
        this.systemOkRadioButton = new RadioButton("Everything ok");
        this.systemOkRadioButton.setToggleGroup(problemGroup);
        this.systemOkRadioButton.setPadding(new Insets(5, 0, 5, 0));
        this.systemNotOkRadioButton = new RadioButton("not ok");
        this.systemNotOkRadioButton.setToggleGroup(problemGroup);


        ToggleGroup fixedGroup = new ToggleGroup();
        this.systemFixedRadioButton = new RadioButton("fixed");
        this.systemFixedRadioButton.setToggleGroup(fixedGroup);
        this.systemFixedRadioButton.setPadding(new Insets(5, 0, 5, 0));
        this.systemNotFixedRadioButton = new RadioButton("not fixed");
        this.systemNotFixedRadioButton.setToggleGroup(fixedGroup);
        this.systemFixedRadioButton.setVisible(false);
        this.systemNotFixedRadioButton.setVisible(false);

        toggleFixedOrNotFixedButtons(this.systemCheckBoxes, this.systemNotOkRadioButton, this.systemFixedRadioButton, this.systemNotFixedRadioButton);

        this.systemErrorCheckBoxes = new ArrayList<>();
        dynamicSystemCheckBoxes(vbox1);

        fixedGroupEventHandler(this.systemCheckBoxes, this.systemFixedRadioButton, false, systemErrorCheckBoxes);
        fixedGroupEventHandler(this.systemCheckBoxes, this.systemNotFixedRadioButton, true, systemErrorCheckBoxes);



        vbox1.getChildren().addAll(this.systemOkRadioButton, this.systemNotOkRadioButton);
        vbox1.getChildren().addAll(this.systemCheckBoxes);
        vbox1.getChildren().addAll(this.systemFixedRadioButton, this.systemNotFixedRadioButton);

        return vbox1;
    }

    private VBox errorSystemVbox() {
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

        Label error = new Label("Error(s)");
        vbox1.getChildren().add(error);


        ToggleGroup fixedGroup = new ToggleGroup();
        this.systemFixedRadioButton = new RadioButton("fixed");
        this.systemFixedRadioButton.setToggleGroup(fixedGroup);
        this.systemFixedRadioButton.setPadding(new Insets(5, 0, 5, 0));
        this.systemNotFixedRadioButton = new RadioButton("not fixed");
        this.systemNotFixedRadioButton.setToggleGroup(fixedGroup);

        this.systemErrorCheckBoxes = new ArrayList<>();
        dynamicSystemCheckBoxes(vbox1);

        fixedGroupEventHandler(this.systemCheckBoxes, this.systemFixedRadioButton, false, this.systemErrorCheckBoxes);
        fixedGroupEventHandler(this.systemCheckBoxes, this.systemNotFixedRadioButton, true, this.systemErrorCheckBoxes);


        vbox1.getChildren().addAll(this.systemCheckBoxes);
        vbox1.getChildren().addAll(this.systemFixedRadioButton, this.systemNotFixedRadioButton);

        return vbox1;
    }

    private void dynamicSystemCheckBoxes(VBox vbox1) {
        this.systemCheckBoxes.forEach(checkBox -> checkBox.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            this.systemErrorCheckBoxes.forEach(checkBox1 -> vbox1.getChildren().remove(checkBox1));
            this.systemErrorCheckBoxes.clear();
            if (this.systemCheckBoxes.stream().noneMatch(CheckBox::isSelected)) {
                this.systemFixedRadioButton.setSelected(false);
                this.systemNotFixedRadioButton.setSelected(false);
            }
            this.systemCheckBoxes.stream().filter(CheckBox::isSelected).forEachOrdered(checkBox1 -> this.systemErrorCheckBoxes.add(new CheckBox(checkBox1.getText())));
            if(this.systemNotFixedRadioButton.isSelected()){
                this.systemErrorCheckBoxes.forEach(e->e.setVisible(true));
            } else this.systemErrorCheckBoxes.forEach(e->e.setVisible(false));
            vbox1.getChildren().addAll(this.systemErrorCheckBoxes);
        }));
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
    //restrict to select radiobutton when no checkbox selected
    private void fixedGroupEventHandler(List<CheckBox> checkBoxes, RadioButton notFixed, boolean visible, List<CheckBox> errorBoxes) {
        notFixed.selectedProperty().addListener((obs, wasPreviouslySelected, isNowSelected) -> {
            if (checkBoxes.stream().anyMatch(CheckBox::isSelected)) {
                if (isNowSelected) {
                    notFixed.setSelected(true);
                }
            } else {
                notFixed.setSelected(false);
            }
            System.out.println(errorBoxes==null);
            if(errorBoxes!=null){
                System.out.println(errorBoxes.size());
                if(visible){
                    errorBoxes.forEach(e->e.setVisible(visible));
                } else errorBoxes.forEach(e->e.setVisible(visible));
            }
        });
    }

}
