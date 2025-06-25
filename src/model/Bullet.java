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
        this.row = row * 191.5 + 153;
        this.column = column * 153.5 + 590;
        setting();
    }

    protected void setting(){
        Image image = new Image(getClass().getResource("/view/images/green bullet.png").toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitWidth(50);
        imageV.setFitHeight(50);
        imageV.setLayoutX(this.column);
        imageV.setLayoutY(this.row);
        this.imageView = imageV;
    }

    public void endBullet(){
        DayLevel.getInstance().getDayAnc().getChildren().remove(imageView);
    }

    public void move(){
        column += 20;
        imageView.setLayoutX(column);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
