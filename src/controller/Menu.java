package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    private Object obj;

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
                AnchorPane root = (AnchorPane) ((DayLevel) obj).getInstance().getMenuBTN().getScene().getRoot();
                root.getChildren().remove(this.anchorPane);
                ((DayLevel) obj).getInstance().play();
                DayLevel.setMenu(0);
            }
        });

        exitBTN.setOnAction(event -> {
            DayLevel.getInstance().saveGame(DayLevel.getInstance().buildGameState());

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
            DayLevel.setMenu(0);
            FirstPage.playAudio();
            DayLevel.stopAudio();
            FirstPage.playAudio();
        });

        restartBTN.setOnAction(event -> {
//            if (obj instanceof DayLevel){
//                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
//                try {
//                    loader.load();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//                PickIcon controller= loader.getController();
//                Stage stage= new Stage();
//                stage.setScene(new Scene(loader.getRoot()));
//
//                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
//                stage.setFullScreen(true);
//                stage.show();
//            }
//            Stage oldStage = (Stage) restartBTN.getScene().getWindow();
//            oldStage.close();
//            DayLevel.setMenu(0);
//            DayLevel.stopAudio();
//            FirstPage.playAudio();
            DayLevel.getInstance().loadGame();
        });

    }

    public void setObj(Object object){
        obj = object;
    }

}
