package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.Group;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;


public class IceShroom extends BombPlants{

    private Timeline timer;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    private static Group group;

    private Timeline iceShroomTimer;

    private static int availableNum;

    public IceShroom(int i, int j){
        super(i, j, 75, 15);
        DayLevel.getInstance().setAvailablePicked(false,availableNum);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/Ice shroom.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
        iceShroomTimer = new Timeline(
                new KeyFrame(Duration.seconds(1),event -> BOMB()),
                new KeyFrame(Duration.seconds(4),event -> afterBomb())
        );
        iceShroomTimer.setCycleCount(1);
        iceShroomTimer.play();
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public IceShroom(){
        price = 75;
    }

    @Override
    protected void BOMB() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/Ice shroom sound.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        for (int i=0; i<5; i++){
            for(int j=0; j<9; j++){
                ArrayList<Zombie> zombies = cells[i][j].getZombies();
                for(Zombie z : zombies){
                    z.pause();
                }
            }
        }

        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }

    private void afterBomb(){
        for (int i=0; i<5; i++){
            for(int j=0; j<9; j++){
                ArrayList<Zombie> zombies = cells[i][j].getZombies();
                for(Zombie z : zombies){
                    z.play();
                }
            }
        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new IceShroom(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop() {
        iceShroomTimer.pause();
    }

    @Override
    public void play() {
        iceShroomTimer.play();
    }

    @Override
    public void end() {
        if (iceShroomTimer != null){
            iceShroomTimer.stop();
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
