package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

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
        private Group group7;

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
        private Button plant7BTN;

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

        private ArrayList<ImageView> imageViews = new ArrayList<>(7);

        private ArrayList<String> plantsPicked = new ArrayList<>(7);

        private ArrayList<Group> groupsOfPicked = new ArrayList<>(7);

        private ArrayList<Button> buttonsOfPicked = new ArrayList<>(7);

        private int choose = 0;

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

            peashooterSelecterBTN.setOnAction(event -> {
                    if (selecter("pea shooter card", peaShooterImage))
                        peaShooterImage.setOpacity(0.79);
            });
            sunflowerSelecterBTN.setOnAction(event -> {
                    if (selecter("sunflower card", sunflowerImage))
                        sunflowerImage.setOpacity(0.79);
            });
            cherrybombSelecterBTN.setOnAction(event -> {
                    if (selecter("cherry bomb card", cherryBombImage))
                        cherryBombImage.setOpacity(0.79);
            });
            wallnutSelecterBTN.setOnAction(event -> {
                    if (selecter("wall nut card", wallNutImage))
                        wallNutImage.setOpacity(0.79);
            });
            snowshooterSelecterBTN.setOnAction(event -> {
                    if (selecter("snow shooter card", snowShooterImage))
                        snowShooterImage.setOpacity(0.79);
            });
            repeaterSelecterBTN.setOnAction(event -> {
                    if (selecter("repeater card", repeaterImage))
                        repeaterImage.setOpacity(0.79);
            });
            jalapenoSelecterBTN.setOnAction(event -> {
                    if (selecter("jalapenos card", jalapenoImage))
                        jalapenoImage.setOpacity(0.79);
            });
            tallNutSelecterBTN.setOnAction(event -> {
                    if (selecter("tall nut card", tallNutImage))
                        tallNutImage.setOpacity(0.79);
            });

            exitBTN.setOnAction(event -> Platform.exit());

            plant1BTN.setOnAction(event -> {
                    removePic(group1);
                    plantsPicked.remove(0);
                    imageViews.get(0).setOpacity(1);
            });
            plant2BTN.setOnAction(event -> {
                    removePic(group2);
                    plantsPicked.remove(1);
                    imageViews.get(1).setOpacity(1);
            });
            plant3BTN.setOnAction(event -> {
                    removePic(group3);
                    plantsPicked.remove(2);
                    imageViews.get(2).setOpacity(1);
            });
            plant4BTN.setOnAction(event -> {
                    removePic(group4);
                    plantsPicked.remove(3);
                    imageViews.get(3).setOpacity(1);
            });
            plant5BTN.setOnAction(event -> {
                    removePic(group5);
                    plantsPicked.remove(4);
                    imageViews.get(4).setOpacity(1);
            });
            plant6BTN.setOnAction(event -> {
                    removePic(group6);
                    plantsPicked.remove(5);
                    imageViews.get(5).setOpacity(1);
            });
            plant7BTN.setOnAction(event -> {
                    removePic(group7);
                    plantsPicked.remove(6);
                    imageViews.get(6).setOpacity(1);
            });
    }

    public boolean selecter(String str, ImageView im){
            if (plantsPicked.contains(str)){
                    return false;
            }
            if (choose == 7){
                    return false;
            }
            for (int i = 0 ; i < 7 ; i++){
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

    public void setPlants(){
            for (int i = 0; i < 7; i++) {
                    plantsPicked.add(null);
            }
    }

    public void setImages(){
                for (int i = 0; i < 7; i++) {
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
                                            toRemove = node;
                                            System.out.println("FOUND ImageView!");
                                    } else if (child instanceof Button) {
                                            child.setOpacity(0.45);
                                    }
                            }
                    }
            }

            if (toRemove != null) {
                    group.getChildren().remove(toRemove);
                    choose--;
            }
    }
}
