import controller.DayLevel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    public static void main(String[] args) {
       launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        AnchorPane root= FXMLLoader.load(this.getClass().getResource("view/PickIcon.fxml"));
        stage.setScene(new Scene(root));
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);// Any keys you click it won't get out of fullscreen.
        stage.setFullScreen(true);

        stage.show();
    }
}