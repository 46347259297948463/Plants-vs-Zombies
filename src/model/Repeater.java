package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class Repeater extends PeaPlants{

    private final static int HP = 4;

    private final static int price = 200;

    private final static int bullets = 2;

    private final static double rechargeTime = 7;

    private Bullet bullet1;

    private Bullet bullet2;

    private static boolean available = true;

    private Zombie zombie1;

    private Zombie zombie2;

    private Cell[][] cells;

    private Timeline moveBulletTimer1;

    private Timeline moveBulletTimer2;

    private double bulletRow1 = row;

    private double bulletRow2 = row;

    private double endRow1;

    private double endRow2;

    public Repeater(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
        cells = DayLevel.getInstance().getCells();
        setZombie();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/repeater.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        if (endRow1 != -1){
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(2) , event -> shoot(zombie1)));
            shootTimer.setCycleCount(Timeline.INDEFINITE);
            shootTimer.play();
        }
    }

    public Repeater(){

    }

    @Override
    public void shoot(Zombie zombie) {
        setZombie();
        if (endRow1 == -1 || zombie1 == null || zombie1.isDead()) {
            return;
        } else if (endRow1 != -1) {
            bulletRow1 = row;
            if (moveBulletTimer1 != null) {
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet1.getImageView());
                moveBulletTimer1.stop();
            }
            bullet1 = new Bullet(bulletRow1, column);
            DayLevel.getInstance().getDayAnc().getChildren().add(bullet1.getImageView());
            moveBulletTimer1 = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet1()));
            moveBulletTimer1.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer1.play();
        }
        if (endRow2 == -1 || zombie2 == null || zombie2.isDead()) {
            return;
        } else if (endRow2 != -1) {
            bulletRow2 = row;
            if (moveBulletTimer2 != null) {
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet2.getImageView());
                moveBulletTimer2.stop();
            }
            bullet2 = new Bullet(bulletRow2, column + 0.5);
            DayLevel.getInstance().getDayAnc().getChildren().add(bullet2.getImageView());
            moveBulletTimer2 = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet2()));
            moveBulletTimer2.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer2.play();
        }
    }

    public void moveBullet1(){
        if (bullet1 != null){
            bullet1.move();
            if (bullet1.getImageView().getLayoutX() >= endRow1 && zombie1 != null){
                moveBulletTimer1.stop();
                zombie1.takeDamage(1);
                if (zombie1.isDead()){
                    zombie1.dead();
                    setZombie();
                }
                bullet1.endBullet();
            }
        }
    }

    public void moveBullet2(){
        if (bullet2 != null){
            bullet2.move();
            if (bullet2.getImageView().getLayoutX() >= endRow2 && zombie2 != null){
                moveBulletTimer2.stop();
                zombie2.takeDamage(1);
                if (zombie2.isDead()){
                    zombie2.dead();
                    setZombie();
                }
                bullet2.endBullet();
            }

        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Repeater(row, column);
    }

    private void setZombie(){
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
                    if (z.getColumn() < min1) {
                        zombie2 = zombie1;
                        min2 = min1;
                        zombie1 = z;
                        min1 = z.getColumn();
                    } else if (z.getColumn() < min2) {
                        zombie2 = z;
                        min2 = z.getColumn();
                    }
                }
            }
        }

        if (zombie1 != null) {
            endRow1 = min1;
        } else {
            endRow1 = -1;
        }

        if (zombie2 != null) {
            endRow2 = min2;
        } else {
            endRow2 = -1;
        }
    }
}
