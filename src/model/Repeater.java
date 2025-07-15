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

public class Repeater extends PeaPlants{

    private final static int HP = 4;

    private final static int bullets = 2;

    private Bullet bullet1;

    private Bullet bullet2;

    private Zombie zombie1;

    private Zombie zombie2;

    private Cell[][] cells;

    private Timeline moveBulletTimer1;

    private Timeline moveBulletTimer2;

    private double endRow1;

    private double endRow2;

    private Timeline timer;

    private static Group group;

    private static int availableNum;

    public Repeater(int i, int j) {
        super(HP, i, j, 200, bullets, 10);
        DayLevel.getInstance().setAvailablePicked(false, availableNum);
        cells = DayLevel.getInstance().getCells();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/repeater.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        shootTimer = new Timeline(new KeyFrame(Duration.seconds(2) , event -> shoot(zombie1)));
        shootTimer.setCycleCount(Timeline.INDEFINITE);
        shootTimer.play();
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public Repeater(){
        price = 200;
    }

    private void moveBullet1(){
        if (bullet1 != null){
            bullet1.move();
            if (bullet1.getImageView().getLayoutX() >= endRow1 && zombie1 != null){
                moveBulletTimer1.stop();
                zombie1.takeDamage(1);
                if (zombie1.isDead()){
                    zombie1.dead();
                    findZombie();
                }
                bullet1.endBullet();
            }
        }
    }

    private void moveBullet2(){
        if (bullet2 != null){
            bullet2.move();
            if (bullet2.getImageView().getLayoutX() >= endRow2 && zombie2 != null){
                moveBulletTimer2.stop();
                zombie2.takeDamage(1);
                if (zombie2.isDead()){
                    zombie2.dead();
                    findZombie();
                }
                bullet2.endBullet();
            }

        }
    }

    private void findZombie() {
        int j = row;
        zombie1 = null;
        zombie2 = null;
        double min1 = Double.MAX_VALUE;
        double min2 = Double.MAX_VALUE;

        for (int i = column; i < 9; i++) {
            ArrayList<Zombie> zombies = cells[j][i].getZombies();
            if (zombies != null && !zombies.isEmpty()) {
                for (Zombie z : zombies) {
                    if (z.isDead()) continue;

                    double zombieX = z.getColumn();
                    if (zombieX < min1) {
                        // شیفت زامبی اول به دوم
                        zombie2 = zombie1;
                        min2 = min1;

                        zombie1 = z;
                        min1 = zombieX;
                    } else if (zombieX < min2 && z != zombie1) {
                        zombie2 = z;
                        min2 = zombieX;
                    }
                }
            }
        }

        if (zombie1 != null) {
            endRow1 = min1;
        } else {
            endRow1 = -1;
        }

        if (zombie2 != null && zombie1.getHP() == 1) {
            endRow2 = min2;
        } else {
            zombie2 = zombie1;
            endRow2 = min1;
        }
    }

    @Override
    public void shoot(Zombie zombie) {
        findZombie();

        if (zombie1 != null && !zombie1.isDead()) {
            if (moveBulletTimer1 != null) {
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet1.getImageView());
                moveBulletTimer1.stop();
            }
            bullet1 = new Bullet(row, column);
            try {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(
                        getClass().getResource("/view/audio/hit sound.wav")
                );
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.loop(1);
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            DayLevel.getInstance().getDayAnc().getChildren().add(bullet1.getImageView());
            moveBulletTimer1 = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet1()));
            moveBulletTimer1.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer1.play();
        }

        if (zombie2 != null && !zombie2.isDead()) {
            if (moveBulletTimer2 != null) {
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet2.getImageView());
                moveBulletTimer2.stop();
            }
            bullet2 = new Bullet(row, column + 0.5);
            DayLevel.getInstance().getDayAnc().getChildren().add(bullet2.getImageView());
            moveBulletTimer2 = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet2()));
            moveBulletTimer2.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer2.play();
        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Repeater(row, column);
    }

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, availableNum);
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop(){
        if (moveBulletTimer1 != null){
            moveBulletTimer1.pause();
        }
        if (moveBulletTimer2 != null){
            moveBulletTimer2.pause();
        }
        shootTimer.pause();
    }

    @Override
    public void play(){
        if (moveBulletTimer1 != null){
            moveBulletTimer1.play();
        }
        if (moveBulletTimer2 != null){
            moveBulletTimer2.play();
        }
        shootTimer.play();
    }

    public static void setAvailableNum(int a) {
        availableNum = a;
    }

    public static void setGroup(Group g) {
        group = g;
    }

}
