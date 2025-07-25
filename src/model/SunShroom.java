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

public class SunShroom extends Plants {

    private final static int HP = 4;

    private Timeline sunTimeline;

    private Timeline increasePointTimer;

    private Timeline timer;

    private static Group group;

    private Sun sun;

    private static int availableNum;

    public SunShroom(int i, int j){
        super(HP, i, j, 25, 5);
        DayLevel.getInstance().setAvailablePicked(false,availableNum);
        ImageView imageView = new ImageView(getClass().getResource("/images/sun shroom.png").toString());
        imageView.setFitHeight(75);
        imageView.setFitWidth(75);
        setImage(imageView);
        group.setOpacity(0.7);
        increasePointTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> startTimer1()),
                new KeyFrame(Duration.seconds(10), event -> increaseSize()),
                new KeyFrame(Duration.seconds(12), event -> startTimer2())
        );
        increasePointTimer.setCycleCount(1);
        increasePointTimer.play();
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    private void startTimer1(){
        if(sunTimeline == null){
            sunTimeline = new Timeline(new KeyFrame(Duration.millis(7500), event -> firstMakeSun() ));
            sunTimeline.setCycleCount(Timeline.INDEFINITE);
            sunTimeline.play();
        }
    }

    public void firstMakeSun(){
        if(isDead()){
            sunTimeline.stop();
        }
        else {
            sun = new Sun(row , column, true);
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
            sun.getButton().setOnAction(event -> {
                if (!DayLevel.getInstance().isStopMod) {
                    DayLevel.getInstance().depositSunPoints(15);
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

    private void increaseSize(){
        sunTimeline.stop();
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            image.setFitHeight(image.getFitHeight() + 1 );
            image.setFitWidth(image.getFitWidth() + 1);
        }));
        timer.setCycleCount(20);
        timer.play();
    }

    private void startTimer2(){
        if(sunTimeline == null){
            sunTimeline = new Timeline(new KeyFrame(Duration.millis(7500), event -> makeSun() ));
            sunTimeline.setCycleCount(Timeline.INDEFINITE);
            sunTimeline.play();
        }
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

    public SunShroom(){
        price = 25;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new SunShroom(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop() {
        increasePointTimer.pause();
    }

    @Override
    public void play() {
        increasePointTimer.play();
    }

    @Override
    public void end() {
        if (increasePointTimer != null){
            increasePointTimer.stop();
        }
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
