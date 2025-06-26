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

    private final static int rechargeTime = 5;

    private Timeline timer;

    public WallNut(int i, int j, Group group) {
        super(HP, i, j, 50, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
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
    public void end() {
        group.setOpacity(1);
    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new WallNut(row, column, group);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);
    }

}
