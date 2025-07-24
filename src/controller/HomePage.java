package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HomePage implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button dayBTN;

    @FXML
    private Button fogBTN;

    @FXML
    private Button nightBTN;

    @FXML
    private Button exitBTN;

    private static HomePage instance;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        HomePage.setInstance(this);

        File save = new File("save.dat");
        if (save.exists() && save.length() != 0){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/LoadPage.fxml"));
                Parent loadContent = loader.load();

                anchorPane.getChildren().add(loadContent);

                AnchorPane.setTopAnchor(loadContent, 285.0);
                AnchorPane.setLeftAnchor(loadContent, 700.0);

                loadContent.setOpacity(1);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        exitBTN.setOnAction(event -> Platform.exit());

        dayBTN.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PickIcon.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            PickIcon controller= loader.getController();
            controller.setObj(DayLevel.getInstance());
            Stage stage= new Stage();
            stage.setScene(new Scene(loader.getRoot()));

            stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
            stage.setFullScreen(true);
            stage.show();
            Stage oldStage = (Stage) dayBTN.getScene().getWindow();
            oldStage.close();
        });

    }

    public static void setInstance(HomePage instance) {
        HomePage.instance = instance;
    }

    public Button getExitBTN() {
        return exitBTN;
    }

    public static HomePage getInstance() {
        return instance;
    }

    public AnchorPane getAnchorPane() {
        return anchorPane;
    }

}
