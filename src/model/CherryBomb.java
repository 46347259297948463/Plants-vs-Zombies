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

public class CherryBomb extends BombPlants{

    private static Timeline timer;

    private Timeline cherryBombTimer;

    private Cell[][] cells;

    private static Group group;

    private static int availableNum;

    public CherryBomb(int i, int j) {
        super(i, j, 150, 15);
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
        ImageView imageView = new ImageView(getClass().getResource("/view/images/cherry bomb.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);

        if (!isOnSaveMode) {
            cherryBombTimer = new Timeline(
                    new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                    new KeyFrame(Duration.seconds(2), event -> BOMB())
            );
            cherryBombTimer.setCycleCount(1);
            cherryBombTimer.play();
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public CherryBomb(){
        price = 150;
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
        return new CherryBomb(row, column);
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
        int rows = cells.length;
        int columns = cells[0].length;

        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < columns) {
                    cells[i][j].removeAllZombies();
                }
            }
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
        if (cherryBombTimer != null){
            cherryBombTimer.pause();
        }
    }

    @Override
    public void stop(){
        if (cherryBombTimer != null) {
            cherryBombTimer.pause();
        }
    }

    @Override
    public void play() {
        if (cherryBombTimer != null) {
            cherryBombTimer.play();
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

    public Timeline getCherryBombTimer() {
        return cherryBombTimer;
    }

    public void setCherryBombTimer(double l) {
        if (l != -1) {
            cherryBombTimer = new Timeline(
                    new KeyFrame(Duration.seconds(0), event -> increaseSize()),
                    new KeyFrame(Duration.seconds(2), event -> BOMB())
            );
            cherryBombTimer.setCycleCount(1);
            cherryBombTimer.playFrom(Duration.seconds(l));
        }
    }
}
