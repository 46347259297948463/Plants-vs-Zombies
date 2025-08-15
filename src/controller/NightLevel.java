package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Node;
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
import java.util.*;

import static java.lang.System.*;

public class NightLevel implements Initializable {

    @FXML
    private ImageView box;

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
    private Group group1;

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
    private Group group2;

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
    private Group group3;

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
    private Group group4;

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
    private Group group5;

    @FXML
    private Group group6;

    @FXML
    private Button menuBTN;

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
    private AnchorPane nightAnc;

    private ArrayList<String> names = new ArrayList<>();

    private ArrayList<String> cardNames = new ArrayList<>();

    private ArrayList<Group> groupsOfPicked = new ArrayList<>(6);

    private ArrayList<Button> buttonsOfPicked = new ArrayList<>(6);

    private ArrayList<Plants> plants = new ArrayList<>(6);

    private boolean[] availablePicked = {true, true, true, true, true, true};

    private static int menu = 0;

    public static boolean isStopMod = false;

    private Timeline gameTimer;

    private Timeline zombieTimer;

    private Timeline midTimer;

    private Timeline finalTimer;

    private Cell[][] cells = new Cell[5][9];

    private static Clip clip;

    private static NightLevel instance;

    public static boolean isOnSaveMode = false;

    private Boolean isShovelMode = false;

    private Timeline exitTimer;

    private Random random = new Random();

    private int[] numberOfZombies = new int[5];

    private Plants selectedPlant = null;

    private Group selectedGroup = null;

    private int availableNum = -1;

    private static final String SAVE_FILE = "save.dat";

    private int[] X = {-1, -1, -1, -1, -1, -1};

    private int[] Y = {-1, -1, -1, -1, -1, -1};

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Plants.obj = NightLevel.getInstance();
        Zombie.obj = NightLevel.getInstance();
        Bullet.obj = NightLevel.getInstance();
        Menu.obj = NightLevel.getInstance();
        SavePage.obj = NightLevel.getInstance();

        setButtons();
        setGroups();
        fillBoard();

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/night.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        NightLevel.setInstance(this);

        sunPoints.setText("50");

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
                    AnchorPane root = (AnchorPane) menuBTN.getScene().getRoot();
                    root.getChildren().add(menuContent);

                    AnchorPane.setTopAnchor(menuContent, 260.0);
                    AnchorPane.setLeftAnchor(menuContent, 710.0);

