package model;

import controller.DayLevel;
import controller.NightLevel;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class Zombie {

    protected double row;

    protected double column;

    private double HP = 5;

    private int speed = 2;

    private ImageView image;

    private Timeline moveTimeline;

    private int i = 0;

    private double limitColumn;

    private Timeline eatTimeline;

    public int rowBTN;

    public int columnBTN = 8;

    private double currentWidthBTN;

    private Timeline updateTimer;

    private Clip clip;

    private static boolean lose = false;

    public static Object obj;

    private Cell[][] cells;

    private int isHypnotized = 1;

    private int iHypnotized;

    private int jHypnotized;

    private Zombie eatenZombie;

    public Zombie(double x, double y, int rowBTN){
        if (obj instanceof DayLevel) {
            cells = DayLevel.getInstance().getCells();
        } else if (obj instanceof NightLevel) {
            cells = NightLevel.getInstance().getCells();
        }
        this.column = x;
        this.row = y;
        this.rowBTN = rowBTN;

        setImageOnAnc();

        startMove();

        updateTimer = new Timeline(new KeyFrame(Duration.millis(10), event -> update()));
        updateTimer.setCycleCount(Timeline.INDEFINITE);
        updateTimer.play();
    }

    public void hypnotize() {
        isHypnotized *= -1;
        iHypnotized = columnBTN;
        jHypnotized = rowBTN;
        if (isHypnotized == 1) {
            image.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        } else if (isHypnotized == -1) {
            image.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        }
    }


    protected void setImageOnAnc(){
        Image image = new Image(getClass().getResource("/view/images/normalZombies.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
        imageV.setLayoutX(column);
        imageV.setLayoutY(row);
        imageV.setFitHeight(220);
        imageV.setFitWidth(165);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().getDayAnc().getChildren().add(imageV);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().getNightAnc().getChildren().add(imageV);
        }
    }

    private void startMove(){
        if(moveTimeline == null){
            currentWidthBTN = cells[rowBTN][columnBTN].getButton().getWidth();
            limitColumn = column - currentWidthBTN;
            moveTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> move()));
            moveTimeline.setCycleCount(Timeline.INDEFINITE);
            moveTimeline.play();
        }
    }

    private void stopMove(){
        if(moveTimeline != null){
            moveTimeline.stop();
            moveTimeline = null;
        }
        if (eatTimeline != null) {
            eatTimeline.stop();
            eatTimeline = null;
        }
        if (clip != null) {
            clip.stop();
        }
    }

    private void move(){
        if (columnBTN > -1 && columnBTN < 9){
            if (!eat() && !eatZombie()){
                column -= (isHypnotized * (speed/2));
                image.setLayoutX(column);
            } else if (eat() && (cells[rowBTN][columnBTN].getPlant() instanceof HypnoShroom) && iHypnotized == columnBTN
                    && jHypnotized == rowBTN && isHypnotized == -1) {
                column -= (isHypnotized * (speed/2));
                image.setLayoutX(column);
            } else {
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                            getClass().getResource("/view/audio/eating sound.wav")
                    );
                    clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                moveTimeline.stop();
                clip.start();
                if (eatZombie()) {
                    clip.start();
                    eatTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                        if(eatenZombie != null){
                            eatenZombie.takeDamage(1);
                            if (eatenZombie.isDead()){
                                eatenZombie.dead();
                                eatenZombie = null;
                                eatTimeline.stop();
                                moveTimeline = null;
                                eatTimeline = null;
                                clip.stop();
                                startMove();
                            }
                        } else {
                            eatTimeline.stop();
                            moveTimeline = null;
                            clip.stop();
                            startMove();
                        }
                    }));
                    eatTimeline.setCycleCount(Timeline.INDEFINITE);
                    eatTimeline.play();
                } else {
                    if (cells[rowBTN][columnBTN].getPlant() instanceof HypnoShroom) {
                        if (iHypnotized != columnBTN && jHypnotized != rowBTN) {
                            eatTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                                Plants plant = cells[rowBTN][columnBTN].getPlant();
                                if(plant != null){
                                    plant.takeDamage(1);
                                    if (plant.isDead()){
                                        cells[rowBTN][columnBTN].getGroup().getChildren().remove(plant.getImage());
                                        cells[rowBTN][columnBTN].removePlant();
                                        plant.end();
                                    }
                                    eatTimeline.stop();
                                    moveTimeline = null;
                                    eatTimeline = null;
                                    clip.stop();
                                    hypnotize();
                                    startMove();
                                } else {
                                    eatTimeline.stop();
                                    eatTimeline = null;
                                    moveTimeline = null;
                                    clip.stop();
                                    startMove();
                                }
                            }));
                            eatTimeline.setCycleCount(1);
                            eatTimeline.play();
                        } else {
                            moveTimeline = null;
                            startMove();
                        }
                    } else {
                        clip.start();
                        eatTimeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
                            Plants plant = cells[rowBTN][columnBTN].getPlant();
                            if(plant != null){
                                plant.takeDamage(1);
                                if (plant.isDead()){
                                    cells[rowBTN][columnBTN].getGroup().getChildren().remove(plant.getImage());
                                    cells[rowBTN][columnBTN].removePlant();
                                    plant.end();
                                    eatTimeline.stop();
                                    moveTimeline = null;
                                    eatTimeline = null;
                                    clip.stop();
                                    startMove();
                                }
                            } else {
                                eatTimeline.stop();
                                moveTimeline = null;
                                clip.stop();
                                startMove();
                            }
                        }));
                        eatTimeline.setCycleCount(Timeline.INDEFINITE);
                        eatTimeline.play();
                    }
                }
            }
        }
    }

    private void update() {
        if (limitColumn > column) {
            if (columnBTN >= 0 && columnBTN < 9) {
                cells[rowBTN][columnBTN].removeZombie(this);
            }
            columnBTN -= isHypnotized;
            if (columnBTN < 0) {
                if (!lose){
                    if (obj instanceof DayLevel) {
                        DayLevel.getInstance().lose();
                    } else if (obj instanceof NightLevel) {
                        NightLevel.getInstance().lose();
                    }
                    lose = true;
                }
                return;
            } else if (column > 1780) {
                if (isHypnotized == -1) {
                    dead();
                    System.out.println("Dead!");
                    System.out.println("columnBTN = " + columnBTN);
                    return;
                }
            }

            currentWidthBTN = cells[rowBTN][columnBTN].getButton().getWidth();
            limitColumn = column - (isHypnotized * currentWidthBTN);
            cells[rowBTN][columnBTN].setZombies(this);

        }
        if (isDead()) {
            stopMove();
            dead();
            return;
        }
        image.setLayoutX(column);
    }

    private boolean eat(){
        return cells[rowBTN][columnBTN].getPlant() != null;
    }

    private boolean eatZombie(){
        if (findZombie()) {
            if (isHypnotized != eatenZombie.isHypnotized) {
                return true;
            }
        }
        return false;
    }

    private boolean findZombie() {
        int j = rowBTN;
        eatenZombie = null;

        if (isHypnotized == -1) {
            double min = Double.MAX_VALUE;
            for (int i = columnBTN; i < 9; i++) {
                ArrayList<Zombie> zombies = cells[j][i].getZombies();
                if (zombies != null && !zombies.isEmpty()) {
                    for (Zombie z : zombies) {
                        if (z.isDead()) continue;
                        if (z.getColumn() < min) {
                            eatenZombie = z;
                            min = z.getColumn();
                        }
                    }
                }
            }
        } else {
            double min = Double.MIN_VALUE;
            for (int i = columnBTN; i >= 0; i--) {
                ArrayList<Zombie> zombies = cells[j][i].getZombies();
                if (zombies != null && !zombies.isEmpty()) {
                    for (Zombie z : zombies) {
                        if (z.isDead()) continue;
                        if (z.getColumn() > min) {
                            eatenZombie = z;
                            min = z.getColumn();
                        }
                    }
                }
            }
        }

        if (eatenZombie != null) {
            return true;
        }
        return false;
    }


    public void takeDamage(double damage){
        HP -= damage;
    }

    public boolean isDead(){
        return HP <= 0;
    }

    public void dead(){
        cells[rowBTN][columnBTN].removeZombie(this);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().getDayAnc().getChildren().remove(this.image);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().getNightAnc().getChildren().remove(this.image);
        }
        if (updateTimer != null) {
            updateTimer.stop();
        }
        if (moveTimeline != null) {
            moveTimeline.stop();
        }
        if (eatTimeline != null) {
            eatTimeline.stop();
        }
        if (clip != null) {
            clip.stop();
            clip.close();
            clip = null;
        }

    }

    public void stop() {
        stopMove();
    }

    public void play() {
        startMove();
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHP(double HP) {
        this.HP = HP;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public double getRow() {
        return row;
    }

    public double getColumn() {
        return column;
    }

    public int getSpeed() {
        return speed;
    }

    public double getHP() {
        return HP;
    }

    public ImageView getImage() {
        return image;
    }

}