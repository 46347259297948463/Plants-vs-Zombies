package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SnowBullet extends  Bullet{

    public SnowBullet(double row, double column){
        super(row, column);
    }

    @Override
    protected void setting(){
        Image image = new Image(getClass().getResource("/view/images/snow bullet.png").toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitWidth(50);
        imageV.setFitHeight(50);
        imageV.setLayoutX(this.column);
        imageV.setLayoutY(this.row);
        this.imageView = imageV;
        isSnow = true;
        color = "blue";
    }
}
