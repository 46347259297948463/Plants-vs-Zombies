package model;


import javafx.scene.Group;
import javafx.scene.image.ImageView;

public abstract class Plants {
    protected int HP;

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

    public abstract void end();

    public abstract Plants clonePlant(int row, int column);

    public String getName() {
        return this.getClass().getSimpleName().toLowerCase();
    }

    public void takeDamage(int damage){
        HP -= damage;
    }

    public boolean isDead(){
        return HP <= 0;
    }

    public int getHP() {
        return HP;
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

    public void setImage(ImageView image) {
        this.image = image;
    }

    protected abstract void recharge();

    public abstract void stop();

    public abstract void play();

}
