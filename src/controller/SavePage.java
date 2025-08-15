package controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class SavePage implements Initializable {


    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button noBTN;

    @FXML
    private Button yesBTN;

    public static Object obj;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        noBTN.setOnAction(event -> Platform.exit());

        yesBTN.setOnAction(event -> {
            if (obj instanceof DayLevel) {
                DayLevel.getInstance().saveGame(DayLevel.getInstance().buildGameState());
            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().saveGame(NightLevel.getInstance().buildGameState());
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().saveGame(FogLevel.getInstance().buildGameState());
            }
            Platform.exit();
        });

    }

}
