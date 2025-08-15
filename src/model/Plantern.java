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

public class Plantern extends Plants{

    private Timeline planternTimer;

    private Timeline timer;

    private Cell[][] cells;

    private static Group group;

    private static int availableNum;

    private final static int HP = 4;

    private Clip clip;

    public Plantern(int i, int j) {
        super(HP, i, j, 25, 15);
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

        ImageView imageView = new ImageView(getClass().getResource("/view/images/plantern.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(150);
        imageView.setLayoutX(25);
        setImage(imageView);

        if (!isOnSaveMode) {
            planternTimer = new Timeline(new KeyFrame(Duration.seconds(3), event -> light()));
            planternTimer.setCycleCount(1);
            planternTimer.play();
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public Plantern() {
        price = 25;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Plantern(row, column);
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
        if (planternTimer != null) {
            planternTimer.pause();
        }
    }

    @Override
    public void play() {
        if (planternTimer != null && planternTimer.getStatus() == Timeline.Status.PAUSED) {
            planternTimer.play();
        }
    }

    @Override
    public void end() {
        if (planternTimer != null) {
            planternTimer.pause();
        }
        for (int i = 0 ; i < 5 ; i++) {
            for (int j = 4 ; j < 9 ; j++) {
                if (cells[i][j].getCloudImage() == null) {
                    FogLevel.getInstance().setCloud(cells[i][j]);
                }
            }
        }
        cells[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
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

    private void light() {

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/plantern sound.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int rows = cells.length;
        int columns = cells[0].length;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns) {
                    FogLevel.getInstance().removeCloud(cells[i][j]);
                }
            }
        }
    }

    public Timeline getPlanternTimer() {
        return planternTimer;
    }

    public void setPlanternTimer(double l) {
        if (l != -1) {
            planternTimer = new Timeline(new KeyFrame(Duration.seconds(3), event -> light()));
            planternTimer.setCycleCount(1);
            planternTimer.playFrom(Duration.seconds(l));
        }
    }

}
