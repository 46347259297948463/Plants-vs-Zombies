package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button backToGameBTN;

    @FXML
    private Button exitBTN;

    @FXML
    private Button homeBTN;

    @FXML
    private Slider musicVolume = new Slider();

    @FXML
    private Button restartBTN;

    @FXML
    private Slider soundFX = new Slider();

    public static Object obj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        anchorPane.setStyle("-fx-background-color: transparent;");
//        #717B8B
//        #0E0F1F

        Platform.runLater(() -> {
//            musicVolume.lookup(".track").setStyle(
//                    "-fx-background-color: linear-gradient(to right, #717B8B " + musicVolume.getValue() / musicVolume.getMax() * 100 + "%, #0E0F1F " + musicVolume.getValue() / musicVolume.getMax() * 100 + "%);"
//            );
//            musicVolume.valueProperty().addListener((obs, oldVal, newVal) -> {
//                double percent = newVal.doubleValue() / musicVolume.getMax() * 100;
//                musicVolume.lookup(".track").setStyle(
//                        "-fx-background-color: linear-gradient(to right, #717B8B " + percent + "%, #0E0F1F " + percent + "%);"
//                );
//            });
        });

        Platform.runLater(() -> {
//            soundFX.lookup(".track").setStyle(
//                    "-fx-background-color: linear-gradient(to right, #717B8B " + soundFX.getValue() / soundFX.getMax() * 100 + "%, #0E0F1F " + soundFX.getValue() / soundFX.getMax() * 100 + "%);"
//            );
//
//            soundFX.valueProperty().addListener((obs, oldVal, newVal) -> {
//                double percent = newVal.doubleValue() / soundFX.getMax() * 100;
//                soundFX.lookup(".track").setStyle(
//                        "-fx-background-color: linear-gradient(to right, #717B8B " + percent + "%, #0E0F1F " + percent + "%);"
//                );
//            });
        });

        backToGameBTN.setOnAction(event -> {

            if (obj instanceof DayLevel){
                DayLevel.getInstance().getDayAnc().getChildren().remove(this.anchorPane);
                DayLevel.getInstance().play();
                DayLevel.setMenu(0);
            } else if (obj instanceof  NightLevel) {
                NightLevel.getInstance().getNightAnc().getChildren().remove(this.anchorPane);
                NightLevel.getInstance().play();
                NightLevel.setMenu(0);
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().getFogAnc().getChildren().remove(this.anchorPane);
                FogLevel.getInstance().play();
                FogLevel.setMenu(0);
            }
        });

        exitBTN.setOnAction(event -> {

            AnchorPane root = (AnchorPane) anchorPane.getScene().getRoot();
            root.getChildren().remove(anchorPane);

            if (obj instanceof DayLevel) {
                DayLevel.getInstance().end();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SavePage.fxml"));
                    Parent loadContact = loader.load();
                    DayLevel.getInstance().getDayAnc().getChildren().add(loadContact);

                    AnchorPane.setTopAnchor(loadContact, 260.0);
                    AnchorPane.setLeftAnchor(loadContact, 710.0);

                    loadContact.setOpacity(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().end();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SavePage.fxml"));
                    Parent loadContact = loader.load();
                    NightLevel.getInstance().getNightAnc().getChildren().add(loadContact);

                    AnchorPane.setTopAnchor(loadContact, 260.0);
                    AnchorPane.setLeftAnchor(loadContact, 710.0);

                    loadContact.setOpacity(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }

            } else if (obj instanceof FogLevel){
                FogLevel.getInstance().end();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/SavePage.fxml"));
                    Parent loadContact = loader.load();
                    FogLevel.getInstance().getFogAnc().getChildren().add(loadContact);

                    AnchorPane.setTopAnchor(loadContact, 260.0);
                    AnchorPane.setLeftAnchor(loadContact, 710.0);

                    loadContact.setOpacity(1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

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
            FirstPage.playAudio();

            if (obj instanceof DayLevel) {
                DayLevel.getInstance().end();
                DayLevel.setMenu(0);
                DayLevel.stopAudio();
                DayLevel.getInstance().restart();
            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().end();
                NightLevel.setMenu(0);
                NightLevel.stopAudio();
                NightLevel.getInstance().restart();
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().end();
                FogLevel.setMenu(0);
                FogLevel.stopAudio();
                FogLevel.getInstance().restart();
            }

        });

        restartBTN.setOnAction(event -> {
            if (obj instanceof DayLevel){
                DayLevel.getInstance().end();
                DayLevel.setMenu(0);
                DayLevel.stopAudio();

                DayLevel.getInstance().restart();
                Stage oldStage = (Stage) restartBTN.getScene().getWindow();
                oldStage.close();

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PickIcon controller = loader.getController();
                controller.setObj(DayLevel.getInstance());
                FirstPage.playAudio();

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.setFullScreen(true);
                stage.show();
            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().stop();
                NightLevel.setMenu(0);
                NightLevel.stopAudio();

                NightLevel.getInstance().restart();
                Stage oldStage = (Stage) restartBTN.getScene().getWindow();
                oldStage.close();

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PickIcon controller = loader.getController();
                controller.setObj(NightLevel.getInstance());
                FirstPage.playAudio();

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.setFullScreen(true);
                stage.show();
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().stop();
                FogLevel.setMenu(0);
                FogLevel.stopAudio();

                FogLevel.getInstance().restart();
                Stage oldStage = (Stage) restartBTN.getScene().getWindow();
                oldStage.close();

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PickIcon controller = loader.getController();
                controller.setObj(FogLevel.getInstance());
                FirstPage.playAudio();

                Stage stage = new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
                stage.setFullScreen(true);
                stage.show();
            }

        });

    }

}
