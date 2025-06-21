package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
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

    @FXML
    private Group group00;

    @FXML
    private Group group01;

    @FXML
    private Group group02;

    @FXML
    private Group group03;

    @FXML
    private Group group04;

    @FXML
    private Group group05;

    @FXML
    private Group group06;

    @FXML
    private Group group07;

    @FXML
    private Group group08;

    @FXML
    private Group group10;

    @FXML
    private Group group11;

    @FXML
    private Group group12;

    @FXML
    private Group group13;

    @FXML
    private Group group14;

    @FXML
    private Group group15;

    @FXML
    private Group group16;

    @FXML
    private Group group17;

    @FXML
    private Group group18;

    @FXML
    private Group group20;

    @FXML
    private Group group21;

    @FXML
    private Group group22;

    @FXML
    private Group group23;

    @FXML
    private Group group24;

    @FXML
    private Group group25;

    @FXML
    private Group group26;

    @FXML
    private Group group27;

    @FXML
    private Group group28;

    @FXML
    private Group group30;

    @FXML
    private Group group31;

    @FXML
    private Group group32;

    @FXML
    private Group group33;

    @FXML
    private Group group34;

    @FXML
    private Group group35;

    @FXML
    private Group group36;

    @FXML
    private Group group37;

    @FXML
    private Group group38;

    @FXML
    private Group group40;

    @FXML
    private Group group41;

    @FXML
    private Group group42;

    @FXML
    private Group group43;

    @FXML
    private Group group44;

    @FXML
    private Group group45;

    @FXML
    private Group group46;

    @FXML
    private Group group47;

    @FXML
    private Group group48;

    @FXML
    private AnchorPane dayAnc;


    private ArrayList<String> names = new ArrayList<>();

    private ArrayList<Group> groupsOfPicked = new ArrayList<>(7);

    private ArrayList<Button> buttonsOfPicked = new ArrayList<>(7);

    private ArrayList<Plants> plants = new ArrayList<>(7);

    private Cell[][] cells = new Cell[5][9];

    private Plants selectedPlant = null;

    private GameTimer timer;

    private static DayLevel instance;

    private Boolean isShovelMode = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        DayLevel.setInstance(this);

        sunPoints.setText("0");

        timer = new GameTimer();
        timer.start();

        RandomSun randomSun = new RandomSun();

        setButtons();
        setGroups();
        fillBoard();

        plant1BTN.setOnAction(event -> {
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
        shovelBTN.setOnAction(event -> {
           isShovelMode = true;
        });
    }

    private void cellOnAction(){
        for (int i=0; i<5; i++){
            for (int j=0; j<9; j++){
                final int row = i;
                final int column = j;
                cells[i][j].getButton().setOnAction(event -> {
                    if (isShovelMode) {
                        if (cells[row][column].getPlant() != null) {
                            cells[row][column].getGroup().getChildren().remove(cells[row][column].getPlant().getImage());
                            cells[row][column].getPlant().end();
                            cells[row][column].setPlants(null);
                        }
                        isShovelMode = false;
                    } else if (selectedPlant != null){
                        Plants newPlant = selectedPlant.clonePlant(row , column);
                        if (newPlant.getPrice() <= Integer.parseInt(sunPoints.getText())){
                            newPlant.getImage().setMouseTransparent(true);
                            cells[row][column].getGroup().getChildren().add(newPlant.getImage());
                            cells[row][column].setPlants(newPlant);
                            withdrawSunPoints(newPlant.getPrice());
                            selectedPlant = null;

                        } else {
                            newPlant.end();
                            newPlant = null;
                        }
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

    private void fillBoard(){
        cells[0][0] = new Cell(0, 0, cell00, group00);
        cells[0][1] = new Cell(0, 1, cell01, group01);
        cells[0][2] = new Cell(0, 2, cell02, group02);
        cells[0][3] = new Cell(0, 3, cell03, group03);
        cells[0][4] = new Cell(0, 4, cell04, group04);
        cells[0][5] = new Cell(0, 5, cell05, group05);
        cells[0][6] = new Cell(0, 6, cell06, group06);
        cells[0][7] = new Cell(0, 7, cell07, group07);
        cells[0][8] = new Cell(0, 8, cell08, group08);

        cells[1][0] = new Cell(1, 0, cell10, group10);
        cells[1][1] = new Cell(1, 1, cell11, group11);
        cells[1][2] = new Cell(1, 2, cell12, group12);
        cells[1][3] = new Cell(1, 3, cell13, group13);
        cells[1][4] = new Cell(1, 4, cell14, group14);
        cells[1][5] = new Cell(1, 5, cell15, group15);
        cells[1][6] = new Cell(1, 6, cell16, group16);
        cells[1][7] = new Cell(1, 7, cell17, group17);
        cells[1][8] = new Cell(1, 8, cell18, group18);

        cells[2][0] = new Cell(2, 0, cell20, group20);
        cells[2][1] = new Cell(2, 1, cell21, group21);
        cells[2][2] = new Cell(2, 2, cell22, group22);
        cells[2][3] = new Cell(2, 3, cell23, group23);
        cells[2][4] = new Cell(2, 4, cell24, group24);
        cells[2][5] = new Cell(2, 5, cell25, group25);
        cells[2][6] = new Cell(2, 6, cell26, group26);
        cells[2][7] = new Cell(2, 7, cell27, group27);
        cells[2][8] = new Cell(2, 8, cell28, group28);

        cells[3][0] = new Cell(3, 0, cell30, group30);
        cells[3][1] = new Cell(3, 1, cell31, group31);
        cells[3][2] = new Cell(3, 2, cell32, group32);
        cells[3][3] = new Cell(3, 3, cell33, group33);
        cells[3][4] = new Cell(3, 4, cell34, group34);
        cells[3][5] = new Cell(3, 5, cell35, group35);
        cells[3][6] = new Cell(3, 6, cell36, group36);
        cells[3][7] = new Cell(3, 7, cell37, group37);
        cells[3][8] = new Cell(3, 8, cell38, group38);

        cells[4][0] = new Cell(4, 0, cell40, group40);
        cells[4][1] = new Cell(4, 1, cell41, group41);
        cells[4][2] = new Cell(4, 2, cell42, group42);
        cells[4][3] = new Cell(4, 3, cell43, group43);
        cells[4][4] = new Cell(4, 4, cell44, group44);
        cells[4][5] = new Cell(4, 5, cell45, group45);
        cells[4][6] = new Cell(4, 6, cell46, group46);
        cells[4][7] = new Cell(4, 7, cell47, group47);
        cells[4][8] = new Cell(4, 8, cell48, group48);
    }

    public static DayLevel getInstance() {
        return instance;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public static void setInstance(DayLevel dayLevel) {
        instance = dayLevel;
    }

    public void withdrawSunPoints(int n){
        sunPoints.setText(((Integer.parseInt(sunPoints.getText())) - n) + "");
    }

    public void depositSunPoints(int n){
        sunPoints.setText((Integer.parseInt(sunPoints.getText()) + n) + "");
    }

    public AnchorPane getDayAnc(){
        return dayAnc;
    }
}

