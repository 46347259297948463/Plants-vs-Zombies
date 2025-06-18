package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TallNut extends NutPlants{

    private final static int HP = 16;
    private final static int price = 125;
    private final static double rechargeTime = 8;
    private static boolean available = true;

    public TallNut(int i, int j) {
        super(HP, i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/tall nut.png").toString());
        imageView.setFitWidth(140);
        imageView.setFitHeight(190);
        setImage(imageView);
    }
    public TallNut(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new TallNut(row, column);
    }
}
