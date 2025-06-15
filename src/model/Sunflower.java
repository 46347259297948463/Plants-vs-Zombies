package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Sunflower extends Plants{

    private final static int HP = 4;
    private final static int price = 50;
    private final static double rechargeTime = 3;
    private static boolean available = true;

    public Sunflower(int i, int j) {
        super(HP, i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/sunflower.png").toString());
        imageView.setFitWidth(140);
        imageView.setFitHeight(140);
        setImage(imageView);
    }

    public Sunflower(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Sunflower(row, column);
    }
}
