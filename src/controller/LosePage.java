package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LosePage implements Initializable {

    @FXML
    private Button homeBTN;

    @FXML
    private Button tryAgainBTN;

    private static Object obj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        homeBTN.setOnAction(event -> {
            FirstPage.playAudio();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/HomePage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FirstPage.playAudio();

            HomePage controller= loader.getController();
            Stage stage= new Stage();
            stage.setScene(new Scene(loader.getRoot()));

            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
            stage.setFullScreen(true);
            stage.show();
            Stage oldStage = (Stage) homeBTN.getScene().getWindow();
            oldStage.close();
        });

        tryAgainBTN.setOnAction(event -> {
            if (obj instanceof DayLevel){
                DayLevel.getInstance().stop();
                DayLevel.setMenu(0);
                DayLevel.stopAudio();
                FirstPage.playAudio();
                Stage oldStage = (Stage) tryAgainBTN.getScene().getWindow();
                oldStage.close();

                DayLevel.resetInstance();

                FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PickIcon controller= loader.getController();
                controller.setObj(DayLevel.getInstance());
                DayLevel.getInstance().restart();

                Stage stage= new Stage();
                stage.setScene(new Scene(loader.getRoot()));
                stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
                stage.setFullScreen(true);
                stage.show();
            }
        });

    }

    public static void setObj(Object object){
        obj = object;
    }

}
