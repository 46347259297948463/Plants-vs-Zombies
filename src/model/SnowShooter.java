package model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SnowShooter extends PeaPlants{

    private final static int HP = 4;
    private final static int price = 175;
    private final static int bullets = 1;
    private final static double rechargeTime = 6;
    private static boolean available = true;

    public SnowShooter(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/snow shooter.png").toString());
        imageView.setFitWidth(120);
        imageView.setFitHeight(125);
        setImage(imageView);
    }
    public SnowShooter(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new SnowShooter(row, column);
    }
}
