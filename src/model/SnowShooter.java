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

public class SnowShooter extends PeaPlants{

    private final static int HP = 4;

    private final static int bullets = 1;

    private SnowBullet bullet;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double endRow;

    private Timeline halfSpeedTimer;

    private static Timeline timer;

    private static Group group;

    private static int availableNum;

    public SnowShooter(int i, int j) {
        super(HP, i, j, 175, bullets, 9);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
        cells = DayLevel.getInstance().getCells();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/snow shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(2) , event -> shoot(zombie)));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public SnowShooter(){
        price = 175;
    }

    private void moveBullet(){
        if (bullet != null) {
            bullet.move();
        }
        if (bullet.getImageView().getLayoutX() >= endRow && zombie != null){
            moveBulletTimer.stop();
            zombie.takeDamage(1);
            if (zombie.isDead()){
                zombie.dead();
                findZombie();
            } else {
                zombie.setSpeed(zombie.getSpeed() / 2);
                halfSpeedTimer = new Timeline(new KeyFrame(Duration.millis(1000), event -> back()));
                halfSpeedTimer.setCycleCount(Timeline.INDEFINITE);
                halfSpeedTimer.play();
            }
            bullet.endBullet();
        }
    }

    private void back(){
       if (zombie != null){
           if (zombie instanceof ImpZombie){
               zombie.setSpeed(4);
           }
           else {
               zombie.setSpeed(2);
           }
           halfSpeedTimer.stop();
       }
    }

    private double findZombie(){
        int j = row;
        zombie = null;
        double min = Double.MAX_VALUE;

        for (int i = column; i < 9; i++){
            ArrayList<Zombie> zombies = cells[j][i].getZombies();
            if (zombies != null && !zombies.isEmpty()){
                for (Zombie z : zombies){
                    if (z.isDead()) continue;
                    if (z.getColumn() < min){
                        zombie = z;
                        min = z.getColumn();
                    }
                }
            }
        }

        if (zombie != null){
            return min;
        }
        return -1;
    }

    @Override
    protected void shoot(Zombie zombie) {
        endRow = findZombie();
        if (endRow == -1 || zombie == null || zombie.isDead()) {
            return;
        } else if (endRow != -1) {
            if (moveBulletTimer != null){
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet.getImageView());
                moveBulletTimer.stop();
            }
            bullet = new SnowBullet(row, column);
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

    @Override
    public Plants clonePlant(int row, int column) {
        return new SnowShooter(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop(){
        if (moveBulletTimer != null){
            moveBulletTimer.pause();
        }
        shootTimer.pause();
    }

    @Override
    public void play(){
        if (moveBulletTimer != null){
            moveBulletTimer.play();
        }
        shootTimer.play();
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
