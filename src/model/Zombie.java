package model;

import javax.swing.text.html.ImageView;

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
}
