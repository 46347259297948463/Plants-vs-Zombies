package model;

import controller.DayLevel;
import controller.FogLevel;
import controller.NightLevel;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.util.ArrayList;

public class PeaShooter extends PeaPlants {

    private final static int HP = 4;

    private final static int bullets = 1;

    private Bullet bullet;

    private Zombie zombie;

    private Cell[][] cells;

    private Timeline moveBulletTimer;

    private double endRow;

    private static Timeline timer;

    private static Group group;

    private static int availableNum;

    public PeaShooter(int i, int j) {
        super(HP, i, j, 100, bullets, 6);
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = DayLevel.getInstance().getCells();
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = NightLevel.getInstance().getCells();
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(false, availableNum);
            cells = FogLevel.getInstance().getCells();
        }
        ImageView imageView = new ImageView(getClass().getResource("/view/images/pea shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);

        if (!isOnSaveMode) {
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(2), event -> shoot(zombie)));
            getShootTimer().setCycleCount(Timeline.INDEFINITE);
            getShootTimer().play();
        }

        group.setOpacity(0.7);

        timer = new Timeline(new KeyFrame(Duration.seconds(rechargeTime), event -> recharge()));
        timer.setCycleCount(1);
        timer.play();
    }

    public PeaShooter() {
        price = 100;
    }

    private void moveBullet(double endRow) {
        if (bullet != null) {
            bullet.move();
            endRow = findZombie();
            if (bullet.getImageView().getLayoutX() >= endRow && zombie != null) {
                endRow = findZombie();
                moveBulletTimer.stop();
                zombie.takeDamage(1);
                if (zombie.isDead()) {
                    zombie.dead();
                    endRow = findZombie();
                }
                bullet.endBullet();
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

    @Override
    protected void shoot(Zombie zombie) {
        endRow = findZombie();
        if (endRow == -1 || zombie == null || zombie.isDead()) {
            return;
        } else if (endRow != -1) {
            if (moveBulletTimer != null) {
                if (obj instanceof DayLevel) {
                    DayLevel.getInstance().getDayAnc().getChildren().remove(bullet.getImageView());
                } else if (obj instanceof NightLevel) {
                    NightLevel.getInstance().getNightAnc().getChildren().remove(bullet.getImageView());
                } if (obj instanceof FogLevel) {
                    FogLevel.getInstance().getFogAnc().getChildren().remove(bullet.getImageView());
                }
                moveBulletTimer.stop();
            }
            bullet = new Bullet(row, column, 1);
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
            if (obj instanceof DayLevel) {
                DayLevel.getInstance().getDayAnc().getChildren().add(bullet.getImageView());
            } else if (obj instanceof NightLevel) {
                NightLevel.getInstance().getNightAnc().getChildren().add(bullet.getImageView());
            } else if (obj instanceof FogLevel) {
                FogLevel.getInstance().getFogAnc().getChildren().add(bullet.getImageView());
            }
            moveBulletTimer = new Timeline(new KeyFrame(Duration.millis(50), event -> moveBullet(endRow)));
            moveBulletTimer.setCycleCount(Timeline.INDEFINITE);
            moveBulletTimer.play();
        }
    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new PeaShooter(row, column);
    }

    @Override
    protected void recharge() {
        if (obj instanceof DayLevel) {
            DayLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof NightLevel) {
            NightLevel.getInstance().setAvailablePicked(true, availableNum);
        } else if (obj instanceof FogLevel) {
            FogLevel.getInstance().setAvailablePicked(true, availableNum);
        }
        timer.stop();
        group.setOpacity(1);
    }

    @Override
    public void stop(){
        if (moveBulletTimer != null){
            moveBulletTimer.pause();
        }
        if (getShootTimer() != null) {
            shootTimer.pause();
        }
    }

    @Override
    public void play(){
        if (moveBulletTimer != null){
            moveBulletTimer.play();
        }
        if (getShootTimer() != null) {
            shootTimer.play();
        }
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

    public void setShootTimer(double l) {
        if (l != -1) {
            shootTimer = new Timeline(new KeyFrame(Duration.seconds(2), event -> shoot(zombie)));
            getShootTimer().setCycleCount(Timeline.INDEFINITE);
            getShootTimer().playFrom(Duration.seconds(l));
            System.out.println("the saved timer");
            System.out.println(l);
        }
    }

}