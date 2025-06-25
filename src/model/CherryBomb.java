package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class CherryBomb extends BombPlants{

    private final static int price = 150;

    private final static double rechargeTime = 8;

    private static boolean available = true;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    public CherryBomb(int i, int j) {
        super(i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/cherry bomb.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
        Timeline cherryBombTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                new KeyFrame(Duration.seconds(2) , event -> BOMB())
        );
        cherryBombTimer.setCycleCount(1);
        cherryBombTimer.play();

    }

    private void increaseSize(){
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            image.setFitHeight(image.getFitHeight() + 1 );
            image.setFitWidth(image.getFitWidth() + 1);
        }));
        timer.setCycleCount(20);
        timer.play();
    }
    public CherryBomb(){
    }

    @Override
    public void end() {}

    @Override
    public Plants clonePlant(int row, int column) {
        return new CherryBomb(row, column);
    }


    @Override
    public void BOMB() {

        if (column > 0 && row > 0) cells[row-1][column-1].removeAllZombies();
        if (column > 0) cells[row][column-1].removeAllZombies();
        if (column > 0 && row < 8) cells[row + 1][column - 1].removeAllZombies();
        if (row > 0) cells[row - 1][column].removeAllZombies();
        cells[row][column].removeAllZombies();
        if (row < 8) cells[row + 1][column].removeAllZombies();
        if (column < 8 && row > 0) cells[row -1][column + 1].removeAllZombies();
        if (column < 8) cells[row][column + 1].removeAllZombies();
        if (column < 8 && row < 8) cells[row + 1][column + 1].removeAllZombies();

        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }
}
