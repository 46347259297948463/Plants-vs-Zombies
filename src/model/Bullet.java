package model;

import controller.DayLevel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Bullet {

    protected String color = "green";

    protected double row;

    protected double column;

    protected boolean isSnow = false;

    protected ImageView imageView;

    public Bullet(double row, double column){
        this.row = row;
        this.column = column;
        Image image = new Image(getClass().getResource("/view/images/green bullet.png").toString());
        ImageView imageV = new ImageView(image);
        this.imageView = imageV;
    }

    public void endBullet(){
        DayLevel.getInstance().getDayAnc().getChildren().remove(this);
    }

    public void move(){
        row += 40;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
