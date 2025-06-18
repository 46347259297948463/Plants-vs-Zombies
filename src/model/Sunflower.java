package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Sunflower extends Plants{

    private final static int HP = 4;
    private final static int price = 50;
    private final static double rechargeTime = 3;
    private static boolean available = true;
    private Timeline sunTimeline;
    public Sunflower(int i, int j){
        super(HP, i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/sunflower.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        startTimer();
    }

    private void startTimer(){
        if(sunTimeline == null){
            sunTimeline = new Timeline(new KeyFrame(Duration.millis(6500), event -> makeSun() ));
            sunTimeline.setCycleCount(Timeline.INDEFINITE);
            sunTimeline.play();
        }
    }

    public Sunflower(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Sunflower(row, column);
    }

    public void makeSun(){
        if(isDead()){
            sunTimeline.stop();
        }
        else {
            Sun sun = new Sun(row , column);
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
            sun.getButton().setOnAction(event -> {
                DayLevel.getInstance().setSunPoints(25);
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
            });
        }
    }
}
