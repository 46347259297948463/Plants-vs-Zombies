package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sunflower extends Plants{

    private final static int HP = 4;
    private final static int price = 50;
    private final static double rechargeTime = 3;
    private static boolean available = true;

    public Sunflower(int hp, int i, int j, int price) {
        super(hp, i, j, price, rechargeTime);
        Image image = new Image(getClass().getResource("/view/images/sunflower.png").toString());
        ImageView imageView = new ImageView(image);
        setImage(imageView);
    }

    public Sunflower(){

    }
}
