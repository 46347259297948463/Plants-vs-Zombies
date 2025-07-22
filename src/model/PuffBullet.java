package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.*;

public class PuffBullet extends Bullet{

    public PuffBullet(double row, double column) {
            super(row, column);
    }

    @Override
    protected void setting(){
        Image image = new Image(getClass().getResource("/view/images/purple bullet.png").toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(10);
        imageV.setFitWidth(10);
        imageV.setLayoutX(this.column);
        imageV.setLayoutY(this.row);
        this.imageView = imageV;
        color = "purple";
        isSnow = false;
    }
}
