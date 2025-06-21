package model;

import controller.DayLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public class PeaShooter extends PeaPlants{

    private final static int HP = 4;

    private final static int price = 100;

    private final static int bullets = 1;

    private final static double rechargeTime = 4;

    private Bullet bullet;

    private static boolean available = true;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double bulletRow = row;

    private double endRow;

    public PeaShooter(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
        cells = DayLevel.getInstance().getCells();
        endRow = setZombie();
        ImageView imageView = new ImageView(getClass().getResource("/view/images/pea shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
        if (endRow != -1){
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(1) , event -> shoot(zombie)));
            shootTimer.setCycleCount(Timeline.INDEFINITE);
            shootTimer.play();
        }
    }

    public PeaShooter(){

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
            bullet.endBullet();
        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new PeaShooter(row, column);
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