                    menuContent.setOpacity(1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                menu++;
            }
        });

        if (!isOnSaveMode) {
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
                            if (isGameFinish()) {
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
                                    FXMLLoader loader = new FXMLLoader(getClass()
                                            .getResource("../view/WinPage.fxml"));
                                    Parent winContent = loader.load();

                                    WinPage.setObj(NightLevel.getInstance());
                                    nightAnc.getChildren().add(winContent);

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
                                cells[row][column].getGroup().getChildren().remove(cells[row][column]
                                        .getPlant().getImage());
                                cells[row][column].getPlant().end();
                                cells[row][column].setPlants(null);
                            }
                            isShovelMode = false;
                        } else if (selectedPlant != null && cells[row][column].getPlant() == null
                                && availableNum != -1) {
                            if (cells[row][column].isAvailable()) {
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
                                        newPlant.setNeedCoffee(false);
                                        cells[row][column].getGroup().getChildren().add(newPlant.getImage());
                                        if (selectedPlant instanceof CoffeeBean) {
                                            System.out.println("Coffee");
                                            cells[row][column].setCoffeeBean(newPlant);
                                        } else {
                                            cells[row][column].setPlants(newPlant);
                                        }
                                        withdrawSunPoints(newPlant.getPrice());
                                    }
                                }
                            } else if (selectedPlant instanceof GraveBuster && cells[row][column].isGrave()) {
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
                                        newPlant.setNeedCoffee(false);
                                        cells[row][column].getGroup().getChildren().add(newPlant.getImage());
                                        cells[row][column].setPlants(newPlant);
                                        withdrawSunPoints(newPlant.getPrice());
                                    }
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
        cardNames = new ArrayList<>(names);
        fixCards();
        deleteCard$setPlants();
    }

    private void fixCards(){
        for (int i = 0 ; i < 6 ; i++) {
            if (names.get(i) != null) {
                groupsOfPicked.get(i).getChildren().remove(buttonsOfPicked.get(i));
                Image image = new Image(getClass().getResource("/view/images/" + names.get(i)
                        + ".png").toString());
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
                    case "blover":
                        plants.add(new Blover());
                        Blover.setGroup(groupsOfPicked.get(plants.size() - 1));
                        Blover.setAvailableNum(plants.size() - 1);
                        break;
                    case "coffee bean":
                        plants.add(new CoffeeBean());
                        CoffeeBean.setGroup(groupsOfPicked.get(plants.size() - 1));
                        CoffeeBean.setAvailableNum(plants.size() - 1);
                        break;
                    case "doom shroom":
                        plants.add(new Doomshroom());
                        Doomshroom.setGroup(groupsOfPicked.get(plants.size() - 1));
                        Doomshroom.setAvailableNum(plants.size() - 1);
                        break;
                    case "grave buster":
                        plants.add(new GraveBuster());
                        GraveBuster.setGroup(groupsOfPicked.get(plants.size() - 1));
                        GraveBuster.setAvailableNum(plants.size() - 1);
                        break;
                    case "hypno shroom":
                        plants.add(new HypnoShroom());
                        HypnoShroom.setGroup(groupsOfPicked.get(plants.size() - 1));
                        HypnoShroom.setAvailableNum(plants.size() - 1);
                        break;
                    case "ice shroom":
                        plants.add(new IceShroom());
                        IceShroom.setGroup(groupsOfPicked.get(plants.size() - 1));
                        IceShroom.setAvailableNum(plants.size() - 1);
                        break;
                    case "plantern":
                        plants.add(new Plantern());
                        Plantern.setGroup(groupsOfPicked.get(plants.size() - 1));
                        Plantern.setAvailableNum(plants.size() - 1);
                        break;
                    case "puff shroom":
                        plants.add(new PuffShroom());
                        PuffShroom.setGroup(groupsOfPicked.get(plants.size() - 1));
                        PuffShroom.setAvailableNum(plants.size() - 1);
                        break;
                    case "scaredy shroom":
                        plants.add(new ScaredyShroom());
                        ScaredyShroom.setGroup(groupsOfPicked.get(plants.size() - 1));
                        ScaredyShroom.setAvailableNum(plants.size() - 1);
                        break;
                    case "sun shroom":
                        plants.add(new SunShroom());
                        SunShroom.setGroup(groupsOfPicked.get(plants.size() - 1));
                        SunShroom.setAvailableNum(plants.size() - 1);
                        break;
                }
            }
        }
    }

    private void fillBoard() {
        cells[0][0] = new Cell(cell00, group00);
        cells[0][1] = new Cell(cell01, group01);
        cells[0][2] = new Cell(cell02, group02);
        cells[0][3] = new Cell(cell03, group03);
        cells[0][4] = new Cell(cell04, group04);
        cells[0][5] = new Cell(cell05, group05);
        cells[0][6] = new Cell(cell06, group06);
        cells[0][7] = new Cell(cell07, group07);
        cells[0][8] = new Cell(cell08, group08);

        cells[1][0] = new Cell(cell10, group10);
        cells[1][1] = new Cell(cell11, group11);
        cells[1][2] = new Cell(cell12, group12);
        cells[1][3] = new Cell(cell13, group13);
        cells[1][4] = new Cell(cell14, group14);
        cells[1][5] = new Cell(cell15, group15);
        cells[1][6] = new Cell(cell16, group16);
        cells[1][7] = new Cell(cell17, group17);
        cells[1][8] = new Cell(cell18, group18);

        cells[2][0] = new Cell(cell20, group20);
        cells[2][1] = new Cell(cell21, group21);
        cells[2][2] = new Cell(cell22, group22);
        cells[2][3] = new Cell(cell23, group23);
        cells[2][4] = new Cell(cell24, group24);
        cells[2][5] = new Cell(cell25, group25);
        cells[2][6] = new Cell(cell26, group26);
        cells[2][7] = new Cell(cell27, group27);
        cells[2][8] = new Cell(cell28, group28);

        cells[3][0] = new Cell(cell30, group30);
        cells[3][1] = new Cell(cell31, group31);
        cells[3][2] = new Cell(cell32, group32);
        cells[3][3] = new Cell(cell33, group33);
        cells[3][4] = new Cell(cell34, group34);
        cells[3][5] = new Cell(cell35, group35);
        cells[3][6] = new Cell(cell36, group36);
        cells[3][7] = new Cell(cell37, group37);
        cells[3][8] = new Cell(cell38, group38);

        cells[4][0] = new Cell(cell40, group40);
        cells[4][1] = new Cell(cell41, group41);
        cells[4][2] = new Cell(cell42, group42);
        cells[4][3] = new Cell(cell43, group43);
        cells[4][4] = new Cell( cell44, group44);
        cells[4][5] = new Cell(cell45, group45);
        cells[4][6] = new Cell(cell46, group46);
        cells[4][7] = new Cell(cell47, group47);
        cells[4][8] = new Cell(cell48, group48);

        if (!isOnSaveMode) {
            int n = 1;
            for (int i = 0 ; i < 6 ; i++) {
                int x = (int) (Math.random() * 5) + 4;
                int y = (int) (Math.random() * 5);

                for (int j = 0 ; j < i ; j++) {
                    if (x == X[j] && y == Y[j]) {
                        x = (int) (Math.random() * 5) + 4;
                        y = (int) (Math.random() * 5);
                        j = -1;
                    }
                }
                ImageView imageView = new ImageView("/view/images/grave_" + n + ".png");
                imageView.setFitWidth(135);
                imageView.setFitHeight(140);
                cells[y][x].setGraveImage(imageView);
                cells[y][x].getGroup().getChildren().add(imageView);
                cells[y][x].setAvailable(false);
                cells[y][x].setGrave(true);
                n++;
                X[i] = x;
                Y[i] = y;
            }
        }
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
        if (zombieTimer != null) {
            zombieTimer.stop();
        }
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
        if (zombieTimer != null) {
            zombieTimer.stop();
        }
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

                numberOfZombies[i]++;

                if (choose == 0){
                    Zombie zombie = new Zombie( 1780, i * 185 + 130, i);
                    cells[i][8].setZombies(zombie);
                } else {
                    ConeheadZombie coneheadZombie = new ConeheadZombie( 1780, i * 185 + 130, i);
                    cells[i][8].setZombies(coneheadZombie);
                }
            }

            for (int i = 0 ; i < 6 ; i++) {
               if (cells[Y[i]][X[i]].isGrave()) {
                   int choose = random.nextInt(2);
                   Node grave = cells[Y[i]][X[i]].getGraveImage();

                   Bounds graveBoundsInScene = grave.localToScene(grave.getBoundsInLocal());

                   Point2D topLeftInTarget = nightAnc.sceneToLocal(
                           graveBoundsInScene.getMinX(),
                           graveBoundsInScene.getMinY()
                   );

                   if (choose == 0){
                       numberOfZombies[Y[i]]++;

                       Zombie zombie = new Zombie(
                               topLeftInTarget.getX(),
                               topLeftInTarget.getY(),
                               Y[i]
                       );

                       zombie.columnBTN = X[i];
                       cells[Y[i]][X[i]].setZombies(zombie);

                   } else {
                       numberOfZombies[Y[i]]++;

                       ConeheadZombie coneheadZombie = new ConeheadZombie(
                               topLeftInTarget.getX(),
                               topLeftInTarget.getY(),
                               Y[i]
                       );

                       coneheadZombie.columnBTN = X[i];
                       cells[Y[i]][X[i]].setZombies(coneheadZombie);
                   }
               }
            }

        } ));
        midTimer.setCycleCount(4);
        midTimer.play();
    }

    private void step3(){
        if (midTimer != null) {
            midTimer.stop();
            midTimer = null;
        }
        if (zombieTimer != null) {
            zombieTimer.stop();
        }
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
        if (zombieTimer != null) {
            zombieTimer.stop();
        }
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
        if (zombieTimer != null) {
            zombieTimer.stop();
        }
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
                    numberOfZombies[i]++;
                    Zombie zombie = new Zombie( 1780, i * 185 + 130, i);
                    cells[i][8].setZombies(zombie);
                } else if (choose == 1) {
                    numberOfZombies[i]++;
                    ConeheadZombie coneheadZombie = new ConeheadZombie( 1780, i * 185 + 130, i);
                    cells[i][8].setZombies(coneheadZombie);
                }else if (choose == 2){
                    numberOfZombies[i]++;
                    ScreenDoorZombie screenDoorZombie = new ScreenDoorZombie( 1780, i * 185 + 130, i);
                    cells[i][8].setZombies(screenDoorZombie);
                }else {
                    numberOfZombies[i]++;
                    ImpZombie impZombie = new ImpZombie( 1780, i * 185 + 130, i);
                    cells[i][8].setZombies(impZombie);
                }
            }

            for (int i = 0 ; i < 6 ; i++) {
                if (cells[Y[i]][X[i]].isGrave()) {
                    int choose = random.nextInt(4);

                    Node grave = cells[Y[i]][X[i]].getGraveImage();

                    Bounds graveBoundsInScene = grave.localToScene(grave.getBoundsInLocal());

                    Point2D topLeftInTarget = nightAnc.sceneToLocal(
                            graveBoundsInScene.getMinX(),
                            graveBoundsInScene.getMinY()
                    );

                    numberOfZombies[Y[i]]++;

                    if (choose == 0) {
                        Zombie zombie = new Zombie(
                                topLeftInTarget.getX(),
                                topLeftInTarget.getY(),
                                Y[i]
                        );

                        zombie.columnBTN = X[i];
                        cells[Y[i]][X[i]].setZombies(zombie);
                    } else if (choose == 1) {
                        ConeheadZombie coneheadZombie = new ConeheadZombie(
                                topLeftInTarget.getX(),
                                topLeftInTarget.getY(),
                                Y[i]
                        );

                        coneheadZombie.columnBTN = X[i];
                        cells[Y[i]][X[i]].setZombies(coneheadZombie);
                    } else if (choose == 2) {
                        ScreenDoorZombie screenDoorZombie = new ScreenDoorZombie(
                                topLeftInTarget.getX(),
                                topLeftInTarget.getY(),
                                Y[i]
                        );

                        screenDoorZombie.columnBTN = X[i];
                        cells[Y[i]][X[i]].setZombies(screenDoorZombie);
                    } else if (choose == 3) {
                        ImpZombie impZombie = new ImpZombie(
                                topLeftInTarget.getX(),
                                topLeftInTarget.getY(),
                                Y[i]
                        );

                        impZombie.columnBTN = X[i];
                        cells[Y[i]][X[i]].setZombies(impZombie);
                    }
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

            LosePage.setObj(NightLevel.getInstance());

            nightAnc.getChildren().add(loseContent);

            AnchorPane.setTopAnchor(loseContent, 260.0);
            AnchorPane.setLeftAnchor(loseContent, 690.0);

            loseContent.setOpacity(1);
        } catch (IOException ev) {
            ev.printStackTrace();
        }
        stop();
        exitTimer.stop();
    }

    private static void setInstance(NightLevel nightLevel) {
        instance = nightLevel;
    }

    public void setAvailablePicked(Boolean bool, int i){
        if (i != -1){
            availablePicked[i] = bool;
        }
    }

    public static void setMenu(int menu) {
        NightLevel.menu = menu;
    }

    public static NightLevel getInstance() {
        if (instance == null) {
            instance = new NightLevel();
        }
        return instance;
    }

    public Cell[][] getCells() {
        return cells;
    }

    public AnchorPane getNightAnc(){
        return nightAnc;
    }

    public Button getMenuBTN() {
        return menuBTN;
    }

    public GameState buildGameState() {
        GameState gameState = new GameState();
        gameState.type = NightLevel.getInstance().getClass().getSimpleName();
        gameState.sunPoints = Integer.parseInt(sunPoints.getText());
        gameState.zombies = getZombiesData();
        gameState.plants = getPlantsData();
        gameState.XOfGraves = X;
        gameState.YOfGraves = Y;

        if (gameTimer == null) {
            gameState.isOnGameMode = false;
        } else {
            gameState.gameTimer = (long) NightLevel.getInstance().gameTimer.getCurrentTime().toSeconds();
        }
        gameState.names = cardNames;
        return gameState;
    }

    public void saveGame(GameState gameState) {
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(SAVE_FILE));
            out.writeObject(gameState);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public GameState loadGame() {
        try
        {
            ObjectInputStream input = new ObjectInputStream(new FileInputStream(SAVE_FILE));
            GameState gameState = (GameState) input.readObject();
            input.close();
            return gameState;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void applyGameState() {
        GameState loadedState = loadGame();
        if (loadedState != null){
            isOnSaveMode = false;
            sunPoints.setText(String.valueOf(loadedState.sunPoints));
            this.setNames(loadedState.names);
            X = loadedState.XOfGraves;
            Y = loadedState.YOfGraves;

            for (int i = 0 ; i < 6 ; i++) {
                ImageView imageView = new ImageView("/view/images/grave_" + (i + 1) + ".png");
                imageView.setFitWidth(135);
                imageView.setFitHeight(140);
                cells[Y[i]][X[i]].setGraveImage(imageView);
                cells[Y[i]][X[i]].getGroup().getChildren().add(imageView);
                cells[Y[i]][X[i]].setAvailable(false);
                cells[Y[i]][X[i]].setGrave(true);
            }
            if (loadedState.isOnGameMode) {
                gameTimer = new Timeline(
                        new KeyFrame(Duration.seconds(20), e -> step1()),
                        new KeyFrame(Duration.seconds(36), e -> step2()),
                        new KeyFrame(Duration.seconds(52), e -> midAttack()),
                        new KeyFrame(Duration.seconds(61), e -> step3()),
                        new KeyFrame(Duration.seconds(77), e -> step4()),
                        new KeyFrame(Duration.seconds(93), e -> finalAttack()),
                        new KeyFrame(Duration.seconds(107), e -> {
                            if (finalTimer != null) {
                                finalTimer.stop();
                                finalTimer = null;
                            }
                            zombieTimer = null;
                            if (gameTimer != null) {
                                gameTimer.stop();
                                gameTimer = null;
                            }
                            exitTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
                                if (isGameFinish()) {
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
                                        FXMLLoader loader = new FXMLLoader(getClass()
                                                .getResource("../view/WinPage.fxml"));
                                        Parent winContent = loader.load();

                                        WinPage.setObj(NightLevel.getInstance());
                                        nightAnc.getChildren().add(winContent);

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
                gameTimer.playFrom(Duration.seconds(loadedState.gameTimer));
            } else {
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

                            WinPage.setObj(NightLevel.getInstance());
                            nightAnc.getChildren().add(winContent);

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
            }

            for (ZombieData zombieData : loadedState.zombies){
                switch (zombieData.type) {
                    case "Zombie":
                        Zombie zombie = new Zombie(zombieData.y, zombieData.x, zombieData.rowBTN);
                        zombie.columnBTN = zombieData.columnBTN;
                        cells[zombie.rowBTN][zombie.columnBTN].setZombies(zombie);
                        zombie.setHP(zombieData.HP);
                        break;
                    case "ConeheadZombie":
                        ConeheadZombie coneheadZombie = new ConeheadZombie(zombieData.y, zombieData.x
                                , zombieData.rowBTN);
                        coneheadZombie.columnBTN = zombieData.columnBTN;
                        cells[coneheadZombie.rowBTN][coneheadZombie.columnBTN].setZombies(coneheadZombie);
                        coneheadZombie.setHP(zombieData.HP);
                        break;
                    case "ScreenDoorZombie":
                        ScreenDoorZombie screenDoorZombie = new ScreenDoorZombie(zombieData.y, zombieData.x
                                , zombieData.rowBTN);
                        screenDoorZombie.columnBTN = zombieData.columnBTN;
                        cells[screenDoorZombie.rowBTN][screenDoorZombie.columnBTN].setZombies(screenDoorZombie);
                        screenDoorZombie.setHP(zombieData.HP);
                        break;
                    case "ImpZombie":
                        ImpZombie impZombie = new ImpZombie(zombieData.y, zombieData.x, zombieData.rowBTN);
                        impZombie.columnBTN = zombieData.columnBTN;
                        cells[impZombie.rowBTN][impZombie.columnBTN].setZombies(impZombie);
                        impZombie.setHP(zombieData.HP);
                        break;
                }
            }

            for (PlantData plantData : loadedState.plants) {
                Plants.isOnSaveMode = true;
                switch (plantData.type) {
                    case "CherryBomb":
                        CherryBomb cherryBomb = new CherryBomb(plantData.row, plantData.column);
                        cherryBomb.setHP(plantData.HP);
                        cherryBomb.setCherryBombTimer(plantData.plantTimer[0]);
                        cells[cherryBomb.getRow()][cherryBomb.getColumn()].setPlants(cherryBomb);
                        cells[cherryBomb.getRow()][cherryBomb.getColumn()].getGroup().getChildren().add(cherryBomb.getImage());
                        break;
                    case "Jalapenos":
                        Jalapenos jalapenos = new Jalapenos(plantData.row, plantData.column);
                        jalapenos.setHP(plantData.HP);
                        jalapenos.setJalopenosTimer(plantData.plantTimer[0]);
                        cells[jalapenos.getRow()][jalapenos.getColumn()].setPlants(jalapenos);
                        cells[jalapenos.getRow()][jalapenos.getColumn()].getGroup().getChildren().add(jalapenos.getImage());
                        break;
                    case "PeaShooter":
                        PeaShooter peaShooter = new PeaShooter(plantData.row, plantData.column);
                        peaShooter.setHP(plantData.HP);
                        peaShooter.setShootTimer(plantData.plantTimer[0]);
                        cells[peaShooter.getRow()][peaShooter.getColumn()].setPlants(peaShooter);
                        cells[peaShooter.getRow()][peaShooter.getColumn()].getGroup().getChildren().add(peaShooter.getImage());
                        break;
                    case "Repeater":
                        Repeater repeater = new Repeater(plantData.row, plantData.column);
                        repeater.setHP(plantData.HP);
                        repeater.setShootTimer(plantData.plantTimer[0]);
                        cells[repeater.getRow()][repeater.getColumn()].setPlants(repeater);
                        cells[repeater.getRow()][repeater.getColumn()].getGroup().getChildren().add(repeater.getImage());
                        break;
                    case "SnowShooter":
                        SnowShooter snowShooter = new SnowShooter(plantData.row, plantData.column);
                        snowShooter.setHP(plantData.HP);
                        snowShooter.setShootTimer(plantData.plantTimer[0]);
                        cells[snowShooter.getRow()][snowShooter.getColumn()].setPlants(snowShooter);
                        cells[snowShooter.getRow()][snowShooter.getColumn()].getGroup().getChildren().add(snowShooter.getImage());
                        break;
                    case "Sunflower":
                        Sunflower sunflower = new Sunflower(plantData.row, plantData.column);
                        sunflower.setHP(plantData.HP);
                        sunflower.setSunTimeline(plantData.plantTimer[0]);
                        cells[sunflower.getRow()][sunflower.getColumn()].setPlants(sunflower);
                        cells[sunflower.getRow()][sunflower.getColumn()].getGroup().getChildren().add(sunflower.getImage());
                        break;
                    case "TallNut":
                        TallNut tallNut = new TallNut(plantData.row, plantData.column);
                        tallNut.setHP(plantData.HP);
                        cells[tallNut.getRow()][tallNut.getColumn()].setPlants(tallNut);
                        cells[tallNut.getRow()][tallNut.getColumn()].getGroup().getChildren().add(tallNut.getImage());
                        break;
                    case "WallNut":
                        WallNut wallNut = new WallNut(plantData.row, plantData.column);
                        wallNut.setHP(plantData.HP);
                        cells[wallNut.getRow()][wallNut.getColumn()].setPlants(wallNut);
                        cells[wallNut.getRow()][wallNut.getColumn()].getGroup().getChildren().add(wallNut.getImage());
                        break;
                    case "Blover":
                        Blover blover = new Blover(plantData.row , plantData.column);
                        blover.setHP(plantData.HP);
                        blover.setBloverTimer(plantData.plantTimer[0]);
                        cells[blover.getRow()][blover.getColumn()].setPlants(blover);
                        cells[blover.getRow()][blover.getColumn()].getGroup().getChildren().add(blover.getImage());
                        break;
                    case "CoffeeBean":
                        CoffeeBean coffeeBean = new CoffeeBean(plantData.row , plantData.column);
                        coffeeBean.setHP(plantData.HP);
                        coffeeBean.setCoffeeBeanTimer(plantData.plantTimer[0]);
                        cells[coffeeBean.getRow()][coffeeBean.getColumn()].setPlants(coffeeBean);
                        cells[coffeeBean.getRow()][coffeeBean.getColumn()].getGroup().getChildren()
                                .add(coffeeBean.getImage());
                        break;
                    case "DoomShroom":
                        Doomshroom doomshroom = new Doomshroom(plantData.row, plantData.column);
                        doomshroom.setHP(plantData.HP);
                        doomshroom.setCoffee(plantData.coffee);
                        doomshroom.setNeedCoffee(plantData.needCoffee);
                        doomshroom.setDoomshroomTimer(plantData.plantTimer[0]);
                        cells[doomshroom.getRow()][doomshroom.getColumn()].setPlants(doomshroom);
                        cells[doomshroom.getRow()][doomshroom.getColumn()].getGroup().getChildren()
                                .add(doomshroom.getImage());
                        break;
                    case "GraveBuster":
                        GraveBuster graveBuster = new GraveBuster(plantData.row , plantData.column);
                        graveBuster.setHP(plantData.HP);
                        graveBuster.setBusterTimer(plantData.plantTimer[0]);
                        cells[graveBuster.getRow()][graveBuster.getColumn()].setPlants(graveBuster);
                        cells[graveBuster.getRow()][graveBuster.getColumn()].getGroup().getChildren()
                                .add(graveBuster.getImage());
                        break;
                    case "HypnoShroom":
                        HypnoShroom hypnoShroom = new HypnoShroom(plantData.row, plantData.column);
                        hypnoShroom.setHP(plantData.HP);
                        hypnoShroom.setCoffee(plantData.coffee);
                        hypnoShroom.setNeedCoffee(plantData.needCoffee);
                        cells[hypnoShroom.getRow()][hypnoShroom.getColumn()].setPlants(hypnoShroom);
                        cells[hypnoShroom.getRow()][hypnoShroom.getColumn()].getGroup().getChildren()
                                .add(hypnoShroom.getImage());
                        break;
                    case "IceShroom":
                        IceShroom iceShroom = new IceShroom(plantData.row, plantData.column);
                        iceShroom.setHP(plantData.HP);
                        iceShroom.setCoffee(plantData.coffee);
                        iceShroom.setNeedCoffee(plantData.needCoffee);
                        iceShroom.setIceShroomTimer(plantData.plantTimer[0]);
                        cells[iceShroom.getRow()][iceShroom.getColumn()].setPlants(iceShroom);
                        cells[iceShroom.getRow()][iceShroom.getColumn()].getGroup().getChildren()
                                .add(iceShroom.getImage());
                        break;
                    case "Plantern":
                        Plantern plantern = new Plantern(plantData.row , plantData.column);
                        plantern.setHP(plantData.HP);
                        plantern.setPlanternTimer(plantData.plantTimer[0]);
                        cells[plantern.getRow()][plantern.getColumn()].setPlants(plantern);
                        cells[plantern.getRow()][plantern.getColumn()].getGroup().getChildren()
                                .add(plantern.getImage());
                        break;
                    case "PuffShroom":
                        PuffShroom puffShroom = new PuffShroom(plantData.row, plantData.column);
                        puffShroom.setHP(plantData.HP);
                        puffShroom.setCoffee(plantData.coffee);
                        puffShroom.setNeedCoffee(plantData.needCoffee);
                        puffShroom.setShootTimer(plantData.plantTimer[0]);
                        cells[puffShroom.getRow()][puffShroom.getColumn()].setPlants(puffShroom);
                        cells[puffShroom.getRow()][puffShroom.getColumn()].getGroup().getChildren()
                                .add(puffShroom.getImage());
                        break;
                    case "ScaredyShroom":
                        ScaredyShroom scaredyShroom = new ScaredyShroom(plantData.row, plantData.column);
                        scaredyShroom.setHP(plantData.HP);
                        scaredyShroom.setCoffee(plantData.coffee);
                        scaredyShroom.setNeedCoffee(plantData.needCoffee);
                        scaredyShroom.setShootTimer(plantData.plantTimer[0]);
                        cells[scaredyShroom.getRow()][scaredyShroom.getColumn()].setPlants(scaredyShroom);
                        cells[scaredyShroom.getRow()][scaredyShroom.getColumn()].getGroup().getChildren()
                                .add(scaredyShroom.getImage());
                        break;
                    case "SunShroom":
                        SunShroom sunShroom = new SunShroom(plantData.row, plantData.column, plantData.heightImage
                                , plantData.widthImage);
                        sunShroom.setHP(plantData.HP);
                        sunShroom.setCoffee(plantData.coffee);
                        sunShroom.setNeedCoffee(plantData.needCoffee);
                        sunShroom.setSunTimeline(plantData.plantTimer[0]);
                        sunShroom.setIncreaseSizeTimer(plantData.plantTimer[1]);
                        cells[sunShroom.getRow()][sunShroom.getColumn()].setPlants(sunShroom);
                        cells[sunShroom.getRow()][sunShroom.getColumn()].getGroup().getChildren()
                                .add(sunShroom.getImage());
                        break;
                }
                Plants.isOnSaveMode = false;
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
        ArrayList<PlantData> plantDatas = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].getPlant() != null) {
                    Plants plant = cells[i][j].getPlant();
                    PlantData plantData = new PlantData(plant.getRow(), plant.getColumn(), plant.getHP(),
                            plant.getClass().getSimpleName(), plant.isCoffee(), plant.isNeedCoffee());
                    switch (plant.getClass().getSimpleName()) {
                        case "Blover" :
                            if (((Blover) plant).getBloverTimer() != null) {
                                plantData.plantTimer[0] = ((Blover) plant).getBloverTimer().getCurrentTime()
                                        .toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "CherryBomb" :
                            if (((CherryBomb) plant).getCherryBombTimer() != null) {
                                plantData.plantTimer[0] = ((CherryBomb) plant).getCherryBombTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "CoffeeBean" :
                            if (((CoffeeBean) plant).getCoffeeBeanTimer() != null) {
                                plantData.plantTimer[0] = ((CoffeeBean) plant).getCoffeeBeanTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "GraveBuster" :
                            if (((GraveBuster) plant).getBusterTimer() != null) {
                                plantData.plantTimer[0] = ((GraveBuster) plant).getBusterTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "IceShroom" :
                            if (((IceShroom) plant).getIceShroomTimer() != null) {
                                plantData.plantTimer[0] = ((IceShroom) plant).getIceShroomTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "Jalapenos" :
                            if (((Jalapenos) plant).getJalopenosTimer() != null) {
                                plantData.plantTimer[0] = ((Jalapenos) plant).getJalopenosTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "PeaShooter" :
                        case "PuffShroom" :
                        case "Repeater" :
                        case "ScaredyShroom" :
                        case "SnowShooter" :
                            if (((PeaPlants) plant).getShootTimer() != null) {
                                plantData.plantTimer[0] = ((PeaPlants) plant).getShootTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            Timeline t = ((PeaPlants) plant).getShootTimer();
                            Duration current = t.getCurrentTime();
                            boolean isPaused = t.getStatus() == Animation.Status.PAUSED;
                            break;
                        case "Sunflower" :
                            if (((Sunflower) plant).getSunTimeline() != null) {
                                plantData.plantTimer[0] = ((Sunflower) plant).getSunTimeline()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "Plantern" :
                            if (((Plantern) plant).getPlanternTimer() != null) {
                                plantData.plantTimer[0] = ((Plantern) plant).getPlanternTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                        case "SunShroom" :
                            plantData.heightImage = plant.getImage().getFitHeight();
                            plantData.widthImage = plant.getImage().getFitWidth();

                            if (((SunShroom) plant).getSunTimeline() != null) {
                                plantData.plantTimer[0] = ((SunShroom) plant).getSunTimeline()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            if (((SunShroom) plant).getIncreaseSizeTimer() != null) {
                                plantData.plantTimer[1] = ((SunShroom) plant).getIncreaseSizeTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[1] = -1;
                            }
                            break;
                        case "DoomShroom" :
                            if (((Doomshroom) plant).getDoomshroomTimer() != null) {
                                plantData.plantTimer[0] = ((Doomshroom) plant).getDoomshroomTimer()
                                        .getCurrentTime().toMillis();
                            } else {
                                plantData.plantTimer[0] = -1;
                            }
                            break;
                    }
                    plantDatas.add(plantData);
                }
            }
        }

        return plantDatas;
    }

    public void restart() {
        groupsOfPicked.clear();
        buttonsOfPicked.clear();
        plants.clear();
        cells = new Cell[5][9];
        isStopMod = false;

        setButtons();
        setGroups();
        fillBoard();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j] != null && cells[i][j].getGroup() != null) {
                    cells[i][j].getGroup().getChildren().clear();
                    cells[i][j].setPlants(null);
                    cells[i][j].setZombies(null);
                }
            }
        }

        sunPoints.setText("0");
        selectedPlant = null;
        selectedGroup = null;
        availableNum = -1;

        if (gameTimer != null) {
            gameTimer.stop();
            gameTimer = null;
        }
    }

    public void end() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 9; j++) {
                if (cells[i][j].getPlant() != null) {
                    cells[i][j].getPlant().end();
                }
                if (cells[i][j].getZombies() != null) {
                    for (Zombie z : cells[i][j].getZombies()) {
                        z.stop();
                    }
                }
            }
        }
    }

}
