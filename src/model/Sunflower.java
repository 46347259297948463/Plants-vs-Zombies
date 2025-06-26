package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sunflower extends Plants{

    private final static int HP = 4;

    private final static double rechargeTime = 3;

    private Timeline sunTimeline;

    private Timeline timer;

    public Sunflower(int i, int j, Group group){
        super(HP, i, j, 50, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
        ImageView imageView = new ImageView(getClass().getResource("/view/images/sunflower.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        startTimer();
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    private void startTimer(){
        if(sunTimeline == null){
            sunTimeline = new Timeline(new KeyFrame(Duration.millis(6500), event -> makeSun() ));
            sunTimeline.setCycleCount(Timeline.INDEFINITE);
            sunTimeline.play();
        }
    }

    public Sunflower(){
        price = 50;
    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new Sunflower(row, column, group);
    }

    public void makeSun(){
        if(isDead()){
            sunTimeline.stop();
        }
        else {
            Sun sun = new Sun(row , column, true);
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
            sun.getButton().setOnAction(event -> {
                DayLevel.getInstance().depositSunPoints(25);
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
            });
        }
    }

    public void end(){
        sunTimeline.stop();
        group.setOpacity(1);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);
    }

}
