package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Zombie {
    private double row;
    private int column;
    private int HP = 5;
    private int speed = 4;
    private ImageView image;
    private boolean attack = false;

    public Zombie(int x, int y){
        this.row = x;
        this.column = y;
        Image image = new Image(getClass().getResource("/view/images/normalZombies.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
    }

    public void move(){
        if (!attack){
            row -= speed;
        }
    }

    public void takeDamage(int damage){
        HP -= damage;
    }

    public boolean isDead(){
        return HP <= 0;
    }

    public void update(){

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
