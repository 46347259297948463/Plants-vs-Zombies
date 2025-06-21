package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class SnowShooter extends PeaPlants{

    private final static int HP = 4;

    private final static int price = 175;

    private final static int bullets = 1;

    private final static double rechargeTime = 6;

    private Bullet bullet;

    private static boolean available = true;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double bulletRow = row;

    private double endRow;

    private Timeline halfSpeedTimer;

    public SnowShooter(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
        cells = DayLevel.getInstance().getCells();
        endRow = setZombie();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/snow shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        if (endRow != -1){
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(1) , event -> shoot(zombie)));
            shootTimer.setCycleCount(Timeline.INDEFINITE);
            shootTimer.play();
        }
    }
    public SnowShooter(){

    }

    @Override
    public void shoot(Zombie zombie) {
        endRow = setZombie();
        bulletRow = row;
        moveBulletTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> moveBullet(endRow)));
        moveBulletTimer.setCycleCount(Timeline.INDEFINITE);
        moveBulletTimer.play();
    }

    public void moveBullet(double endRow){
        if (bullet != null){
            bullet.endBullet();
        }
        bullet = new Bullet(bulletRow, column);
        DayLevel.getInstance().getDayAnc().getChildren().add(bullet.getImageView());
        bulletRow += 15;
        if (bulletRow >= endRow){
            moveBulletTimer.stop();
            zombie.takeDamage(1);
            zombie.setSpeed(zombie.getSpeed() / 2);
            halfSpeedTimer = new Timeline(new KeyFrame(Duration.millis(1500), event -> back()));
            halfSpeedTimer.setCycleCount(Timeline.INDEFINITE);
            halfSpeedTimer.play();
            bullet.endBullet();
        }
    }

    private void back(){
        if (zombie instanceof ImpZombie){
            zombie.setSpeed(8);
        }
        else {
            zombie.setSpeed(4);
        }
        halfSpeedTimer.stop();
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new SnowShooter(row, column);
    }

    private double setZombie(){
        int i = row , j = column;
        while (i < 9 && cells[j][i].getZombies() == null){
            i++;
        }
        if (i == 9){
            return -1;
        }
        ArrayList<Zombie> zombies = cells[j][i].getZombies();
        double min = 9;
        for (Zombie z : zombies){
            if (z.getRow() < min){
                zombie = z;
                min = z.getRow();
            }
        }
        return min * 80 + 250;
    }
}
