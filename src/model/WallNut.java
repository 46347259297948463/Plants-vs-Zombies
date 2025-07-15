package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WallNut extends NutPlants{

    private final static int HP = 10;

    private Timeline timer;

    private static Group group;

    private static int availableNum;

    public WallNut(int i, int j) {
        super(HP, i, j, 50, 8);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/wall nut.png").toString());
        imageView.setFitWidth(125);
        imageView.setFitHeight(130);
        setImage(imageView);
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public WallNut(){
        price = 50;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new WallNut(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void end() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void play() {

    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public static void setGroup(Group g) {
        group = g;
    }

}
