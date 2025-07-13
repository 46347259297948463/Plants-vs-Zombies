package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import model.Sun;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
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
        sunTimer = new Timeline(new KeyFrame(Duration.millis(9000) , event -> randomSun()));
        sunTimer.setCycleCount(Timeline.INDEFINITE);
        sunTimer.play();
    }

    private void randomSun(){
        row = random.nextInt(650) + 345;
        stopColumn = random.nextInt(300) + 275;
        column = 0;
        sun = new Sun(row, column, false);
        DayLevel.getInstance().getDayAnc().getChildren().add(sun.getGroup());
        moveTimer = new Timeline(new KeyFrame(Duration.millis(65) , event -> moveSun()));
        moveTimer.setCycleCount(Timeline.INDEFINITE);
        moveTimer.play();
    }

    private void moveSun(){
        column += 7;
        sun.getGroup().setLayoutY(column);
        sun.getButton().setOnAction(event -> {
            if (!DayLevel.getInstance().isStopMod) {
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                            getClass().getResource("/view/audio/sun sound.wav")
                    );
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
                    gainControl.setValue(6.0f);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DayLevel.getInstance().depositSunPoints(25);
                sun.endSun();
            }
        });
        if (column >= stopColumn){
            moveTimer.stop();
        }
    }

    public void stop(){
        if (sunTimer != null){
            sunTimer.stop();
        }
        if (moveTimer != null){
            moveTimer.stop();
        }
        if (sun != null){
            sun.stop();
        }
    }

    public void play(){
        if (sunTimer != null){
            sunTimer.play();
        }
        if (moveTimer != null){
            moveTimer.play();
        }
        if (sun != null){
            sun.play();
        }
    }

}
