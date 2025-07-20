package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import model.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
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
    private Button shovelBTN;

    @FXML
    private Label sunPoints;

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

    @FXML
    private Button menuBTN;

    private ArrayList<String> names = new ArrayList<>();

    private ArrayList<Group> groupsOfPicked = new ArrayList<>(6);

    private ArrayList<Button> buttonsOfPicked = new ArrayList<>(6);

    private ArrayList<Plants> plants = new ArrayList<>(6);

    private Cell[][] cells = new Cell[5][9];

    private Plants selectedPlant = null;

    private Group selectedGroup = null;

    private Timeline gameTimer;

    private Timeline exitTimer;

    private static DayLevel instance;

    private Boolean isShovelMode = false;

    private boolean[] availablePicked = {true, true, true, true, true, true};

    private int[] numberOfZombies = new int[5];

    private int availableNum = -1;

    private RandomSun randomSun;

    private Timeline zombieTimer;

    private Random random = new Random();

    private Timeline midTimer;

    private Timeline finalTimer;

    private static Clip clip;

    private static int menu = 0;

    public boolean isStopMod = false;

    public static boolean isOnSaveMode = false;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/day.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DayLevel.setInstance(this);

        sunPoints.setText("0");

        menuBTN.setOnAction(event -> {
            if (menu == 0 && !isStopMod) {
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                            getClass().getResource("/view/audio/pause sound.wav")
                    );
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception ev) {
                    ev.printStackTrace();
                }
                stop();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Menu.fxml"));
                    Parent menuContent = loader.load();
                    if (loader.getController() instanceof Menu) {
                        ((Menu) loader.getController()).setObj(DayLevel.getInstance());
                    }
                    AnchorPane root = (AnchorPane) menuBTN.getScene().getRoot();
                    root.getChildren().add(menuContent);

                    AnchorPane.setTopAnchor(menuContent, 260.0);
                    AnchorPane.setLeftAnchor(menuContent, 710.0);

                    menuContent.setOpacity(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            menu++;
        });

        gameTimer = new Timeline(
                new KeyFrame(Duration.seconds(20), e -> step1()),
                new KeyFrame(Duration.seconds(36), e -> step2()),
                new KeyFrame(Duration.seconds(52), e -> midAttack()),
                new KeyFrame(Duration.seconds(61), e -> step3()),
                new KeyFrame(Duration.seconds(77), e -> step4()),
                new KeyFrame(Duration.seconds(93), e -> finalAttack()),
                new KeyFrame(Duration.seconds(107), e -> {
                    finalTimer.stop();
                    finalTimer = null;
                    zombieTimer = null;
                    gameTimer.stop();
                    gameTimer = null;
                    exitTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                        if (isGameFinish()){
                            clip.stop();
                            try {
                                AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                                        getClass().getResource("/view/audio/victory sound.wav")
                                );
                                Clip clip = AudioSystem.getClip();
                                clip.open(audioStream);
                                clip.start();
                            } catch (Exception ev) {
                                ev.printStackTrace();
                            }

                            try {
                                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/WinPage.fxml"));
                                Parent winContent = loader.load();

                                WinPage.setObj(DayLevel.getInstance());
                                dayAnc.getChildren().add(winContent);

                                AnchorPane.setTopAnchor(winContent, 250.0);
                                AnchorPane.setLeftAnchor(winContent, 690.0);

                                winContent.setOpacity(1);
                            } catch (IOException ev) {
                                ev.printStackTrace();
                            }
                            stop();
                            exitTimer.stop();
                        }
                    }));
                    exitTimer.setCycleCount(Timeline.INDEFINITE);
                    exitTimer.play();
                })
        );
        gameTimer.setCycleCount(1);
        gameTimer.play();

         randomSun = new RandomSun();

        if (!isOnSaveMode) {
            setButtons();
            setGroups();
            fillBoard();
        }

        plant1BTN.setOnAction(event -> {
            if (!isStopMod) {
                selectedPlant = plants.get(0);
                selectedGroup = groupsOfPicked.get(0);
                availableNum = 0;
            }
        });
        plant2BTN.setOnAction(event -> {
            if (!isStopMod) {
                selectedPlant = plants.get(1);
                selectedGroup = groupsOfPicked.get(1);
                availableNum = 1;
            }
        });
        plant3BTN.setOnAction(event -> {
            if (!isStopMod) {
                selectedPlant = plants.get(2);
                selectedGroup = groupsOfPicked.get(2);
                availableNum = 2;
            }
        });
        plant4BTN.setOnAction(event -> {
            if (!isStopMod) {
                selectedPlant = plants.get(3);
                selectedGroup = groupsOfPicked.get(3);
                availableNum = 3;
            }
        });
        plant5BTN.setOnAction(event -> {
            if (!isStopMod) {
                selectedPlant = plants.get(4);
                selectedGroup = groupsOfPicked.get(4);
                availableNum = 4;
            }
        });
        plant6BTN.setOnAction(event -> {
            if (!isStopMod) {
                selectedPlant = plants.get(5);
                selectedGroup = groupsOfPicked.get(5);
                availableNum = 5;
            }
        });

        cellOnAction();

        shovelBTN.setOnAction(event -> {
            if (!isStopMod) {
                isShovelMode = true;
            }
        });

    }

    private void cellOnAction(){
        for (int i=0; i<5; i++){
            for (int j=0; j<9; j++){
                final int row = i;
                final int column = j;
                cells[i][j].getButton().setOnAction(event -> {
                    if (!isStopMod) {
                        if (isShovelMode) {
                            if (cells[row][column].getPlant() != null) {
                                try {
                                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                                            getClass().getResource("/view/audio/shovel sound.wav")
                                    );
                                    Clip clip = AudioSystem.getClip();
                                    clip.open(audioStream);
                                    clip.start();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                cells[row][column].getGroup().getChildren().remove(cells[row][column].getPlant().getImage());
                                cells[row][column].getPlant().end();
                                cells[row][column].setPlants(null);
                            }
                            isShovelMode = false;
                        } else if (selectedPlant != null && cells[row][column].getPlant() == null && availableNum != -1) {
                            if (availablePicked[availableNum]) {
                                if (selectedPlant.getPrice() <= Integer.parseInt(sunPoints.getText())) {
                                    try {
                                        AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                                                getClass().getResource("/view/audio/planting sound.wav")
                                        );
                                        Clip clip = AudioSystem.getClip();
                                        clip.open(audioStream);
                                        clip.start();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    Plants newPlant = selectedPlant.clonePlant(row, column);
                                    newPlant.getImage().setMouseTransparent(true);
                                    cells[row][column].getGroup().getChildren().add(newPlant.getImage());
                                    cells[row][column].setPlants(newPlant);
                                    withdrawSunPoints(newPlant.getPrice());
                                }
                            }
                            selectedGroup = null;
                            selectedPlant = null;
                            availableNum = -1;
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
        for (int i = 0 ; i < 6 ; i++) {
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
    }

    private void setGroups(){
        groupsOfPicked.add(group1);
        groupsOfPicked.add(group2);
        groupsOfPicked.add(group3);
        groupsOfPicked.add(group4);
        groupsOfPicked.add(group5);
        groupsOfPicked.add(group6);
    }

    private void deleteCard$setPlants(){
        for (int i = 0; i < 6 ; i++){
            if (names.get(i) != null){
                names.set(i, names.get(i).substring(0, names.get(i).length()-5));
                switch (names.get(i)){
                    case "pea shooter":
                        plants.add(new PeaShooter());
                        PeaShooter.setGroup(groupsOfPicked.get(plants.size() - 1));
                        PeaShooter.setAvailableNum(plants.size() - 1);
                        break;
                    case "sunflower":
                        plants.add(new Sunflower());
                        Sunflower.setGroup(groupsOfPicked.get(plants.size() - 1));
                        Sunflower.setAvailableNum(plants.size() - 1);
                        break;
                    case "repeater":
                        plants.add(new Repeater());
                        Repeater.setGroup(groupsOfPicked.get(plants.size() - 1));
                        Repeater.setAvailableNum(plants.size() - 1);
                        break;
                    case "snow shooter":
                        plants.add(new SnowShooter());
                        SnowShooter.setGroup(groupsOfPicked.get(plants.size() - 1));
                        SnowShooter.setAvailableNum(plants.size() - 1);
                        break;
                    case "wall nut":
                        plants.add(new WallNut());
                        WallNut.setGroup(groupsOfPicked.get(plants.size() - 1));
                        WallNut.setAvailableNum(plants.size() - 1);
                        break;
                    case "tall nut":
                        plants.add(new TallNut());
                        TallNut.setGroup(groupsOfPicked.get(plants.size() - 1));
                        TallNut.setAvailableNum(plants.size() - 1);
                        break;
                    case "cherry bomb":
                        plants.add(new CherryBomb());
                        CherryBomb.setGroup(groupsOfPicked.get(plants.size() - 1));
                        CherryBomb.setAvailableNum(plants.size() - 1);
                        break;
                    case "jalapenos":
                        plants.add(new Jalapenos());
                        Jalapenos.setGroup(groupsOfPicked.get(plants.size() - 1));
                        Jalapenos.setAvailableNum(plants.size() - 1);
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

    public void withdrawSunPoints(int n){
        sunPoints.setText(((Integer.parseInt(sunPoints.getText())) - n) + "");
    }

    public void depositSunPoints(int n){
        sunPoints.setText((Integer.parseInt(sunPoints.getText()) + n) + "");
    }

    private void step1(){
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/zombies are coming sound.wav")
            );
            Clip coming = AudioSystem.getClip();
            coming.open(audioStream);
            coming.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        zombieTimer = new Timeline(new KeyFrame(Duration.seconds(3), event -> basicZombie()));
        zombieTimer.setCycleCount(Timeline.INDEFINITE);
        zombieTimer.play();
    }

    private void step2(){
        zombieTimer.stop();
        zombieTimer = new Timeline(new KeyFrame(Duration.seconds(2) , event -> {
            int choose = random.nextInt(2);
            if (choose == 0){
                basicZombie();
            } else {
                coneHeadZombie();
            }
        }));
        zombieTimer.setCycleCount(Timeline.INDEFINITE);
        zombieTimer.play();
    }

    private void midAttack(){
        zombieTimer.stop();
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/wave sound.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ev) {
            ev.printStackTrace();
        }
        midTimer = new Timeline(new KeyFrame(Duration.seconds(2), event -> {
            for (int i = 0; i < 5; i++){
                int choose = random.nextInt(2);
                if (choose == 0){
                    basicZombie();
                } else {
                    coneHeadZombie();
                }
            }
        } ));
        midTimer.setCycleCount(4);
        midTimer.play();
    }

    private void step3(){
        midTimer.stop();
        midTimer = null;
        zombieTimer.stop();
        zombieTimer = new Timeline(new KeyFrame(Duration.seconds(2) , event -> {
            int choose = random.nextInt(3);
            if (choose == 0){
                basicZombie();
            } else if (choose == 1) {
                coneHeadZombie();
            }else {
                screenDoorZombie();
            }
        }));
        zombieTimer.setCycleCount(Timeline.INDEFINITE);
        zombieTimer.play();
    }

    private void step4(){
        zombieTimer.stop();
        zombieTimer = new Timeline(new KeyFrame(Duration.seconds(1) , event -> {
            int choose = random.nextInt(4);
            if (choose == 0){
                basicZombie();
            } else if (choose == 1) {
                coneHeadZombie();
            }else if (choose == 2){
                screenDoorZombie();
            }else {
                impZombie();
            }
        }));
        zombieTimer.setCycleCount(Timeline.INDEFINITE);
        zombieTimer.play();
    }

    private void finalAttack(){
        zombieTimer.stop();
        zombieTimer = null;
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/wave sound.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ev) {
            ev.printStackTrace();
        }
        finalTimer = new Timeline(new KeyFrame(Duration.seconds(1.5), event -> {
            for (int i = 0; i < 5; i++){
                int choose = random.nextInt(4);
                if (choose == 0){
                    basicZombie();
                } else if (choose == 1) {
                    coneHeadZombie();
                }else if (choose == 2){
                    screenDoorZombie();
                }else {
                    impZombie();
                }
            }
        }));
        finalTimer.setCycleCount(8);
        finalTimer.play();
    }

    private void basicZombie(){
        int row;
        while (!setRandomZombies(row = random.nextInt(5)));
        numberOfZombies[row]++;
        Zombie zombie = new Zombie( 1780, row * 185 + 130, row);
        cells[row][8].setZombies(zombie);
    }

    private void coneHeadZombie(){
        int row;
        while (!setRandomZombies(row = random.nextInt(5)));
        numberOfZombies[row]++;
        ConeheadZombie zombie = new ConeheadZombie( 1780, row * 185 + 130, row);
        cells[row][8].setZombies(zombie);
    }

    private void screenDoorZombie(){
        int row;
        while (!setRandomZombies(row = random.nextInt(5)));
        numberOfZombies[row]++;
        ScreenDoorZombie zombie = new ScreenDoorZombie( 1780, row * 185 + 130, row);
        cells[row][8].setZombies(zombie);
    }

    private void impZombie(){
        int row;
        while (!setRandomZombies(row = random.nextInt(5)));
        numberOfZombies[row]++;
        ImpZombie zombie = new ImpZombie(1780, row * 185 + 130, row);
        cells[row][8].setZombies(zombie);
    }

    private boolean isGameFinish(){
        for (int i = 0; i<5 ; i++){
            for (int j = 0; j<9; j++){
                if (cells[i][j].getZombies() != null){
                    return false;
                }
            }
        }
        return true;
    }

    private boolean setRandomZombies(int k){
        for (int i = 0 ; i < 5; i++){
            if (numberOfZombies[k] - numberOfZombies[i] >=3){
                return false;
            }
        }
        return true;
    }

    public void stop(){
        isStopMod = true;
        if (gameTimer != null){
            gameTimer.pause();
        }
        if (zombieTimer != null){
            zombieTimer.pause();
        }
        if (randomSun != null){
            randomSun.stop();
        }
        if (midTimer != null){
            midTimer.pause();
        }
        if (finalTimer != null){
            finalTimer.pause();
        }
        for (int i = 0; i < 5 ; i++){
            for (int j = 0 ; j < 9; j++){
                if (cells[i][j].getPlant() != null){
                    cells[i][j].getPlant().stop();
                }
                if (cells[i][j].getZombies() != null){
                    for (Zombie zombie : cells[i][j].getZombies()){
                        zombie.stop();
                    }
                }
            }
        }
    }

    public void play(){
        isStopMod = false;
        if (gameTimer != null){
            gameTimer.play();
        }
        if (zombieTimer != null){
            zombieTimer.play();
        }
        if (randomSun != null){
            randomSun.play();
        }
        if (midTimer != null){
            midTimer.play();
        }
        if (finalTimer != null){
            finalTimer.play();
        }
        for (int i = 0; i < 5 ; i++){
            for (int j = 0 ; j < 9; j++){
                if (cells[i][j].getPlant() != null){
                    cells[i][j].getPlant().play();
                }
                if (cells[i][j].getZombies() != null){
                    for (Zombie zombie : cells[i][j].getZombies()){
                        zombie.play();
                    }
                }
            }
        }
    }

    public static void stopAudio(){
        clip.stop();
    }

    public static void playAudio(){
        clip.start();
    }

    public void lose(){
        clip.stop();
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/lose sound.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception ev) {
            ev.printStackTrace();
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LosePage.fxml"));
            Parent loseContent = loader.load();

            LosePage.setObj(DayLevel.getInstance());

            dayAnc.getChildren().add(loseContent);

            AnchorPane.setTopAnchor(loseContent, 260.0);
            AnchorPane.setLeftAnchor(loseContent, 690.0);

            loseContent.setOpacity(1);
        } catch (IOException ev) {
            ev.printStackTrace();
        }
        stop();
        exitTimer.stop();
    }

    private static void setInstance(DayLevel dayLevel) {
        instance = dayLevel;
    }

    public void setAvailablePicked(Boolean bool, int i){
        if (i != -1){
            availablePicked[i] = bool;
        }
    }

    public static void setMenu(int menu) {
        DayLevel.menu = menu;
    }

    public static DayLevel getInstance() {
        return instance;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public AnchorPane getDayAnc(){
        return dayAnc;
    }

    public Button getMenuBTN() {
        return menuBTN;
    }

    public GameState buildGameState() {
        GameState gameState = new GameState();

        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0 ; j < 9 ; j++){
                if(cells[i][j].getZombies() != null){
                    for (Zombie zombie : cells[i][j].getZombies()){
                        gameState.zombies.add(new ZombieData(
                                zombie.getColumn(),
                                zombie.getRow(),
                                zombie.getHP(),
                                zombie.rowBTN,
                                zombie.columnBTN,
                                zombie.getClass().getSimpleName()
                        ));
                    }
                }
                Plants plant = cells[i][j].getPlant();
                if (plant != null){
                    gameState.plants.add(new PlantData(i, j, plant.getHP(), plant.getClass().getSimpleName()));
                }
            }
        }
        gameState.sunPoints = Integer.parseInt(sunPoints.getText());
        return gameState;
    }

    public void saveGame(GameState gameState) {
        try {
            gameState.sunPoints = Integer.parseInt(sunPoints.getText());
            gameState.zombies = getZombiesData();
            gameState.plants = getPlantsData();
            gameState.gameTimer = (long) gameTimer.getCurrentTime().toSeconds();
            gameState.names = names;
            gameState.rechargeTime = getRechargeTimer();

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("save.dat"));
            out.writeObject(gameState);
            out.close();
            System.out.println("GAME SAVED");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState loadGame() {
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream("save.dat"));
            GameState gameState = (GameState) input.readObject();
            input.close();
            System.out.println("GAME LOADED");
            return gameState;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void ApplyGameState() {
        GameState loadedState = loadGame();
        if (loadedState != null){
            sunPoints.setText(String.valueOf(loadedState.sunPoints));
            this.setNames(loadedState.names);
            setButtons();
            setGroups();
            fillBoard();
            gameTimer.stop();
            for (ZombieData zombieData : loadedState.zombies){
                switch (zombieData.type) {
                    case "Zombie":
                        Zombie zombie = new Zombie(zombieData.x, zombieData.y, zombieData.rowBTN);
                        zombie.columnBTN = zombieData.columnBTN;
                        cells[zombie.rowBTN][zombie.columnBTN].setZombies(zombie);
                        zombie.setHP(zombieData.HP);
                        break;
                    case "ConeheadZombie":
                        ConeheadZombie coneheadZombie = new ConeheadZombie(zombieData.x, zombieData.y, zombieData.rowBTN);
                        coneheadZombie.columnBTN = zombieData.columnBTN;
                        cells[coneheadZombie.rowBTN][coneheadZombie.columnBTN].setZombies(coneheadZombie);
                        coneheadZombie.setHP(zombieData.HP);
                        break;
                    case "ScreenDoorZombie":
                        ScreenDoorZombie screenDoorZombie = new ScreenDoorZombie(zombieData.x, zombieData.y, zombieData.rowBTN);
                        screenDoorZombie.columnBTN = zombieData.columnBTN;
                        cells[screenDoorZombie.rowBTN][screenDoorZombie.columnBTN].setZombies(screenDoorZombie);
                        screenDoorZombie.setHP(zombieData.HP);
                        break;
                    case "ImpZombie":
                        ImpZombie impZombie = new ImpZombie(zombieData.x, zombieData.y, zombieData.rowBTN);
                        impZombie.columnBTN = zombieData.columnBTN;
                        cells[impZombie.rowBTN][impZombie.columnBTN].setZombies(impZombie);
                        impZombie.setHP(zombieData.HP);
                        break;
                }
            }

            for (PlantData plantData : loadedState.plants){
                switch (plantData.type){
                    case "CherryBomb" :
                         CherryBomb cherryBomb = new CherryBomb(plantData.row , plantData.column);
                         cherryBomb.setHP(plantData.HP);
                         cells[plantData.row][plantData.column].setPlants(cherryBomb);
                         break;
                    case "Jalapenos" :
                        Jalapenos jalapenos = new Jalapenos(plantData.row , plantData.column);
                        jalapenos.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(jalapenos);
                        break;
                    case "PeaShooter" :
                        PeaShooter peaShooter = new PeaShooter(plantData.row , plantData.column);
                        peaShooter.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(peaShooter);
                        break;
                    case "Repeater" :
                        Repeater repeater  = new Repeater(plantData.row , plantData.column);
                        repeater.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(repeater);
                        break;
                    case "SnowShooter" :
                        SnowShooter snowShooter = new SnowShooter(plantData.row , plantData.column);
                        snowShooter.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(snowShooter);
                        break;
                    case "Sunflower" :
                        Sunflower sunflower = new Sunflower(plantData.row , plantData.column);
                        sunflower.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(sunflower);
                        break;
                    case "TallNut" :
                        TallNut tallNut = new TallNut(plantData.row , plantData.column);
                        tallNut.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(tallNut);
                        break;
                    case "WallNut" :
                        WallNut wallNut = new WallNut(plantData.row , plantData.column);
                        wallNut.setHP(plantData.HP);
                        cells[plantData.row][plantData.column].setPlants(wallNut);
                        break;
                }
            }

        }
    }

    private ArrayList<ZombieData> getZombiesData() {
        ArrayList<ZombieData> zombieData = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0; j < 9 ; j++){
                if (cells[i][j].getZombies() != null) {
                    for (Zombie zombie : cells[i][j].getZombies()){
                        ZombieData z = new ZombieData(zombie.getRow(), zombie.getColumn(), zombie.getHP(),
                                zombie.rowBTN, zombie.columnBTN, zombie.getClass().getSimpleName());
                        zombieData.add(z);
                    }
                }
            }
        }

        return zombieData;
    }

    private ArrayList<PlantData> getPlantsData() {
        ArrayList<PlantData> plantData = new ArrayList<>();
        for (int i = 0 ; i < 5 ; i++){
            for (int j = 0; j < 9 ; j++){
                if (cells[i][j].getPlant() != null) {
                    Plants plant = cells[i][j].getPlant();
                    PlantData p = new PlantData(plant.getRow(), plant.getColumn(), plant.getHP(),
                            plant.getClass().getSimpleName());
                    plantData.add(p);
                }
            }
        }

        return plantData;
    }

    private ArrayList<Long> getRechargeTimer() {
        ArrayList<Long> l = new ArrayList<>();
        for (Plants plant : plants) {
            l.add((long) plant.getTimer().getCurrentTime().toSeconds());
        }
        return l;
    }

}