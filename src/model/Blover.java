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

public class Blover extends Plants{

    private Timeline bloverTimer;

    private Timeline timer;

    private Cell[][] cells;

    private static Group group;

    private static int availableNum;

    private final static int HP = 4;

    private Clip clip;

    public Blover(int i, int j) {
        super(HP, i, j, 100, 15);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false,availableNum);
            cells = DayLevel.getInstance().getCells();
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false,availableNum);
            cells = NightLevel.getInstance().getCells();
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false,availableNum);
            cells = FogLevel.getInstance().getCells();
        }
        needCoffee = false;
        coffee = true;
        ImageView imageView = new ImageView(getClass().getResource("/view/images/blover.png").toString());
        imageView.setFitWidth(90);
        imageView.setFitHeight(120);
        imageView.setLayoutX(25);
        setImage(imageView);

        if (!isOnSaveMode) {
            bloverTimer = new Timeline(
                    new KeyFrame(Duration.seconds(3), event -> poof()),
                    new KeyFrame(Duration.seconds(13), event -> afterPoof())
            );
            bloverTimer.setCycleCount(1);
            bloverTimer.play();
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public Blover() {
        price = 100;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Blover(row, column);
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
        if (bloverTimer != null) {
            bloverTimer.pause();
        }
    }

    @Override
    public void play() {
        if (bloverTimer != null) {
            bloverTimer.play();
        }
    }

    @Override
    public void end() {
        if (bloverTimer != null) {
            bloverTimer.pause();
        }
    }

    @Override
    public Timeline getTimer() {
        return timer;
    }

    public static void setGroup(Group g) {
        group = g;
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    private void poof() {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/blover sound.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 4 ; j < 9 ; j++) {
                FogLevel.getInstance().removeCloud(cells[i][j]);
            }
        }
        cells[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }

    private void afterPoof() {
        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 4 ; j < 9 ; j++) {
                FogLevel.getInstance().setCloud(cells[i][j]);
            }
        }
        end();
    }

    public Timeline getBloverTimer() {
        return bloverTimer;
    }

    public void setBloverTimer(double l) {
        if (l != -1) {
            bloverTimer = new Timeline(
                    new KeyFrame(Duration.seconds(3), event -> poof()),
                    new KeyFrame(Duration.seconds(13), event -> afterPoof())
            );
            bloverTimer.setCycleCount(1);
            bloverTimer.playFrom(Duration.seconds(l));
        }
    }
}
