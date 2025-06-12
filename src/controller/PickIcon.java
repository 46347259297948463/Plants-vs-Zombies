package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PickIcon implements Initializable{

        @FXML
        private Button cherrybombSelecterBTN;

        @FXML
        private Group group1;

        @FXML
        private Group group2;

        @FXML
        private Group group3;

        @FXML
        private Group group4;

        @FXML
        private Group group5;

        @FXML
        private Group group6;

        @FXML
        private Group group7;

        @FXML
        private Button jalapenoSelecterBTN;

        @FXML
        private Button peashooterSelecterBTN;

        @FXML
        private Button plant1BTN;

        @FXML
        private Button plant2BTN;

        @FXML
        private Button plant3BTN;

        @FXML
        private Button plant4BTN;

        @FXML
        private Button plant5BTN;

        @FXML
        private Button plant6BTN;

        @FXML
        private Button plant7BTN;

        @FXML
        private Button playBTN;

        @FXML
        private Button repeaterSelecterBTN;

        @FXML
        private Button snowshooterSelecterBTN;

        @FXML
        private Button snowshooterSelecterBTN15;

        @FXML
        private Button snowshooterSelecterBTN152;

        @FXML
        private Button snowshooterSelecterBTN1521;

        @FXML
        private Button snowshooterSelecterBTN153;

        @FXML
        private Button snowshooterSelecterBTN1531;

        @FXML
        private Button snowshooterSelecterBTN154;

        @FXML
        private Button snowshooterSelecterBTN1541;

        @FXML
        private Button sunflowerSelecterBTN;

        @FXML
        private Button tallNutSelecterBTN;

        @FXML
        private Button wallnutSelecterBTN;

        private ArrayList<String> plantsPicked = new ArrayList<>(7);

        private ArrayList<Group> groupsOfPicked = new ArrayList<>(7);

    @Override
    public void initialize(URL location, ResourceBundle resources) {
            setGroups();
            peashooterSelecterBTN.setOnAction(event -> {
                    selecter("pea shooter card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            sunflowerSelecterBTN.setOnAction(event -> {
                    selecter("sunflower card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            cherrybombSelecterBTN.setOnAction(event -> {
                    selecter("cherry bomb card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            wallnutSelecterBTN.setOnAction(event -> {
                    selecter("wall nut card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            snowshooterSelecterBTN.setOnAction(event -> {
                    selecter("snow shooter card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            repeaterSelecterBTN.setOnAction(event -> {
                    selecter("repeater card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            jalapenoSelecterBTN.setOnAction(event -> {
                    selecter("jalapenos card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });
            tallNutSelecterBTN.setOnAction(event -> {
                    selecter("tall nut card");
                    peashooterSelecterBTN.setOpacity(0.45);
            });


    }

    public void selecter(String str){
            for (int i = 0 ; i < 7 ; i++){
                    if(plantsPicked.get(i).equals(null)){
                            plantsPicked.set(i, str);
                            Image image = new Image("/images/" + str + ".png");
                            ImageView imageView = new ImageView(image);
                            Button button = new Button();
                            button.setOpacity(0);
                            button.setText("");
                            StackPane stackPane = new StackPane();
                            stackPane.getChildren().addAll(imageView, button);
                            groupsOfPicked.get(i).getChildren().addAll(stackPane);

                    }
            }
    }

    public void setGroups(){
            groupsOfPicked.add(group1);
            groupsOfPicked.add(group2);
            groupsOfPicked.add(group3);
            groupsOfPicked.add(group4);
            groupsOfPicked.add(group5);
            groupsOfPicked.add(group6);
            groupsOfPicked.add(group7);
    }
}
