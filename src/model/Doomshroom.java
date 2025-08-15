package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Doomshroom extends BombPlants{

    private Timeline timer;

    private Timeline doomshroomTimer;

    private Cell[][] cells;

    private static Group group;

    private static int availableNum;

    public Doomshroom(int i, int j) {
        super(i, j, 125, 30);
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
        ImageView imageView = new ImageView("/view/images/Doom shroom.png");
        imageView.setFitWidth(115);
        imageView.setFitHeight(120);
        setImage(imageView);

        if (obj instanceof NightLevel || obj instanceof FogLevel) {
           if (!isOnSaveMode) {
               doomshroomTimer = new Timeline(
                       new KeyFrame(Duration.seconds(1.5), event1 -> BOMB()),
                       new KeyFrame(Duration.seconds(16.5), even2 -> afterBOMB())
               );
               doomshroomTimer.setCycleCount(1);
               doomshroomTimer.play();
           }
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public Doomshroom() {
        price = 125;
    }

    private void afterBOMB() {
        cells[row][column].getGroup().getChildren().remove(image);
        cells[row][column].setAvailable(true);
        cells[row][column].removePlant();
    }

    @Override
    protected void BOMB() {
        try {
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                    getClass().getResource("/view/audio/cherrybomb sound.wav")
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int columns = cells[0].length;

        for (int i = 0; i < 5 ; i++) {
            for (int j = column - 3; j <= column + 3; j++) {
                if  (j >= 0 && j < columns) {
                    cells[i][j].removeAllZombies();
                }
            }
        }

        image.setImage(new Image(getClass().getResource("/view/images/after doom shroom bomb.png").toString()));
        image.setFitWidth(135);
        image.setFitHeight(105);
        cells[row][column].setAvailable(false);
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Doomshroom(row, column);
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
        if (doomshroomTimer != null) {
            doomshroomTimer.pause();
        }
    }

    @Override
    public void play() {
        if (doomshroomTimer != null) {
            doomshroomTimer.play();
        }
    }

    @Override
    public void end() {
        if (doomshroomTimer != null){
            doomshroomTimer.pause();
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
        if (coffee && doomshroomTimer == null) {
            doomshroomTimer = new Timeline(
                    new KeyFrame(Duration.seconds(1.5), event1 -> BOMB()),
                    new KeyFrame(Duration.seconds(16.5), even2 -> afterBOMB())
            );
            doomshroomTimer.setCycleCount(1);
            doomshroomTimer.play();
        }
    }

    public void setDoomshroomTimer(double l) {
        if (l != -1) {
            doomshroomTimer = new Timeline(
                    new KeyFrame(Duration.seconds(1.5), event1 -> BOMB()),
                    new KeyFrame(Duration.seconds(16.5), even2 -> afterBOMB())
            );
            doomshroomTimer.setCycleCount(1);
            doomshroomTimer.playFrom(Duration.seconds(l));
        }
    }

    public Timeline getDoomshroomTimer() {
        return doomshroomTimer;
    }
}
