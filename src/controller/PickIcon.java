package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import controller.FirstPage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PickIcon implements Initializable{

        @FXML
        private ImageView cherryBombImage;

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
        private ImageView jalapenoImage;

        @FXML
        private Button jalapenoSelecterBTN;

        @FXML
        private ImageView peaShooterImage;

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
        private Button playBTN;

        @FXML
        private ImageView repeaterImage;

        @FXML
        private Button repeaterSelecterBTN;

        @FXML
        private ImageView snowShooterImage;

        @FXML
        private Button snowshooterSelecterBTN;

        @FXML
        private ImageView sunflowerImage;

        @FXML
        private Button sunflowerSelecterBTN;

        @FXML
        private ImageView tallNutImage;

        @FXML
        private Button tallNutSelecterBTN;

        @FXML
        private ImageView wallNutImage;

        @FXML
        private Button wallnutSelecterBTN;

        @FXML
        private Button resetBTN;

        @FXML
        private Button exitBTN;

        @FXML
        private Button homeBTN;

        @FXML
        private VBox thrownVbox;

        private ArrayList<ImageView> imageViews = new ArrayList<>(6);

        private ArrayList<String> plantsPicked = new ArrayList<>(6);

        private ArrayList<Group> groupsOfPicked = new ArrayList<>(6);

        private ArrayList<Button> buttonsOfPicked = new ArrayList<>(6);

        private int choose = 0;

        private Object obj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

            setImages();
            setPlants();
            setButtons();
            setGroups();

            resetBTN.setOnAction(event -> {
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
                    try {
                            loader.load();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }

                    PickIcon controller= loader.getController();

                    Stage stage= new Stage();
                    stage.setScene(new Scene(loader.getRoot()));

                    stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
                    stage.setFullScreen(true);
                    stage.show();

                    ((Stage)resetBTN.getScene().getWindow()).hide();
            });

            homeBTN.setOnAction(event -> {
                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/HomePage.fxml"));
                    try {
                            loader.load();
                    } catch (IOException e) {
                            e.printStackTrace();
                    }

                    HomePage controller= loader.getController();
                    Stage stage= new Stage();
                    stage.setScene(new Scene(loader.getRoot()));

                    stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
                    stage.setFullScreen(true);
                    stage.show();
                    Stage oldStage = (Stage) homeBTN.getScene().getWindow();
                    oldStage.close();
            });

            exitBTN.setOnAction(event -> Platform.exit());

            peashooterSelecterBTN.setOnAction(event -> {
                    if (selecter("pea shooter card", peaShooterImage))
                        peaShooterImage.setOpacity(0.45);
            });
            sunflowerSelecterBTN.setOnAction(event -> {
                    if (selecter("sunflower card", sunflowerImage))
                        sunflowerImage.setOpacity(0.45);
            });
            cherrybombSelecterBTN.setOnAction(event -> {
                    if (selecter("cherry bomb card", cherryBombImage))
                        cherryBombImage.setOpacity(0.45);
            });
            wallnutSelecterBTN.setOnAction(event -> {
                    if (selecter("wall nut card", wallNutImage))
                        wallNutImage.setOpacity(0.45);
            });
            snowshooterSelecterBTN.setOnAction(event -> {
                    if (selecter("snow shooter card", snowShooterImage))
                        snowShooterImage.setOpacity(0.45);
            });
            repeaterSelecterBTN.setOnAction(event -> {
                    if (selecter("repeater card", repeaterImage))
                        repeaterImage.setOpacity(0.45);
            });
            jalapenoSelecterBTN.setOnAction(event -> {
                    if (selecter("jalapenos card", jalapenoImage))
                        jalapenoImage.setOpacity(0.45);
            });
            tallNutSelecterBTN.setOnAction(event -> {
                    if (selecter("tall nut card", tallNutImage))
                        tallNutImage.setOpacity(0.45);
            });

            plant1BTN.setOnAction(event -> {
                    playNButtton(group1, 0);
            });
            plant2BTN.setOnAction(event -> {
                    playNButtton(group2, 1);
            });
            plant3BTN.setOnAction(event -> {
                    playNButtton(group3, 2);
            });
            plant4BTN.setOnAction(event -> {
                    playNButtton(group4, 3);
            });
            plant5BTN.setOnAction(event -> {
                    playNButtton(group5, 4);
            });
            plant6BTN.setOnAction(event -> {
                    playNButtton(group6, 5);
            });

            playBTN.setOnAction(event -> {
                    if (isFinish()){
                            if (obj instanceof DayLevel) {
                                    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/DayLevel.fxml"));
                                    try {
                                            loader.load();
                                    } catch (IOException e) {
                                            e.printStackTrace();
                                    }

                                    DayLevel controller = loader.getController();
                                    controller.setNames(plantsPicked);
                                    Stage stage = new Stage();
                                    stage.setScene(new Scene(loader.getRoot()));

                                    stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
                                    stage.setFullScreen(true);
                                    stage.show();

                                    ((Stage) resetBTN.getScene().getWindow()).hide();
                                    FirstPage.stopAudio();
                            }
                    }
            });
    }

    public void playNButtton(Group group, int i){
            removePic(group);
            plantsPicked.set(i, null);
            imageViews.get(i).setOpacity(1);
    }

    public boolean selecter(String str, ImageView im){
            if (plantsPicked.contains(str)){
                    return false;
            }
            if (choose == 6){
                    return false;
            }
            for (int i = 0 ; i < 6 ; i++){
                    if(plantsPicked.get(i) == null){
                            plantsPicked.set(i, str);
                            groupsOfPicked.get(i).getChildren().remove(buttonsOfPicked.get(i));
                            Image image = new Image(getClass().getResource("/view/images/" + str + ".png").toString());
                            ImageView imageView = new ImageView(image);
                            imageView.setFitWidth(180);
                            imageView.setFitHeight(130);
                            imageViews.set(i, im);
                            buttonsOfPicked.get(i).setOpacity(0);
                            StackPane stackPane = new StackPane();
                            stackPane.getChildren().addAll(imageView, buttonsOfPicked.get(i));
                            groupsOfPicked.get(i).getChildren().addAll(stackPane);
                            choose++;
                            if (isFinish()){
                                    playBTN.setOpacity(1);
                            }
                            return true;
                    }
            }
            return true;
    }

    public void setButtons(){
            buttonsOfPicked.add(plant1BTN);
            buttonsOfPicked.add(plant2BTN);
            buttonsOfPicked.add(plant3BTN);
            buttonsOfPicked.add(plant4BTN);
            buttonsOfPicked.add(plant5BTN);
            buttonsOfPicked.add(plant6BTN);
    }

    public void setGroups(){
            groupsOfPicked.add(group1);
            groupsOfPicked.add(group2);
            groupsOfPicked.add(group3);
            groupsOfPicked.add(group4);
            groupsOfPicked.add(group5);
            groupsOfPicked.add(group6);
    }

    public void setPlants(){
            for (int i = 0; i < 6; i++) {
                    plantsPicked.add(null);
            }
    }

    public void setImages(){
            for (int i = 0; i < 6; i++) {
                        imageViews .add(null);
            }
    }

    public void removePic(Group group){
            Node toRemove = null;

            for (Node node : group.getChildren()) {
                    if (node instanceof StackPane) {
                            StackPane sp = (StackPane) node;
                            for (Node child : sp.getChildren()) {
                                    if (child instanceof ImageView) {
                                            toRemove = child;
                                    } else if (child instanceof Button) {
                                            child.setOpacity(0.45);
                                    }
                            }
                            if (toRemove != null) {
                                    sp.getChildren().remove(toRemove);
                                    choose--;
                            }
                    }
            }
            if (!isFinish()){
                    playBTN.setOpacity(0.8);
            }
    }

    public boolean isFinish(){
            return choose == 6;
    }

    public void setObj(Object object){
            obj = object;
    }

}
