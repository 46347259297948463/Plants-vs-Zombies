package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
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

    private static Timeline timer;

    private Cell[][] cells;

    private static Group group;

    private Timeline iceShroomTimer;

    private static int availableNum;

    public IceShroom(int i, int j){
        super(i, j, 75, 25);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = DayLevel.getInstance().getCells();
            needCoffee = true;
            coffee = false;
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = NightLevel.getInstance().getCells();
            needCoffee = false;
            coffee = true;
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = FogLevel.getInstance().getCells();
            needCoffee = false;
            coffee = true;
        }
        ImageView imageView = new ImageView(getClass().getResource("/view/images/Ice shroom.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);

        if (obj instanceof NightLevel || obj instanceof FogLevel) {
            if (!isOnSaveMode) {
                iceShroomTimer = new Timeline(
                        new KeyFrame(Duration.seconds(1),event -> BOMB()),
                        new KeyFrame(Duration.seconds(11),event -> afterBomb())
                );
                iceShroomTimer.setCycleCount(1);
                iceShroomTimer.play();
            }
        }

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

        for (int i = 0 ; i < 5 ; i++){
            for(int j = 0 ; j < 9 ; j++){
                ArrayList<Zombie> zombies = cells[i][j].getZombies();
                if (zombies != null) {
                    for(Zombie z : zombies){
                        z.stop();
                    }
                }
            }
        }

        cells[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
    }

    private void afterBomb(){
        for (int i = 0 ; i < 5; i++){
            for(int j = 0; j < 9; j++){
                ArrayList<Zombie> zombies = cells[i][j].getZombies();
                if (zombies != null) {
                    for (Zombie z : zombies) {
                        z.play();
                    }
                }
            }
        }
        iceShroomTimer.stop();
        end();
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new IceShroom(row, column);
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
        if (iceShroomTimer != null) {
            iceShroomTimer.pause();
        }
    }

    @Override
    public void play() {
        if (iceShroomTimer != null) {
            iceShroomTimer.play();
        }
    }

    @Override
    public void end() {
        if (iceShroomTimer != null){
            iceShroomTimer.pause();
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

    public void setCoffee(boolean coffee) {
        this.coffee = coffee;
        if (coffee && iceShroomTimer == null) {
            if (!isOnSaveMode) {
                iceShroomTimer = new Timeline(
                        new KeyFrame(Duration.seconds(1),event1 -> BOMB()),
                        new KeyFrame(Duration.seconds(5),event2 -> afterBomb())
                );
                iceShroomTimer.setCycleCount(1);
                iceShroomTimer.play();
            }
        }
    }

    public Timeline getIceShroomTimer() {
        return iceShroomTimer;
    }

    public void setIceShroomTimer(double l) {
        if (l != -1) {
            iceShroomTimer = new Timeline(
                    new KeyFrame(Duration.seconds(1),event1 -> BOMB()),
                    new KeyFrame(Duration.seconds(5),event2 -> afterBomb())
            );
            iceShroomTimer.setCycleCount(1);
            iceShroomTimer.playFrom(Duration.seconds(l));
        }
    }
}
