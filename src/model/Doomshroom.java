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

    public Doomshroom(int i, int j) {
        super(i, j, 125, 20);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
        ImageView imageView = new ImageView("/view/images/Doom shroom.png");
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);

    }

}
