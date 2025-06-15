package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Repeater extends PeaPlants{

    private final static int HP = 4;
    private final static int price = 200;
    private final static int bullets = 2;
    private final static double rechargeTime = 15;
    private static boolean available = true;

    public Repeater(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
        Image image = new Image(getClass().getResource("/view/images/repeater.png").toString());
        ImageView imageView = new ImageView(image);
        setImage(imageView);
    }

    public Repeater(){

    }
}
