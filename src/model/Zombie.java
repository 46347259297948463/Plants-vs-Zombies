package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class Zombie {
    private double row;
    private int column;
    private int HP = 5;
    private int speed = 4;
    private ImageView image;
    private boolean attack = false;
    private Timeline moveTimeline;
    private boolean isremoved = false;

    public Zombie(int x, int y){
        this.row = x;
        this.column = y;
        Image image = new Image(getClass().getResource("/view/images/normalZombies.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
    }

    public void startmove(){
        if(moveTimeline == null){
            moveTimeline = new Timeline(new KeyFrame(Duration.millis(100), event -> move()));
            moveTimeline.setCycleCount(Timeline.INDEFINITE);
        }
    }

    public void stopmove(){
        if(moveTimeline != null){
            moveTimeline.stop();
        }
    }

    public void move(){
        if (!attack){
            row -= speed;
            image.setLayoutX(row);
        }
    }

    public void update(){
        if(isDead()){
            stopmove();
            isremoved = true;
            //باید روی زامبی ها فور بزنیم و اگر این بولین درست بود از روت عکس آن رو ریموو کنیم
            return;
        }
        if(row <= 250){
            //بازیکن باخته و زامبی به انتهای زمین رسیده است
            //پیشنهاد من این است یک بولین تعریف شود که مشخص کند بازی تمام شده
            //گفتم هم فکری کنیم بعد تعییرات این بخش رو بدم
        }
        image.setLayoutX(row);
    }

    public void takeDamage(int damage){
        HP -= damage;
    }

    public boolean isDead(){
        return HP <= 0;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }
}
