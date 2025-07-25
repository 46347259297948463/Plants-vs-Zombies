package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Doomshroom extends BombPlants{

    private Timeline timer;

    private Timeline doomshroomTimer;

    private Cell[][] cells = DayLevel.getInstance().getCells();

    private static Group group;

    private static int availableNum;

    private ImageView soil;

    public Doomshroom(int i, int j) {
        super(i, j, 125, 20);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
        ImageView imageView = new ImageView("/view/images/Doom shroom.png");
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
        doomshroomTimer = new Timeline(
                new KeyFrame(Duration.seconds(1.5), event -> BOMB()),
                new KeyFrame(Duration.seconds(16.5), event -> afterBOMB())
        );

    }

    private void afterBOMB() {
        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(soil);
        DayLevel.getInstance().getCells()[row][column].setAvailable(true);
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

        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
        cells[row][column].removePlant();
        soil = new ImageView("/view/images/after doom shroom bomb.png");
        soil.setFitWidth(120);
        soil.setFitHeight(130);
        DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(soil);
        DayLevel.getInstance().getCells()[row][column].setAvailable(false);
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Doomshroom(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop() {
        doomshroomTimer.pause();
    }

    @Override
    public void play() {
        doomshroomTimer.play();
    }

    @Override
    public void end() {
        if (doomshroomTimer != null){
            doomshroomTimer.stop();
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
