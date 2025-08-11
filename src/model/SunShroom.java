package model;

import controller.DayLevel;
import controller.FogLevel;
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

public class SunShroom extends Plants {

    private final static int HP = 4;

    private static int availableNum;

    private static Group group;

    private static Timeline timer;

    private Timeline sunTimeline;

    private Timeline increaseSizeTimer;

    private boolean isGrown = false;

    private boolean isEnded = false;

    private Sun sun;

    private int n;

    public SunShroom(int i, int j) {
        super(HP, i, j, 25, 5);

        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false, availableNum);
            needCoffee = true;
            coffee = false;
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false, availableNum);
            needCoffee = true;
            coffee = false;
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false, availableNum);
            needCoffee = true;
            coffee = false;
        }

        ImageView imageView = new ImageView(getClass().getResource("/view/images/sun shroom.png").toString());
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        imageView.setLayoutX(column + 30);
        imageView.setLayoutY(row + 50);
        setImage(imageView);

        if (obj instanceof NightLevel /*|| obj instanceof FogLevel*/) {
            Timeline growTimer = new Timeline(new KeyFrame(Duration.seconds(20), e -> isGrown = true));
            growTimer.setCycleCount(1);
            growTimer.play();

            increaseSizeTimer = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (isGrown) {
                    increaseSizeTimer.stop();
                    return;
                }
                image.setFitHeight(image.getFitHeight() * 1.005);
                image.setFitWidth(image.getFitWidth() * 1.005);
            }));
            increaseSizeTimer.setCycleCount(Timeline.INDEFINITE);
            increaseSizeTimer.play();

            startTimer();
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public SunShroom() {
        price = 25;
    }

    private void startTimer() {
        if (sunTimeline == null) {
            sunTimeline = new Timeline(new KeyFrame(Duration.seconds(7.5), event -> makeSun()));
            sunTimeline.setCycleCount(Timeline.INDEFINITE);
            sunTimeline.play();
        }
    }

    public void makeSun() {
        if (isDead() || isEnded) {
            sunTimeline.stop();
            return;
        }
        n++;
        sun = new Sun(row, column, 3, n);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(sun.getGroup());
        }

        sun.getButton().setOnAction(event -> {
            if ((obj instanceof DayLevel) && !DayLevel.getInstance().isStopMod) {
                DayLevel.getInstance().depositSunPoints(isGrown ? 25 : 15);
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
            } else if ((obj instanceof NightLevel) && !NightLevel.getInstance().isStopMod) {
                NightLevel.getInstance().depositSunPoints(isGrown ? 25 : 15);
                NightLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
            } else if ((obj instanceof FogLevel) && !FogLevel.getInstance().isStopMod) {
                FogLevel.getInstance().depositSunPoints(isGrown ? 25 : 15);
                FogLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(sun.getGroup());
            }
            playSunSound();
        });
    }

    private void playSunSound() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(getClass().getResource("/view/audio/sun sound.wav"));
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(6.0f);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
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
    public Plants clonePlant(int row, int column) {
        return new SunShroom(row, column);
    }

    @Override
    public void stop() {
        if (sunTimeline != null) sunTimeline.pause();
        if (increaseSizeTimer != null) increaseSizeTimer.pause();
        if (sun != null) sun.stop();
    }


    @Override
    public void play() {
        if (sunTimeline != null) sunTimeline.play();
        if (!isGrown && increaseSizeTimer != null && increaseSizeTimer.getStatus() == Timeline.Status.PAUSED) {
            increaseSizeTimer.play();
        }
        if (sun != null) sun.play();
    }

    @Override
    public void end() {
        isEnded = true;
        if (sunTimeline != null) sunTimeline.stop();
        if (increaseSizeTimer != null) increaseSizeTimer.stop();
        if (sun != null) sun.stop();
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

    public void setCoffee(boolean coffee) {
        this.coffee = coffee;
        if (coffee && increaseSizeTimer == null) {
            Timeline growTimer = new Timeline(new KeyFrame(Duration.seconds(20), e -> isGrown = true));
            growTimer.setCycleCount(1);
            growTimer.play();

            increaseSizeTimer = new Timeline(new KeyFrame(Duration.millis(200), e -> {
                if (isGrown) {
                    increaseSizeTimer.stop();
                    return;
                }
                image.setFitHeight(image.getFitHeight() * 1.005);
                image.setFitWidth(image.getFitWidth() * 1.005);
            }));
            increaseSizeTimer.setCycleCount(Timeline.INDEFINITE);
            increaseSizeTimer.play();

            startTimer();
        }
    }

}
