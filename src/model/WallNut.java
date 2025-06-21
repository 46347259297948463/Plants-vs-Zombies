package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WallNut extends NutPlants{

    private final static int HP = 10;

    private final static int price = 50;

    private final static int rechargeTime = 5;

    private static boolean available = true;

    public WallNut(int i, int j) {
        super(HP, i, j, price, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/wall nut.png").toString());
        imageView.setFitWidth(125);
        imageView.setFitHeight(130);
        setImage(imageView);
    }
    public WallNut(){

    }

    @Override
    public void end() {}

    @Override
    public Plants clonePlant(int row, int column) {
        return new WallNut(row, column);
    }
}
