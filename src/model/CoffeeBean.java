package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class CoffeeBean extends Plants{

    private static Timeline timer;

    private Timeline coffeeBeanTimer;

    private static Group group;

    private static int availableNum;

    private Plants plant;

    public CoffeeBean(int i, int j) {
        super(4, i, j, 75, 10);

        ImageView imageView = new ImageView(getClass().getResource("/view/images/coffee bean.png").toString());
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);
        imageView.setLayoutX(5);
        imageView.setLayoutY(5);
        setImage(imageView);

        coffeeBeanTimer = new Timeline(new KeyFrame(Duration.seconds(5), event -> {
            if (plant != null) {
                plant.setCoffee(true);
            }
            if (obj instanceof DayLevel) {
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
            }
            end();
        }));
        coffeeBeanTimer.setCycleCount(1);
        coffeeBeanTimer.play();

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public CoffeeBean() {
        price = 75;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new CoffeeBean(row, column);
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
    public void stop() {
        coffeeBeanTimer.pause();
    }

    @Override
    public void play() {
        if (coffeeBeanTimer != null && coffeeBeanTimer.getStatus() == Timeline.Status.PAUSED) {
            coffeeBeanTimer.play();
        }
    }

    @Override
    public void end() {
        if (coffeeBeanTimer != null) {
            coffeeBeanTimer.stop();
        }
        coffeeBeanTimer = null;
    }

    @Override
    public Timeline getTimer() {
        return null;
    }

    public static void setGroup(Group g) {
        group = g;
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public void setPlant(Plants plant) {
        this.plant = plant;
    }
}
