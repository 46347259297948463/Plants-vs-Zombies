package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class TallNut extends NutPlants{

    private final static int HP = 16;

    private final static double rechargeTime = 8;

    private Timeline timer;

    public TallNut(int i, int j, Group group) {
        super(HP, i, j, 125, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
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
    public void end() {
        group.setOpacity(1);
    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new TallNut(row, column, group);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);
    }

}
