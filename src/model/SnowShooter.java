package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;

public class SnowShooter extends PeaPlants{

    private final static int HP = 4;

    private final static int bullets = 1;

    private final static double rechargeTime = 6;

    private SnowBullet bullet;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double endRow;

    private Timeline halfSpeedTimer;

    private Timeline timer;

    public SnowShooter(int i, int j, Group group) {
        super(HP, i, j, 175, bullets, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
        cells = DayLevel.getInstance().getCells();
        endRow = setZombie();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/snow shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        if (endRow != -1){
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(2) , event -> shoot(zombie)));
            shootTimer.setCycleCount(Timeline.INDEFINITE);
            shootTimer.play();
        }
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public SnowShooter(){
        price = 175;
    }

    @Override
    public void shoot(Zombie zombie) {
        endRow = setZombie();
        if (endRow == -1 || zombie == null || zombie.isDead()) {
            return;
        } else if (endRow != -1) {
            if (moveBulletTimer != null){
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet.getImageView());
                moveBulletTimer.stop();
            }
            bullet = new SnowBullet(row,column);
            DayLevel.getInstance().getDayAnc().getChildren().add(bullet.getImageView());
            moveBulletTimer = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet()));
            moveBulletTimer.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer.play();
        }
    }

    public void moveBullet(){
        if (bullet != null) {
            bullet.move();
        }
        if (bullet.getImageView().getLayoutX() >= endRow && zombie != null){
            moveBulletTimer.stop();
            zombie.takeDamage(1);
            if (zombie.isDead()){
                zombie.dead();
                setZombie();
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
               zombie.setSpeed(2);
           }
           else {
               zombie.setSpeed(1);
           }
           halfSpeedTimer.stop();
       }
    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new SnowShooter(row, column, group);
    }

    private double setZombie(){
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
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);
    }

}
