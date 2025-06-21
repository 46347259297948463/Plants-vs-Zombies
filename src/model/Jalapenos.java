package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Jalapenos extends BombPlants{

    private final static int price = 125;

    private final static double rechargeTime = 10;

    private static boolean available = true;

    public Jalapenos(int i, int j) {
        super(i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/jalapenos.png").toString());
        imageView.setFitWidth(135);
        imageView.setFitHeight(140);
        setImage(imageView);
    }

    public Jalapenos(){

    }

    @Override
    public void end() {}

    @Override
    public Plants clonePlant(int row, int column) {
        return new Jalapenos(row, column);
    }

    @Override
    public void BOMB() {

    }

}
