package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WallNut extends NutPlants{

    private final static int HP = 10;
    private final static int price = 50;
    private final static int rechargeTime = 10;
    private static boolean available = true;

    public WallNut(int i, int j) {
        super(HP, i, j, price, rechargeTime);
        Image image = new Image(getClass().getResource("/view/images/wall nut.png").toString());
        ImageView imageView = new ImageView(image);
        setImage(imageView);
    }
    public WallNut(){

    }
}
