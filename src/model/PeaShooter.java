package model;

import javafx.scene.image.ImageView;

public class PeaShooter extends PeaPlants{

    private final static int HP = 4;
    private final static int price = 100;
    private final static int bullets = 1;
    private final static double rechargeTime = 7.5;
    private static boolean available = true;

    public PeaShooter(int i, int j) {
        super(HP, i, j, price, bullets, rechargeTime);
        ImageView imageView = new ImageView(getClass().getResource("/view/images/pea shooter.png").toString());
        imageView.setFitWidth(125);
        imageView.setFitHeight(125);
        this.setImage(imageView);
    }

    public PeaShooter(){

    }

    @Override
    public Plants clonePlant(int row, int column) {
        return new PeaShooter(row, column);
    }
}
