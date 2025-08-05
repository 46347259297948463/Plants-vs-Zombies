package model;

import controller.DayLevel;
import controller.NightLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.util.Arrays;

public class SunShroom extends Plants {

    private final static int HP = 4;

    private Timeline sunTimeline;

    private Timeline startTimer;

    private Timeline increaseSizeTimer;

    private Timeline timer;

    private static Group group;

    private Sun sun;

    private static int availableNum;

    private boolean isStartTwo = false;

    private  int n = 0;

    public SunShroom(int i, int j){
        super(HP, i, j, 25, 5);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false,availableNum);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false,availableNum);
        }
        ImageView imageView = new ImageView(getClass().getResource("/view/images/sun shroom.png").toString());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setLayoutX(column + 30);
        imageView.setLayoutY(row + 50);
        setImage(imageView);
        group.setOpacity(0.7);
        startTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> startTimer(false)),

                new KeyFrame(Duration.seconds(20), event -> startTimer(true))
        );
        startTimer.setCycleCount(1);
        startTimer.play();

        increaseSizeTimer = new Timeline(new KeyFrame(Duration.millis(200), event -> {
            if (isStartTwo) {
                increaseSizeTimer.stop();
            }
            image.setFitHeight(image.getFitHeight() * 1.005 );
            image.setFitWidth(image.getFitWidth() * 1.005);
        }));
        increaseSizeTimer.setCycleCount(Timeline.INDEFINITE);
        increaseSizeTimer.play();

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public SunShroom(){
        price = 25;
    }

    private void startTimer(boolean bool){
        isStartTwo = bool;
        sunTimeline = new Timeline(new KeyFrame(Duration.millis(7500), event -> makeSun()));
        sunTimeline.setCycleCount(Timeline.INDEFINITE);
        sunTimeline.play();
    }

    public void makeSun() {
        if (isDead()) {
            sunTimeline.stop();
        } else {
            n++;
            sun = new Sun(row, column, 3, n);

            if (obj instanceof DayLevel) {
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
                System.out.println("row: " + row + ", column: " + column);
                System.out.println("DayLevel: " + DayLevel.getInstance());
                System.out.println("Cells: " + Arrays.deepToString(DayLevel.getInstance().getCells()));
                System.out.println("Cell: " + DayLevel.getInstance().getCells()[row][column]);
                sun.getButton().setOnAction(event -> {
                    if (!DayLevel.getInstance().isStopMod) {
                        if (isStartTwo) {
                            DayLevel.getInstance().depositSunPoints(25);
                        } else {
                            DayLevel.getInstance().depositSunPoints(15);
                        }
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
            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
                System.out.println("row: " + row + ", column: " + column);
                System.out.println("NightLevel: " + NightLevel.getInstance());
                System.out.println("Cells: " + Arrays.deepToString(NightLevel.getInstance().getCells()));
                System.out.println("Cell: " + NightLevel.getInstance().getCells()[row][column]);
                sun.getButton().setOnAction(event -> {
                    if (!NightLevel.getInstance().isStopMod) {
                        if (isStartTwo) {
                            NightLevel.getInstance().depositSunPoints(25);
                        } else {
                            NightLevel.getInstance().depositSunPoints(15);
                        }
                        NightLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
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
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new SunShroom(row, column);
    }

    @Override
    protected void recharge() {
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(true, availableNum);
        }
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop() {
        startTimer.pause();
        sunTimeline.pause();
        increaseSizeTimer.pause();
    }

    @Override
    public void play() {
        startTimer.play();
        sunTimeline.play();
        increaseSizeTimer.play();
    }

    @Override
    public void end() {
        if (startTimer != null){
            startTimer.stop();
        }
        if (sunTimeline != null) {
            sunTimeline.stop();
        }
        if (increaseSizeTimer != null) {
            increaseSizeTimer.stop();
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
