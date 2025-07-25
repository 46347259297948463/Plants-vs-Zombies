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
import java.util.ArrayList;

public class ScaredyShroom extends PeaPlants{

    private final static int HP = 4;

    private final static int bullets = 1;

    private Bullet bullet;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double endrow;

    private static Timeline timer;

    private static Group group;

    private static int availableNum;

    private boolean scared = false;

    private ImageView scaredImg;

    public ScaredyShroom(int i, int j) {
        super(HP, i, j, 25 , bullets, 10);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
        cells = DayLevel.getInstance().getCells();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/scaredy shroom.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        scaredImg = new ImageView(getClass().getResource("/view/images/scare scaredy.png").toString());
        scaredImg.setFitWidth(120);
        scaredImg.setFitHeight(125);
        setImage(imageView);
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(2), event -> shoot(zombie)));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();

    }

    public ScaredyShroom(){
        price = 25;
    }

    @Override
    protected void shoot(Zombie zombie) {
        endrow = findZombie();
        if (zombie.columnBTN <= column + 1 && zombie.columnBTN >= column - 1){
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(this.image);
            scared = true;
            DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(scaredImg);
        }
        else {
            if (scared) {
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().remove(scaredImg);
                DayLevel.getInstance().getCells()[row][column].getGroup().getChildren().add(this.image);
                scared = false;
            }
            if(endrow == -1 || zombie == null || zombie.isDead()) {
                return;
            }else if(endrow != -1) {
                if (moveBulletTimer != null) {
                    DayLevel.getInstance().getDayAnc().getChildren().remove(bullet.getImageView());
                    moveBulletTimer.stop();
                }
                bullet = new PuffBullet(row, column);
                try {
                    AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                            getClass().getResource("/view/audio/hit sound.wav")
                    );
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioStream);
                    clip.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DayLevel.getInstance().getDayAnc().getChildren().add(bullet.getImageView());
                moveBulletTimer = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet()));
                moveBulletTimer.setCycleCount(Timeline.INDEFINITE);
                moveBulletTimer.play();
            }
        }
    }

    private double findZombie() {
        int j = row;
        zombie = null;
        double min = Double.MAX_VALUE;

        for (int i = column; i < 9; i++) {
            ArrayList<Zombie> zombies = cells[j][i].getZombies();
            if (zombies != null && !zombies.isEmpty()) {
                for (Zombie z : zombies) {
                    if (z.isDead()) continue;
                    if (z.getColumn() < min) {
                        zombie = z;
                        min = z.getColumn();
                    }
                }
            }
        }

        if (zombie != null) {
            return min;
        }
        return -1;
    }

    private void moveBullet() {
        if (bullet != null){
            bullet.move();
            endrow = findZombie();
            if(bullet.getImageView().getLayoutX() >= endrow && zombie != null){
                endrow = findZombie();
                moveBulletTimer.stop();
                zombie.takeDamage(1);
                if(zombie.isDead()){
                    zombie.dead();
                    endrow = findZombie();
                }
                bullet.endBullet();
            }
        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new PuffShroom(row,column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop() {
        if (moveBulletTimer != null){
            moveBulletTimer.pause();
        }
        if (shootTimer != null) {
            shootTimer.pause();
        }
    }

    @Override
    public void play() {
        if (moveBulletTimer != null){
            moveBulletTimer.play();
        }
        if (shootTimer != null) {
            shootTimer.play();
        }
    }

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
