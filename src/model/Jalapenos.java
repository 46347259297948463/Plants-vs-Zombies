package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Jalapenos extends BombPlants{

    private final static int price = 125;
    private final static double rechargeTime = 30;
    private static boolean available = true;

    public Jalapenos(int i, int j, int price) {
        super(i, j, price, rechargeTime);
        Image image = new Image(getClass().getResource("/view/images/jalapenos.png").toString());
        ImageView imageView = new ImageView(image);
        setImage(imageView);
    }

    public Jalapenos(){

    }

    @Override
    public void BOMB() {

    }

}
