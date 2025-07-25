package model;


import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

public abstract class Plants {

    private int HP;

    protected int row;

    protected int column;

    protected int price;

    protected ImageView image;

    protected double rechargeTime;

    public Plants(int hp, int i, int j, int price, double rechargeTime){
        HP = hp;
        row = i;
        column = j;
        this.price = price;
        this.rechargeTime = rechargeTime;
    }

    public Plants(){

    }

    public void takeDamage(int damage){
        HP -= damage;
    }

    public boolean isDead(){
        return HP <= 0;
    }

    public abstract Plants clonePlant(int row, int column);

    protected abstract void recharge();

    public abstract void stop();

    public abstract void play();

    public abstract void end();

    public void setImage(ImageView image) {
        this.image = image;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public int getPrice() {
        return price;
    }

    public ImageView getImage() {
        return image;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public abstract Timeline getTimer();

}
