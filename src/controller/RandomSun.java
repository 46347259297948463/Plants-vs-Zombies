package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Sun;

public class RandomSun {

    private Timeline sunTimer;

    private Timeline moveTimer;

    private Sun sun;

    private int stopColumn;

    private int row;

    private int column = 0;

    public RandomSun(){
        sunTimer = new Timeline(new KeyFrame(Duration.millis(5000) , event -> randomSun()));
        sunTimer.setCycleCount(Timeline.INDEFINITE);
        sunTimer.play();
    }

    private void randomSun(){
        row = (int) Math.random() * 712 + 245;
        column = (int) Math.random() * 470 + 80;
        moveTimer = new Timeline(new KeyFrame(Duration.millis(500) , event -> makeSun()));
        moveTimer.setCycleCount(Timeline.INDEFINITE);
        moveTimer.play();
    }

    private void makeSun(){
        sun = new Sun(row, column);
        sun.getButton().setOnAction(event -> {
            DayLevel.getInstance().setSunPoints(25);
        });
        column+=10;
        if (column > stopColumn){
            moveTimer.stop();
        }
    }
}
