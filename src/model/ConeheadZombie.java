package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ConeheadZombie extends Zombie{

    public ConeheadZombie(int x, int y) {
        super(x, y);
        setHP(7);
        Image image = new Image(getClass().getResource("/view/images/coneheadZombie.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
    }
}
