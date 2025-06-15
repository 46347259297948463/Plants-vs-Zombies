package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TallNut extends NutPlants{

    private final static int HP = 16;
    private final static int price = 125;
    private final static double rechargeTime = 20;
    private static boolean available = true;

    public TallNut(int i, int j) {
        super(HP, i, j, price, rechargeTime);
        Image image = new Image(getClass().getResource("/view/images/tall nut.png").toString());
        ImageView imageView = new ImageView(image);
        setImage(imageView);
    }
    public TallNut(){

    }
}
