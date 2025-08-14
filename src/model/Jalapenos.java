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

public class Jalapenos extends BombPlants{

    private Cell[][] cells;

    private static Timeline timer;

    private Timeline jalopenosTimer;

    private static Group group;

    private static int availableNum;

    public Jalapenos(int i, int j) {
        super(i, j, 125, 18);
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
        ImageView imageView = new ImageView(getClass().getResource("/view/images/jalapenos.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);

        if (!isOnSaveMode) {
            jalopenosTimer = new Timeline(
                    new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                    new KeyFrame(Duration.seconds(2) , event -> BOMB())
            );
            jalopenosTimer.setCycleCount(1);
            jalopenosTimer.play();
        }

        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public Jalapenos(){
        price = 125;
    }

    private void increaseSize(){
        Timeline timer = new Timeline(new KeyFrame(Duration.millis(100), event -> {
            image.setFitHeight(image.getFitHeight() + 1 );
            image.setFitWidth(image.getFitWidth() + 1);
        }));
        timer.setCycleCount(20);
        timer.play();
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Jalapenos(row, column);
    }

    @Override
    protected void BOMB() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/jalapeno sound.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int j = 0 ; j < 9 ; j++){
            cells[row][j].removeAllZombies();
        }

        cells[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
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
    public void end() {
        if (jalopenosTimer != null){
            jalopenosTimer.pause();
        }
    }

    @Override
    public void stop() {
        jalopenosTimer.pause();
    }

    @Override
    public void play() {
        jalopenosTimer.play();
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

    public Timeline getJalopenosTimer() {
        return jalopenosTimer;
    }

    public void setJalopenosTimer(double l) {
        jalopenosTimer = new Timeline(
                new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                new KeyFrame(Duration.seconds(2) , event -> BOMB())
        );
        jalopenosTimer.setCycleCount(1);
        jalopenosTimer.playFrom(Duration.seconds(l));
    }
}
