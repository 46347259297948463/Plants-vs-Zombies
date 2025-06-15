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
        ImageView imageView = new ImageView(getClass().getResource("/view/images/repeater.png").toString());
        imageView.setFitWidth(140);
        imageView.setFitHeight(140);
        setImage(imageView);
    }

    public Repeater(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new Repeater(row, column);
    }
}
