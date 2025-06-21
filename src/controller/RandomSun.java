package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Sun;

import java.util.Random;

public class RandomSun {

    private Timeline sunTimer;

    private Timeline moveTimer;

    private Sun sun;

    private int stopColumn;

    private int row;

    private int column = 0;

    private Random random = new Random();

    public RandomSun(){
        sunTimer = new Timeline(new KeyFrame(Duration.millis(8000) , event -> randomSun()));
        sunTimer.setCycleCount(Timeline.INDEFINITE);
        sunTimer.play();
    }

    private void randomSun(){
        row = random.nextInt(650) + 345;
        stopColumn = random.nextInt(300) + 275;
        column = 0;

        moveTimer = new Timeline(new KeyFrame(Duration.millis(100) , event -> makeSun()));
        moveTimer.setCycleCount(Timeline.INDEFINITE);
        moveTimer.play();
    }

    private void makeSun(){
        if (sun != null){
            sun.endSun();
            sun.timeline.stop();
        }
        sun = new Sun(row, column, false);
        DayLevel.getInstance().getDayAnc().getChildren().add(sun.getGroup());
        column += 10;
        sun.getButton().setOnAction(event -> {
            DayLevel.getInstance().depositSunPoints(25);
            sun.endSun();
        });
        if (column >= stopColumn){
            moveTimer.stop();
        }
    }
}
