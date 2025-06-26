package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class PeaShooter extends PeaPlants {

    private final static int HP = 4;

    private final static int bullets = 1;

    private final static double rechargeTime = 4;

    private Bullet bullet;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double endRow;

    private Timeline timer;

    public PeaShooter(int i, int j, Group group) {
        super(HP, i, j, 100, bullets, rechargeTime);
        this.group = group;
        DayLevel.getInstance().setAvailablePicked(false, DayLevel.getInstance().getAvailableNum());
        cells = DayLevel.getInstance().getCells();
        endRow = setZombie();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/pea shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        if (endRow != -1) {
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(2), event -> shoot(zombie)));
            shootTimer.setCycleCount(Timeline.INDEFINITE);
            shootTimer.play();
        }
        group.setOpacity(0.7);
        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public PeaShooter() {
        price = 100;
    }

    @Override
    public void shoot(Zombie zombie) {
        endRow = setZombie();
        if (endRow == -1 || zombie == null || zombie.isDead()) {
            return;
        } else if (endRow != -1) {
            if (moveBulletTimer != null) {
                DayLevel.getInstance().getDayAnc().getChildren().remove(bullet.getImageView());
                moveBulletTimer.stop();
            }
            bullet = new Bullet(row, column);
            DayLevel.getInstance().getDayAnc().getChildren().add(bullet.getImageView());
            moveBulletTimer = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet(endRow)));
            moveBulletTimer.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer.play();
        }
    }

    public void moveBullet(double endRow) {
        if (bullet != null) {
            bullet.move();
            if (bullet.getImageView().getLayoutX() >= endRow && zombie != null) {
                moveBulletTimer.stop();
                zombie.takeDamage(1);
                if (zombie.isDead()) {
                    zombie.dead();
                    setZombie();
                }
                bullet.endBullet();
            }
        }

    }

    @Override
    public Plants clonePlant(int row, int column, Group group) {
        return new PeaShooter(row, column, group);
    }

    private double setZombie() {
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

    @Override
    protected void recharge() {
        DayLevel.getInstance().setAvailablePicked(true, DayLevel.getInstance().getAvailableNum());
        timer.stop();
        group.setOpacity(1);
    }

}
