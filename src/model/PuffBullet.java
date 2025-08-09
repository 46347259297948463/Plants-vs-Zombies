package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PuffBullet extends Bullet{

    public PuffBullet(double row, double column, int n) {
            super(row, column, n);
    }

    @Override
    protected void setting(){
        Image image = new Image(getClass().getResource("/view/images/purple bullet.png").toString());
        ImageView imageV = new ImageView(image);
        imageV.setFitHeight(50);
        imageV.setFitWidth(50);
        if (n == 1) {
            imageV.setLayoutX(this.column);
            imageV.setLayoutY(this.row + 77);
        } else {
            imageV.setLayoutX(this.column);
            imageV.setLayoutY(this.row + 55);
        }
        this.imageView = imageV;
        color = "purple";
        isSnow = false;
    }

    @Override
    public void move(){
        column += 30;
        imageView.setLayoutX(column);
    }

}
