package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Jalapenos extends BombPlants{

    private final static double rechargeTime = 10;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    private Timeline timer;

    private Timeline jalopenosTimer;

    public Jalapenos(int i, int j, Group group) {
        super(i, j, 125, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
        ImageView imageView = new ImageView(getClass().getResource("/view/images/jalapenos.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
        jalopenosTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                new KeyFrame(Duration.seconds(2) , event -> BOMB())
        );
        jalopenosTimer.setCycleCount(1);
        jalopenosTimer.play();

        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public Jalapenos(){
        price = 125;
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
    public void end() {
        if (jalopenosTimer != null){
            jalopenosTimer.stop();
        }
        group.setOpacity(1);
    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new Jalapenos(row, column, group);
    }

    @Override
    public void BOMB() {
        for (int j = 0 ; j < 9 ; j++){
            cells[row][j].removeAllZombies();
        }
        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);}
}
