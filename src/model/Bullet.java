package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {

    protected String color = "green";
    protected double row;
    protected boolean isSnow = false;
    ImageView imageView;

    public Bullet(){
        Image image = new Image(getClass().getResource("/view/images/green bullet.png").toString());
        ImageView imageV = new ImageView(image);
        this.imageView = imageV;
    }

//    public void setColor(String newColor) {
//        color = newColor;
//    }

    public void move(){
        row += 40;
    }

}
