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

public class GraveBuster extends Plants{

    private final static int HP = 4;

    private static Timeline timer;

    private static Group group;

    private static int availableNum;

    private Timeline busterTimer;

    private Cell[][] cells;

    private int y = -25;

    private Clip clip;

    private int n = 0;

    public GraveBuster(int i, int j) {
        super(HP, i, j, 75, 8);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = DayLevel.getInstance().getCells();
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = NightLevel.getInstance().getCells();
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = FogLevel.getInstance().getCells();
        }
        ImageView imageView = new ImageView(getClass().getResource("/view/images/grave buster.png").toString());
        imageView.setFitWidth(175);
        imageView.setFitHeight(145);
        imageView.setLayoutY(y);
        imageView.setLayoutX(-10);
        setImage(imageView);

        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/grave buster sound.wav")
            );
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!isOnSaveMode) {
            busterTimer = new Timeline(new KeyFrame(Duration.seconds(0.75),event -> eat()));
            busterTimer.setCycleCount(5);
            busterTimer.play();
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    private void eat() {
        n++;
        if (n == 5) {
            y += 2;
            image.setLayoutY(y);
            cells[row][column].getGroup().getChildren().remove(cells[row][column].getGraveImage());
            cells[row][column].setGrave(false);
            cells[row][column].setAvailable(true);
            cells[row][column].getGroup().getChildren().remove(this.image);
            cells[row][column].setPlants(null);
            if (obj instanceof NightLevel) {
                NightLevel.getInstance().X[cells[row][column].getNumberOfGrave()] = -1;
                NightLevel.getInstance().Y[cells[row][column].getNumberOfGrave()] = -1;
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().X[cells[row][column].getNumberOfGrave()] = -1;
                FogLevel.getInstance().Y[cells[row][column].getNumberOfGrave()] = -1;
            }
        } else {
            y += 2;
            image.setLayoutY(y);
        }
    }

    public GraveBuster() {
        price = 75;
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new GraveBuster(row, column);
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
        busterTimer.pause();
        clip.stop();
    }

    @Override
    public void play() {
        busterTimer.play();
        clip.start();
    }

    @Override
    public void end() {
        if (busterTimer != null) {
            busterTimer.pause();
        }
        clip.stop();

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

    public Timeline getBusterTimer() {
        return busterTimer;
    }

    public void setBusterTimer(double l) {
        if (l != -1) {
            busterTimer = new Timeline(new KeyFrame(Duration.seconds(0.75),event -> eat()));
            busterTimer.setCycleCount(5);
            busterTimer.playFrom(Duration.seconds(l));
        }
    }
}
