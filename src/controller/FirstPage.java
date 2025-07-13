package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class FirstPage implements Initializable {

    @FXML
    private ProgressBar progressBar;

    private Timeline loading;

    private static Clip clip;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/pick icon.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        progressBar.setProgress(0);
        double[] amount = {0};
        loading = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            progressBar.setProgress(amount[0]);
            amount[0] += 0.05;
            if (amount[0] >= 1.25){
                loading.stop();

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
                Stage oldStage = (Stage) progressBar.getScene().getWindow();
                oldStage.close();
            }
        }));
        loading.setCycleCount(50);
        loading.play();
        Platform.runLater(() -> {
            if (progressBar.lookup(".track") != null) {
                progressBar.lookup(".track").setStyle("-fx-opacity: 0;");
            }
            if (progressBar.lookup(".bar") != null) {
                progressBar.lookup(".bar").setStyle("-fx-opacity: 1; -fx-background-color: green;");
            }
        });
    }

    public static void stopAudio(){
        clip.stop();
    }

    public static void playAudio(){
        clip.start();
    }
}