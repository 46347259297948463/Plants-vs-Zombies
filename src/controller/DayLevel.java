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
    private Button cell00;

    @FXML
    private Button cell01;

    @FXML
    private Button cell02;

    @FXML
    private Button cell03;

    @FXML
    private Button cell04;

    @FXML
    private Button cell05;

    @FXML
    private Button cell06;

    @FXML
    private Button cell07;

    @FXML
    private Button cell08;

    @FXML
    private Button cell10;

    @FXML
    private Button cell11;

    @FXML
    private Button cell12;

    @FXML
    private Button cell13;

    @FXML
    private Button cell14;

    @FXML
    private Button cell15;

    @FXML
    private Button cell16;

    @FXML
    private Button cell17;

    @FXML
    private Button cell18;

    @FXML
    private Button cell20;

    @FXML
    private Button cell21;

    @FXML
    private Button cell22;

    @FXML
    private Button cell23;

    @FXML
    private Button cell24;

    @FXML
    private Button cell25;

    @FXML
    private Button cell26;

    @FXML
    private Button cell27;

    @FXML
    private Button cell28;

    @FXML
    private Button cell30;

    @FXML
    private Button cell31;

    @FXML
    private Button cell32;

    @FXML
    private Button cell33;

    @FXML
    private Button cell34;

    @FXML
    private Button cell35;

    @FXML
    private Button cell36;

    @FXML
    private Button cell37;

    @FXML
    private Button cell38;

    @FXML
    private Button cell40;

    @FXML
    private Button cell41;

    @FXML
    private Button cell42;

    @FXML
    private Button cell43;

    @FXML
    private Button cell44;

    @FXML
    private Button cell45;

    @FXML
    private Button cell46;

    @FXML
    private Button cell47;

    @FXML
    private Button cell48;

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

    private Plants selectedPlant = null;

    private Button[][] board = new Button[5][9];

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        setButtons();
        setGroups();
        fillBoard();

        plant1BTN.setOnAction(event -> {
            selectedPlant = plants.get(0);
            selectedPlant = plants.get(0);
        });
        plant2BTN.setOnAction(event -> {
            selectedPlant = plants.get(1);
        });
        plant3BTN.setOnAction(event -> {
            selectedPlant = plants.get(2);
        });
        plant4BTN.setOnAction(event -> {
            selectedPlant = plants.get(3);
        });
        plant5BTN.setOnAction(event -> {
            selectedPlant = plants.get(4);
        });
        plant6BTN.setOnAction(event -> {
            selectedPlant = plants.get(5);
        });
        plant7BTN.setOnAction(event -> {
            selectedPlant = plants.get(6);
        });

        cellOnAction();
    }

    private void cellOnAction(){
        for (int i=0; i<5; i++){
            for (int j=0; j<9; j++){
                final int row = i;
                final int column = j;
                board[i][j].setOnAction(event -> {
                    if (selectedPlant != null && board[row][column].getGraphic() == null){
                        Plants newPlant = selectedPlant.clonePlant(row , column);
                        board[row][column].setGraphic(newPlant.getImage());
                    }
                });
            }
        }
    }

    public void setNames(ArrayList<String> plants){
        names = plants;
        fixCards();
        deleteCard$setPlants();
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

    private void setButtons(){
        buttonsOfPicked.add(plant1BTN);
        buttonsOfPicked.add(plant2BTN);
        buttonsOfPicked.add(plant3BTN);
        buttonsOfPicked.add(plant4BTN);
        buttonsOfPicked.add(plant5BTN);
        buttonsOfPicked.add(plant6BTN);
        buttonsOfPicked.add(plant7BTN);
    }

    private void setGroups(){
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

    private void fillBoard(){
        board[0][0] = cell00; board[0][1] = cell01; board[0][2] = cell02; board[0][3] = cell03; board[0][4] = cell04;
        board[0][5] = cell05; board[0][6] = cell06; board[0][7] = cell07; board[0][8] = cell08;

        board[1][0] = cell10; board[1][1] = cell11; board[1][2] = cell12; board[1][3] = cell13; board[1][4] = cell14;
        board[1][5] = cell15; board[1][6] = cell16; board[1][7] = cell17; board[1][8] = cell18;

        board[2][0] = cell20; board[2][1] = cell21; board[2][2] = cell22; board[2][3] = cell23; board[2][4] = cell24;
        board[2][5] = cell25; board[2][6] = cell26; board[2][7] = cell27; board[2][8] = cell28;

        board[3][0] = cell30; board[3][1] = cell31; board[3][2] = cell32; board[3][3] = cell33; board[3][4] = cell34;
        board[3][5] = cell35; board[3][6] = cell36; board[3][7] = cell37; board[3][8] = cell38;

        board[4][0] = cell40; board[4][1] = cell41; board[4][2] = cell42; board[4][3] = cell43; board[4][4] = cell44;
        board[4][5] = cell45; board[4][6] = cell46; board[4][7] = cell47; board[4][8] = cell48;
    }
}

