package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import model.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DayLevel implements Initializable {

    @FXML
    private AnchorPane dayAnc;

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
    private Button shovelBTN;

    @FXML
    private Label sunPoints;

    @FXML
    private Button menuBTN;

    private ArrayList<String> names = new ArrayList<>();

    private ArrayList<Group> groupsOfPicked = new ArrayList<>(7);

    private ArrayList<Button> buttonsOfPicked = new ArrayList<>(7);

    private ArrayList<Plants> plants = new ArrayList<>(7);


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setButtons();
        setGroups();
        deleteCard$setPlants();

        plant1BTN.setOnAction(event -> {
        });

    }

    public void setNames(ArrayList<String> plants){
        names = plants;
        fixCards();
    }

    private void fixCards(){
        for (int i = 0 ; i < 7 ; i++) {
            if (names.get(i) != null) {
                groupsOfPicked.get(i).getChildren().remove(buttonsOfPicked.get(i));
                Image image = new Image(getClass().getResource("/view/images/" + names.get(i) + ".png").toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(180);
                imageView.setFitHeight(130);
                buttonsOfPicked.get(i).setOpacity(0);
                StackPane stackPane = new StackPane();
                stackPane.getChildren().addAll(imageView, buttonsOfPicked.get(i));
                groupsOfPicked.get(i).getChildren().addAll(stackPane);
            }
        }
    }

    public void setButtons(){
        buttonsOfPicked.add(plant1BTN);
        buttonsOfPicked.add(plant2BTN);
        buttonsOfPicked.add(plant3BTN);
        buttonsOfPicked.add(plant4BTN);
        buttonsOfPicked.add(plant5BTN);
        buttonsOfPicked.add(plant6BTN);
        buttonsOfPicked.add(plant7BTN);
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

//    public void setPlants(){
//        for (String str : names){
//            switch (str){
//                case "pea shooter card":
//                    plants.add(new PeaShooter());
//
//            }
//        }
//    }

    private void deleteCard$setPlants(){
        for (int i = 0; i < 7 ; i++){
            if (names.get(i) != null){
                names.set(i, names.get(i).substring(0, names.get(i).length()-5));
                switch (names.get(i)){
                    case "pea shooter":
                        plants.add(new PeaShooter());
                        break;
                    case "sunflower":
                        plants.add(new Sunflower());
                        break;
                    case "repeater":
                        plants.add(new Repeater());
                        break;
                    case "snow shooter":
                        plants.add(new SnowShooter());
                        break;
                    case "wall nut":
                        plants.add(new WallNut());
                        break;
                    case "tall nut":
                        plants.add(new TallNut());
                        break;
                    case "cherry bomb":
                        plants.add(new CherryBomb());
                        break;
                    case "jalapenos":
                        plants.add(new Jalapenos());
                        break;
                }
            }
        }
    }

//    private void cellOnAction(Cell cell) {
//        cell.setOnMouseClicked(event -> {
//            if (event.getButton() == MouseButton.PRIMARY) {
//
//                cell.setClockwise(false); // پادساعتگرد
//            } else if (event.getButton() == MouseButton.SECONDARY) {
//
//                cell.setClockwise(true);// ساعتگرد
//            }
//
//        });
//    }

}
