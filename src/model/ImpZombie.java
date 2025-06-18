package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImpZombie extends Zombie{
    public ImpZombie(int x, int y) {
        super(x, y);
        setHP(3);
        setSpeed(8);
        Image image = new Image(getClass().getResource("/view/images/impZombie.png").toString());
        ImageView imageV = new ImageView(image);
        setImage(imageV);
    }
}
