package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class Sunflower extends Plants{

    private final static int HP = 4;

    private Timeline sunTimeline;

    private Timeline timer;

    private static Group group;

    private Sun sun;

    private static int availableNum;

    public Sunflower(int i, int j){
        super(HP, i, j, 50, 5);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
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
            sunTimeline = new Timeline(new KeyFrame(Duration.millis(7500), event -> makeSun() ));
            sunTimeline.setCycleCount(Timeline.INDEFINITE);
            sunTimeline.play();
        }
    }

    public Sunflower(){
        price = 50;
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public static void setGroup(Group g) {
        group = g;
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
            sun = new Sun(row , column, true);
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
            sun.getButton().setOnAction(event -> {
                if (!DayLevel.getInstance().isStopMod) {
                    DayLevel.getInstance().depositSunPoints(25);
                    DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
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
                }
            });
        }
    }

    public void end(){
        sunTimeline.stop();
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop(){
        sunTimeline.pause();
        if (sun != null){
            sun.stop();
        }
    }

    @Override
    public void play(){
       sunTimeline.play();
        if (sun != null){
            sun.play();
        }
    }

}
