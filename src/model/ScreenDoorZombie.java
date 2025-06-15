package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ScreenDoorZombie extends Zombie{

    public ScreenDoorZombie(int x, int y) {
        super(x, y);
        setHP(10);
        Image image = new Image(getClass().getResource("/view/images/doorZombie.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
    }
}
