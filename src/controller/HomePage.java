package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomePage implements Initializable {


    @FXML
    private Button dayBTN;

    @FXML
    private Button fogBTN;

    @FXML
    private Button nightBTN;

    @FXML
    private Button exitBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        exitBTN.setOnAction(event -> Platform.exit());

        dayBTN.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            PickIcon.setObj(new DayLevel());
            PickIcon controller= loader.getController();
            Stage stage= new Stage();
            stage.setScene(new Scene(loader.getRoot()));

            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
            stage.setFullScreen(true);
            stage.show();
            Stage oldStage = (Stage) dayBTN.getScene().getWindow();
            oldStage.close();
        });
    }

}
