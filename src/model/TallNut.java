package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TallNut extends NutPlants{

    private final static int HP = 16;

    private static Timeline timer;

    private static Group group;

    private static int availableNum;

    public TallNut(int i, int j) {
        super(HP, i, j, 125, 12);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false, availableNum);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false, availableNum);
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false, availableNum);
        }
        ImageView imageView = new ImageView(getClass().getResource("/view/images/tall nut.png").toString());
        imageView.setFitWidth(140);
        imageView.setFitHeight(190);
        setImage(imageView);
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public TallNut(){
        price = 125;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new TallNut(row, column);
    }

    @Override
    protected void recharge() {
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(true, availableNum);
        }
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

    @Override
    public Timeline getTimer() {
        return timer;
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public static void setGroup(Group g) {
        group = g;
    }
}
