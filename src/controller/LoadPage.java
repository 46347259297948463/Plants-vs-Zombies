package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoadPage implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button noBTN;

    @FXML
    private Button yesBTN;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        noBTN.setOnAction(event -> {
            Parent root = HomePage.getInstance().getExitBTN().getScene().getRoot();
            if (root instanceof AnchorPane) {
                HomePage.getInstance().getAnchorPane().getChildren().remove(this.anchorPane);
            }
        });

        yesBTN.setOnAction(event -> {
            DayLevel.isOnSaveMode = true;
            FirstPage.stopAudio();
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/DayLevel.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            DayLevel controller= loader.getController();
            Stage stage= new Stage();
            stage.setScene(new Scene(loader.getRoot()));

            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
            stage.setFullScreen(true);
            stage.show();

            Stage oldStage = (Stage) yesBTN.getScene().getWindow();
            oldStage.close();

            DayLevel.getInstance().applyGameState();
        });

    }

}
