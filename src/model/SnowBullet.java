package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SnowBullet extends  Bullet{

    public SnowBullet(double row, double column){
        super(row, column);
        color = "blue";
        isSnow = true;
        Image image = new Image(getClass().getResource("/view/images/snow bullet.png").toString());
        ImageView imageV = new ImageView(image);
        this.imageView = imageV;
        isSnow = true;
    }
}
