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

    private Timeline moveBulletTimer;

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
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(1) , event -> shoot(zombie1)));
            shootTimer.setCycleCount(Timeline.INDEFINITE);
            shootTimer.play();
        }
    }

    public Repeater(){

    }

    @Override
    public void shoot(Zombie zombie) {
        setZombie();
        bulletRow1 = row;
        bulletRow2 = row + 10;
        moveBulletTimer = new Timeline(new KeyFrame(Duration.millis(100), event -> moveBullet()));
        moveBulletTimer.setCycleCount(Timeline.INDEFINITE);
        moveBulletTimer.play();
    }

    public void moveBullet(){
        if (bullet1 != null && bullet2 != null){
            bullet1.endBullet();
            bullet2.endBullet();
        }
        bullet1 = new Bullet(bulletRow1, column);
        bullet2 = new Bullet(bulletRow2, column);
        DayLevel.getInstance().getDayAnc().getChildren().add(bullet1.getImageView());
        DayLevel.getInstance().getDayAnc().getChildren().add(bullet2.getImageView());
        bulletRow1 += 15;
        bulletRow2 += 15;
        if (bulletRow1 >= endRow1){
            moveBulletTimer.stop();
            zombie1.takeDamage(1);
            bullet1.endBullet();
        }
        if (bulletRow2 >= endRow2){
            moveBulletTimer.stop();
            zombie2.takeDamage(1);
            bullet2.endBullet();
        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Repeater(row, column);
    }

    private void setZombie(){
        int i = row , j = column;
        while (i < 9 && cells[j][i].getZombies() == null){
            i++;
        }
        if (i == 9){
            endRow1 = -1;
            return;
        }
        ArrayList<Zombie> zombies = cells[j][i].getZombies();
        double min1 = 9;
        double min2 = 9;
        for (Zombie z : zombies){
            if (z.getRow() < min1){
                zombie1 = z;
                min2 = min1;
                min1 = z.getRow();
            } else if (z.getRow() < min2) {
                zombie2 = z;
                min2 = z.getRow();
            }
        }

        if (zombie1.getHP() != 1){
            zombie2 = zombie1;
            min2 = min1;
        }
        endRow1 = min1 * 80 + 250;
        endRow2 = min2 * 80 + 250;
    }
}
