package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Jalapenos extends BombPlants{

    private final static int price = 125;

    private final static double rechargeTime = 10;

    private static boolean available = true;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    public Jalapenos(int i, int j) {
        super(i, j, price, rechargeTime);
        available = false;

        ImageView imageView = new ImageView(getClass().getResource("/view/images/jalapenos.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
        Timeline jalopenosTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                new KeyFrame(Duration.seconds(2) , event -> BOMB())
        );
        jalopenosTimer.setCycleCount(1);
        jalopenosTimer.play();
    }

    public Jalapenos(){

    }

    private void increaseSize(){
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            image.setFitHeight(image.getFitHeight() + 1 );
            image.setFitWidth(image.getFitWidth() + 1);
        }));
        timer.setCycleCount(20);
        timer.play();
    }

    @Override
    public void end() {}

    @Override
    public Plants clonePlant(int row, int column) {
        return new Jalapenos(row, column);
    }

    @Override
    public void BOMB() {
        for (int j = 0 ; j < 9 ; j++){
            cells[row][j].removeAllZombies();
        }
        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }

}
